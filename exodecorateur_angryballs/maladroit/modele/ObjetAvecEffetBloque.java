package exodecorateur_angryballs.maladroit.modele;

import mesmaths.cinematique.Collisions;

public class ObjetAvecEffetBloque extends ObjetAvecEffetCollision {

	public ObjetAvecEffetBloque(ObjetAvecEffet o) {
		super(o);
	}

	@Override
	public void collisionContour(double abscisseCoinHautGauche, double ordonnéeCoinHautGauche, double largeur,
			double hauteur) {
		Collisions.collisionBilleContourAvecArretHorizontal(this.getPosition(), this.getRayon(), this.getVitesse(),
				abscisseCoinHautGauche, largeur);
		Collisions.collisionBilleContourAvecArretVertical(this.getPosition(), this.getRayon(), this.getVitesse(),
				ordonnéeCoinHautGauche, hauteur);
	}

}
