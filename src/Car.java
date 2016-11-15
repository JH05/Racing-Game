/**
 * @author JH05
 */
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.ImageIcon;

/***
 * Car class
 */
@SuppressWarnings("serial")
public class Car implements Serializable 
{
    public int maximumSpeedAllowed;
    public int currentCarSpeed = 0;
    public int x, y, startingX, startingY, carIdentifier;
    public static final int WASD = 0;
    public static final int ARROW = 1;
    private ArrayList <Integer> userKeys;
    private InputKeys inputKeys;
    private static final String[] DEFAULT_CARS = { "Red.jpg", "Blue.jpg" };
    private static final int[][] SET_STARTING_POSITION = { { 380, 550 }, { 380, 500 } };
    private ArrayList <ImageIcon> carImagesArray;
    private ImageIcon currentCarImage;
    private Rectangle collisionHitBox;
    private double currentCarAngle = 180.0D;
    
    // Method to be used to get the starting image, the x-coordinates and the y-coordinates coordinates
    // it will also be used to get which input keys each player will be using [0 or 1]
    public Car(String getImageLocation, int x, int y, int inputKeys) 
    {
    	getCar(getImageLocation, x, y, inputKeys);
    }
    
    // Method used to set the car to the default position.
    // sets the starting positions of the cars based on the users input keys [0 or 1]/
    // the method will then car the getCar method to get the car images.
    public Car(int playerNumber, int inputKeys) 
    {
        this.carIdentifier = playerNumber;
        String imagePath = DEFAULT_CARS[playerNumber];
        int xPOS = SET_STARTING_POSITION[playerNumber][0];
        int yPOS = SET_STARTING_POSITION[playerNumber][1];
        getCar(imagePath, xPOS, yPOS, inputKeys);
    }

    /***
     * Car Section
     ***/
    
    // Method used to get the car images and loop them through the Image Icon array list.
    private void getCarImages(String getImageLocation) 
    {    	
        String getNameOfFile = getImageLocation.split(".jpg")[0];
        this.carImagesArray = new ArrayList<ImageIcon>(16);
        
        for (int index = 0; index < 16; index++) 
        {
            int imageNumber = index + 1;
            String imageFileName = getNameOfFile + "_" + imageNumber + ".jpg";
            URL imagePath = getClass().getClassLoader().getResource("Resources/" + imageFileName);
            this.carImagesArray.add(new ImageIcon(imagePath));
        }        
    }
    
    // Method used to render the car images.
    public void renderCars(Graphics g) 
    {
        g.drawImage(this.currentCarImage.getImage(), this.x, this.y, null);
    }
    
    // Method to set the cars image path, the x-coordinates, y-coordinates and the players input keys, which is used to get which
    // player they are, either player 1 [0], or player 2 [1].
	private void setCar(String imagePath, int xAxis, int yAxis, int inputKeys) 
    {
        this.inputKeys = new InputKeys(inputKeys);
        this.userKeys = new ArrayList<Integer>();
        getCarImages(imagePath);
        setCarImage((ImageIcon) this.carImagesArray.get(4));
        this.x = xAxis;
        this.y = yAxis;
        this.startingX = xAxis;
        this.startingY = yAxis;
        this.collisionHitBox = new Rectangle(this.x, this.y, 30, this.currentCarImage.getIconHeight());
    }
    
	// Method used to get the car.
    public void getCar(String imagePath, int x, int y, int inputKeys)
    {
    	this.setCar(imagePath, x, y, inputKeys);
    }
    
    // Method used to reset the car position, this method will be used if the two players crash into each other.
    public void resetCarPositions() 
    {
        this.userKeys.clear();
        this.x = this.startingX;
        this.y = this.startingY;
        this.currentCarSpeed = 0;
        this.currentCarAngle = 180.0D;
        this.collisionHitBox = new Rectangle(this.x, this.y, this.currentCarImage.getIconWidth(), this.currentCarImage.getIconHeight());
        setCarImage((ImageIcon) this.carImagesArray.get(4));
    }
    
    // Method to get the hit box bounds used for the car(s).
    public Rectangle getHitBoxBound() 
    {
        return this.collisionHitBox;
    }
    
    // Method used to set the maximum speed allowed.
    public void setMaximumSpeedAllowed() 
    {
        maximumSpeedAllowed = 100;
    }

    // Method used to get the maximum speed.
    public void getMaximumSpeedAllowed(int carSpeed)
    {
    	this.maximumSpeedAllowed = carSpeed;
    }
    
