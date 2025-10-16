import pandas as pd
df = pd.read_csv("cleaned/FIFA_2015_2022_master.csv")

print("🧍 Total players:", len(df))
print("🆔 Unique FootSim IDs:", df['footsim_id'].nunique())
print("⚽ Unique years:", df['year'].unique())
print("⚙️ Missing values:\n", df.isna().sum())
