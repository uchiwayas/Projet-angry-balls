package exodecorateur_angryballs.maladroit.modele;

import mesmaths.geometrie.base.Vecteur;

public class ObjetAvecEffetNewton extends ObjetAvecEffetAcceleration {

	@Override
	public void deplacer() {
		this.getAcc�l�ration().ajoute(OutilsObjet.gestionAcc�l�rationNewton(this, billes));	

	}

	@Override
	public void collisionContour(double abscisseCoinHautGauche, double ordonn�eCoinHautGauche, double largeur,
			double hauteur) {
		// TODO Auto-generated method stub

	}
}
