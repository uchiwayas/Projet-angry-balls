package exodecorateur_angryballs.maladroit.modele;

import mesmaths.cinematique.Collisions;

public class ObjetAvecEffetRebond extends ObjetAvecEffetCollision {

	public ObjetAvecEffetRebond(ObjetAvecEffet o) {
		super(o);
	}

	@Override
	public void collisionContour(int abscisseCoinHautGauche, int ordonnéeCoinHautGauche, double largeur,
			double hauteur) {
		Collisions.collisionObjetContourAvecRebond(this.getPosition(), this.getRayon(), this.getVitesse(),
				abscisseCoinHautGauche, ordonnéeCoinHautGauche, largeur, hauteur);
	}
}