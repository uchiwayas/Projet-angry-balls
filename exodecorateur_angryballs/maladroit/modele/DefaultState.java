package exodecorateur_angryballs.maladroit.modele;

import java.awt.event.MouseEvent;

import mesmaths.geometrie.base.Vecteur;

public class DefaultState implements ObjetState {

	@Override
	public void mouseDragged(MouseEvent e, ObjetAvecEffetDragAndDrop obj) {}


	@Override
	public void mouseReleased(MouseEvent e, ObjetAvecEffetDragAndDrop obj) {}


	@Override
	public void mousePressed(MouseEvent e, ObjetAvecEffetDragAndDrop obj) {
		Vecteur mousePos = new Vecteur(e.getX(), e.getY());
		if (mousePos.distanceCarre(obj.getPosition()) <= obj.getRayon()* obj.getRayon()) {
			obj.setState(new PressedState());
			obj.mousePressed(e);
		}
	}
}
