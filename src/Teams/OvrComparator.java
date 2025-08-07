package Teams;

import Players.Player;

import java.util.Comparator;

public class OvrComparator implements Comparator<Player> {

    @Override
    public int compare(Player one, Player two) {
        if(one.getOvr() == two.getOvr())
            return 0;
        else if(one.getOvr() < two.getOvr())
            return 1;
        else if(one.getOvr() > two.getOvr())
            return -1;
        else
            return 0;
    }
}
