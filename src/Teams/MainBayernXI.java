// src/Teams/MainBayernXI.java
package Teams;

import Players.Player;
import Team_Specifics.Formations;
import Team_Specifics.PlayerPositions;
import Team_Specifics.TeamName;
import Utils.VectorTwoDim;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainBayernXI {

    public static void main(String[] args) {
        // 4-2-3-1
        Team fcb4231 = new Team(TeamName.BAYERN_MUNCHEN, Formations.FOUR_TWO_THREE_ONE, buildBayern(), "Vincent Kompany");
        System.out.println("=== Bayern — 4-2-3-1 ===");
        printXI(fcb4231.assignXI());

        // 4-3-3 (optional)
        Team fcb433 = new Team(TeamName.BAYERN_MUNCHEN, Formations.FOUR_THREE_THREE, buildBayern(), "Vincent Kompany");
        System.out.println("\n=== Bayern — 4-3-3 ===");
        printXI(fcb433.assignXI());
    }

    private static void printXI(List<Player> xi) {
        Set<Player> unique = new HashSet<>(xi);
        System.out.println("Picked: " + xi.size() + " (unique: " + unique.size() + ")");
        int i = 1;
        for (Player p : xi) {
            System.out.printf("%2d) %-22s %-3s  OVR=%d%n",
                    i++, p.getPlayerName(), p.getPosition().name(), p.getOvr());
        }
    }

    private static List<Player> buildBayern() {
        List<Player> s = new ArrayList<>();
        VectorTwoDim O = new VectorTwoDim(0, 0);

        // -------- GK (DIV, HAN, KIC, POS, REF) --------
        s.add(pGK("Manuel Neuer", O, 88, 88, 91, 90, 88));
        s.add(pGK("Sven Ulreich", O, 76, 77, 72, 76, 78));

        // -------- CBs --------
        s.add(pOF("Matthijs de Ligt", PlayerPositions.CB, O, 71, 47, 70, 65, 86, 85));
        s.add(pOF("Dayot Upamecano",   PlayerPositions.CB, O, 84, 40, 66, 68, 83, 86));
        s.add(pOF("Kim Min-jae",       PlayerPositions.CB, O, 78, 38, 66, 64, 85, 84));

        // -------- Fullbacks / Wingbacks --------
        s.add(pOF("Alphonso Davies",   PlayerPositions.LB, O, 96, 66, 74, 86, 72, 76));
        s.add(pOF("Noussair Mazraoui", PlayerPositions.RB, O, 82, 57, 76, 80, 76, 73));
        s.add(pOF("Raphaël Guerreiro", PlayerPositions.LB, O, 78, 69, 83, 82, 70, 67));

        // -------- Midfield --------
        s.add(pOF("Joshua Kimmich",    PlayerPositions.CM,  O, 67, 73, 88, 84, 78, 74));
        s.add(pOF("Leon Goretzka",     PlayerPositions.CM,  O, 78, 79, 78, 78, 76, 86));
        s.add(pOF("Konrad Laimer",     PlayerPositions.CDM, O, 84, 65, 74, 80, 77, 79));
        s.add(pOF("Aleksandar Pavlovic",PlayerPositions.CM, O, 63, 64, 78, 77, 70, 73));

        // -------- Attackers --------
        s.add(pOF("Jamal Musiala",     PlayerPositions.CAM, O, 85, 79, 82, 92, 45, 68));
        s.add(pOF("Leroy Sané",        PlayerPositions.RW,  O, 92, 84, 83, 88, 48, 74));
        s.add(pOF("Kingsley Coman",    PlayerPositions.LW,  O, 92, 79, 78, 89, 46, 70));
        s.add(pOF("Serge Gnabry",      PlayerPositions.RW,  O, 84, 81, 77, 84, 50, 75));
        s.add(pOF("Harry Kane",        PlayerPositions.ST,  O, 72, 92, 83, 82, 45, 85));
        s.add(pOF("Mathys Tel",        PlayerPositions.ST,  O, 89, 79, 67, 82, 45, 76));
        s.add(pOF("Thomas Müller",     PlayerPositions.CF,  O, 67, 82, 82, 79, 53, 74));

        return s;
    }

    // --- helpers wired to your latest Player constructors ---

    // Outfield: (pace, shooting, passing, dribbling, defending, physicality)
    private static Player pOF(String name, PlayerPositions pos, VectorTwoDim pitch,
                              int pace, int shooting, int passing, int dribbling, int defending, int physicality) {
        return new Player(name, TeamName.BAYERN_MUNCHEN, pos, pitch,
                pace, shooting, passing, dribbling, defending, physicality);
    }

    // Goalkeeper: (diving, handling, kicking, positioning, reflexes)
    private static Player pGK(String name, VectorTwoDim pitch,
                              int diving, int handling, int kicking, int positioning, int reflexes) {
        return new Player(name, TeamName.BAYERN_MUNCHEN, PlayerPositions.GK, pitch,
                diving, handling, kicking, positioning, reflexes);
    }
}
