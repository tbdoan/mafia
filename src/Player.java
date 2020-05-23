import java.util.*;

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
	public String voteDaytime(ArrayList<Player> players)
	{
		Scanner c = new Scanner(System.in);
		while(true) {
			System.out.println("Who do you vote to kill?");
			String vote = c.nextLine();
			for(int i = 0; i<players.size(); i++) {
				//if the player exists and is alive
				if(vote.equals(players.get(i).getName())
						&& players.get(i).getStatus()) {
					return vote;
				}
			}
			System.out.println("Player doesn't exist or is already dead. Try" +
					" again");
		}
	}
}
