package exodecorateur_angryballs.maladroit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ICI : IL N'Y A RIEN A CHANGER
 * 
 */

public class EcouteurBoutonArreter implements ActionListener {
	AnimationObjets animationBilles;

	public EcouteurBoutonArreter(AnimationObjets animationBilles) {
		this.animationBilles = animationBilles;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.animationBilles.arrêterAnimation();
	}

}
