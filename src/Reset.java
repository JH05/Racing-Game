/**
 * @author 1332852
 */
import javax.swing.JOptionPane;

/***
 * Reset class
 */
public class Reset
{
	// creates an object called two players that is called to car class.
	private Car[] twoPlayers;
	
	// creates a string called message.
	String resetMessage;
	
	// reset method.
    public Reset()
	{ 

	}
	
    // reset game method used to set the cars back to their original/default positions after a collision.
	public void ResetGame()
	{
		// uses the string reset message to print out a message.
		resetMessage = "You've crashed!\nWould you like to start again?";
		
		// creates an integer to easy to pop up a standard dialog box that prompts
		// the user for a value or informs them of the error message.
		// Brings up a dialog with the options: Yes, No and Cancel.
		int userOption = JOptionPane.showConfirmDialog(null, resetMessage);
	        
		// if the user option is equal to 0, or yes.
		if (userOption == 0) 
		{
			// creates an array of car array that call the car class.
			Car[] carArray;
			
			// creates an integer called car array length that gets the two players length that has 
			// been set by the car array.
			int carArrayLength = (carArray = this.twoPlayers).length; 
	            
			// creates a for loop that gets the car array length and if it is less than 
			for (int index = 0; index < carArrayLength; index++) 
			{ 
				// sets the current player to the cars array index.
				Car currentPlayer = carArray[index];
				
				// resets the cars positions on the track/map.
				currentPlayer.resetCarPositions();
			}
		}
		else  
			// terminates the currently running java virtual machine. 
			System.exit(0); 
	}
}