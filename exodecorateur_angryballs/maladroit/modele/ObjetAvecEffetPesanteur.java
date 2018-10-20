package exodecorateur_angryballs.maladroit.modele;

import java.util.Vector;

import mesmaths.geometrie.base.Vecteur;

public class ObjetAvecEffetPesanteur extends ObjetAvecEffetAcceleration {
	
	Vecteur pesanteur;
	
	public ObjetAvecEffetPesanteur(Objet o, Vecteur pesanteur)
	{
		super(o);
		this.pesanteur = pesanteur;
	}

	@Override
	public void gestionAccélération(Vector<Objet> objets) {
		ObjetOriginal.gestionAccélération(objets);
		this.getAccélération().ajoute(this.pesanteur); // contribution du champ de pesanteur
	}
	
}