    /***
     * Movement Section
     ***/
    
    // Method used to set the car's rotation from the current car images array.
    private void setCarRotation(int rotation) 
    {
        int index = this.carImagesArray.indexOf(this.currentCarImage);
        if ((rotation < 0) && (index == 0)) { index = 16; }
        if ((rotation > 0) && (index == 15)) { index = -1; }
        this.currentCarImage = ((ImageIcon) this.carImagesArray.get(index + rotation));
    }
    
    // Method used to move the car around the screen.
    private void carMovement() 
    {
        double carDistance = this.currentCarSpeed * 0.1D;
        this.x = ( (int) ( this.x - carDistance * Math.cos ( Math.toRadians ( this.currentCarAngle ) ) ) );
        this.y = ( (int) ( this.y - carDistance * Math.sin ( Math.toRadians ( this.currentCarAngle ) ) ) );
        this.x = (this.x >= 755 ? 754 : this.x);
        this.y = (this.y >= 551 ? 550 : this.y);
        this.x = (this.x <= 49 ? 50 : this.x);
        this.y = (this.y <= 100 ? 99 : this.y);
        this.collisionHitBox.x = this.x;
        this.collisionHitBox.y = this.y;
    }
    
    // Method to set the cars acceleration.
    private void accelerateCar() 
    {
        this.currentCarSpeed = (this.currentCarSpeed >= this.maximumSpeedAllowed ? this.maximumSpeedAllowed : this.currentCarSpeed + 10);
    }
    
    // Method used to reverse the car.
    private void reverseCar() 
    {
        this.currentCarSpeed = (this.currentCarSpeed == this.maximumSpeedAllowed - 200 ? this.maximumSpeedAllowed - 200 : this.currentCarSpeed - 10);
    }
    
    // Method used to turn the car left.
    private void turnCarLeft() 
    {
    	setCarRotation(-1);
        this.currentCarAngle -= 22.5D;
    }
    
    // Method used to turn the car right.
    private void turnCarRight() 
    {
    	setCarRotation(1);
        this.currentCarAngle += 22.5D;
    }
    
    // Method used to set the car image.
    private void setCarImage(ImageIcon carImage) 
    {
        this.currentCarImage = carImage;
    }
    
    // Method used handle if the key has been pressed.
    public void keyPressed(int inputKey) 
    {
        if (!this.userKeys.contains(inputKey)) 
        {
            this.userKeys.add(inputKey);
        }
    }
    
    // Method used to hand if the key has been released.
    public void keyReleased(int inputKey) 
    {
        int index = this.userKeys.indexOf(inputKey);
        
        if (index != -1) 
        {
            this.userKeys.remove(index);
            
            if (inputKey == this.inputKeys.UP || inputKey == this.inputKeys.DOWN)
            {
                this.currentCarSpeed = 0;
            }
        }
    }
    
    // Method used to handle the active keys.
    public void activeKeys() 
    {
        for (Iterator<Integer> index = this.userKeys.iterator(); index.hasNext();) 
        {
            int inputKey = ((Integer) index.next());
            if (inputKey == this.inputKeys.UP) { accelerateCar(); } 
            if (inputKey == this.inputKeys.DOWN) { reverseCar(); } 
            if (inputKey == this.inputKeys.LEFT) { turnCarLeft(); } 
            if (inputKey == this.inputKeys.RIGHT) { turnCarRight(); }
        }
        
        carMovement();
    }    
}

/***
 * InputKeys class is used to get the required input keys that the user will have to press to get the car moving.
 * e.g. 'w', or the up arrow key will accelerate the car forwards.
 * This is achieved by using an array or an array of integers which store these key values.
 * Which then is stored in public integers used in the Car class file.
 */
@SuppressWarnings("serial")
class InputKeys implements Serializable
{
    public int UP;
    public int DOWN;
    public int LEFT;
    public int RIGHT;
    
    private final int[][] setofKeys = {{ 87, 83, 68, 65 }, { 38, 40, 39, 37 } };
    
    // Used to set the Input key to the corresponding set of keys.
    // The first array is used to get the users input key 
	// and the second array is used to get the corresponding key in the setOfKeys.
    public InputKeys(int inputKeys)
    {
        this.UP = this.setofKeys[inputKeys][0];
        this.DOWN = this.setofKeys[inputKeys][1];
        this.RIGHT = this.setofKeys[inputKeys][2];
        this.LEFT = this.setofKeys[inputKeys][3];
    }
}