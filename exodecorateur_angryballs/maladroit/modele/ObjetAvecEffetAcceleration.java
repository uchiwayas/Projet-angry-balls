package exodecorateur_angryballs.maladroit.modele;


public abstract class ObjetAvecEffetAcceleration extends ObjetAvecEffet {

	public ObjetAvecEffetAcceleration(Objet o) {
		super(o);
	}
	
	@Override
	public void collisionContour(int abscisseCoinHautGauche, int ordonnéeCoinHautGauche, double largeur,
			double hauteur) {
		ObjetOriginal.collisionContour(abscisseCoinHautGauche, ordonnéeCoinHautGauche, largeur,
				hauteur);
	}
	
}
