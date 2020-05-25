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

	/**
	 * prompts for a player and returns it if valid
	 *
	 * @param players - list of players
	 * @param message - custom message
	 * @return
	 */
	public Player vote(ArrayList<Player> players, String message)
	{
		Scanner c = new Scanner(System.in);
		while(true) {
			System.out.println(message);
			String vote = c.nextLine();
			for(int i = 0; i<players.size(); i++) {
				Player currPlayer = players.get(i);
				//if the player exists and is alive
				if(vote.equals(currPlayer.getName())
						&& currPlayer.getStatus()) {
					return currPlayer;
				} else if(vote.equals("")) {
					return null;
				}
			}
			System.out.println("Invalid Name");
		}
	}

	public boolean equals(Player player) {
		if(player == null) {
			return false;
		} else if(getName().equals(player.getName())) {
			return true;
		} else {
			return false;
		}
	}
}
