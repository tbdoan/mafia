import java.util.Scanner;
import java.util.ArrayList;
public class Player 
{
	protected String name;
	protected boolean status;
	
	public Player(String name, boolean status)
	{
		this.name = name;
		this.status = status;
	}
	
	public String getName()
	{
		return name;
	}
	public boolean getStatus()
	{
		return status;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public void setStatus(boolean status)
	{
		this.status = status;
	}
	@SuppressWarnings("resource")
	public String voteDaytime(String vote, int n, int i, ArrayList<String> alive)
	{
		Scanner c = new Scanner(System.in);
		while(k=0)
		{
			System.out.println("Who do you vote to kill?");
			vote = c.nextLine();
			for(i = 0; i<alive.size(); i++)
			{
				if(vote.equals(alive.get(i)))
				{
					k++;
				}
            }
            System.out.println("Error, please try again.");
        }
        return vote;
	}
}
