// src/Teams/MainLiverpoolXI.java
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

public class MainLiverpoolXI {

    public static void main(String[] args) {
        // 4-3-3
        Team lfc433 = new Team(TeamName.LIVERPOOL, Formations.FOUR_THREE_THREE, buildLiverpool(), "Arne Slot");
        System.out.println("=== Liverpool — 4-3-3 ===");
        printXI(lfc433.assignXI());

        // 4-2-3-1
        Team lfc4231 = new Team(TeamName.LIVERPOOL, Formations.FOUR_TWO_THREE_ONE, buildLiverpool(), "Arne Slot");
        System.out.println("\n=== Liverpool — 4-2-3-1 ===");
        printXI(lfc4231.assignXI());

        // (Optional) force a fallback by removing natural RWs
        /*
        List<Player> noRW = new ArrayList<>();
        for (Player p : buildLiverpool()) if (p.getPosition() != PlayerPositions.RW) noRW.add(p);
        Team lfcNoRW = new Team(TeamName.LIVERPOOL, Formations.FOUR_THREE_THREE, noRW, "Arne Slot");
        System.out.println("\n=== Liverpool — 4-3-3 (No RW available: fallback test) ===");
        printXI(lfcNoRW.assignXI());
        */
    }

    private static void printXI(List<Player> xi) {
        Set<Player> unique = new HashSet<>(xi);
        System.out.println("Picked: " + xi.size() + " (unique: " + unique.size() + ")");
        int i = 1;
        for (Player p : xi) {
            System.out.printf("%2d) %-20s %-3s  OVR=%d%n",
                    i++, p.getPlayerName(), p.getPosition().name(), p.getOvr());
        }
    }

    /** Build Liverpool squad using FC25-style face stats mapped into your 5 attributes. */
    private static List<Player> buildLiverpool() {
        List<Player> s = new ArrayList<>();

        // -------- GK --------
        // shortPass, longPass, vision = PAS ; finishing = SHO ; defending = DEF
        s.add(p("Alisson", PlayerPositions.GK, 85, 85, 85, 35, 56));              // OVR 89-ish
        s.add(p("Caoimhin Kelleher", PlayerPositions.GK, 77, 77, 77, 33, 43));    // OVR ~77

        // -------- CB --------
        s.add(p("Virgil van Dijk", PlayerPositions.CB, 71, 71, 71, 60, 89));      // OVR ~89
        s.add(p("Ibrahima Konaté", PlayerPositions.CB, 59, 59, 59, 34, 83));      // OVR ~83
        s.add(p("Joe Gomez", PlayerPositions.CB, 71, 71, 71, 28, 80));            // OVR ~80
        s.add(p("Jarell Quansah", PlayerPositions.CB, 59, 59, 59, 34, 74));       // OVR ~75

        // -------- FB / WB --------
        s.add(p("Trent Alexander-Arnold", PlayerPositions.RB, 90, 90, 90, 72, 80)); // OVR ~86
        s.add(p("Conor Bradley", PlayerPositions.RB, 66, 66, 66, 59, 70));          // OVR ~75
        s.add(p("Andy Robertson", PlayerPositions.LB, 82, 82, 82, 61, 81));         // OVR ~85
        s.add(p("Kostas Tsimikas", PlayerPositions.LB, 74, 74, 74, 57, 73));        // OVR ~77

        // -------- Midfield --------
        s.add(p("Wataru Endo", PlayerPositions.CDM, 71, 71, 71, 68, 79));           // OVR ~80
        s.add(p("Alexis Mac Allister", PlayerPositions.CM, 85, 85, 85, 82, 76));    // OVR ~86
        s.add(p("Dominik Szoboszlai", PlayerPositions.CAM, 84, 84, 84, 80, 59));    // OVR ~81
        s.add(p("Curtis Jones", PlayerPositions.CM, 75, 75, 75, 71, 70));           // OVR ~79
        s.add(p("Harvey Elliott", PlayerPositions.CM, 77, 77, 77, 71, 53));         // OVR ~78
        s.add(p("Ryan Gravenberch", PlayerPositions.CM, 77, 77, 77, 76, 68));       // OVR ~78
        s.add(p("Stefan Bajcetic", PlayerPositions.CDM, 74, 74, 74, 58, 67));       // OVR ~74

        // -------- Wingers / Forwards --------
        s.add(p("Mohamed Salah", PlayerPositions.RW, 82, 82, 82, 87, 45));          // OVR ~89
        s.add(p("Luis Díaz", PlayerPositions.LW, 75, 75, 75, 80, 39));              // OVR ~84
        s.add(p("Diogo Jota", PlayerPositions.ST, 75, 75, 75, 82, 57));             // OVR ~85
        s.add(p("Cody Gakpo", PlayerPositions.CF, 78, 78, 78, 83, 48));             // OVR ~83
        s.add(p("Darwin Núñez", PlayerPositions.ST, 72, 72, 72, 80, 46));           // OVR ~82
        s.add(p("Ben Doak", PlayerPositions.RW, 66, 66, 66, 74, 55));               // depth

        return s;
    }

    private static Player p(String name, PlayerPositions pos,
                            int shortPass, int longPass, int vision,
                            int finishing, int defending) {
        return new Player(
                name,
                TeamName.LIVERPOOL,
                pos,
                new VectorTwoDim(0, 0),   // pitch location not needed for selection demo
                shortPass, longPass, vision, finishing, defending
        );
    }
}
