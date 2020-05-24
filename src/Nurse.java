import java.util.Scanner;
import java.util.ArrayList;
public class Nurse extends Villager
{
    public Nurse(String name, boolean status)
    {
        super(name, status);
    }
    @SuppressWarnings("resource")
    public Player save(String save, ArrayList <Player> players)
    {
        Scanner c = new Scanner(System.in);
        while(true)
        {
            System.out.println("Who would you like to save?");
            save = c.nextLine();
            for(int i = 0; i<players.size();i++)
            {
            	if(save.equals(players.get(i).getName()) && players.get(i).getStatus())
            	{
            		return players.get(i);
            	}
            }
            System.out.println("Player doesn't exist or is already dead. Try again");
        }
    }
}
