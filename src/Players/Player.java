package Players;

import org.bson.Document;

import Exceptions.PlayerException;
import Team_Specifics.PlayerPositions;
import Team_Specifics.TeamName;
import Utils.VectorTwoDim;

public class Player {
    private static final int FIVE = 5;

    private final String name;
    private final TeamName team;
    private final PlayerPositions position;
    private int pace;
    private int shooting;
    private int passing;
    private int dribbling;
    private int defending;
    private int physicality;
    private int diving;
    private int handling;
    private int kicking;
    private int positioning;
    private int reflexes;
    private int ovr;
    private VectorTwoDim positionInPitch;
    private boolean isRetired;

    public Player(String name, TeamName team, PlayerPositions position, VectorTwoDim positionOnPitch, int pace, int shooting, int passing, int dribbling, int defending, int physicality, boolean isRetired) {
        setAttributes(pace, shooting, passing, dribbling, defending, physicality);
        if(name.isEmpty())
            throw new PlayerException("Invalid Player Name");
        this.name = name;
        this.team = team;
        this.position = position;
        this.positionInPitch = positionOnPitch;
        this.isRetired = isRetired;
        calculateOvr();
    }

    public Player(String name, TeamName team, PlayerPositions position, VectorTwoDim positionOnPitch, int diving, int handling, int kicking, int positioning, int reflexes)
    {
        setGoalieAttributes(diving, handling, kicking, positioning, reflexes);
        if(name.isEmpty())
            throw new PlayerException("Invalid Player Name");
        this.name = name;
        this.team = team;
        this.position = position;
        this.positionInPitch = positionOnPitch;
        calculateOvr();
    }

    public void setGoalieAttributes(int diving, int handling, int kicking, int positioning, int reflexes)
    {
        if(diving < 0 || diving > 100)
            throw new PlayerException("Invalid Diving");
        else if(handling < 0 || handling > 100)
            throw new PlayerException("Invalid Handling");
        else if(kicking < 0 || kicking > 100)
            throw new PlayerException("Invalid Kicking");
        else if(positioning < 0 || positioning > 100)
            throw new PlayerException("Invalid Positioning");
        else if(reflexes < 0 || reflexes > 100)
            throw new PlayerException("Invalid Reflexes");
        else if(physicality < 0 || physicality > 100)
            throw new PlayerException("Invalid Physicality");
        this.diving = diving;
        this.handling = handling;
        this.kicking = kicking;
        this.positioning = positioning;
        this.reflexes = reflexes;
    }

    public void setAttributes(int pace, int shooting, int passing, int dribbling, int defending, int physicality) {
        if(pace < 0 || pace > 100)
            throw new PlayerException("Invalid pace");
        else if(shooting < 0 || shooting > 100)
            throw new PlayerException("Invalid shooting");
        else if(passing < 0 || passing > 100)
            throw new PlayerException("Invalid passing");
        else if(dribbling < 0 || dribbling > 100)
            throw new PlayerException("Invalid dribbling");
        else if(defending < 0 || defending > 100)
            throw new PlayerException("Invalid defending");
        else if(physicality < 0 || physicality > 100)
            throw new PlayerException("Invalid physicality");
        this.pace = pace;
        this.shooting = shooting;
        this.passing = passing;
        this.dribbling = dribbling;
        this.defending = defending;
        this.physicality = physicality;
    }

