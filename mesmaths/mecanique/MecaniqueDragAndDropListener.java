package mesmaths.mecanique;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import exodecorateur_angryballs.maladroit.modele.Objet;
import exodecorateur_angryballs.maladroit.vues.CadreAngryBalls;
import mesmaths.geometrie.base.Vecteur;

/**
 * gestion des opérations de mécanique du point matériel
 * 
 */
public class MecaniqueDragAndDropListener implements MouseListener {
	
	Objet obj;
	CadreAngryBalls cadre;
	Vecteur v = new Vecteur(0,0);
	
	public MecaniqueDragAndDropListener() {
	}
	
	public MecaniqueDragAndDropListener(Objet obj, CadreAngryBalls cadre) {
		this.obj = obj;
		this.cadre = cadre;
	}
	
	public Vecteur gestionAccelerationDragAndDrop(Objet obj, CadreAngryBalls cadre) {
		return v;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		v = new Vecteur(10,10);
	} 

	@Override
	public void mouseEntered(MouseEvent e) {		
	}

	@Override
	public void mouseExited(MouseEvent e) {		
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}
}
