package exodecorateur_angryballs.maladroit;

import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import exodecorateur_angryballs.maladroit.vues.CadreAngryBalls;

public class ControllerAngryBalls implements Observer {
	
	public AnimationObjets animation;
	public CadreAngryBalls cadre;
	public Vector<BoutonObservable> boutons;
	
	public ControllerAngryBalls(AnimationObjets animation, CadreAngryBalls cadre) {
		this.animation = animation;
		this.cadre = cadre;
		
		// Création et ajout des boutons de contrôl
		BoutonObservable btnLancer = new EcouteurBoutonLancer(cadre.lancerBilles);
		BoutonObservable btnArreter = new EcouteurBoutonArreter(cadre.arrêterBilles);
		
		btnLancer.addObserver(this);
		btnArreter.addObserver(this); 
		
		boutons.add(btnLancer);
		boutons.add(btnArreter);
	}

	@Override
	public void update(Observable observable, Object obj) {
		// TODO Auto-generated method stub
		if (observable instanceof EcouteurBoutonLancer)
			animation.lancerAnimation();
		if (observable instanceof EcouteurBoutonArreter)
			animation.arrêterAnimation();
	}
}
