import java.util.Scanner;
import java.util.ArrayList;
public class Nurse extends Villager
{
    public Nurse(String name, boolean status)
    {
        super(name, status);
    }
    @SuppressWarnings("resource")
    public void save(String save, int n, int i, ArrayList <String> alive,ArrayList<String> dead)
    {
        Scanner c = new Scanner(System.in);
        System.out.println("Who would you like to save?");
        save = c.nextLine();

        while(n=0)
        {
            for(i = 0; i < alive.size(); i++)
            {
                if(save.equals(dead.get(i)))
                {
                    alive.add(dead.get(i));
                    dead.remove(i);
                    n++;
                }
            }
        }
    }
}