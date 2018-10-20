package exodecorateur_angryballs.maladroit.modele;

import java.util.Vector;

import mesmaths.geometrie.base.Vecteur;

public abstract class Objet {
	
//	public abstract void deplacer(Vector<Objet> objets);
	
	/**
	 * gestion de l'éventuelle collision de l'objet (this) avec le contour rectangulaire de l'écran défini par (abscisseCoinHautGauche, ordonnéeCoinHautGauche, largeur, hauteur)
	 * 
	 * détecte si il y a collision et le cas échéant met à jour position et vitesse
	 * 
	 * La nature du comportement de la bille en réponse à cette collision est définie dans les classes dérivées
	 * */
	
	public abstract int getClef();
	public abstract Vecteur getPosition();
	public abstract double getRayon();
	public abstract Vecteur getVitesse();
	public abstract double masse();
	public abstract Vecteur getAccélération();
	public abstract void gestionAccélération(Vector<Objet> objets);
}
