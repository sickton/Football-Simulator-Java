package Team_Specifics;

import java.util.ArrayList;
import java.util.List;

public enum Formations {
    FOUR_THREE_THREE("4-3-3", 4, 3, 3, List.of(
            PlayerPositions.GK,
            PlayerPositions.RB,
            PlayerPositions.CB,
            PlayerPositions.CB,
            PlayerPositions.LB,
            PlayerPositions.CDM,
            PlayerPositions.CM,
            PlayerPositions.CM,
            PlayerPositions.RW,
            PlayerPositions.ST,
            PlayerPositions.LW
    )),
    FOUR_FOUR_TWO("4-4-2", 4, 2, 4, List.of(
            PlayerPositions.GK,
            PlayerPositions.RB,
            PlayerPositions.CB,
            PlayerPositions.CB,
            PlayerPositions.LB,
            PlayerPositions.LM,
            PlayerPositions.CM,
            PlayerPositions.CM,
            PlayerPositions.RM,
            PlayerPositions.ST,
            PlayerPositions.ST
    )),
    FOUR_TWO_THREE_ONE("4-2-3-1", 4, 2, 4, List.of(
            PlayerPositions.GK,
            PlayerPositions.LB,
            PlayerPositions.CB,
            PlayerPositions.CB,
            PlayerPositions.RB,
            PlayerPositions.CDM,
            PlayerPositions.CDM,
            PlayerPositions.CAM,
            PlayerPositions.LW,
            PlayerPositions.RW,
            PlayerPositions.ST
    )),
    THREE_FIVE_TWO("3-5-2", 3, 5, 2, List.of(
            PlayerPositions.GK,
            PlayerPositions.CB,
            PlayerPositions.CB,
            PlayerPositions.CB,
            PlayerPositions.LM,
            PlayerPositions.RM,
            PlayerPositions.CM,
            PlayerPositions.CDM,
            PlayerPositions.CM,
            PlayerPositions.ST,
            PlayerPositions.ST
    )),
    THREE_FOUR_THREE("3-4-3", 3, 4, 3, List.of(PlayerPositions.GK,
            PlayerPositions.RB,
            PlayerPositions.CB,
            PlayerPositions.CB,
            PlayerPositions.LB,
            PlayerPositions.CDM,
            PlayerPositions.CM,
            PlayerPositions.CAM,
            PlayerPositions.LW,
            PlayerPositions.ST,
            PlayerPositions.RW));

    private final String formation;
    private final int defenders;
    private final int midfielders;
    private final int attackers;
    private final List<PlayerPositions> positions;

    Formations(String formation, int defenders, int midfielders, int attackers, List<PlayerPositions> positions) {
        this.formation = formation;
        this.defenders = defenders;
        this.midfielders = midfielders;
        this.attackers = attackers;
        this.positions = positions;
    }

    public String getFormation() {
        return this.formation;
    }

    public int getDefenders() {
        return defenders;
    }

    public int getMidfielders() {
        return midfielders;
    }

    public int getAttackers() {
        return attackers;
    }

    public List<PlayerPositions> getPositions()
    {
        return this.positions;
    }
}
