package exodecorateur_angryballs.maladroit.modele;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import exodecorateur_angryballs.maladroit.vues.CadreAngryBalls;
import mesmaths.geometrie.base.Vecteur;

public class ObjetAvecEffetDragAndDrop extends ObjetSourisListener {
	private ObjetState myState;

	public ObjetAvecEffetDragAndDrop(CadreAngryBalls cadre, Objet o) {
		super(cadre, o);
		setState(new DefaultState());
	}
	
	void setState(final ObjetState newState) {
        myState = newState;
    }

	@Override
	public void mousePressed(MouseEvent e) {
		myState.mousePressed(e, this);
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		myState.mouseDragged(e, this);
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		myState.mouseReleased(e, this);
	}
	

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		Vecteur mousePos = new Vecteur(e.getX(), e.getY());
		if (mousePos.distanceCarre(this.getPosition()) < this.getRayon()* this.getRayon()) {
			cadre.setCursor(new Cursor(Cursor.HAND_CURSOR));
		}
		else cadre.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}

}
