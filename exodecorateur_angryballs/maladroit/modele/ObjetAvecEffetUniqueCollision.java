package exodecorateur_angryballs.maladroit.modele;

public abstract class ObjetAvecEffetUniqueCollision extends ObjetAvecEffet {
	
	public ObjetAvecEffetUniqueCollision(ObjetAvecEffet o) {
		super(o);
	}

	public abstract void collisionContour(double abscisseCoinHautGauche, double ordonn�eCoinHautGauche, double largeur, double hauteur);
		
}
