package exodecorateur_angryballs.maladroit.modele;

import java.awt.Graphics;
import java.util.Vector;

import mesmaths.geometrie.base.Vecteur;

public abstract class Objet {

	/**
	 * gestion de l'éventuelle collision de cet objet avec les autres objets
	 *
	 * objets est la liste de touts les objets en mouvement
	 * 
	 * Le comportement par défaut est le choc parfaitement élastique (c-à-d
	 * rebond sans amortissement)
	 * 
	 * @return true si il y a collision et dans ce cas les positions et vecteurs
	 *         vitesses des 2 billes impliquées dans le choc sont modifiées si
	 *         renvoie false, il n'y a pas de collision et les billes sont
	 *         laissées intactes
	 */	
	public boolean gestionCollisionObjetObjet(Vector<Objet> objets) {
		return OutilsObjet.gestionCollisionObjetObjet(this, objets);
	}
	
	public abstract int getClef();
	public abstract Vecteur getPosition();
	public abstract double getRayon();
	public abstract Vecteur getVitesse();
	public abstract double masse();
	public abstract Vecteur getAccélération();
	public abstract void déplacer(double deltaT);
	public abstract void gestionAccélération(Vector<Objet> objets);
	public abstract void collisionContour(int i, int j, double largeurBillard, double hauteurBillard);
	public abstract void dessine(Graphics graphics);
}
