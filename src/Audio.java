/**
 * @author 1332852
 */
import javax.swing.JApplet;
import java.applet.AudioClip;
import java.net.URL;

/***
 * Audio class
 */
public class Audio 
{
	// method audio that calls the play sound files.
	public Audio()
	{
		// calls the play tire spin class.
		playTireSpin();
		
		// calls the play car crash class.
		playCarCrash();
		
		// calls the play finish class.
		playFinish();
	}
	
	// method used to play the tire spin audio clip. 
	public void playTireSpin()
	{
		// 
		URL tireSpin = this.getClass().getResource("Sounds/TireSpin.wav");
		AudioClip sound = JApplet.newAudioClip(tireSpin);
		sound.play();
	}
	
	public void playCarCrash()
	{
		URL carCrash = this.getClass().getResource("Sounds/CarCrash.wav");
		AudioClip sound = JApplet.newAudioClip(carCrash);
		sound.play();
	}
	
	public void playFinish()
	{
		URL finish = this.getClass().getResource("Sounds/ClappingCrowd.wav");
		AudioClip sound = JApplet.newAudioClip(finish);
		sound.play();
	}
}
