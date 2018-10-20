package exodecorateur_angryballs.maladroit.modele;

import java.awt.Graphics;

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
	
	@Override
	public void d�placer(double deltaT) {
		ObjetOriginal.d�placer(deltaT);
	}
	
	@Override
	public void collisionContour(int i, int j, double largeurBillard, double hauteurBillard) {
		ObjetOriginal.collisionContour(i, j, largeurBillard, hauteurBillard);
	}
	
	@Override
	public void dessine(Graphics graphics) {
		ObjetOriginal.dessine(graphics);
	}
	
}
