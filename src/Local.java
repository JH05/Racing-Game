/**
 *
 * @author 1332852
 */
import javax.swing.JFrame;

/***
 * Local class
 */
public class Local
{
	// creates the frame main.
    private final JFrame main;
    
    // creates the frame game.
    private final JFrame game;
    
    // calls the game class.
    private final Game play;
    
    // main local method used to get the main and game frame as well as call the game class.
    public Local(JFrame main, JFrame game, Game play)
    {
    	// sets the current object (main) to local main.
        this.main = main;
        
        // sets the current object (game) to local game.
        this.game = game;        
        
        // sets the current object (play) to local play.
        this.play = play;
        
        // creates a new car object called player one which sets the first car (Red) 
        // and sets the x and y axis. It finally sets the keys the user will use 1 [ARROW].
        Car playerTwo = new Car("Red.jpg", 380, 550, 1);
        
        // creates a new car object called player one which sets the first car (Blue) 
        // and sets the x and y axis. It finally sets the keys the user will use 0 [WASD].
        Car playerOne = new Car("Blue.jpg", 380, 500, 0);
        
        // sets the current object (play) to call the get players method in game and sets
        // current players to player one.
        this.play.getPlayers(playerOne);
        
        // sets the current object (play) to call the get players method in game and sets
        // current players to player two.
        this.play.getPlayers(playerTwo);
        
        // adds the specified key listener to receive key events from play.
        this.play.addKeyListener(this.play);
        
        // sets the focusable state of play to the specified value (true).
        this.play.setFocusable(true);
    }
    
    // play method used to hide the main JFrame, and show the game frame.
    // the method will also call start new game from the game class.
    public void play()
    {
    	// hides this (main) frame.
        this.main.setVisible(false);
        
        // shows this (game) frame.
        this.game.setVisible(true);
        
        // calls the start new game class from game.
        this.play.startNewGame();
    }
}