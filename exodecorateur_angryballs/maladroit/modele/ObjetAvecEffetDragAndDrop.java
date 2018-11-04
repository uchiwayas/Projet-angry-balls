package exodecorateur_angryballs.maladroit.modele;

import java.awt.event.MouseEvent;

import exodecorateur_angryballs.maladroit.vues.CadreAngryBalls;
import mesmaths.geometrie.base.Vecteur;

public class ObjetAvecEffetDragAndDrop extends ObjetSourisListener {
	double preX, preY, X_precedent, Y_precedent;
	boolean PressIN = false;

	public ObjetAvecEffetDragAndDrop(CadreAngryBalls cadre, Objet o) {
		super(cadre, o);
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		if (PressIN)
		{
			if (this.getPosition().x % 1 == 0) //contre les petits mouvements de souris
				X_precedent = this.getPosition().x;
			if (this.getPosition().y % 1 == 0) //contre les petits mouvements de souris
				X_precedent = this.getPosition().y;
		    updateLocation(e);
		}
	}


	@Override
	public void mousePressed(MouseEvent e) {
		preX = this.getPosition().x - e.getX();
	    preY = this.getPosition().y - e.getY();
	    X_precedent = this.getPosition().x;
	    Y_precedent = this.getPosition().y;
		if (Math.sqrt(Math.pow(e.getX()-this.getPosition().x,2)+(Math.pow(e.getY()-this.getPosition().y,2))) < this.getRayon()) {
			updateLocation(e);
			PressIN = true;
		}
	    
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(PressIN) {
			updateLocation(e);
			this.getAccélération().ajoute(new Vecteur(-this.getVitesse().x, -this.getVitesse().y)); //reset
			
			this.getAccélération().ajoute(new Vecteur((this.getPosition().x - X_precedent)/100, (this.getPosition().y - Y_precedent)/100)); //ajout vitesse de souris
			PressIN = false;
		}
	}
	
	
	public void updateLocation(MouseEvent e) {
		this.getPosition().x = preX + e.getX();
		this.getPosition().y = preY + e.getY();
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
		// TODO Auto-generated method stub
		
	}

}
