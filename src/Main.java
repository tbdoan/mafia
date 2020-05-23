import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<Player> players = new ArrayList<>();
    private static ArrayList<String> setUp() {
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
        return names;
    }
    private void assignRoles(ArrayList<String> names) {
        int numMafia = (int) Math.floor(names.size() / 3);
        int numNurses = (int) Math.floor(2 * numMafia / 3);
        int numDetectives = numNurses;
        for (int i = 0; i < names.size(); i++) {
            if(i < numMafia) {
                players.add(new Mafia(names[i], true));
            } else if(i < numNurses) {
                players.add(new Nurse(names[i], true));
            } else if(i < numDetectives)

        }
    }
    public static void main(String args[]) {
        ArrayList<String> names = setUp();



    }

}