    public void calculateOvr() {
        PlayerPositions positionOnPitch = this.position;
        switch (positionOnPitch) {
            case GK:
                this.ovr = (int) Math.round((0.28 * this.diving) + (0.28 * this.reflexes) + (0.18 * this.handling) + (0.18 * this.positioning) + (0.08 * this.kicking));
                break;
            case CB:
                this.ovr = (int) Math.round((0.10 * this.pace) + (0.05 * this.dribbling) + (0.05 * this.shooting) + (0.10 * this.passing) + (0.45 * this.defending) + (0.25 * this.physicality));
                break;
            case RB:
            case LB:
                this.ovr = (int) Math.round((0.20 * this.pace) + (0.15 * this.dribbling) + (0.05 * this.shooting) + (0.20 * this.passing) + (0.30 * this.defending) + (0.10 * this.physicality));
                break;
            case RWB:
            case LWB:
                this.ovr = (int) Math.round((0.22 * this.pace) + (0.18 * this.dribbling) + (0.05 * this.shooting) + (0.20 * this.passing) + (0.28 * this.defending) + (0.07 * this.physicality));
                break;
            case CDM:
                this.ovr = (int) Math.round((0.10 * this.pace) + (0.10 * this.dribbling) + (0.05 * this.shooting) + (0.20 * this.passing) + (0.35 * this.defending) + (0.20 * this.physicality));
                break;
            case CM:
                this.ovr = (int) Math.round((0.15 * this.pace) + (0.20 * this.dribbling) + (0.10 * this.shooting) + (0.30 * this.passing) + (0.15 * this.defending) + (0.10 * this.physicality));
                break;
            case CAM:
                this.ovr = (int) Math.round((0.15 * this.pace) + (0.30 * this.dribbling) + (0.20 * this.shooting) + (0.25 * this.passing) + (0.05 * this.defending) + (0.05 * this.physicality));
                break;
            case RM:
            case LM:
                this.ovr = (int) Math.round((0.22 * this.pace) + (0.22 * this.dribbling) + (0.18 * this.shooting) + (0.20 * this.passing) + (0.08 * this.defending) + (0.10 * this.physicality));
                break;
            case RW:
            case LW:
                this.ovr = (int) Math.round((0.25 * this.pace) + (0.25 * this.dribbling) + (0.25 * this.shooting) + (0.15 * this.passing) + (0.05 * this.defending) + (0.05 * this.physicality));
                break;
            case ST:
            case CF:
                this.ovr = (int) Math.round((0.20 * this.pace) + (0.20 * this.dribbling) + (0.40 * this.shooting) + (0.10 * this.passing) + (0.03 * this.defending) + (0.07 * this.physicality));
                break;
        }
    }

    public void setPace(int pace) {
        this.pace = pace;
    }

    public void setShooting(int shooting) {
        this.shooting = shooting;
    }

    public void setPassing(int passing) {
        this.passing = passing;
    }

    public void setDribbling(int dribbling) {
        this.dribbling = dribbling;
    }

    public void setDefending(int defending) {
        this.defending = defending;
    }

    public void setPhysicality(int physicality) {
        this.physicality = physicality;
    }

    public void setDiving(int diving) {
        this.diving = diving;
    }

    public void setHandling(int handling) {
        this.handling = handling;
    }

    public void setKicking(int kicking) {
        this.kicking = kicking;
    }

    public void setPositioning(int positioning) {
        this.positioning = positioning;
    }

    public void setReflexes(int reflexes) {
        this.reflexes = reflexes;
    }

    public void setRetired(boolean retired) {
        this.isRetired = retired;
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

    public int getPassing() {
        return passing;
    }

    public int getShooting() {
        return shooting;
    }

    public int getPace() {
        return pace;
    }

    public int getDribbling() {
        return dribbling;
    }

    public int getPhysicality() {
        return physicality;
    }

    public int getDiving() {
        return diving;
    }

    public int getHandling() {
        return handling;
    }

    public int getKicking() {
        return kicking;
    }

    public int getReflexes() {
        return reflexes;
    }

    public int getPositioning() {
        return positioning;
    }

    public boolean isRetired() {
        return isRetired;
    }
    public Document toDocument()
    {
        Document doc = new Document();
        doc.append("name", this.name);
        doc.append("team", this.team.toString());
        doc.append("position", this.position.toString());
        doc.append("ovr", this.ovr);
        doc.append("pace", this.pace);
        doc.append("shooting", this.shooting);
        doc.append("passing", this.passing);
        doc.append("dribbling", this.dribbling);
        doc.append("defending", this.defending);
        doc.append("physicality", this.physicality);
        doc.append("diving", this.diving);
        doc.append("handling", this.handling);
        doc.append("kicking", this.kicking);
        doc.append("reflexes", this.reflexes);
        doc.append("positioning", this.positioning);
        doc.append("retired", this.isRetired);
        return doc;
    }
}