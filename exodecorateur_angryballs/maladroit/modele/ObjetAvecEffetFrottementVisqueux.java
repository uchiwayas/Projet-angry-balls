package exodecorateur_angryballs.maladroit.modele;

import java.util.Vector;

import mesmaths.mecanique.MecaniquePoint;

public class ObjetAvecEffetFrottementVisqueux extends ObjetAvecEffetAcceleration {

	public ObjetAvecEffetFrottementVisqueux(Objet o) {
		super(o);
	}

	@Override
	public void gestionAccélération(Vector<Objet> objets) {
		ObjetOriginal.gestionAccélération(objets);
		this.getAccélération().ajoute(MecaniquePoint.freinageFrottement(this.masse(), this.getVitesse())); // contribution de l'accélération due au frottement dans l'air
		
	}
	
}
