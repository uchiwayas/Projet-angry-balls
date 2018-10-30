package exodecorateur_angryballs.maladroit.modele;

import java.util.Vector;

import exodecorateur_angryballs.maladroit.vues.CadreAngryBalls;
import mesmaths.mecanique.MecaniqueDragAndDropListener;

public class ObjetAvecEffetDragAndDrop extends ObjetAvecEffetAcceleration {
	CadreAngryBalls cadre;

	public ObjetAvecEffetDragAndDrop(CadreAngryBalls cadre, Objet o) {
		super(o);
		this.cadre = cadre;
	}
	
	@Override
	public void gestionAcc�l�ration(Vector<Objet> objets) {
		ObjetOriginal.gestionAcc�l�ration(objets);
		MecaniqueDragAndDropListener m = new MecaniqueDragAndDropListener(this, cadre);
		this.getAcc�l�ration().ajoute(m.gestionAccelerationDragAndDrop(this, cadre));
		
	}

}
