package mesmaths.mecanique;

import java.awt.MouseInfo;

import exodecorateur_angryballs.maladroit.modele.Objet;
import exodecorateur_angryballs.maladroit.vues.CadreAngryBalls;
import mesmaths.geometrie.base.Vecteur;

/**
 * gestion des opérations de mécanique du point matériel
 * 
 */
public class MecaniqueDragAndDrop {
	
	public static Vecteur gestionAccelerationDragAndDrop(Objet obj, CadreAngryBalls cadre) {
		
		int x = MouseInfo.getPointerInfo().getLocation().x;
		int y = MouseInfo.getPointerInfo().getLocation().y;
		
		obj.getPosition().x = x - cadre.getX();
		obj.getPosition().y = y - cadre.getY()*(1.2);
		
		
		
		return new Vecteur (0,0);
	}

}
