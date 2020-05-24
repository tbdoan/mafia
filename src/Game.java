import java.util.*;
import static java.util.Collections.shuffle;

public class Game {
    private static ArrayList<Player> players = new ArrayList<>();
    private static ArrayList<Player> spectators = new ArrayList<>();

    private static int mafiaIndex;
    private static int nurseIndex;
    private static int detectiveIndex;



    public Game() {
        setUp();
    }

    /**
     * game flow
     */
    public void run() {

        //mafia chooses this person to kill
        String target = voteToMurder();

        //person dies and is moved to spectators
        moveToSpectators(target);

        //skip a few stages forward

        //villagers chooses and kills a person
        String mobbed = voteToMob();
        moveToSpectators(mobbed);
    }

    /**
     * Counts votes from all Players and determines person to kill
     *
     * @return - name of loser
     */
    public String voteToMob() {
        Map<String,Integer> votes =
                new HashMap<>();
        for (Player player : players) {
            String name = player.vote(players);
            if (votes.keySet().contains(name))
                votes.put(name, votes.get(name) + 1);
            else
                votes.put(name, 1);
        }

        return countVotes(votes);
    }

    /**
     * Counts votes from all Players and determines person to kill
     *
     * @return - name of loser
     */
    public String voteToMurder() {
        Map<String,Integer> votes =
                new HashMap<>();
        for (Player player : players) {
            if(player instanceof Mafia) {
                String name = player.vote(players);
                if (votes.keySet().contains(name))
                    votes.put(name, votes.get(name) + 1);
                else
                    votes.put(name, 1);
            }
        }
        return countVotes(votes);
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
     * helper method to count who has most votes
     *
     * @param votes - Map of votes
     * @return - preson with most votes
     */
    private String countVotes(Map<String, Integer> votes) {
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

    private void moveToSpectators(String target) {
        for (int i = 0; i < players.size(); i++) {
            if(players.get(i).getName().equals(target)) {
                players.remove(i);
                spectators.add(new Player(target, false));
                return;
            }
        }
    }

    /**
     * state of the game
     *
     * @return - 1 if citizens win, 2 if mafia win, 3 if game in progress
     */
    private int gameState() {


    }

    /**
     * uses list of names to randomly generate roles as well as set
     * partition indices
     *
     * @param names - list of player names
     */
    private static void assignRoles(ArrayList<String> names) {
        int numMafia = (int) Math.floor(names.size() / 3);
        int numNurse = (int) Math.floor(names.size() / 4);
        int numDetective = numNurse;

        mafiaIndex = numMafia;
        nurseIndex = mafiaIndex + numNurse;
        detectiveIndex = nurseIndex + numDetective;

        shuffle(names);
        for(int i = 0; i < names.size(); i++) {
            if(i < mafiaIndex) {
                players.add(new Mafia(names.get(i),true));
            } else if(i < nurseIndex) {
                players.add(new Nurse(names.get(i), true));
            } else if(i < detectiveIndex){
                players.add(new Detective(names.get(i), true));
            } else {
                players.add(new Citizen(names.get(i), true));
            }
        }


    }
}
