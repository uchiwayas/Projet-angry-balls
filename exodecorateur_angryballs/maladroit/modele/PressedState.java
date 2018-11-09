package exodecorateur_angryballs.maladroit.modele;

import java.awt.event.MouseEvent;

import mesmaths.geometrie.base.Vecteur;

public class PressedState implements ObjetState {
	

	private static final double COEF_AMPLIFICATION = 10.0e-6;

	@Override
	public void mouseDragged(MouseEvent e, ObjetAvecEffetDragAndDrop obj) {
		accel(e, obj);
	}

	@Override
	public void mouseReleased(MouseEvent e, ObjetAvecEffetDragAndDrop obj) {
		obj.setState(new DefaultState());		
	}

	@Override
	public void mousePressed(MouseEvent e, ObjetAvecEffetDragAndDrop obj) {
		accel(e, obj);
	}
	
	public void accel(MouseEvent e, ObjetAvecEffetDragAndDrop obj) {
		obj.getAccélération().ajoute(new Vecteur((-obj.getPosition().x+ e.getX())*COEF_AMPLIFICATION, (-obj.getPosition().y + e.getY())*COEF_AMPLIFICATION));
	}
}
