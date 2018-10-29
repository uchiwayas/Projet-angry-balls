package exodecorateur_angryballs.maladroit.modele;

import java.util.Vector;

public class ObjetAvecEffetRU extends ObjetAvecEffetAcceleration {

	public ObjetAvecEffetRU(Objet o) {
		super(o);
	}

	@Override
	public void gestionAccélération(Vector<Objet> objets) {ObjetOriginal.gestionAccélération(objets);}
}
