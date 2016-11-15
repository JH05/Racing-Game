/**
 * @author JH05
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

/***
 * Main class
 */
@SuppressWarnings("serial")
public class Main extends JFrame implements ActionListener
{
    private final Game startGame;
    private final Play playGame;
    
    // main method
    public static void main(String[] args)
    {
        Main main = new Main();
        main.setVisible(true);
    }    
    
    // method called main
    public Main()
    {
        setTitle("Game");
        setSize(200, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Map raceTrack = new Map();
        this.playGame = new Play(this);
        this.startGame = new Game(raceTrack);
        this.startGame.addKeyListener(this.startGame);
        this.startGame.setFocusable(true);    
        add(this.playGame);
    }
     
	@Override
	// invoked when an action occurs.
	// action event is used to indicate that a component-defined action occurred.
    public void actionPerformed(ActionEvent e)
    {    	
        JButton startGame = this.playGame.playBtn();
        JFrame frame = new JFrame("Game");
        frame.setSize(850, 650);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this.startGame);       
        
        if (e.getSource() == startGame)
        {
            Local local = new Local(this, frame, this.startGame);
            local.play();
        }
    }
}
