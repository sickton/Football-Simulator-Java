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

    }

    public List<Player> getRightWingers() {
        List<Player> winger = new ArrayList<Player>();
        for(Player p : squad) {
            if(p.getPosition() == PlayerPositions.RW)
                winger.add(p);
        }
        winger.sort(new OvrComparator());
        return winger;
    }

    public List<Player> getLeftWingers() {
        List<Player> winger = new ArrayList<Player>();
        for(Player p : squad) {
            if(p.getPosition() == PlayerPositions.LW)
                winger.add(p);
        }
        winger.sort(new OvrComparator());
        return winger;
    }

    public List<Player> getStrikers() {
        List<Player> striker = new ArrayList<Player>();
        for(Player p : squad) {
            if(p.getPosition() == PlayerPositions.ST)
                striker.add(p);
        }
        striker.sort(new OvrComparator());
        return striker;
    }

    public List<Player> getCentralForward() {
        List<Player> striker = new ArrayList<Player>();
        for(Player p : squad) {
            if(p.getPosition() == PlayerPositions.CF)
                striker.add(p);
        }
        striker.sort(new OvrComparator());
        return striker;
    }

    public List<Player> getAttackingMids() {
        List<Player> attMids = new ArrayList<Player>();
        for(Player p : squad) {
            if(p.getPosition() == PlayerPositions.CAM)
                attMids.add(p);
        }
        attMids.sort(new OvrComparator());
        return attMids;
    }

    public List<Player> getMidfielders() {
        List<Player> attMids = new ArrayList<Player>();
        for(Player p : squad) {
            if(p.getPosition() == PlayerPositions.CM)
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

    public List<Player> getRightMidfielders()
    {
        List<Player> rightMids = new ArrayList<Player>();
        for(Player p : squad) {
            if(p.getPosition() == PlayerPositions.RM)
                rightMids.add(p);
        }
        return rightMids;
    }

    public List<Player> getLeftMidfielders()
    {
        List<Player> leftMids = new ArrayList<Player>();
        for(Player p : squad) {
            if(p.getPosition() == PlayerPositions.LM)
                leftMids.add(p);
        }
        return leftMids;
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

    public List<Player> getLeftWingbacks() {
        List<Player> wingbacks = new ArrayList<Player>();
        for(Player p : squad) {
            if(p.getPosition() == PlayerPositions.LWB)
                wingbacks.add(p);
        }
        wingbacks.sort(new OvrComparator());
        return wingbacks;
    }

    public List<Player> getRightWingbacks() {
        List<Player> wingbacks = new ArrayList<Player>();
        for(Player p : squad) {
            if(p.getPosition() == PlayerPositions.RWB)
                wingbacks.add(p);
        }
        wingbacks.sort(new OvrComparator());
        return wingbacks;
    }

    public List<Player> getRightBacks() {
        List<Player> wingbacks = new ArrayList<Player>();
        for(Player p : squad) {
            if(p.getPosition() == PlayerPositions.RB)
                wingbacks.add(p);
        }
        wingbacks.sort(new OvrComparator());
        return wingbacks;
    }

    public List<Player> getLeftBacks() {
        List<Player> wingbacks = new ArrayList<Player>();
        for(Player p : squad) {
            if(p.getPosition() == PlayerPositions.LB)
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
        goalkeepers.sort(new OvrComparator());
        return goalkeepers;
    }

    public Map<PlayerPositions, List<Player>> getMatchdaySquad() {
        Map<PlayerPositions, List<Player>> matchdaySquad = new HashMap<PlayerPositions, List<Player>>();
        List<PlayerPositions> formationReq = this.formation.getPositions();
        for(PlayerPositions p : formationReq) {
            if(matchdaySquad.containsKey(p))
                continue;
            matchdaySquad.put(p, new ArrayList<Player>());
        }

        List<Player> goalkeepers = getGoalkeepers();
        List<Player> rightWingbacks = getRightWingbacks();
        List<Player> attackingMids = getAttackingMids();
        List<Player> defenderMids = getDefensiveMids();
        List<Player> centralDefenders = getCentralDefenders();
        List<Player> leftWingbacks = getLeftWingbacks();
        List<Player> leftBacks = getLeftBacks();
        List<Player> rightBacks = getRightBacks();
        List<Player> rightMids = getRightMidfielders();
        List<Player> leftMids = getLeftMidfielders();
        List<Player> mids = getMidfielders();
        List<Player> rightWinger = getRightWingers();
        List<Player> leftWinger = getLeftWingers();
        List<Player> centralForward = getCentralForward();
        List<Player> strikers = getStrikers();

        Map<PlayerPositions, Integer> count = new HashMap<PlayerPositions, Integer>();
        count.put(PlayerPositions.GK, this.formation.getGoalkeeper());
        count.put(PlayerPositions.LB, this.formation.getLeftBacks());
        count.put(PlayerPositions.RB, this.formation.getRightBacks());
        count.put(PlayerPositions.RWB, this.formation.getRightWingBack());
        count.put(PlayerPositions.LWB, this.formation.getLeftWingBack());
        count.put(PlayerPositions.CB, this.formation.getCentralDefenders());
        count.put(PlayerPositions.CF, this.formation.getCentralForward());
        count.put(PlayerPositions.LW, this.formation.getLeftWinger());
        count.put(PlayerPositions.RW, this.formation.getRightWinger());
        count.put(PlayerPositions.ST, this.formation.getStrikers());
        count.put(PlayerPositions.CM, this.formation.getMidfielders());
        count.put(PlayerPositions.CAM, this.formation.getAttackingMidfielders());
        count.put(PlayerPositions.CDM, this.formation.getDefensiveMidfielders());
        count.put(PlayerPositions.LM, this.formation.getLeftMidfielders());
        count.put(PlayerPositions.RM, this.formation.getRightMidfielders());
        for(PlayerPositions p : count.keySet())
        {
            int depth = 0;
            while(count.get(p) != 0 && depth <= (count.get(p) + 1))
            {
                depth++;
                if(p == PlayerPositions.GK)
                    matchdaySquad.get(p).add(goalkeepers.removeFirst());
                else if(p ==  PlayerPositions.LB)
                    matchdaySquad.get(p).add(leftBacks.removeFirst());
                else if(p ==   PlayerPositions.RB)
                    matchdaySquad.get(p).add(rightBacks.removeFirst());
                else if(p == PlayerPositions.RWB)
                    matchdaySquad.get(p).add(rightWingbacks.removeFirst());
                else if(p == PlayerPositions.LWB)
                    matchdaySquad.get(p).add(leftWingbacks.removeFirst());
                else if(p == PlayerPositions.RM)
                    matchdaySquad.get(p).add(rightMids.removeFirst());
                else if(p == PlayerPositions.CAM)
                    matchdaySquad.get(p).add(attackingMids.removeFirst());
                else if(p == PlayerPositions.CDM)
                    matchdaySquad.get(p).add(defenderMids.removeFirst());
                else if(p ==  PlayerPositions.LM)
                    matchdaySquad.get(p).add(leftMids.removeFirst());
                else if(p == PlayerPositions.CM)
                    matchdaySquad.get(p).add(mids.removeFirst());
                else if(p == PlayerPositions.CB)
                    matchdaySquad.get(p).add(centralDefenders.removeFirst());
                else if(p == PlayerPositions.CF)
                    matchdaySquad.get(p).add(centralForward.removeFirst());
                else if(p == PlayerPositions.LW)
                    matchdaySquad.get(p).add(leftWinger.removeFirst());
                else if(p == PlayerPositions.RW)
                    matchdaySquad.get(p).add(rightWinger.removeFirst());
                else if(p == PlayerPositions.ST)
                    matchdaySquad.get(p).add(strikers.removeFirst());
            }
        }
        return matchdaySquad;
    }
}
