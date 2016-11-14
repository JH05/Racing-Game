/**
 *
 * @author 1332852
 */
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import javax.swing.JPanel;

/***
 * Game class
 */
@SuppressWarnings("serial")
public class Game extends JPanel implements KeyListener
{
	// calls the car class.
    private final Car[] twoPlayers;
    
    // calls the map class.
    private final Map getMap;
    
    // calls the collision class.
    private final Collision collision;
    
    // used to schedule tasks for future execution. 
    private final Timer timer;
    
    // calls the audio class.
    //private final Audio audio;
    
    // calls the reset class.
    private final Reset reset;
    
    // calls the scheduled task class.
    private final Task taskScheduled;
    
    // main game method used to get the games track.
    // also used to create new objects for each class.
    public Game(Map getMap)
    {
    	// gets the current object 'get map'.
        this.getMap = getMap;
        
        // gets the current object 'collision' to create a new collision object.
        this.collision = new Collision();
        
        // gets the current object 'audio' to create a new audio object.
        //this.audio = new Audio();
        
        // gets the current object 'two players' to create a new car object with the number of players (2).
        this.twoPlayers = new Car[2];
        
        // gets the current object 'reset' to create a new reset object.
        this.reset = new Reset();
        
        // creates a new timer called timer. 
        this.timer = new Timer();
        
        // gets the current object 'task scheduled' to create a new task (this current object) object.
        this.taskScheduled = new Task(this);
    }
    
    // method to start a new game.
    public void startNewGame() 
    { 
    	// current object timer is used to schedules a specified task - 35 milliseconds (35 Long).
        this.timer.schedule(this.taskScheduled, 35L, 35L); 
    }
       
    // method to get the players and calls the car class and sets the current players.
    public void getPlayers(Car currentPlayers)
    {
    	// loop the length of the current object 'two players', if the length is less than the index
        for (int index = 0; index < this.twoPlayers.length; index++)
        {
        	// if the current object two players array index is equal to null then 
            if (this.twoPlayers[index] == null)
            {
            	// set the current two players array index to car current players.
                this.twoPlayers[index] = currentPlayers;
                
                // terminates the for loop.
                break;
            }
        }
    }
    
    // method to handle player, and track collisions.
    private void collisionHandler()
    {
    	// for loop to check of the length of the two players is less than the current index.
        for (int index = 0; index < this.twoPlayers.length; index++)
        {
        	// compares the index plus two to see if it greater than the length of the two players minus one
        	// if it is true then set next to zero, otherwise add one to the current index.
            int next = (index + 1 > this.twoPlayers.length - 1 ? 0 : index + 1);
            
            // creates a rectangle called current player which gets the two players index and
            // also gets the hit box bounds.
            Rectangle currentPlayer = this.twoPlayers[index].getHitBoxBound();
            
            // creates a rectangle called next player which gets the two players next (created above)
            // to get the hit box bounds of the cars.
            Rectangle nextPlayer = this.twoPlayers[next].getHitBoxBound();
            
            // creates a rectangle called map track grass which gets the grass collision hit box.
            Rectangle mapTrackGrass = this.collision.getGrassHitBox();
            
            // if the current player intersects (determines whether or not this Rectangle and the specified Rectangle intersect) 
            // the map tack grass then
            if (currentPlayer.intersects(mapTrackGrass))
            { 
            	// sets the players speed to 20 by using the get maximum speed allowed class.
                this.twoPlayers[index].getMaximumSpeedAllowed(20); 
            }
            else 
            	// else it sets the players speed to 100 by getting the maximum speed allowed class in car.
                this.twoPlayers[index].getMaximumSpeedAllowed(100); 
            
            // if the current player intersects the next player
            if (currentPlayer.intersects(nextPlayer)) 	
            	// reset the game.
                reset.ResetGame();
        }
    }  

    // method used to get the cars array, and to get the cars array length.
    public void getCarArrayLength()
    {
    	// gets the array of cars that has been created in the car class.
        Car[] carArray;
        
        // creates an integer called car array length that gets the two players car array length.
        // which has been created above.        
        int carArrayLength = (carArray = this.twoPlayers).length; 
    }
    
