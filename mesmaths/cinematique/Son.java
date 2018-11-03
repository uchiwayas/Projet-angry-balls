package mesmaths.cinematique;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import mesmaths.geometrie.base.Vecteur;

public class Son {
	public static int ATTENUATION=50; //1 : meme volume quelque soit le choc, plus ca augmente plus la différence de volume est importante (max recommendé: 80)
	
	public static void sonCollision(double a, Vecteur centreBille){
		try {
			File file = new File("Sons/SonChoc.wav");
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
			
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			
			//intensité du choc
			FloatControl balance = (FloatControl) clip.getControl(FloatControl.Type.BALANCE);
			FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			
			//BALANCE
			Toolkit toolkit = Toolkit.getDefaultToolkit();
			Dimension screenSize = toolkit.getScreenSize();	
			float ratio = (float) (- 1 + (2*(centreBille.x / (screenSize.width*0.5))));
			balance.setValue(ratio);
			
			//VOLUME
			if(a>=0 && a<1) {
				double vol = -(1-a)*ATTENUATION;
				if (vol < -80) vol = -80;
	        	volume.setValue((float) (vol));
			}
			if(a>0.01) //petit bricolage pour arreter le son si deux billes spawn l'une dans l'autre
				clip.start();
			
			
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}
}
