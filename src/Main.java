/**
 *
 * @author 1332852
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

/***
 * Main class
 */
@SuppressWarnings("serial")
public class Main extends JFrame implements ActionListener
{
    // creates a game class object called start game.
    private final Game startGame;
    
    // creates a play class object called play game.
    private final Play playGame;
    
    // main method
    public static void main(String[] args)
    {
    	// creates a new main method called main.
        Main main = new Main();
        
        // Shows this window.
        main.setVisible(true);
    }    
    
    // method called main
    public Main()
    {
        // Sets the frames title.
        setTitle("Game");
        
        // Sets the JFrame size to 200x200.
        setSize(200, 200);
        
        // Exits the application using the System exit method.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Calls the Map class.
        Map raceTrack = new Map();
        
        // creates a new constructor.
        this.playGame = new Play(this);
        
        // creates a new constructor.
        this.startGame = new Game(raceTrack);
        
        // Key events is used to indicate when the player is typing at the keyboard.
        this.startGame.addKeyListener(this.startGame);
        
        // Makes the component focusable.
        this.startGame.setFocusable(true);    
        
        // adds the component to the end of this container.
        add(this.playGame);
    }
     
	@Override
	// invoked when an action occurs.
	// action event is used to indicate that a component-defined action occurred.
    public void actionPerformed(ActionEvent e)
    {    	
		//implementation of a push button called play button.
        JButton startGame = this.playGame.playBtn();
        
        // creates a new JFrame with the title: Game.
        JFrame frame = new JFrame("Game");
        
        // resizes the frame so that it has 850 width and 650 height.
        frame.setSize(850, 650);
        
        // exit the application using the System exit method.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // adds the component to the end of this container.
        frame.add(this.startGame);       
        
        // object on which the Event initially occurred.
        if (e.getSource() == startGame)
        {
        	// creates a local game.
            Local local = new Local(this, frame, this.startGame);
            
            // calls the playLocalGame method in the local class.
            local.play();
        }
    }
}
