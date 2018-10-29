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
	public void gestionAcc�l�ration(Vector<Objet> objets) {
		ObjetOriginal.gestionAcc�l�ration(objets);
		this.getAcc�l�ration().ajoute(MecaniqueDragAndDrop.gestionAccelerationDragAndDrop(this, cadre));
		
	}

}
