package exodecorateur_angryballs.maladroit.modele;

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Vector;

import exodecorateur_angryballs.maladroit.vues.CadreAngryBalls;

public abstract class ObjetSourisListener extends ObjetAvecEffetAcceleration implements MouseListener, MouseMotionListener {
	CadreAngryBalls cadre;

	public ObjetSourisListener(CadreAngryBalls cadre, Objet o) {
		super(o);
		this.cadre = cadre;
		cadre.getBillard().addMouseListener(this);
		cadre.getBillard().addMouseMotionListener(this);
	}
	
	@Override
	public void gestionAccélération(Vector<Objet> objets) {
		ObjetOriginal.gestionAccélération(objets);
	}

}
