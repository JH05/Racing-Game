/**
 * @author JH05
 */
import java.util.TimerTask;

/***
 * Task class
 */
public class Task extends TimerTask 
{ 
    private final Game playGame; 
    
    // method task that get the current object (play game) and sets it to game. 
    public Task(Game game) 
    { 
        this.playGame = game; 
    } 
    
    @Override 
    // Method to run an action performed by this timer task.
    public void run() 
    { 
        this.playGame.handleActiveKeys(); 
        this.playGame.repaint(); 
    } 
}