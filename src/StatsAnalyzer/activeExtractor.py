import pandas as pd

# Load the cleaned master dataset
df = pd.read_csv("cleaned/FIFA_2015_2022_master.csv")

# Separate 2021 and 2022 player sets
df_2021 = df[df["year"] == 2021]
df_2022 = df[df["year"] == 2022]

# Find common player names in both years
common_players = set(df_2021["name"]).intersection(set(df_2022["name"]))

# Keep only those common players from 2022 dataset
active_df = df_2022[df_2022["name"].isin(common_players)].copy()

# Drop duplicates just in case
active_df = active_df.drop_duplicates(subset="name", keep="last")

# Mark them as active for simulated 2025
active_df["year"] = 2025
active_df["active"] = True

# Save refined dataset
active_df.to_csv("cleaned/active_players_2025.csv", index=False)

print(f"Found {len(active_df)} players present in both 2021 & 2022 — likely still active in 2025.")
