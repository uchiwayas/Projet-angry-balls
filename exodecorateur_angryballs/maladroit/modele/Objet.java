package exodecorateur_angryballs.maladroit.modele;

public abstract class Objet {
	
	public abstract void deplacer();
	
	/**
	 * gestion de l'éventuelle collision de l'objet (this) avec le contour rectangulaire de l'écran défini par (abscisseCoinHautGauche, ordonnéeCoinHautGauche, largeur, hauteur)
	 * 
	 * détecte si il y a collision et le cas échéant met à jour position et vitesse
	 * 
	 * La nature du comportement de la bille en réponse à cette collision est définie dans les classes dérivées
	 * */
	public abstract void collisionContour(double abscisseCoinHautGauche, double ordonnéeCoinHautGauche, double largeur, double hauteur);
	
}
