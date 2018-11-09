package exodecorateur_angryballs.maladroit.modele;

import java.awt.Cursor;
import java.awt.event.MouseEvent;

import exodecorateur_angryballs.maladroit.vues.CadreAngryBalls;

public class ObjetAvecEffetDragAndDrop extends ObjetSourisListener {
	double preX, preY, X_precedent, Y_precedent;
	boolean PressIN = false;
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
		if (Math.sqrt(Math.pow(e.getX()-this.getPosition().x,2)+(Math.pow(e.getY()-this.getPosition().y,2))) < this.getRayon()) {
			cadre.setCursor(new Cursor(Cursor.HAND_CURSOR));
		}
		else cadre.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}

}
