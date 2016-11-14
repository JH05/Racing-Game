/**
 *
 * @author 1332852
 */
import java.util.TimerTask;

/***
 * Task class
 */
public class Task extends TimerTask 
{ 
	// creates an object called play game.
    private final Game playGame; 
    
    // method task that get the current object (play game) and sets it to game. 
    public Task(Game game) 
    { 
    	// sets the current objcet (play game) to game.
        this.playGame = game; 
    } 
    
    // overrides a method declaration
    @Override 
    // Method to run an action performed by this timer task.
    public void run() 
    { 
    	// handles the active keys class.
        this.playGame.handleActiveKeys(); 
        
        // repaints component.
        this.playGame.repaint(); 
    } 
}