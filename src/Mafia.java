import java.util.Scanner;
import java.util.ArrayList;
public class Mafia extends Player
{
    public Mafia(String name, boolean status)
    {
        super(name, status);
    }
    @SuppressWarnings( "resource")
    public Player murder(ArrayList <Player> players)
    {
        Scanner c = new Scanner(System.in);
        while(true)
        {
            System.out.println("Who would you like to kill?");
            String kill = c.nextLine();
            for(int i = 0; i<players.size();i++)
            {
            	if(kill.equals(players.get(i).getName())&&players.get(i).getStatus());
            	{
            		return players.get(i);
            	}
            }
            System.out.println("Player doesn't exist or is already dead. Try again");
        }
    }
}