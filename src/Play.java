/**
 * @author JH05
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
	private JButton playGameButton;
		
	// play method that uses an action listener to check if the button has been pressed.
	public Play(ActionListener e)
	{
	    setLayout(null);
	    this.playGameButton = new JButton("Play");
	    this.playGameButton.addActionListener(e);
	    this.playGameButton.setBounds(50, 20, 100, 40);
	    add(this.playGameButton);
	}
	
	// method used to return the play game button and it's action listener.
	public JButton playBtn()
	{
	    return this.playGameButton;
	}
}