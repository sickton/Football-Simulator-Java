import requests

API_KEY = "f80c0148f2af4f2351c57b17f6a34991"
headers = {"x-apisports-key": API_KEY}

# Simple endpoint test
url = "https://v3.football.api-sports.io/status"
resp = requests.get(url, headers=headers)

print(resp.status_code)
print(resp.json())
