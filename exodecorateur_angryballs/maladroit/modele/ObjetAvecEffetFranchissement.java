package exodecorateur_angryballs.maladroit.modele;

import mesmaths.cinematique.Collisions;

public class ObjetAvecEffetFranchissement extends ObjetAvecEffetCollision {

	public ObjetAvecEffetFranchissement(ObjetAvecEffet o) {
		super(o);
	}

	@Override
	public void collisionContour(double abscisseCoinHautGauche, double ordonn�eCoinHautGauche, double largeur,
			double hauteur) {
		Collisions.collisionBilleContourPasseMuraille(this.getPosition(), abscisseCoinHautGauche,
				ordonn�eCoinHautGauche, largeur, hauteur);
	}

}
