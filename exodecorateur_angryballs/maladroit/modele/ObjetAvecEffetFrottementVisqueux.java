package exodecorateur_angryballs.maladroit.modele;

import java.util.Vector;

import mesmaths.mecanique.MecaniquePoint;

public class ObjetAvecEffetFrottementVisqueux extends ObjetAvecEffetAcceleration {

	public ObjetAvecEffetFrottementVisqueux(Objet o) {
		super(o);
	}

	@Override
	public void gestionAcc�l�ration(Vector<Objet> objets) {
		ObjetOriginal.gestionAcc�l�ration(objets);
		this.getAcc�l�ration().ajoute(MecaniquePoint.freinageFrottement(this.masse(), this.getVitesse())); // contribution de l'acc�l�ration due au frottement dans l'air
		
	}
	
}
