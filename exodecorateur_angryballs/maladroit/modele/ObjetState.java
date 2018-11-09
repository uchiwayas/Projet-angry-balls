package exodecorateur_angryballs.maladroit.modele;

import java.awt.event.MouseEvent;

public interface ObjetState {

	public void mouseDragged(MouseEvent e, ObjetAvecEffetDragAndDrop obj);

	public void mouseReleased(MouseEvent e, ObjetAvecEffetDragAndDrop obj);

	public void mousePressed(MouseEvent e, ObjetAvecEffetDragAndDrop obj);
}
