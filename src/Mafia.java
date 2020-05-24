import java.util.Scanner;
import java.util.ArrayList;
public class Mafia extends Player
{
    public Mafia(String name, boolean status)
    {
        super(name, status);
    }

    @Override
    public Player vote(ArrayList<Player> players, String message) {
        return super.vote(players, "Who do you want to kill?");
    }
}