package exodecorateur_angryballs.maladroit.modele;

import java.util.Vector;

public class ObjetAvecEffetNewton extends ObjetAvecEffetAcceleration {

	public ObjetAvecEffetNewton(Objet o) {
		super(o);
	}

	@Override
	public void gestionAccélération(Vector<Objet> objets) {
		ObjetOriginal.gestionAccélération(objets);
		this.getAccélération().ajoute(OutilsObjet.gestionAccélérationNewton(this, objets)); // contribution de l'accélération due à l'attraction des autres objets
		
	}
	
}
