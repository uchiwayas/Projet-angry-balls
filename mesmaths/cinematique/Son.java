package mesmaths.cinematique;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Son {
	public static void sonCollision(double a){
		try {
			File file = new File("Sons/SonChoc.wav");
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
			
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			
			//intensité du choc
			FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			if(a>=0 && a<1)
	        	volume.setValue((float) (-(1-a)*40));
			if(a>0.01) //petit bricolage pour arreter le son si deux billes spawn l'une dans l'autre
				clip.start();
			System.out.println(a);
			
			
			
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}
}
