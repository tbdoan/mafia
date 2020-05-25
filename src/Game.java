import java.util.*;
import static java.util.Collections.shuffle;

public class Game {
    private static ArrayList<Player> players = new ArrayList<>();
    private static ArrayList<Player> spectators = new ArrayList<>();

    private static int mafiaIndex;
    private static int nurseIndex;
    private static int detectiveIndex;

    private static String PLAYER_MESSAGE = "Who do you want to mob?";

    public Game() {
        setUp();
    }

    /**
     * game flow
     */
    public void run() {
        while(gameState() == 3) {
            //mafia chooses a person to kill
            printNames(0, mafiaIndex, "Your mafia are: ");
            Player target = genericVote(0, mafiaIndex,
                    "who would you like to murder?");
            if(target == null) {
                System.out.println(
                        "The mafia have chosen not to kill tonight.");
            } else {
                System.out.println("The mafia have chosen to kill " + target.getName());
            }

            //nurse chooses a person to save
            printNames(mafiaIndex, nurseIndex, "Your nurses are: ");
            Player patient = genericVote(mafiaIndex, nurseIndex,
                    "who would you like to save?");
            if(patient == null) {
                System.out.println(
                        "The nurses have chosen not to save anyone tonight.");
            } else {
                System.out.println("The nurses have chosen to save " + patient.getName());
            }

            //detective chooses a person to investigate
            printNames(nurseIndex, detectiveIndex, "Your detectives are: ");
            Player suspect = genericVote(nurseIndex, detectiveIndex,
                    "who would you like to investigate?");
            if(suspect == null) {
                System.out.println(
                        "The detectives are too busy pigging on donuts " +
                                "tonight");
            } else {
                System.out.println(suspect.getName() + " is a " + suspect.getClass().getSimpleName());
            }

            if(target == null) {
                System.out.println("The mafia has did not kill last night");
            }
            if(target.equals(patient)) {
                System.out.println("The mafia tried to kill " + target.getName()
                        + " but they were saved.");
            } else {
                target.setStatus(false);
                System.out.println(target.getName() + " has been killed in " +
                        "the night");
            }

            System.out.println("Citizens, here is your chance to strike back");
            //citizens choose a person to mob
            Player mobbed = genericVote(0, players.size(),
                    "who would you like to mob?");
            if(mobbed == null) {
                System.out.println("The citizens have decided they are not " +
                        "angry");
            }
            System.out.println("The citizens have decided to kill " + mobbed.getName());

        }
        if(gameState() == 1) {
            System.out.println("Citizens Win!");
        } else {
            System.out.println("Mafia Win!");
        }
    }

    /**
     * Counts votes from all Players and determines person   to kill
     *
     * @return - name of loser
     */
    public Player genericVote(int start, int end, String message) {
        Map<Player,Integer> votes =
                new HashMap<>();
        for (int i = start; i < end; i++) {
            if(players.get(i).getStatus()) {
                Player vote = players.get(i).vote(players,
                        players.get(i).getName() + ", " + message);
                if(vote != null) {
                    String voteName = vote.getName();
                    if (votes.keySet().contains(voteName)) {
                        votes.put(vote, votes.get(voteName) + 1);
                    } else {
                        votes.put(vote, 1);
                    }
                }
            }
        }
        return countVotes(votes);
    }

    public void printNames(int start, int end, String message) {
        System.out.println(message);
        for (int i = start; i < end; i++) {
            if(players.get(i).getStatus()) {
                System.out.print(players.get(i).getName() + " ");
            }
        }
        System.out.println();
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
            } else if(names.contains(s)) {
                System.out.println("Name already taken");
            } else{
                names.add(s);
            }
        }

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

    /**
     * helper method to count who has most votes
     *
     * @param votes - Map of votes
     * @return - preson with most votes
     */
    private Player countVotes(Map<Player, Integer> votes) {
        // Traverse through map to find the candidate with maximum votes.
        int maxValueInMap = 0;
        Player loser = null;
        for (Map.Entry<Player,Integer> entry : votes.entrySet()) {
            Player key  = entry.getKey();
            Integer val = entry.getValue();
            if (val >= maxValueInMap) {
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
        boolean mafiaAlive = false;
        boolean citizenAlive = false;
        for (int i = 0; i < mafiaIndex; i++) {
            if(players.get(i).getStatus()) {
                mafiaAlive = true;
                break;
            }
        }
        for (int i = mafiaIndex; i < (players.size()); i++) {
            if(players.get(i).getStatus()) {
                citizenAlive = true;
                break;
            }
        }

        if(mafiaAlive && citizenAlive) {
            return 3;
        } else if (!citizenAlive) {
            return 2;
        } else {
            return 1;
        }
    }

}
