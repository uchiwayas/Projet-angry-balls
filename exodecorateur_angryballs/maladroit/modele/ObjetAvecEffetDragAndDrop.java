package exodecorateur_angryballs.maladroit.modele;

import java.util.Vector;

import exodecorateur_angryballs.maladroit.vues.CadreAngryBalls;
import mesmaths.mecanique.MecaniqueDragAndDrop;

public class ObjetAvecEffetDragAndDrop extends ObjetAvecEffetAcceleration {
	CadreAngryBalls cadre;

	public ObjetAvecEffetDragAndDrop(CadreAngryBalls cadre, Objet o) {
		super(o);
		this.cadre = cadre;
	}
	
	@Override
	public void gestionAccélération(Vector<Objet> objets) {
		ObjetOriginal.gestionAccélération(objets);
		this.getAccélération().ajoute(MecaniqueDragAndDrop.gestionAccelerationDragAndDrop(this, cadre));
		
	}

}
