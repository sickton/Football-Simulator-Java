package Team_Specifics;

import java.util.ArrayList;
import java.util.List;

public enum Formations {
    FOUR_THREE_THREE("4-3-3", 1, 2, 2, 1, 2, 2,  List.of(
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
    FOUR_FOUR_TWO("4-4-2", 2, 2, 4, 0, 2, 0, List.of(
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
    FOUR_TWO_THREE_ONE("4-2-3-1", 1, 2, 1, 2, 2, 2, List.of(
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
    THREE_FIVE_TWO("3-5-2", 2, 3, 4 , 1, 0, 0, List.of(
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
    THREE_FOUR_THREE("3-4-3", 1, 2, 2, 1, 2, 2, List.of(PlayerPositions.GK,
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
    private final int strikers;
    private final int centralDefenders;
    private final int attackingMidfielders;
    private final int defensiveMidfielders;
    private final int wingbackDefender;
    private final int winger;
    private final List<PlayerPositions> positions;

    Formations(String formation, int strikers, int centralDefenders, int attackingMidfielders, int defensiveMidfielders, int wingbackDefender, int wingers, List<PlayerPositions> positions) {
        this.formation = formation;
        this.strikers = strikers;
        this.centralDefenders = centralDefenders;
        this.attackingMidfielders = attackingMidfielders;
        this.defensiveMidfielders = defensiveMidfielders;
        this.wingbackDefender = wingbackDefender;
        this.winger = wingers;
        this.positions = positions;
    }

    public String getFormation() {
        return this.formation;
    }

    public int getStrikers() {
        return this.strikers;
    }

    public int getCentralDefenders() {
        return this.centralDefenders;
    }

    public int getAttackingMidfielders() {
        return this.attackingMidfielders;
    }

    public int getDefensiveMidfielders() {
        return this.defensiveMidfielders;
    }

    public int getWingback() {
        return this.wingbackDefender;
    }

    public int getWingers() {
        return this.winger;
    }

    public List<PlayerPositions> getPositions()
    {
        return this.positions;
    }
}