	@Override
    // Repaint
    public void repaint()
    {
		// repaints this [repaint] component.
        super.repaint();
        
        // if the two player is equal to null then
        if (this.twoPlayers != null)
        	// call the collision handler.
            collisionHandler(); 
    }    
    
    @Override
    // paint component method is used to render both the track (map) and also the players cars.
    protected void paintComponent(Graphics g)
    {
    	// repaints this [paint component] component.
        super.paintComponent(g);
        
        // gets the default map (track) from the map class.
        this.getMap.renderDefaultMap(g);
        
        // gets the array of cars that has been created in the car class.
        Car[] carArray;
        
        // creates an integer called car array length that gets the two players car array length.
        // which has been created above.
        int carArrayLength = (carArray = this.twoPlayers).length; 
        
        // a for loop which gets the car array length and check to see if the current index is less than
        // the car array length.
        for (int index = 0; index < carArrayLength; index++) 
        { 
        	// sets the current player to the cars array index.
            Car currentPlayer = carArray[index];
            
            // if the current player does not equal null then
            if (currentPlayer != null)
            	// render the cars via current player.
            	currentPlayer.renderCars(g);
        }
    }
    
    /***
     * Gets Keys
     */
    
    // method used to handle the active keys
    public void handleActiveKeys()
    {
    	// gets the array of cars that has been created in the car class.
        Car[] carArray;
        
        // creates an integer called car array length that gets the two players car array length.
        // which has been created above.        
        int carArrayLength = (carArray = this.twoPlayers).length; 
        
        // a for loop which gets the car array length and check to see if the current index is less than
        // the car array length.        
        for (int index = 0; index < carArrayLength; index++) 
        { 
        	// sets the current player to the cars array index.        	
            Car currentPlayer = carArray[index];
 
            // if the current player does not equal null then
            if (currentPlayer != null)
            	// gets the current player to call the active keys class.
            	currentPlayer.activeKeys();
        }
    }
    
    @Override
    // Invoked when a key has been pressed.
    public void keyPressed(KeyEvent arg0)
    {
    	// gets the array of cars that has been created in the car class.
        Car[] carArray;
        
        // creates an integer called car array length that gets the two players car array length.
        // which has been created above.        
        int carArrayLength = (carArray = this.twoPlayers).length; 
        
        // a for loop which gets the car array length and check to see if the current index is less than
        // the car array length.           
        for (int index = 0; index < carArrayLength; index++) 
        { 
        	// sets the current player to the cars array index.            	
            Car currentPlayer = carArray[index];
 
            // if the current player does not equal null then
            if (currentPlayer != null)
            	
            	// gets the current player to return the integer keyCode associated with the key in this event.     	
            	currentPlayer.keyPressed(arg0.getKeyCode());
        }
        
        // prints out the key that has been pressed to the console.
        // used to debug purposes.
        System.out.println("Key Pressed: " + arg0.getKeyCode()); 
    }
    
    @Override
    // Invoked when a key has been released. 
    public void keyReleased(KeyEvent arg0)
    {
    	// gets the array of cars that has been created in the car class.
        Car[] carArray;
        
        // creates an integer called car array length that gets the two players car array length.
        // which has been created above.        
        int carArrayLength = (carArray = this.twoPlayers).length; 
        
        // a for loop which gets the car array length and check to see if the current index is less than
        // the car array length.           
        for (int index = 0; index < carArrayLength; index++) 
        { 
        	// sets the current player to the cars array index.            	
            Car currentPlayer = carArray[index];
 
            // if the current player does not equal null then
            if (currentPlayer != null)
            	
            	// gets the current player to return the integer keyCode associated with the key in this event.    
            	currentPlayer.keyReleased(arg0.getKeyCode());
        }
    }
    
    @Override
    // Invoked when a key has been typed.
    public void keyTyped(KeyEvent arg0) 
    { 
    	// constructs an unsupported operation exception with the specified detail message.
    	throw new UnsupportedOperationException("Not supported yet.");
    }  
}