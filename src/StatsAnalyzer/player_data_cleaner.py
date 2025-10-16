import pandas as pd
import os
import glob
import hashlib


class PlayerDataCleaner:
    """
    Cleans FIFA / EA FC player data (2015–2024) into a consistent format
    compatible with FootSim's Player class.
    Automatically skips 2023 (dataset missing) and standardizes both
    FIFA 15–22 and EA FC 24 schemas.
    """

    def __init__(self, input_dir: str, output_dir: str):
        self.input_dir = input_dir
        self.output_dir = output_dir
        os.makedirs(self.output_dir, exist_ok=True)

    # ----------------------------------------------------------
    # STEP 1: Load raw CSV
    # ----------------------------------------------------------
    def load_csv(self, path: str) -> pd.DataFrame:
        print(f"📥 Loading {path} ...")
        return pd.read_csv(path, low_memory=False, on_bad_lines="skip")

    # ----------------------------------------------------------
    # STEP 2: Select and rename columns (handles both schemas)
    # ----------------------------------------------------------
    def select_and_rename(self, df: pd.DataFrame) -> pd.DataFrame:
        # --- FIFA 15–22 Format ---
        if "short_name" in df.columns:
            df = df.rename(
                columns={
                    "short_name": "name",
                    "club_name": "team",
                    "player_positions": "positions",
                    "physic": "physicality",
                }
            )
            cols_needed = [
                "name",
                "team",
                "positions",
                "overall",
                "pace",
                "shooting",
                "passing",
                "dribbling",
                "defending",
                "physicality",
                "goalkeeping_diving",
                "goalkeeping_handling",
                "goalkeeping_kicking",
                "goalkeeping_positioning",
                "goalkeeping_reflexes",
            ]

        # --- EA FC 24 Format ---
        elif "Name" in df.columns:
            df = df.rename(
                columns={
                    "Name": "name",
                    "Club": "team",
                    "Position": "positions",
                    "Overall": "overall",
                    "Pace": "pace",
                    "Shooting": "shooting",
                    "Passing": "passing",
                    "Dribbling": "dribbling",
                    "Defending": "defending",
                    "Physicality": "physicality",
                    "GK": "goalkeeping_diving",  # EA FC 24 has only one GK column
                }
            )

            # Duplicate GK attribute across all required columns
            for col in [
                "goalkeeping_handling",
                "goalkeeping_kicking",
                "goalkeeping_positioning",
                "goalkeeping_reflexes",
            ]:
                df[col] = df["goalkeeping_diving"]

            cols_needed = [
                "name",
                "team",
                "positions",
                "overall",
                "pace",
                "shooting",
                "passing",
                "dribbling",
                "defending",
                "physicality",
                "goalkeeping_diving",
                "goalkeeping_handling",
                "goalkeeping_kicking",
                "goalkeeping_positioning",
                "goalkeeping_reflexes",
            ]

        else:
            raise ValueError("❌ Unknown dataset format — cannot map columns.")

        df = df[[c for c in cols_needed if c in df.columns]].copy()
        df.dropna(subset=["name", "team", "positions"], inplace=True)
        return df

    # ----------------------------------------------------------
    # STEP 3: Split primary + secondary positions
    # ----------------------------------------------------------
    def split_positions(self, df: pd.DataFrame) -> pd.DataFrame:
        df["positions"] = df["positions"].fillna("").astype(str)
        df["primary_position"] = df["positions"].apply(
            lambda s: s.split(",")[0].strip() if s else ""
        )
        df["secondary_positions"] = df["positions"].apply(
            lambda s: ", ".join([p.strip() for p in s.split(",")[1:]]) if "," in s else ""
        )
        return df

    # ----------------------------------------------------------
    # STEP 4: Fill goalkeeper stats for non-GKs
    # ----------------------------------------------------------
    def impute_gk_stats(self, df: pd.DataFrame) -> pd.DataFrame:
        gk_cols = [
            "goalkeeping_diving",
            "goalkeeping_handling",
            "goalkeeping_kicking",
            "goalkeeping_positioning",
            "goalkeeping_reflexes",
        ]
        df[gk_cols] = df[gk_cols].fillna(0)
        non_gk_mask = ~df["primary_position"].eq("GK")
        df.loc[non_gk_mask, gk_cols] = 0
        return df

    # ----------------------------------------------------------
    # STEP 5: Ensure valid numeric ranges
    # ----------------------------------------------------------
    def validate_ranges(self, df: pd.DataFrame) -> pd.DataFrame:
        stat_cols = [
            "pace",
            "shooting",
            "passing",
            "dribbling",
            "defending",
            "physicality",
            "goalkeeping_diving",
            "goalkeeping_handling",
            "goalkeeping_kicking",
            "goalkeeping_positioning",
            "goalkeeping_reflexes",
        ]
        for col in stat_cols:
            df[col] = pd.to_numeric(df[col], errors="coerce").fillna(0)
            df[col] = df[col].clip(0, 100)
        return df

    # ----------------------------------------------------------
    # STEP 6: Generate unique FootSim ID
    # ----------------------------------------------------------
    def generate_id(self, name: str, team: str, year: int) -> str:
        base = f"{name}_{team}_{year}".encode("utf-8")
        unique_hash = hashlib.sha1(base).hexdigest()[:6].upper()
        return f"FS_{year}_{unique_hash}"

    # ----------------------------------------------------------
    # STEP 7: Clean one file
    # ----------------------------------------------------------
    def clean_file(self, file_path: str, year: int) -> pd.DataFrame:
        df = self.load_csv(file_path)
        df = self.select_and_rename(df)
        df = self.split_positions(df)
        df = self.impute_gk_stats(df)
        df = self.validate_ranges(df)
        df["year"] = 2000 + year
        df["footsim_id"] = df.apply(
            lambda row: self.generate_id(row["name"], row["team"], row["year"]),
            axis=1,
        )
        return df

    # ----------------------------------------------------------
    # STEP 8: Clean all years and merge
    # ----------------------------------------------------------
    def clean_and_merge_all(self, start_year=15, end_year=22):
        all_dfs = []

        for year in range(start_year, end_year + 1):
            pattern = f"male_players_{year}.csv"
            files = glob.glob(os.path.join(self.input_dir, pattern))

            if not files:
                print(f"⚠️ No file found for {year}")
                continue

            for file_path in files:
                print(f"🧹 Cleaning {file_path} ...")
                df = self.clean_file(file_path, year)
                all_dfs.append(df)

        if not all_dfs:
            print("⚠️ No data cleaned. Check input paths.")
            return

        merged = pd.concat(all_dfs, ignore_index=True)
        merged = merged.sort_values(by=["name", "year"]).reset_index(drop=True)

        ordered_cols = [
            "footsim_id",
            "name",
            "team",
            "positions",
            "primary_position",
            "secondary_positions",
            "pace",
            "shooting",
            "passing",
            "dribbling",
            "defending",
            "physicality",
            "goalkeeping_diving",
            "goalkeeping_handling",
            "goalkeeping_kicking",
            "goalkeeping_positioning",
            "goalkeeping_reflexes",
            "overall",
            "year",
        ]
        merged = merged[[c for c in ordered_cols if c in merged.columns]]

        out_path = os.path.join(self.output_dir, "FIFA_2015_2022_master.csv")
        merged.to_csv(out_path, index=False)

        print("\n✅ All years cleaned and merged successfully!")
        print(f"📦 Saved dataset → {out_path}")
        print(f"🧍‍♂️ Total players across years: {len(merged)}")


# ----------------------------------------------------------
# MAIN EXECUTION
# ----------------------------------------------------------
if __name__ == "__main__":
    cleaner = PlayerDataCleaner("data", "cleaned")
    cleaner.clean_and_merge_all(15, 22)
