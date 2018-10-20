package exodecorateur_angryballs.maladroit.modele;

import java.util.Vector;

public abstract class ObjetAvecEffetCollision extends ObjetAvecEffet {
	
	public ObjetAvecEffetCollision(ObjetAvecEffet o) {
		super(o);
	}
		
	@Override
	public void gestionAccélération(Vector<Objet> objets) {
		ObjetOriginal.gestionAccélération(objets);
	}
}
