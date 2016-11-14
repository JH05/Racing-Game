/**
 *
 * @author 1332852
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
    // Sets the maximum currentCarSpeed allowed the car to go.
    public int maximumSpeedAllowed;
    
    // Sets the current car speed.
    public int currentCarSpeed = 0;
    
    // Used to get the x-coordinates and y-coordinates of the image.
    public int x, y, startingX, startingY, carIdentifier;
    
    // Used to get the WASD Keys
    public static final int WASD = 0;
    
    // Used to get the Arrows Keys
    public static final int ARROW = 1;
    
    // Integer Array List used for the user keys.
    private ArrayList <Integer> userKeys;
    
    // Gets the Input Keys
    private InputKeys inputKeys;
    
    // Sets the Default Cars.
    private static final String[] DEFAULT_CARS = { "Red.jpg", "Blue.jpg" };
    
    // Array of an array of integers which set the starting position of the cars.
    private static final int[][] SET_STARTING_POSITION = { { 380, 550 }, { 380, 500 } };
    
    // Array List used to get the car image array.
    private ArrayList <ImageIcon> carImagesArray;
    
    // Gets the current car image.
    private ImageIcon currentCarImage;
    
    // Used for the collision hit box.
    private Rectangle collisionHitBox;
    
    // Sets the current car angle.
    private double currentCarAngle = 180.0D;
    
    // Method to be used to get the starting image, the x-coordinates and the y-coordinates coordinates
    // it will also be used to get which input keys each player will be using [0 or 1]
    public Car(String getImageLocation, int x, int y, int inputKeys) 
    {
    	// gets the cars image, coordinates and the input keys the user will use.
    	getCar(getImageLocation, x, y, inputKeys);
    } // Ends Car method
    
    // Method used to set the car to the default position.
    // sets the starting positions of the cars based on the users input keys [0 or 1]/
    // the method will then car the getCar method to get the car images.
    public Car(int playerNumber, int inputKeys) 
    {
    	// Variable to store the cars identifier, either player 1 [0] or player 2 [1].
        this.carIdentifier = playerNumber;
        
        // image path is used to the get the two default cars and set them to the player's number
        // either player 1 [0], or player 2 [1].
        String imagePath = DEFAULT_CARS[playerNumber];
        
        // sets the x-coordinates starting position of the cars based on the players number which gets the coordinates 
        // from array of the array - e.g. player 1 will have an xPOS of 380.
        int xPOS = SET_STARTING_POSITION[playerNumber][0];
        
        // sets the y-coordinates of the starting cars based on the integer array of the array and the players number.
        // e.g. player 1 will have an yPOS of 550.
        int yPOS = SET_STARTING_POSITION[playerNumber][1];
        
        // gets the car's image, the two coordinates [x and y] and gets the input keys the two players will use
        // either WASD [0] or ARROWS [1].
        getCar(imagePath, xPOS, yPOS, inputKeys);
    } // Ends Car method

    /***
     * Car Section
     ***/
    
    // Method used to get the car images and loop them through the Image Icon array list.
    private void getCarImages(String getImageLocation) 
    {    	
    	// Splits the name of the file into substrings before the file extension.
        String getNameOfFile = getImageLocation.split(".jpg")[0];
        
        // create a new empty list of the 16 capacity.
        this.carImagesArray = new ArrayList<ImageIcon>(16);
        
        // for loop which gets all the car images from 1 to 16 via the loop.
        for (int index = 0; index < 16; index++) 
        {
        	// sets the image number based on the index and adds one onto it.
            int imageNumber = index + 1;
            
            // variable to get the while image file name based on getting the name of the file that was split above
            // and getting the image number from the index and adding the file extension to the end of the file.
            String imageFileName = getNameOfFile + "_" + imageNumber + ".jpg";

            // a variable called imagePath which uses a Uniform Resource Locator to get the Resources folder
            // and also gets the image file name.
            URL imagePath = getClass().getClassLoader().getResource("Resources/" + imageFileName);
            
            // adds the new image icon called image path to the car images array which will be used later to
            // select the image based on the user keyboard input.
            this.carImagesArray.add(new ImageIcon(imagePath));
        }        
    } // Ends getCarImages method
    
    // Method used to render the car images.
    public void renderCars(Graphics g) 
    {
    	// draws the current car image by getting it and also getting the x-coordinates position and y-coordinates position.
        g.drawImage(this.currentCarImage.getImage(), this.x, this.y, null);
    }  // Ends renderCars method  
    
    // Method to set the cars image path, the x-coordinates, y-coordinates and the players input keys, which is used to get which
    // player they are, either player 1 [0], or player 2 [1].
	private void setCar(String imagePath, int xAxis, int yAxis, int inputKeys) 
    {
		// creates a new input key variable which stores the users input keys, either WASD [0] or ARROWS [1].
        this.inputKeys = new InputKeys(inputKeys);
        
        // creates a new users keys to store the keys that the use will be able to use.
        this.userKeys = new ArrayList<Integer>();
        
        // gets the car image and store it in the imagePath string.
        getCarImages(imagePath);
        
        // sets the car image based on the car images array via the image icon. 
        setCarImage((ImageIcon) this.carImagesArray.get(4));
        
        // sets the x coordinates.
        this.x = xAxis;
        
        // sets the y coordinates.
        this.y = yAxis;
        
        // sets the starting x coordinates to the xAxis integer.
        this.startingX = xAxis;
        
        // sets the starting y coordinates to the yAxis integer.
        this.startingY = yAxis;
        
        // sets the collision hit box based on the x and y coordinates of the image, which also uses the get image icon height.
        this.collisionHitBox = new Rectangle(this.x, this.y, 30, this.currentCarImage.getIconHeight());
    } // Ends setCar method
    
	// Method used to get the car.
    public void getCar(String imagePath, int x, int y, int inputKeys)
    {
    	// gets the set car method.
    	this.setCar(imagePath, x, y, inputKeys);
    } // Ends getCar method
    
    // Method used to reset the car position, this method will be used if the two players crash into each other.
    public void resetCarPositions() 
    {
    	// clears the user keys.
        this.userKeys.clear();
        
        // sets the x coordinates to the starting x coordinates.
        this.x = this.startingX;
        
        // sets the y coordinates to the starting y coordinates.
        this.y = this.startingY;
        
        // sets the current car speed back to zero.
        this.currentCarSpeed = 0;
        
        // sets the current angle back to facing right (180 degrees).
        this.currentCarAngle = 180.0D;
        
        // sets the collision hit box to the cars x, and y coordinates as well as the cars height and width.
        this.collisionHitBox = new Rectangle(this.x, this.y, this.currentCarImage.getIconWidth(), this.currentCarImage.getIconHeight());
        
        // sets the car image icon back to the default one - the 4th one in the array (180 degrees).
        setCarImage((ImageIcon) this.carImagesArray.get(4));
    } // Ends resetCarPositions method
    
    // Method to get the hit box bounds used for the car(s).
    public Rectangle getHitBoxBound() 
    {
    	// returns the collision hit box which will be used for the car(s).
        return this.collisionHitBox;
    }  // Ends getHitBoxBounds method
    
    // Method used to set the maximum speed allowed.
    public void setMaximumSpeedAllowed() 
    {
    	// sets the maximum speed allowed the car can go.
        maximumSpeedAllowed = 100;
    }  // Ends setMaximumSpeedAllowed method

    // Method used to get the maximum speed.
    public void getMaximumSpeedAllowed(int carSpeed)
    {
    	// gets the maximum speed from the set method.
    	// uses an integer called car speed to change the maximum speed allowed.
    	this.maximumSpeedAllowed = carSpeed;
    } // Ends getMaximumSpeedAllowed method
    
    /***
     * Movement Section
     ***/
    
    // Method used to set the car's rotation from the current car images array.
    private void setCarRotation(int rotation) 
    {
    	// creates an integer called index which gets the index of the current car image
    	// from the car images array.
        int index = this.carImagesArray.indexOf(this.currentCarImage);
        
        // if the rotation is less than zero, or equal to zero then set the index to 16. 
        if ((rotation < 0) && (index == 0)) { index = 16; }
        
        // if the rotation is greater than zero, or equal to 15 then minus one from the index.
        if ((rotation > 0) && (index == 15)) { index = -1; }
        
        // gets the current car image form the car images array based on the integer index and rotation.
        this.currentCarImage = ((ImageIcon) this.carImagesArray.get(index + rotation));
    } // Ends setCarRotation method
    
    // Method used to move the car around the screen.
    private void carMovement() 
    {
    	// sets the car distance to the current car speed multiplied by .1 degrees.
        double carDistance = this.currentCarSpeed * 0.1D;
        
        // sets the x-coordinates by getting the current x-coordinates minus the car distance multiplied by the cosine of the current car angle
        // which has be calculated by radiant to get the measurement of the radius circle.
        // converts the x-coordinates from a double to an integer.
        this.x = ( (int) ( this.x - carDistance * Math.cos ( Math.toRadians ( this.currentCarAngle ) ) ) );
        
        // sets the y-coordinates by getting the current y-coordinates minus the car distance multiplied by the sine of the current car angle
        // which has be calculated by radiant to get the measurement of the radius circle.
        // converts the y-coordinates from a double to an integer.
        this.y = ( (int) ( this.y - carDistance * Math.sin ( Math.toRadians ( this.currentCarAngle ) ) ) );
        
        // sets the new value of the x coordinates to the current x integer if the x coordinates is greater, or equal to 755.
        // (true) then set the new value of x to 754, or set the new value for the x-coordinates to the current x-coordinates (false).
        // this uses the conditional operator. 
        this.x = (this.x >= 755 ? 754 : this.x);
        
        // sets the new value of the y coordinates based on the conditional operator, if the current y coordinates if greater, or equal to 551.
        this.y = (this.y >= 551 ? 550 : this.y);
        
        // sets the new value of the x coordinates based on the conditional operator output, which checks to see if the current x coordinates value is less than, or
        // equal to 29.
        this.x = (this.x <= 49 ? 50 : this.x);
        
        // sets the new value of the y coordinates based on the conditional operator.
        this.y = (this.y <= 100 ? 99 : this.y);

        // sets the rectangle collision hit box of the image to the new value calculated above.
        this.collisionHitBox.x = this.x;
        
        // sets the collision hit box of the image based on the new value of y calculated above.
        this.collisionHitBox.y = this.y;
    }
    
    // Method to set the cars acceleration.
    private void accelerateCar() 
    {
    	// sets the current speed of the car by using a conditional operator to check if the current car speed is equal, or greater than
    	// to the maximum speed allowed if it is true, then set it to the maximum speed allowed, or if it is false add 10 onto the current speed of the car.
        this.currentCarSpeed = (this.currentCarSpeed >= this.maximumSpeedAllowed ? this.maximumSpeedAllowed : this.currentCarSpeed + 10);
    }
    
    // Method used to reverse the car.
    private void reverseCar() 
    {
    	// sets the current car speed based on the conditional operator's output. If the current car speed if equal to the maximum speed minus 200
    	// then set the maximum speed current car speed to minus the maximum speed allowed minus 200 (keep it the same), otherwise if false, 
    	// set the current car speed minus 10.
        this.currentCarSpeed = (this.currentCarSpeed == this.maximumSpeedAllowed - 200 ? this.maximumSpeedAllowed - 200 : this.currentCarSpeed - 10);
    }
    
    // Method used to turn the car left.
    private void turnCarLeft() 
    {
    	// sets the car rotation minus one.
    	setCarRotation(-1);
    	
    	// sets the current car angle to subtract 22.5 degrees.
        this.currentCarAngle -= 22.5D;
    }
    
    // Method used to turn the car right.
    private void turnCarRight() 
    {
    	// sets the car rotation plus one.
    	setCarRotation(1);
    	
    	// sets the current car angle to add 22.5 degrees.
        this.currentCarAngle += 22.5D;
    }
    
    // Method used to set the car image.
    private void setCarImage(ImageIcon carImage) 
    {
    	// gets the car image icon and sets it to the current car image.
        this.currentCarImage = carImage;
    }
    
    // Method used handle if the key has been pressed.
    public void keyPressed(int inputKey) 
    {
    	// if the users key does not contain the input key. 
        if (!this.userKeys.contains(inputKey)) 
        {
        	// then add the input key to the users key.
            this.userKeys.add(inputKey);
        }
    }
    
    // Method used to hand if the key has been released.
    public void keyReleased(int inputKey) 
    {
    	// sets the integer index to the first occurrence of the integer array of user keys.
        int index = this.userKeys.indexOf(inputKey);
        
        // if the index does not equal minus one
        if (index != -1) 
        {
        	// removes the index at the specified position in this user keys array.
            this.userKeys.remove(index);
            
            // if the input key is equal to up, or is it is equal to down.
            if (inputKey == this.inputKeys.UP || inputKey == this.inputKeys.DOWN)
            {
            	// set the current car speed to zero.
                this.currentCarSpeed = 0;
            }
        }
    }
    
    // Method used to handle the active keys.
    public void activeKeys() 
    {
    	// returns then iterator index over the elements in the user keys list. 
    	// had next is used to check if it is true if the iteration (index) has more elements.
        for (Iterator<Integer> index = this.userKeys.iterator(); index.hasNext();) 
        {
        	// returns the next element in the iteration (index) and store that in the variable input key.
            int inputKey = ((Integer) index.next());
            
            // if the integer input key is equal to the up key on the keyboard (which has been stored in the class InputKeys) 
            // accelerate the car forwards. 
            if (inputKey == this.inputKeys.UP) { accelerateCar(); } 
            
            // if input key equals the down key (either, the down arrow, or the 's' key) then reverse the car.
            if (inputKey == this.inputKeys.DOWN) { reverseCar(); } 
            
            // if input key equal the left key ('a', or the left arrow) then turn the car left.
            if (inputKey == this.inputKeys.LEFT) { turnCarLeft(); } 
            
            // if the input key equal the right key ('d', or the right arrow key) then turn the key right.
            if (inputKey == this.inputKeys.RIGHT) { turnCarRight(); }
        }
        
        // calls the car movement method - used to move the car around the screen.
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
    public int UP;    // UP 
    public int DOWN;  // DOWN 
    public int LEFT;  // LEFT 
    public int RIGHT; // RIGHT 
    
    // Array of an Array of integers - used to store the set of keys need to control the car's movements.
    private final int[][] setofKeys = {{ 87, 83, 68, 65 }, { 38, 40, 39, 37 } };
    
    // Used to set the Input key to the corresponding set of keys.
    // The first array is used to get the users input key 
	// and the second array is used to get the corresponding key in the setOfKeys.
    public InputKeys(int inputKeys)
    {
        // W or Up Arrow
        this.UP = this.setofKeys[inputKeys][0];
        // S or Down Arrow
        this.DOWN = this.setofKeys[inputKeys][1];
        // D or Right Arrow
        this.RIGHT = this.setofKeys[inputKeys][2];
        // A or Left Arrow
        this.LEFT = this.setofKeys[inputKeys][3];
    }
}