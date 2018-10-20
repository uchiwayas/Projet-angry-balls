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
	public Vecteur getAcc�l�ration() {
		return ObjetOriginal.getAcc�l�ration();
	}
	
	public void gestionAcc�l�ration(Vector<Objet> objets) {
		this.getAcc�l�ration().set(Vecteur.VECTEURNUL);
	}
	
	public void collisionContour(double abscisseCoinHautGauche, double ordonn�eCoinHautGauche, double largeur,
			double hauteur) {
	}
}
