package exodecorateur_angryballs.maladroit.modele;

public abstract class Objet {
	
	public abstract void deplacer();
	
	/**
	 * gestion de l'�ventuelle collision de l'objet (this) avec le contour rectangulaire de l'�cran d�fini par (abscisseCoinHautGauche, ordonn�eCoinHautGauche, largeur, hauteur)
	 * 
	 * d�tecte si il y a collision et le cas �ch�ant met � jour position et vitesse
	 * 
	 * La nature du comportement de la bille en r�ponse � cette collision est d�finie dans les classes d�riv�es
	 * */
	public abstract void collisionContour(double abscisseCoinHautGauche, double ordonn�eCoinHautGauche, double largeur, double hauteur);
	
}
