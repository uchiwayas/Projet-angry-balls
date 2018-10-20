package exodecorateur_angryballs.maladroit.modele;

import java.util.Vector;

public class ObjetAvecEffetNewton extends ObjetAvecEffetAcceleration {

	public ObjetAvecEffetNewton(Objet o) {
		super(o);
	}

	@Override
	public void gestionAcc�l�ration(Vector<Objet> objets) {
		ObjetOriginal.gestionAcc�l�ration(objets);
		this.getAcc�l�ration().ajoute(OutilsObjet.gestionAcc�l�rationNewton(this, objets)); // contribution de l'acc�l�ration due � l'attraction des autres objets
		
	}
	
}
