import pandas as pd
import os
import glob
import hashlib


class PlayerDataCleaner:
    """
    Cleans FIFA player data (2015–2022) into a consistent format
    compatible with FootSim's Player class, including retirement flags.
    """

    def __init__(self, input_dir: str, output_dir: str):
        """
        input_dir  : folder with raw FIFA CSVs (e.g. 'data/')
        output_dir : folder where cleaned & merged outputs go (e.g. 'cleaned/')
        """
        self.input_dir = input_dir
        self.output_dir = output_dir
        os.makedirs(self.output_dir, exist_ok=True)

    # ----------------------------------------------------------
    # STEP 1: Load raw CSV
    # ----------------------------------------------------------
    def load_csv(self, path: str) -> pd.DataFrame:
        print(f"📥 Loading {path} ...")
        try:
            df = pd.read_csv(path, low_memory=False, on_bad_lines='skip')
            return df
        except Exception as e:
            print(f"❌ Error loading {path}: {e}")
            return pd.DataFrame()

    # ----------------------------------------------------------
    # STEP 2: Select and rename relevant columns
    # ----------------------------------------------------------
    def select_and_rename(self, df: pd.DataFrame) -> pd.DataFrame:
        cols_needed = [
            'short_name', 'club_name', 'player_positions', 'overall',
            'pace', 'shooting', 'passing', 'dribbling', 'defending', 'physic',
            'goalkeeping_diving', 'goalkeeping_handling', 'goalkeeping_kicking',
            'goalkeeping_positioning', 'goalkeeping_reflexes'
        ]

        missing_cols = [c for c in cols_needed if c not in df.columns]
        if missing_cols:
            print(f"⚠️ Missing columns in file: {missing_cols}")
            return pd.DataFrame()

        df = df[cols_needed].copy()
        df = df.rename(columns={
            'short_name': 'name',
            'club_name': 'team',
            'player_positions': 'positions',
            'physic': 'physicality'
        })
        df.dropna(subset=['name', 'team', 'positions'], inplace=True)
        return df

    # ----------------------------------------------------------
    # STEP 3: Split primary + secondary positions
    # ----------------------------------------------------------
    def split_positions(self, df: pd.DataFrame) -> pd.DataFrame:
        df['positions'] = df['positions'].fillna("").astype(str)
        df['primary_position'] = df['positions'].apply(
            lambda s: s.split(',')[0].strip() if s else ""
        )
        df['secondary_positions'] = df['positions'].apply(
            lambda s: ', '.join([p.strip() for p in s.split(',')[1:]]) if ',' in s else ""
        )
        return df

    # ----------------------------------------------------------
    # STEP 4: Fill goalkeeper stats for non-GKs
    # ----------------------------------------------------------
    def impute_gk_stats(self, df: pd.DataFrame) -> pd.DataFrame:
        gk_cols = [
            'goalkeeping_diving', 'goalkeeping_handling', 'goalkeeping_kicking',
            'goalkeeping_positioning', 'goalkeeping_reflexes'
        ]
        df[gk_cols] = df[gk_cols].fillna(0)
        non_gk_mask = ~df['primary_position'].eq('GK')
        df.loc[non_gk_mask, gk_cols] = 0
        return df

    # ----------------------------------------------------------
    # STEP 5: Ensure valid numeric ranges
    # ----------------------------------------------------------
    def validate_ranges(self, df: pd.DataFrame) -> pd.DataFrame:
        stat_cols = [
            'pace', 'shooting', 'passing', 'dribbling', 'defending', 'physicality',
            'goalkeeping_diving', 'goalkeeping_handling', 'goalkeeping_kicking',
            'goalkeeping_positioning', 'goalkeeping_reflexes'
        ]
        for col in stat_cols:
            if col not in df.columns:
                df[col] = 0
            df[col] = pd.to_numeric(df[col], errors='coerce').fillna(0)
            df[col] = df[col].clip(0, 100)
        return df

    # ----------------------------------------------------------
    # STEP 6: Generate a custom FootSim ID
    # ----------------------------------------------------------
    def generate_id(self, name: str, team: str, year: int) -> str:
        base = f"{name}_{team}_{year}".encode('utf-8')
        unique_hash = hashlib.sha1(base).hexdigest()[:6].upper()
        return f"FS_{year}_{unique_hash}"

    # ----------------------------------------------------------
    # STEP 7: Clean one file fully
    # ----------------------------------------------------------
    def clean_file(self, file_path: str) -> pd.DataFrame:
        df = self.load_csv(file_path)
        if df.empty:
            return df
        df = self.select_and_rename(df)
        if df.empty:
            return df
        df = self.split_positions(df)
        df = self.impute_gk_stats(df)
        df = self.validate_ranges(df)
        return df

    # ----------------------------------------------------------
    # STEP 8: Clean all years and merge + mark retirements
    # ----------------------------------------------------------
    def clean_and_merge_all(self, start_year=15, end_year=22):
        all_dfs = []

        for year in range(start_year, end_year + 1):
            pattern = f"male_players_{year}.csv"
            files = glob.glob(os.path.join(self.input_dir, pattern))
            print(f"🔍 Looking for files with pattern: {pattern}")

            if not files:
                print(f"⚠️ No file found for {year}")
                continue

            for file_path in files:
                print(f"🧹 Cleaning {file_path} ...")
                df = self.clean_file(file_path)
                if df.empty:
                    continue

                df['year'] = year + 2000
                df['footsim_id'] = df.apply(
                    lambda row: self.generate_id(row['name'], row['team'], df['year'].iloc[0]),
                    axis=1
                )
                all_dfs.append(df)

        if not all_dfs:
            print("❌ No valid data cleaned. Check your input paths or column names.")
            return

        merged = pd.concat(all_dfs, ignore_index=True)

        # 🔹 Determine last active year for each player
        last_year = (
            merged.groupby('name')['year']
            .max()
            .reset_index()
            .rename(columns={'year': 'last_active_year'})
        )
        merged = merged.merge(last_year, on='name', how='left')

        # 🔹 Mark retired if last_active_year < 2022
        merged['retired'] = merged['last_active_year'] < 2022
        merged.drop(columns=['last_active_year'], inplace=True)

        # Reorder columns for consistency
        ordered_cols = [
            'footsim_id', 'name', 'team', 'positions',
            'primary_position', 'secondary_positions',
            'pace', 'shooting', 'passing', 'dribbling', 'defending', 'physicality',
            'goalkeeping_diving', 'goalkeeping_handling', 'goalkeeping_kicking',
            'goalkeeping_positioning', 'goalkeeping_reflexes',
            'overall', 'year', 'retired'
        ]
        for col in ordered_cols:
            if col not in merged.columns:
                merged[col] = None
        merged = merged[ordered_cols]

        # Save readable CSV (no compression for IntelliJ)
        out_path = os.path.join(self.output_dir, "FIFA_2015_2022_master.csv")
        merged.to_csv(out_path, index=False, encoding='utf-8')

        print("\n✅ All years cleaned and merged successfully!")
        print(f"📦 Saved dataset → {out_path}")
        print(f"🧍‍♂️ Total players across years: {len(merged)}")
        print(f"🏁 Retired players detected: {merged['retired'].sum()}")


# ----------------------------------------------------------
# MAIN EXECUTION
# ----------------------------------------------------------
if __name__ == "__main__":
    cleaner = PlayerDataCleaner("data", "cleaned")
    cleaner.clean_and_merge_all(15, 22)
