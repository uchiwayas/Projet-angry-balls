package exodecorateur_angryballs.maladroit.modele;

import mesmaths.geometrie.base.Vecteur;

public abstract class ObjetAvecEffet extends Objet {
	ObjetAvecEffet ObjetPrecedent;
	
	@Override
	public int getClef() {
		return ObjetPrecedent.getClef();
	}

	@Override
	public Vecteur getPosition() {
		return ObjetPrecedent.getPosition();
	}

	@Override
	public int getRayon() {
		return ObjetPrecedent.getRayon();
	}

	@Override
	public Vecteur getVitesse() {
		return ObjetPrecedent.getVitesse();
	}

	@Override
	public int masse() {
		return ObjetPrecedent.masse();
	}
}
