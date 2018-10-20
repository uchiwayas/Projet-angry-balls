package exodecorateur_angryballs.maladroit.modele;

import java.util.Vector;

import mesmaths.cinematique.Collisions;
import mesmaths.geometrie.base.Vecteur;
import mesmaths.mecanique.MecaniquePoint;

/**
 * 
 * 
 * Opérations utiles sur les billes
 * 
 * ICI : IL N'Y A RIEN A CHANGER
 * 
 */

public class OutilsObjet {
	/**
	 * @param billes
	 *            est la liste de TOUTES les billes en mouvement
	 * @param cetteBille
	 *            est l'une d'entre elles.
	 * @return la liste des autres billes que cetteBille, c'est-à-dire la liste
	 *         de toutes les billes sauf cetteBille
	 * 
	 */
	public static Vector<Objet> autresObjets(Objet cetObjet, Vector<Objet> Objets) {
		Vector<Objet> autresObjets = new Vector<Objet>();

		Objet ObjetCourant;

		int i;

		for (i = 0; i < Objets.size(); ++i) {
			ObjetCourant = Objets.get(i);
			if (ObjetCourant.getClef() != cetObjet.getClef())
				autresObjets.add(ObjetCourant);
		}

		return autresObjets;
	}

	/**
	 * @param cetteBille
	 *            : une bille particulière
	 * @param billes
	 *            : une liste de billes, cette liste peut contenir cettebille
	 *
	 *            gestion de l'éventuelle collision de cette bille avec les
	 *            autres billes
	 *
	 *            billes est la liste de toutes les billes en mouvement
	 * 
	 *            Le comportement par défaut est le choc parfaitement élastique
	 *            (c-à-d rebond sans amortissement)
	 * 
	 * @return true si il y a collision et dans ce cas les positions et vecteurs
	 *         vitesses des 2 billes impliquées dans le choc sont modifiées si
	 *         renvoie false, il n'y a pas de collision et les billes sont
	 *         laissées intactes
	 */
	public static boolean gestionCollisionObjetObjet(Objet cetObjet, Vector<Objet> Objets) {
		// --- on récupère d'abord dans autresBilles toutes les billes sauf
		// cetteBille ----

		Vector<Objet> autresObjets = OutilsObjet.autresObjets(cetObjet, Objets);

		// --- on cherche à présent la 1ère des autres billes avec laquelle
		// cetteBille est en collision ---------------------
		// -------------- on suppose qu'il ne peut y avoir de collision qui
		// implique plus de deux billes à la fois ---------------

		Objet objetCourant;

		int i;

		for (i = 0; i < autresObjets.size(); ++i) {
			objetCourant = autresObjets.get(i);
			if (Collisions.CollisionBilleBille(cetObjet.getPosition(), cetObjet.getRayon(), cetObjet.getVitesse(),
					cetObjet.masse(), objetCourant.getPosition(), objetCourant.getRayon(), objetCourant.getVitesse(),
					objetCourant.masse()))
				return true;
		}
		return false;
	}

	/**
	 * @param cetteBille
	 *            : une bille particulière
	 * @param billes
	 *            : une liste de billes, cette liste peut contenir cettebille
	 * 
	 *            On suppose que cetteBille subit l'attraction gravitationnelle
	 *            due aux billes contenues dans "billes" autres que cetteBille.
	 * 
	 *            tâche : calcule a, le vecteur accélération subi par cetteBille
	 *            résultant de l'attraction par les autres billes de la liste.
	 * 
	 * @return a : le vecteur accélération résultant
	 * 
	 */
	public static Vecteur gestionAccélérationNewton(Objet cetObjet, Vector<Objet> Objets) {

		// --- on récupère d'abord dans autresBilles toutes les billes sauf
		// celle-ci ----

		Vector<Objet> autresObjets = OutilsObjet.autresObjets(cetObjet, Objets);

		// -------------- à présent on récupère les masses et les positions des
		// autres billes ------------------
		int i;
		Objet objetCourant;

		int d = autresObjets.size();

		double masses[] = new double[d]; // les masses des autres billes
		Vecteur C[] = new Vecteur[d]; // les positions des autres billes

		for (i = 0; i < d; ++i) {
			objetCourant = autresObjets.get(i);
			masses[i] = objetCourant.masse();
			C[i] = objetCourant.getPosition();
		}

		// ------------------ à présent on calcule le champ de gravité exercé
		// par les autres billes sur cette bille ------------------

		return MecaniquePoint.champGravitéGlobal(cetObjet.getPosition(), masses, C);
	}
}
