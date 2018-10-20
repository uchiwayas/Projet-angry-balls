package exodecorateur_angryballs.maladroit.modele;

import mesmaths.geometrie.base.Vecteur;

public class ObjetAvecEffetNewton extends ObjetAvecEffetAcceleration {

	@Override
	public void deplacer() {
		this.getAccélération().ajoute(OutilsObjet.gestionAccélérationNewton(this, billes));	

	}

	@Override
	public void collisionContour(double abscisseCoinHautGauche, double ordonnéeCoinHautGauche, double largeur,
			double hauteur) {
		// TODO Auto-generated method stub

	}
}
