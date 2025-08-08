package Team_Specifics;

import java.util.List;

public enum Formations {
    FOUR_THREE_THREE("4-3-3", 1, 2, 1, 1, 0, 0, 0, 0, 0, 2, 1, 1, 1, 1, 0,  List.of(
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
    FOUR_FOUR_TWO("4-4-2", 1, 2, 1, 1, 0, 0, 1, 1, 0, 2, 0, 0, 0, 2, 0, List.of(
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
    FOUR_TWO_THREE_ONE("4-2-3-1", 1, 2, 1, 1, 0, 0, 0, 0, 1, 0, 2, 1, 1, 1, 0, List.of(
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
    THREE_FIVE_TWO("3-5-2", 1, 3, 0, 0, 0, 0, 1, 1, 0, 2, 1, 0, 0, 2, 0, List.of(
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
    THREE_FOUR_THREE("3-4-3", 1, 2, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, List.of(
            PlayerPositions.GK,
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
    private final int goalkeeper;
    private final int centralDefenders;
    private final int leftBacks;
    private final int rightBacks;
    private final int leftWingBack;
    private final int rightWingBack;
    private final int leftMidfielders;
    private final int rightMidfielders;
    private final int attackingMidfielders;
    private final int defensiveMidfielders;
    private final int midfielders;
    private final int rightWinger;
    private final int leftWinger;
    private final int striker;
    private final int centralForward;
    private final List<PlayerPositions> positions;

    Formations(String formation, int goalkeeper, int centralDefenders, int leftbacks, int rightbacks, int leftWingBack, int rightWingBack, int leftMidfielders, int rightMidfielders, int attackingMidfielders, int midfielders, int defensiveMidfielders, int leftWinger, int rightWinger, int striker, int centralForward, List<PlayerPositions> positions) {
        this.formation = formation;
        this.goalkeeper = goalkeeper;
        this.leftBacks = leftbacks;
        this.rightBacks = rightbacks;
        this.leftWingBack = leftWingBack;
        this.rightWingBack = rightWingBack;
        this.leftMidfielders = leftMidfielders;
        this.rightMidfielders = rightMidfielders;
        this.midfielders = midfielders;
        this.rightWinger = rightWinger;
        this.leftWinger = leftWinger;
        this.striker = striker;
        this.centralForward = centralForward;
        this.centralDefenders = centralDefenders;
        this.attackingMidfielders = attackingMidfielders;
        this.defensiveMidfielders = defensiveMidfielders;
        this.positions = positions;
    }

    public String getFormation() {
        return this.formation;
    }

    public int getStrikers() {
        return this.striker;
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

    public int getRightBacks() {
        return this.rightBacks;
    }


    public List<PlayerPositions> getPositions()
    {
        return this.positions;
    }

    public int getLeftBacks() {
        return leftBacks;
    }

    public int getGoalkeeper() {
        return goalkeeper;
    }

    public int getLeftWingBack() {
        return leftWingBack;
    }

    public int getRightWingBack() {
        return rightWingBack;
    }

    public int getLeftMidfielders() {
        return leftMidfielders;
    }

    public int getRightMidfielders() {
        return rightMidfielders;
    }

    public int getMidfielders() {
        return midfielders;
    }

    public int getRightWinger() {
        return rightWinger;
    }

    public int getLeftWinger() {
        return leftWinger;
    }

    public int getCentralForward() {
        return centralForward;
    }
}
