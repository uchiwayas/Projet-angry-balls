package exodecorateur_angryballs.maladroit.modele;

public abstract class ObjetAvecEffetCollision extends ObjetAvecEffet {
	
	public ObjetAvecEffetCollision(ObjetAvecEffet o) {
		super(o);
	}

	public abstract void collisionContour(double abscisseCoinHautGauche, double ordonn�eCoinHautGauche, double largeur, double hauteur);
		
}
