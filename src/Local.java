/**
 *
 * @author JH05
 */
import javax.swing.JFrame;

/***
 * Local class
 */
public class Local
{
    private final JFrame main;
    private final JFrame game;
    private final Game play;
    
    // main local method used to get the main and game frame as well as call the game class.
    public Local(JFrame main, JFrame game, Game play)
    {
        this.main = main;
        this.game = game;        
        this.play = play;

        Car playerTwo = new Car("Red.jpg", 380, 550, 1);
        Car playerOne = new Car("Blue.jpg", 380, 500, 0);
        
        this.play.getPlayers(playerOne);
        this.play.getPlayers(playerTwo);
        
        this.play.addKeyListener(this.play);
        this.play.setFocusable(true);
    }
    
    // play method used to hide the main JFrame, and show the game frame.
    // the method will also call start new game from the game class.
    public void play()
    {
        this.main.setVisible(false);
        this.game.setVisible(true);
        this.play.startNewGame();
    }
}