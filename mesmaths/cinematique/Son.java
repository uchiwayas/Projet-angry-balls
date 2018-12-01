package mesmaths.cinematique;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import mesmaths.geometrie.base.Vecteur;

public final class Son {
	public static int ATTENUATION=50; //1 : meme volume quelque soit le choc, plus ca augmente plus la différence de volume est importante (max recommendé: 80)
	public static File SON_COLLISION = new File("Sons/SonChoc.wav");
	
	public static void sonCollisionObjetObjet(double a, Vecteur pointDeCollision, double width){
		
			boolean start = true;
			//BALANCE
			float ratio = (float) (- 1 + (2*(pointDeCollision.x / width)));
			
			//VOLUME
			double vol=0;
			System.out.println("a = "+ a);
			if(a>=0 && a<1) {
				vol = -(1-a)*ATTENUATION;
				if (vol < -80) vol = -80;
			}
			if(a<0.1) //petit bricolage pour arreter le son si deux billes spawn l'une dans l'autre
				start = false;

			sonCollision((float)vol, ratio, start);
	}
	
	public static void sonCollisionObjetContour(Vecteur vitesse, double xCollision){
	
	}
	
	public static void sonCollision(float volume, float ratio, boolean start){
		if (start){
			try {
				System.out.println(volume);
				File file = SON_COLLISION;
				AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
				
				Clip clip = AudioSystem.getClip();
				clip.open(audioInputStream);
				
				//droite ou gauche
				FloatControl balance = (FloatControl) clip.getControl(FloatControl.Type.BALANCE);
				balance.setValue(ratio);
				//intensité du choc
				FloatControl vol = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
				vol.setValue(volume);
				
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
}
