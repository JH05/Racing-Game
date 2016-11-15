/**
 * @author JH05
 */
import javax.swing.JOptionPane;

/***
 * Reset class
 */
public class Reset
{
	private Car[] twoPlayers;
	String resetMessage;
	
	// reset method.
    public Reset() { }
	
    // reset game method used to set the cars back to their original/default positions after a collision.
	public void ResetGame()
	{
		resetMessage = "You've crashed!\nWould you like to start again?";
		int userOption = JOptionPane.showConfirmDialog(null, resetMessage);

		if (userOption == 0) 
		{
			Car[] carArray;
			int carArrayLength = (carArray = this.twoPlayers).length; 

			for (int index = 0; index < carArrayLength; index++) 
			{ 
				Car currentPlayer = carArray[index];
				currentPlayer.resetCarPositions();
			}
		}
		else  
		{
			System.exit(0); 
		}
	}
}