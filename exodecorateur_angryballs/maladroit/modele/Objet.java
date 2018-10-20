package exodecorateur_angryballs.maladroit.modele;

import java.awt.Graphics;
import java.util.Vector;

import mesmaths.geometrie.base.Vecteur;

public abstract class Objet {

	/**
	 * gestion de l'�ventuelle collision de cet objet avec les autres objets
	 *
	 * objets est la liste de touts les objets en mouvement
	 * 
	 * Le comportement par d�faut est le choc parfaitement �lastique (c-�-d
	 * rebond sans amortissement)
	 * 
	 * @return true si il y a collision et dans ce cas les positions et vecteurs
	 *         vitesses des 2 billes impliqu�es dans le choc sont modifi�es si
	 *         renvoie false, il n'y a pas de collision et les billes sont
	 *         laiss�es intactes
	 */	
	public boolean gestionCollisionObjetObjet(Vector<Objet> objets) {
		return OutilsObjet.gestionCollisionObjetObjet(this, objets);
	}
	
	public abstract int getClef();
	public abstract Vecteur getPosition();
	public abstract double getRayon();
	public abstract Vecteur getVitesse();
	public abstract double masse();
	public abstract Vecteur getAcc�l�ration();
	public abstract void d�placer(double deltaT);
	public abstract void gestionAcc�l�ration(Vector<Objet> objets);
	public abstract void collisionContour(int i, int j, double largeurBillard, double hauteurBillard);
	public abstract void dessine(Graphics graphics);
}
