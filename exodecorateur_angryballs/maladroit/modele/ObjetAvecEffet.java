package exodecorateur_angryballs.maladroit.modele;

import java.util.Vector;

import mesmaths.geometrie.base.Vecteur;

public abstract class ObjetAvecEffet extends Objet {
	Objet ObjetOriginal;
	
	public ObjetAvecEffet(Objet o){
		this.ObjetOriginal = o;
	}
	
	@Override
	public int getClef() {
		return ObjetOriginal.getClef();
	}

	@Override
	public Vecteur getPosition() {
		return ObjetOriginal.getPosition();
	}

	@Override
	public double getRayon() {
		return ObjetOriginal.getRayon();
	}

	@Override
	public Vecteur getVitesse() {
		return ObjetOriginal.getVitesse();
	}

	@Override
	public double masse() {
		return ObjetOriginal.masse();
	}
	
	@Override
	public Vecteur getAccélération() {
		return ObjetOriginal.getAccélération();
	}
	
	public void gestionAccélération(Vector<Objet> objets) {
		this.getAccélération().set(Vecteur.VECTEURNUL);
	}
	
	public void collisionContour(double abscisseCoinHautGauche, double ordonnéeCoinHautGauche, double largeur,
			double hauteur) {
	}
}
