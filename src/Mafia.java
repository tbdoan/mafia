import java.util.Scanner;
import java.util.ArrayList;
public class Mafia extends Player
{
    public Mafia(String name, boolean status)
    {
        super(name, status);
    }
    @SuppressWarnings("resource")
    public void murder(ArrayList <String> alive,ArrayList<String> dead)
    {
        Scanner c = new Scanner(System.in);
        while(true)
        {
            System.out.println("Who would you like to kill?");
            String kill = c.nextLine();
            String temp;
            for(int n = 0; n < alive.size(); n++)
            {
            	for(String i : alive)
            	{
            		if(kill.equals(alive.get(n)));
            		{
            			temp = alive.get(n);
            			dead.add(temp);
            			alive.remove(temp);
            			break;
            		}
            	}
            }
            break;
        }
    }
}