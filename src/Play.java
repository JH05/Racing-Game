/**
 * @author 1332852
 */
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
 
/***
 * Play class
 */
@SuppressWarnings("serial")
public class Play extends JPanel
{
	// creates a JButton called play game button - will be used to start the game.
	private JButton playGameButton;
		
	// play method that uses an action listener to check if the button has been pressed.
	public Play(ActionListener e)
	{
		// sets the layout manager for this container.
	    setLayout(null);
	    
	    // creates a new JButton object called play that uses the current object play game button.
	    this.playGameButton = new JButton("Play");
	    
	    // adds an ActionListener to the button called play game button.
	    this.playGameButton.addActionListener(e);
	    
	    // Moves and resizes this component (play game button). 
	    // The new location of the top-left corner is specified by, 
	    // x (50) and y (20), and the new size is specified by width (100) and height (40).
	    this.playGameButton.setBounds(50, 20, 100, 40);
	    
	    // adds the specified component (play game button) to the end of this container. 
	    add(this.playGameButton);
	}
	
	// method used to return the play game button and it's action listener.
	public JButton playBtn()
	{
		// returns the current object play game button.
	    return this.playGameButton;
	}
}