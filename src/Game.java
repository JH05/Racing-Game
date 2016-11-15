/**
 * @author JH05
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

    private final Car[] twoPlayers;
    private final Map getMap;
    private final Collision collision;
    private final Timer timer;
    private final Reset reset;
    private final Task taskScheduled;
    
    // main game method used to get the games track.
    // also used to create new objects for each class.
    public Game(Map getMap)
    {
        this.getMap = getMap;
        this.collision = new Collision();
        this.twoPlayers = new Car[2];
        this.reset = new Reset();
        this.timer = new Timer();
        this.taskScheduled = new Task(this);
    }
    
    // method to start a new game.
    public void startNewGame() 
    { 
        this.timer.schedule(this.taskScheduled, 35L, 35L); 
    }
       
    // method to get the players and calls the car class and sets the current players.
    public void getPlayers(Car currentPlayers)
    {
        for (int index = 0; index < this.twoPlayers.length; index++)
        {
            if (this.twoPlayers[index] == null)
            {
                this.twoPlayers[index] = currentPlayers;
                break;
            }
        }
    }
    
    // method to handle player, and track collisions.
    private void collisionHandler()
    {
        for (int index = 0; index < this.twoPlayers.length; index++)
        {
            int next = (index + 1 > this.twoPlayers.length - 1 ? 0 : index + 1);
            Rectangle currentPlayer = this.twoPlayers[index].getHitBoxBound();
            Rectangle nextPlayer = this.twoPlayers[next].getHitBoxBound();
            Rectangle mapTrackGrass = this.collision.getGrassHitBox();

            if (currentPlayer.intersects(mapTrackGrass))
            { 
                this.twoPlayers[index].getMaximumSpeedAllowed(20); 
            }
            else 
            {
                this.twoPlayers[index].getMaximumSpeedAllowed(100); 
            }
            
            if (currentPlayer.intersects(nextPlayer)) 	
            {
                reset.ResetGame();
            }
        }
    }  

    // method used to get the cars array, and to get the cars array length.
    public void getCarArrayLength()
    {
        Car[] carArray;
        int carArrayLength = (carArray = this.twoPlayers).length; 
    }
    
	@Override
    // Repaint
    public void repaint()
    {
        super.repaint();

        if (this.twoPlayers != null)
        {
            collisionHandler(); 
        }
    }    
    
    @Override
    // paint component method is used to render both the track (map) and also the players cars.
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        this.getMap.renderDefaultMap(g);
        Car[] carArray;
        int carArrayLength = (carArray = this.twoPlayers).length; 
        
        for (int index = 0; index < carArrayLength; index++) 
        { 
            Car currentPlayer = carArray[index];

            if (currentPlayer != null)
            {
            	currentPlayer.renderCars(g);
            }
        }
    }
    
    /***
     * Gets Keys
     */
    
    // method used to handle the active keys
    public void handleActiveKeys()
    {
        Car[] carArray;
        int carArrayLength = (carArray = this.twoPlayers).length; 

        for (int index = 0; index < carArrayLength; index++) 
        {
            Car currentPlayer = carArray[index];
 
            if (currentPlayer != null)
            {
            	currentPlayer.activeKeys();
            }
        }
    }
    
    @Override
    // Invoked when a key has been pressed.
    public void keyPressed(KeyEvent arg0)
    {
        Car[] carArray;
        int carArrayLength = (carArray = this.twoPlayers).length; 

        for (int index = 0; index < carArrayLength; index++) 
        {
            Car currentPlayer = carArray[index];

            if (currentPlayer != null)
            {
            	currentPlayer.keyPressed(arg0.getKeyCode());
            }
        }
        
        System.out.println("Key Pressed: " + arg0.getKeyCode()); 
    }
    
    @Override
    // Invoked when a key has been released. 
    public void keyReleased(KeyEvent arg0)
    {
        Car[] carArray;
        int carArrayLength = (carArray = this.twoPlayers).length; 

        for (int index = 0; index < carArrayLength; index++) 
        {
            Car currentPlayer = carArray[index];
 
            if (currentPlayer != null)
            {
            	currentPlayer.keyReleased(arg0.getKeyCode());
            }
        }
    }
    
    @Override
    // Invoked when a key has been typed.
    public void keyTyped(KeyEvent arg0) 
    { 
    	throw new UnsupportedOperationException("Not supported yet.");
    }  
}