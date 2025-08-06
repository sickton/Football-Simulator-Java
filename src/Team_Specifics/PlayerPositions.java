package Team_Specifics;

public enum PlayerPositions {
    GK("Goalkeeper"),

    CB("Defender"),
    RB("Defender"),
    LB("Defender"),
    RWB("Defender"),
    LWB("Defender"),

    CM("Midfielder"),
    CAM("Midfielder"),
    CDM("Midfielder"),
    LM("Midfielder"),
    RM("Midfielder"),

    ST("Attacker"),
    CF("Attacker"),
    RW("Attacker"),
    LW("Attacker");

    private final String role;

    PlayerPositions(String role) {
        this.role = role;
    }

    public String getRole() {
        return this.role;
    }
}
