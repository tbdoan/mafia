import java.util.Scanner;
import java.util.ArrayList;
public class Mafia extends Player
{
    public Mafia(String name, boolean status)
    {
        super(name, status);
    }
    public void murder(String kill, int n, int i, ArrayList <String> alive,ArrayList<String> dead)
    {
        Scanner c = new Scanner(System.in);
        while(n=0)
        {
            System.out.println("Who would you like to kill?");
            kill = c.nextLine();
            for(i = 0; i < alive.size(); i++)
            {
                if(kill.equals(alive.get(i)))
                {
                    dead.add(alive.get(i));
                    alive.remove(i);
                    n++;
                }
                if(!(kill.equals(alive.get(i))))
                {
                    System.out.println("Error made, please try again.");
                }
            }
        }
    }
}