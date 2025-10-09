import pandas as pd
import unicodedata
import re

# --- Load dataset ---
file_path = r"data\male_players_combined_15_to_24.csv"
df = pd.read_csv(file_path, low_memory=False)

print(f"✅ Loaded dataset with {len(df):,} rows.")

# Ensure fifa_version is a clean Python int (fix np.int64 display)
df["fifa_version"] = df["fifa_version"].astype(int)

# --- Normalization function ---
def normalize_name(name):
    """Standardize player names by removing accents, punctuation, and casing."""
    if pd.isna(name):
        return ""
    name = unicodedata.normalize('NFKD', str(name)).encode('ascii', 'ignore').decode('utf-8')  # remove accents
    name = re.sub(r"[^a-zA-Z\s]", "", name)  # remove punctuation and periods
    name = re.sub(r"\s+", " ", name)  # collapse extra spaces
    return name.strip().lower()

# Create a normalized name column
df["normalized_name"] = df["long_name"].fillna(df["short_name"]).apply(normalize_name)

# --- Manual mappings for famous players with name variations ---
name_map = {
    "l messi": "lionel messi",
    "lionel andres messi cuccittini": "lionel messi",

    "c ronaldo": "cristiano ronaldo",
    "cristiano ronaldo dos santos aveiro": "cristiano ronaldo",

    "m salah": "mohamed salah",
    "mo salah": "mohamed salah",
    "mohamed salah ghaly": "mohamed salah",

    "k mbappe": "kylian mbappe",
    "kylian mbappe lottin": "kylian mbappe",

    "e haaland": "erling haaland",
    "erling braut haaland": "erling haaland",
}

df["normalized_name"] = df["normalized_name"].replace(name_map)

# --- Verification for sample stars ---
samples = ["lionel messi", "cristiano ronaldo", "mohamed salah", "kylian mbappe", "erling haaland"]
print("\n👤 Player year coverage check:")
for s in samples:
    versions = sorted([int(v) for v in df.loc[df["normalized_name"] == s, "fifa_version"].unique()])
    print(f"  {s.title():<20}: {versions}")

# --- Save cleaned dataset ---
output_path = r"data\male_players_cleaned.csv"
df.to_csv(output_path, index=False)
print(f"\n💾 Cleaned dataset saved as: {output_path}")
