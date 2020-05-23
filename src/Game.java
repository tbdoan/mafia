import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Game {
    private static ArrayList<Player> players = new ArrayList<>();

    public Game() {
        setUp();
    }

    /**
     * Gets all the names of players from command line input and creates
     * Player objects from the names
     */
    private void setUp() {
        Scanner in = new Scanner(System.in);
        ArrayList<String> names = new ArrayList<>();
        while(true) {
            System.out.println("Enter a Name or Type Done");
            String s = in.nextLine();
            if(s.compareTo("Done") == 0 || s.compareTo("") == 0) {
                break;
            } else {
                names.add(s);
            }
        }
        for (int i = 0; i < names.size(); i++) {
            players.add(new Player(names.get(i), true));
        }
    }

    /**
     * Counts votes from all Players and determines person to kill
     *
     * @return - name of loser
     */
    public String countVotes() {
        Map<String,Integer> votes =
                new HashMap<>();
        for (Player player : players) {
            String name = player.voteDaytime(players);
            if (votes.keySet().contains(name))
                votes.put(name, votes.get(name) + 1);
            else
                votes.put(name, 1);
        }

        // Traverse through map to find the candidate with maximum votes.
        int maxValueInMap = 0;
        String loser = null;
        for (Map.Entry<String,Integer> entry : votes.entrySet()) {
            String key  = entry.getKey();
            Integer val = entry.getValue();
            if (val > maxValueInMap) {
                maxValueInMap = val;
                loser = key;
            }
        }
        return loser;
    }



    //TODO: Implement roles. Right now, everyone is a generic player
    private static void assignRoles(ArrayList<String> names) {
        int numMafia = (int) Math.floor(names.size() / 3);
        int numNurses = (int) Math.floor(2 * numMafia / 3);
        int numDetectives = numNurses;

    }
}
