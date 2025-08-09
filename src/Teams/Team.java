package Teams;

import Exceptions.PlayerException;
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
        if (headCoach.isEmpty())
            throw new PlayerException("Invalid coach name");
        this.name = name;
        this.formation = formation;
        this.squad = squad;
        this.startingXI = new ArrayList<>();
        assignXI();
    }

    public List<Player> assignXI() {
        startingXI.clear();
        Map<PlayerPositions, List<Player>> matchSquad = getMatchdaySquad();
        List<PlayerPositions> form = formation.getPositions();
        Set<Player> pickedPlayers = new HashSet<>();

        for (PlayerPositions p : form) {
            List<Player> bucket = matchSquad.get(p);
            Player selected = null;

            // Primary pick
            if (bucket != null) {
                for (Player candidate : bucket) {
                    if (!pickedPlayers.contains(candidate)) {
                        selected = candidate;
                        break;
                    }
                }
            }

            // Fallback if primary fails
            if (selected == null) {
                PlayerPositions fallbackPos = getFallback(p);
                if (fallbackPos != null && matchSquad.containsKey(fallbackPos)) {
                    for (Player candidate : matchSquad.get(fallbackPos)) {
                        if (!pickedPlayers.contains(candidate)) {
                            selected = candidate;
                            break;
                        }
                    }
                }
            }

            if (selected != null) {
                startingXI.add(selected);
                pickedPlayers.add(selected);

                // Remove from all buckets to avoid duplicates
                for (List<Player> list : matchSquad.values()) {
                    list.remove(selected);
                }
            } else {
                System.out.println("No eligible player for position: " + p);
            }
        }
        return startingXI;
    }

    // Simple fallback rules (can expand later)
    private PlayerPositions getFallback(PlayerPositions primary) {
        switch (primary) {
            case CAM: return PlayerPositions.CM;
            case CM: return PlayerPositions.CAM;
            case RB: return PlayerPositions.RWB;
            case LB: return PlayerPositions.LWB;
            case RWB: return PlayerPositions.RB;
            case LWB: return PlayerPositions.LB;
            case RW: return PlayerPositions.ST;
            case LW: return PlayerPositions.ST;
            case ST: return PlayerPositions.CF;
            case CF: return PlayerPositions.ST;
            default: return null;
        }
    }

    // ----- Getters for roles -----
    public List<Player> getRightWingers() {
        List<Player> winger = new ArrayList<>();
        for (Player p : squad) {
            if (p.getPosition() == PlayerPositions.RW)
                winger.add(p);
        }
        winger.sort(new OvrComparator());
        return winger;
    }

    public List<Player> getLeftWingers() {
        List<Player> winger = new ArrayList<>();
        for (Player p : squad) {
            if (p.getPosition() == PlayerPositions.LW)
                winger.add(p);
        }
        winger.sort(new OvrComparator());
        return winger;
    }

    public List<Player> getStrikers() {
        List<Player> striker = new ArrayList<>();
        for (Player p : squad) {
            if (p.getPosition() == PlayerPositions.ST)
                striker.add(p);
        }
        striker.sort(new OvrComparator());
        return striker;
    }

    public List<Player> getCentralForward() {
        List<Player> striker = new ArrayList<>();
        for (Player p : squad) {
            if (p.getPosition() == PlayerPositions.CF)
                striker.add(p);
        }
        striker.sort(new OvrComparator());
        return striker;
    }

    public List<Player> getAttackingMids() {
        List<Player> attMids = new ArrayList<>();
        for (Player p : squad) {
            if (p.getPosition() == PlayerPositions.CAM)
                attMids.add(p);
        }
        attMids.sort(new OvrComparator());
        return attMids;
    }

    public List<Player> getMidfielders() {
        List<Player> attMids = new ArrayList<>();
        for (Player p : squad) {
            if (p.getPosition() == PlayerPositions.CM)
                attMids.add(p);
        }
        attMids.sort(new OvrComparator());
        return attMids;
    }

    public List<Player> getDefensiveMids() {
        List<Player> defMids = new ArrayList<>();
        for (Player p : squad) {
            if (p.getPosition() == PlayerPositions.CDM)
                defMids.add(p);
        }
        defMids.sort(new OvrComparator());
        return defMids;
    }

    public List<Player> getRightMidfielders() {
        List<Player> rightMids = new ArrayList<>();
        for (Player p : squad) {
            if (p.getPosition() == PlayerPositions.RM)
                rightMids.add(p);
        }
        rightMids.sort(new OvrComparator());
        return rightMids;
    }

    public List<Player> getLeftMidfielders() {
        List<Player> leftMids = new ArrayList<>();
        for (Player p : squad) {
            if (p.getPosition() == PlayerPositions.LM)
                leftMids.add(p);
        }
        leftMids.sort(new OvrComparator());
        return leftMids;
    }

    public List<Player> getCentralDefenders() {
        List<Player> centralDefs = new ArrayList<>();
        for (Player p : squad) {
            if (p.getPosition() == PlayerPositions.CB)
                centralDefs.add(p);
        }
        centralDefs.sort(new OvrComparator());
        return centralDefs;
    }

    public List<Player> getLeftWingbacks() {
        List<Player> wingbacks = new ArrayList<>();
        for (Player p : squad) {
            if (p.getPosition() == PlayerPositions.LWB)
                wingbacks.add(p);
        }
        wingbacks.sort(new OvrComparator());
        return wingbacks;
    }

    public List<Player> getRightWingbacks() {
        List<Player> wingbacks = new ArrayList<>();
        for (Player p : squad) {
            if (p.getPosition() == PlayerPositions.RWB)
                wingbacks.add(p);
        }
        wingbacks.sort(new OvrComparator());
        return wingbacks;
    }

    public List<Player> getRightBacks() {
        List<Player> wingbacks = new ArrayList<>();
        for (Player p : squad) {
            if (p.getPosition() == PlayerPositions.RB)
                wingbacks.add(p);
        }
        wingbacks.sort(new OvrComparator());
        return wingbacks;
    }

    public List<Player> getLeftBacks() {
        List<Player> wingbacks = new ArrayList<>();
        for (Player p : squad) {
            if (p.getPosition() == PlayerPositions.LB)
                wingbacks.add(p);
        }
        wingbacks.sort(new OvrComparator());
        return wingbacks;
    }

    public List<Player> getGoalkeepers() {
        List<Player> goalkeepers = new ArrayList<>();
        for (Player p : squad) {
            if (p.getPosition() == PlayerPositions.GK)
                goalkeepers.add(p);
        }
        goalkeepers.sort(new OvrComparator());
        return goalkeepers;
    }

    public Map<PlayerPositions, List<Player>> getMatchdaySquad() {
        Set<PlayerPositions> required = new HashSet<>(this.formation.getPositions());
        Map<PlayerPositions, List<Player>> matchdaySquad = new EnumMap<>(PlayerPositions.class);

        for (PlayerPositions p : required) {
            matchdaySquad.put(p, new ArrayList<>());
        }

        // Source lists
        List<Player> goalkeepers = new ArrayList<>(getGoalkeepers());
        List<Player> rightWingbacks = new ArrayList<>(getRightWingbacks());
        List<Player> attackingMids = new ArrayList<>(getAttackingMids());
        List<Player> defenderMids = new ArrayList<>(getDefensiveMids());
        List<Player> centralDefenders = new ArrayList<>(getCentralDefenders());
        List<Player> leftWingbacks = new ArrayList<>(getLeftWingbacks());
        List<Player> leftBacks = new ArrayList<>(getLeftBacks());
        List<Player> rightBacks = new ArrayList<>(getRightBacks());
        List<Player> rightMids = new ArrayList<>(getRightMidfielders());
        List<Player> leftMids = new ArrayList<>(getLeftMidfielders());
        List<Player> mids = new ArrayList<>(getMidfielders());
        List<Player> rightWinger = new ArrayList<>(getRightWingers());
        List<Player> leftWinger = new ArrayList<>(getLeftWingers());
        List<Player> centralForward = new ArrayList<>(getCentralForward());
        List<Player> strikers = new ArrayList<>(getStrikers());

        Map<PlayerPositions, Integer> count = new EnumMap<>(PlayerPositions.class);
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

        for (PlayerPositions p : count.keySet()) {
            if (!required.contains(p)) continue;
            int starters = count.getOrDefault(p, 0);
            if (starters <= 0) continue;
            int take = starters + 1;

            List<Player> src;
            if (p == PlayerPositions.GK) src = goalkeepers;
            else if (p == PlayerPositions.LB) src = leftBacks;
            else if (p == PlayerPositions.RB) src = rightBacks;
            else if (p == PlayerPositions.RWB) src = rightWingbacks;
            else if (p == PlayerPositions.LWB) src = leftWingbacks;
            else if (p == PlayerPositions.RM) src = rightMids;
            else if (p == PlayerPositions.CAM) src = attackingMids;
            else if (p == PlayerPositions.CDM) src = defenderMids;
            else if (p == PlayerPositions.LM) src = leftMids;
            else if (p == PlayerPositions.CM) src = mids;
            else if (p == PlayerPositions.CB) src = centralDefenders;
            else if (p == PlayerPositions.CF) src = centralForward;
            else if (p == PlayerPositions.LW) src = leftWinger;
            else if (p == PlayerPositions.RW) src = rightWinger;
            else if (p == PlayerPositions.ST) src = strikers;
            else continue;

            int limit = Math.min(take, src.size());
            if (limit > 0) {
                matchdaySquad.get(p).addAll(src.subList(0, limit));
            }
        }
        return matchdaySquad;
    }
}