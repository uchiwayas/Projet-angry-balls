package exodecorateur_angryballs.maladroit.vues;

import java.util.Vector;

import exodecorateur_angryballs.maladroit.modele.Objet;

public class TestCadreAngryBallsSeul {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Vector<Objet> objets = new Vector<Objet>();
		CadreAngryBalls cadre = new CadreAngryBalls("angry balls", "animation de billes marrantes", objets);
		cadre.montrer();
	}

}
