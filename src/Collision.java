/**
 * @author 1332852
 */
import java.awt.Rectangle;

/***
 * Collision class
 */
public class Collision 
{
	// variable used to set the grass collision hit box.
    private Rectangle grassCollisionHitBox;
    // variable used to set the outer edge collision hit box.
    private Rectangle outerEdgeCollisionHitBox;
    
    public Collision()
    {    	
    	// calls the set grass hit box method.
    	setGrassHitBox();
    	// calls the set outer edge hit box method.
    	setOuterEdgeHitBox();    	
    }
    
    // Method to set the grass collision hit box.
    public void setGrassHitBox()
    {
    	// sets the grass collision hit box to:
    	// - x the specified X coordinate.
    	// - y the specified Y coordinate.
    	// - width the width of the Rectangle.
    	// - height the height of the Rectangle.
    	this.grassCollisionHitBox = new Rectangle(150, 200, 550, 300);
    }
    
    // Method to set the outer edge hit box.
    public void setOuterEdgeHitBox()
    {
    	// sets the grass collision hit box to:
    	// - x the specified X coordinate.
    	// - y the specified Y coordinate.
    	// - width the width of the Rectangle.
    	// - height the height of the Rectangle.
    	this.outerEdgeCollisionHitBox = new Rectangle();	
    }
    
    // Method to get the grass collision hit box.
    public Rectangle getGrassHitBox()
    {
    	// returns the collision hit box for the grass.
        return this.grassCollisionHitBox;
    }

    // Method to get the outer edge collision hit box.
    public Rectangle getOuterEdgeHitBox()
    {
    	// returns the collision hit box for the outer edge
        return this.outerEdgeCollisionHitBox;
    }    
}


