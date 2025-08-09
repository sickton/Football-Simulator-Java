package Team_Specifics;

public enum PlayerPositions {
    GK("Goalkeeper",    0.10, 0.05, 0.10, 0.05, 0.70),
    CB("Defender",      0.10, 0.15, 0.10, 0.05, 0.60),
    RB("Defender",      0.20, 0.15, 0.15, 0.05, 0.45),
    LB("Defender",      0.20, 0.15, 0.15, 0.05, 0.45),
    RWB("Defender",     0.25, 0.15, 0.15, 0.05, 0.40),
    LWB("Defender",     0.25, 0.15, 0.15, 0.05, 0.40),

    CM("Midfielder",    0.25, 0.20, 0.30, 0.10, 0.15),
    CAM("Midfielder",   0.25, 0.10, 0.35, 0.25, 0.05),
    CDM("Midfielder",   0.15, 0.20, 0.15, 0.05, 0.45),
    LM("Midfielder",    0.25, 0.20, 0.25, 0.20, 0.10),
    RM("Midfielder",    0.25, 0.20, 0.25, 0.20, 0.10),

    ST("Attacker",      0.10, 0.05, 0.20, 0.55, 0.10),
    CF("Attacker",      0.10, 0.05, 0.20, 0.55, 0.10),
    RW("Attacker",      0.20, 0.10, 0.25, 0.35, 0.10),
    LW("Attacker",      0.20, 0.10, 0.25, 0.35, 0.10);

    private final String role;
    private final double shortPassWeight;
    private final double longPassWeight;
    private final double visionWeight;
    private final double finishingWeight;
    private final double defendingWeight;

    PlayerPositions(String role,
                    double shortPassWeight,
                    double longPassWeight,
                    double visionWeight,
                    double finishingWeight,
                    double defendingWeight) {
        this.role = role;
        this.shortPassWeight = shortPassWeight;
        this.longPassWeight = longPassWeight;
        this.visionWeight = visionWeight;
        this.finishingWeight = finishingWeight;
        this.defendingWeight = defendingWeight;
    }

    public String getRole() {
        return this.role;
    }

    public double getShortPassWeight() {
        return shortPassWeight;
    }

    public double getLongPassWeight() {
        return longPassWeight;
    }

    public double getVisionWeight() {
        return visionWeight;
    }

    public double getFinishingWeight() {
        return finishingWeight;
    }

    public double getDefendingWeight() {
        return defendingWeight;
    }
}
