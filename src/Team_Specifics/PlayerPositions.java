package Team_Specifics;

public enum PlayerPositions {
    GK(RoleSpecifics.GOALKEEPERS),
    CB(RoleSpecifics.DEFENDERS),
    RB(RoleSpecifics.DEFENDERS),
    LB(RoleSpecifics.DEFENDERS),
    RWB(RoleSpecifics.WINGBACKS),
    LWB(RoleSpecifics.WINGBACKS),

    CM(RoleSpecifics.MIDFIELDERS),
    CAM(RoleSpecifics.MIDFIELDERS),
    CDM(RoleSpecifics.MIDFIELDERS),
    LM(RoleSpecifics.MIDFIELDERS),
    RM(RoleSpecifics.MIDFIELDERS),

    ST(RoleSpecifics.ATTACKERS),
    CF(RoleSpecifics.ATTACKERS),
    RW(RoleSpecifics.ATTACKERS),
    LW(RoleSpecifics.ATTACKERS);

    private final RoleSpecifics role;

    PlayerPositions(RoleSpecifics role) {
        this.role = role;
    }

    public RoleSpecifics getRole() {
        return this.role;
    }
}
