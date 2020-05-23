import java.util.Scanner;
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
	public String voteDaytime(String [] player, String vote, int i, int k)
	{
		Scanner c = new Scanner(System.in);
		k = 0;
		while(k<1)
		{
			System.out.println("Who do you vote to kill?");
			vote = c.nextLine();
			for(i = 0; i<player.length; i++)
			{
				if(vote.equals(player[i]))
				{
					k++;
				}
				else
				{
					System.out.println("Sorry, the name typed is invalid. Please try again");
				}
			}
		}
		return vote;
	}
}
