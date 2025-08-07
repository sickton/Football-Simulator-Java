package Teams;

import Exceptions.PlayerException;
import Exceptions.TeamException;
import Players.Player;
import Team_Specifics.Formations;
import Team_Specifics.PlayerPositions;
import Team_Specifics.TeamName;

import java.util.*;

public class Team {
    private TeamName name;
    private Formations formation;
    private List<Player> squad;
    private List<Player> startingXI;
    private String headCoach;

    public Team(TeamName name, Formations formation, List<Player> squad, String headCoach) {
        if(headCoach.isEmpty())
            throw new PlayerException("Invalid coach name");
        this.name = name;
        this.formation = formation;
        this.squad = squad;
        assignXI();
    }

    public void  assignXI() {
        int strikers = this.formation.getStrikers();
        int centralDefenders = this.formation.getCentralDefenders();
        int attackingMidfielders = this.formation.getAttackingMidfielders();
        int defensiveMidfielders = this.formation.getDefensiveMidfielders();
        int wingbackDefender = this.formation.getWingback();
        int wingers = this.formation.getWingers();

        Map<PlayerPositions, Integer> squadMap = this.getSquadPerRole();

        List<Player> goalies = getGoalkeepers();
        List<Player> winger = getWingers();
        List<Player> striker = getStrikers();
        List<Player> attMid = getAttackingMids();
        List<Player> defMid = getDefensiveMids();
        List<Player> centerBacks = getCentralDefenders();
        List<Player> wingbacks = getWingbacks();

        if(goalies.isEmpty())
            throw new TeamException("Goalkeepers absent from squad");
        startingXI.addFirst(goalies.getFirst());
    }

    public List<Player> getWingers() {
        List<Player> winger = new ArrayList<Player>();
        for(Player p : squad) {
            if(p.getPosition() == PlayerPositions.RW || p.getPosition() == PlayerPositions.LW)
                winger.add(p);
        }
        winger.sort(new OvrComparator());
        return winger;
    }

    public List<Player> getStrikers() {
        List<Player> striker = new ArrayList<Player>();
        for(Player p : squad) {
            if(p.getPosition() == PlayerPositions.ST || p.getPosition() == PlayerPositions.CF)
                striker.add(p);
        }
        striker.sort(new OvrComparator());
        return striker;
    }

    public List<Player> getAttackingMids() {
        List<Player> attMids = new ArrayList<Player>();
        for(Player p : squad) {
            if(p.getPosition() == PlayerPositions.CM || p.getPosition() == PlayerPositions.CAM)
                attMids.add(p);
        }
        attMids.sort(new OvrComparator());
        return attMids;
    }

    public List<Player> getDefensiveMids() {
        List<Player> defMids = new ArrayList<Player>();
        for(Player p : squad) {
            if(p.getPosition() == PlayerPositions.CDM)
                defMids.add(p);
        }
        defMids.sort(new OvrComparator());
        return defMids;
    }

    public List<Player> getCentralDefenders() {
        List<Player> centralDefs = new ArrayList<Player>();
        for(Player p : squad) {
            if(p.getPosition() == PlayerPositions.CB)
                centralDefs.add(p);
        }
        centralDefs.sort(new OvrComparator());
        return centralDefs;
    }

    public List<Player> getWingbacks() {
        List<Player> wingbacks = new ArrayList<Player>();
        for(Player p : squad) {
            if(p.getPosition() == PlayerPositions.LB || p.getPosition() == PlayerPositions.RB
            || p.getPosition() == PlayerPositions.RWB || p.getPosition() == PlayerPositions.LWB)
                wingbacks.add(p);
        }
        wingbacks.sort(new OvrComparator());
        return wingbacks;
    }

    public List<Player> getGoalkeepers() {
        List<Player> goalkeepers = new ArrayList<Player>();
        for(Player p : squad) {
            if(p.getPosition() == PlayerPositions.GK)
                goalkeepers.add(p);
        }
        return goalkeepers;
    }

    public Map<PlayerPositions, Integer> getSquadPerRole() {
        Map<PlayerPositions, Integer> squad = new HashMap<PlayerPositions, Integer>();

        squad.put(PlayerPositions.GK, getGoalkeepers().size());
        squad.put(PlayerPositions.CB, getCentralDefenders().size());
        int st = 0;
        int cf = 0;
        int cam = 0;
        int cdm = 0;
        int lm = 0;
        int rm = 0;
        int cm = 0;
        int lb = 0;
        int rb = 0;
        int lwb = 0;
        int rwb = 0;
        for(Player p : this.squad)
        {
            if(PlayerPositions.ST == p.getPosition())
                st++;
            else if(PlayerPositions.CF == p.getPosition())
                cf++;
            else if(PlayerPositions.CAM == p.getPosition())
                cam++;
            else if(PlayerPositions.LB == p.getPosition())
                lb++;
            else if(PlayerPositions.RB == p.getPosition())
                rb++;
            else if(PlayerPositions.LWB == p.getPosition())
                lwb++;
            else if(PlayerPositions.RWB == p.getPosition())
                rwb++;
            else if(PlayerPositions.CDM == p.getPosition())
                cdm++;
            else if(PlayerPositions.LM == p.getPosition())
                lm++;
            else if(PlayerPositions.RM == p.getPosition())
                rm++;
            else if(PlayerPositions.CM == p.getPosition())
                cm++;
        }
        squad.put(PlayerPositions.ST, st);
        squad.put(PlayerPositions.CF, cf);
        squad.put(PlayerPositions.CAM, cam);
        squad.put(PlayerPositions.LB, lb);
        squad.put(PlayerPositions.RB, rb);
        squad.put(PlayerPositions.LWB, lwb);
        squad.put(PlayerPositions.RWB, rwb);
        squad.put(PlayerPositions.CDM, cdm);
        squad.put(PlayerPositions.LM, lm);
        squad.put(PlayerPositions.RM, rm);
        squad.put(PlayerPositions.CM, cm);
        return squad;
    }
}
