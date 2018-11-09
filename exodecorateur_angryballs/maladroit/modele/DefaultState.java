package exodecorateur_angryballs.maladroit.modele;

import java.awt.event.MouseEvent;

public class DefaultState implements ObjetState {

	@Override
	public void mouseDragged(MouseEvent e, ObjetAvecEffetDragAndDrop obj) {}


	@Override
	public void mouseReleased(MouseEvent e, ObjetAvecEffetDragAndDrop obj) {}


	@Override
	public void mousePressed(MouseEvent e, ObjetAvecEffetDragAndDrop obj) {
		if (Math.sqrt(Math.pow(e.getX()-obj.getPosition().x,2)+(Math.pow(e.getY()-obj.getPosition().y,2))) < obj.getRayon()) {
			obj.setState(new PressedState());
			obj.mousePressed(e);
			
		}
	}
}
