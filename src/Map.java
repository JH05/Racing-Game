/**
 *
 * @author 1332852
 */
import java.awt.Color;
import java.awt.Graphics;

/***
 * Map class
 */
public class Map
{
    
    // Method to call the set collision hit boxes.
    public Map()
    {
    	
    }
        
    // Method to render the default track
    public void renderDefaultMap(Graphics g)
    {
        Color c1 = Color.green;
        g.setColor(c1);
        g.fillRect(150, 200, 550, 300); // Grass
         
        Color c2 = Color.black;
        g.setColor(c2);
        g.drawRect(50, 100, 750, 500);  // Outer Edge
        g.drawRect(150, 200, 550, 300); // Inner Edge
         
        Color c3 = Color.yellow;
        g.setColor(c3);
        g.drawRect(100, 150, 650, 400); // Mid-lane Marker
         
        Color c4 = Color.white;
        g.setColor(c4);
        g.drawLine(425, 500, 425, 600); // Start Line
    }
}