package exodecorateur_angryballs.maladroit.modele;

import java.awt.event.MouseEvent;

import mesmaths.geometrie.base.Vecteur;

public class PressedState implements ObjetState {
	

	private static final double COEF_AMPLIFICATION = 5.0e-3;

	@Override
	public void mouseDragged(MouseEvent e, ObjetAvecEffetDragAndDrop obj) {
		obj.getVitesse().set(Vecteur.VECTEURNUL);
		obj.getAccélération().set(Vecteur.VECTEURNUL);
		obj.getAccélération().set(new Vecteur((-obj.getPosition().x + e.getX())*COEF_AMPLIFICATION, (-obj.getPosition().y + e.getY())*COEF_AMPLIFICATION));
	}

	@Override
	public void mouseReleased(MouseEvent e, ObjetAvecEffetDragAndDrop obj) {
		obj.setState(new DefaultState());		
	}

	@Override
	public void mousePressed(MouseEvent e, ObjetAvecEffetDragAndDrop obj) {
		System.out.println("x");
		/*obj.getVitesse().set(Vecteur.VECTEURNUL);
		obj.getAccélération().set(Vecteur.VECTEURNUL);*/
		if (Math.sqrt(Math.pow(e.getX()-obj.getPosition().x,2)+(Math.pow(e.getY()-obj.getPosition().y,2))) < obj.getRayon()/2)
			obj.getAccélération().set(new Vecteur((-obj.getPosition().x+ e.getX())*COEF_AMPLIFICATION, (-obj.getPosition().y + e.getY())*COEF_AMPLIFICATION));
		/*le jour de la soutenance*/
	}
	
	//if (u.difference(obj.getPosition()).normeCarrée < rayon * rayon)
}
