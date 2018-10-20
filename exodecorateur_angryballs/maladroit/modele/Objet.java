package exodecorateur_angryballs.maladroit.modele;

import java.util.Vector;

import mesmaths.geometrie.base.Vecteur;

public abstract class Objet {
	
//	public abstract void deplacer(Vector<Objet> objets);
	
	/**
	 * gestion de l'�ventuelle collision de l'objet (this) avec le contour rectangulaire de l'�cran d�fini par (abscisseCoinHautGauche, ordonn�eCoinHautGauche, largeur, hauteur)
	 * 
	 * d�tecte si il y a collision et le cas �ch�ant met � jour position et vitesse
	 * 
	 * La nature du comportement de la bille en r�ponse � cette collision est d�finie dans les classes d�riv�es
	 * */
	
	public abstract int getClef();
	public abstract Vecteur getPosition();
	public abstract double getRayon();
	public abstract Vecteur getVitesse();
	public abstract double masse();
	public abstract Vecteur getAcc�l�ration();
	public abstract void gestionAcc�l�ration(Vector<Objet> objets);
}
