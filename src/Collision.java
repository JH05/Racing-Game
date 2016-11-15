/**
 * @author JH05
 */
import java.awt.Rectangle;

/***
 * Collision class
 */
public class Collision 
{
    private Rectangle grassCollisionHitBox;
    private Rectangle outerEdgeCollisionHitBox;
    
    public Collision()
    {    	
    	setGrassHitBox();
    	setOuterEdgeHitBox();    	
    }
    
    // Method to set the grass collision hit box.
    public void setGrassHitBox()
    {
    	this.grassCollisionHitBox = new Rectangle(150, 200, 550, 300);
    }
    
    // Method to set the outer edge hit box.
    public void setOuterEdgeHitBox()
    {
    	this.outerEdgeCollisionHitBox = new Rectangle();	
    }
    
    // Method to get the grass collision hit box.
    public Rectangle getGrassHitBox()
    {
        return this.grassCollisionHitBox;
    }

    // Method to get the outer edge collision hit box.
    public Rectangle getOuterEdgeHitBox()
    {
        return this.outerEdgeCollisionHitBox;
    }    
}


