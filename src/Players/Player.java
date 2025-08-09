package Players;

import Exceptions.PlayerException;
import Team_Specifics.PlayerPositions;
import Team_Specifics.TeamName;
import Utils.VectorTwoDim;

public class Player {
    private static final int FIVE = 5;

    private String name;
    private TeamName team;
    private PlayerPositions position;
    private int shortPassing;
    private int longPassing;
    private int vision;
    private int finishing;
    private int defending;
    private int ovr;
    private VectorTwoDim positionInPitch;

    public Player(String name, TeamName team, PlayerPositions position, VectorTwoDim positionOnPitch, int shortPassing, int longPassing, int vision, int finishing, int defending) {
        setAttributes(shortPassing, longPassing, vision, finishing, defending);
        if(name.isEmpty())
            throw new PlayerException("Invalid Player Name");
        this.name = name;
        this.team = team;
        this.position = position;
        this.positionInPitch = positionOnPitch;
        calculateOvr();
    }

    public void setAttributes(int shortPassing, int longPassing, int vision, int finishing, int defending) {
        if(shortPassing < 0 || shortPassing > 100)
            throw new PlayerException("Invalid short passing");
        else if(longPassing < 0 || longPassing > 100)
            throw new PlayerException("Invalid long passing");
        else if(vision < 0 || vision > 100)
            throw new PlayerException("Invalid vision");
        else if(finishing < 0 || finishing > 100)
            throw new PlayerException("Invalid finishing");
        else if(defending < 0 || defending > 100)
            throw new PlayerException("Invalid defending");
        this.shortPassing = shortPassing;
        this.longPassing = longPassing;
        this.vision = vision;
        this.finishing = finishing;
        this.defending = defending;
    }

    public void calculateOvr() {
        PlayerPositions pos = this.position;
        double weighted =
                this.shortPassing * pos.getShortPassWeight() +
                        this.longPassing  * pos.getLongPassWeight() +
                        this.vision       * pos.getVisionWeight() +
                        this.finishing    * pos.getFinishingWeight() +
                        this.defending    * pos.getDefendingWeight();

        this.ovr = (int) Math.round(weighted);
    }


    public String getPlayerName() {
        return this.name;
    }

    public TeamName getTeam() {
        return this.team;
    }
    public PlayerPositions getPosition() {
        return this.position;
    }

    public int getShortPassing() {
        return this.shortPassing;
    }

    public int getLongPassing() {
        return this.longPassing;
    }

    public int getVision() {
        return this.vision;
    }

    public int getFinishing() {
        return this.finishing;
    }

    public int getDefending() {
        return this.defending;
    }

    public int getOvr() {
        return this.ovr;
    }

    public VectorTwoDim getPositionOnPitch() {
        return this.positionInPitch;
    }

    public void setPositionOnPitch(VectorTwoDim position) {
        this.positionInPitch = position;
    }
}
