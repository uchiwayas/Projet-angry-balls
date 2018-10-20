package exodecorateur_angryballs.maladroit.modele;

import java.util.Vector;

import mesmaths.cinematique.Collisions;
import mesmaths.geometrie.base.Vecteur;
import mesmaths.mecanique.MecaniquePoint;

/**
 * 
 * 
 * Op�rations utiles sur les billes
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
	 * @return la liste des autres billes que cetteBille, c'est-�-dire la liste
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
	 *            : une bille particuli�re
	 * @param billes
	 *            : une liste de billes, cette liste peut contenir cettebille
	 *
	 *            gestion de l'�ventuelle collision de cette bille avec les
	 *            autres billes
	 *
	 *            billes est la liste de toutes les billes en mouvement
	 * 
	 *            Le comportement par d�faut est le choc parfaitement �lastique
	 *            (c-�-d rebond sans amortissement)
	 * 
	 * @return true si il y a collision et dans ce cas les positions et vecteurs
	 *         vitesses des 2 billes impliqu�es dans le choc sont modifi�es si
	 *         renvoie false, il n'y a pas de collision et les billes sont
	 *         laiss�es intactes
	 */
	public static boolean gestionCollisionObjetObjet(Objet cetObjet, Vector<Objet> Objets) {
		// --- on r�cup�re d'abord dans autresBilles toutes les billes sauf
		// cetteBille ----

		Vector<Objet> autresObjets = OutilsObjet.autresObjets(cetObjet, Objets);

		// --- on cherche � pr�sent la 1�re des autres billes avec laquelle
		// cetteBille est en collision ---------------------
		// -------------- on suppose qu'il ne peut y avoir de collision qui
		// implique plus de deux billes � la fois ---------------

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
	 *            : une bille particuli�re
	 * @param billes
	 *            : une liste de billes, cette liste peut contenir cettebille
	 * 
	 *            On suppose que cetteBille subit l'attraction gravitationnelle
	 *            due aux billes contenues dans "billes" autres que cetteBille.
	 * 
	 *            t�che : calcule a, le vecteur acc�l�ration subi par cetteBille
	 *            r�sultant de l'attraction par les autres billes de la liste.
	 * 
	 * @return a : le vecteur acc�l�ration r�sultant
	 * 
	 */
	public static Vecteur gestionAcc�l�rationNewton(Objet cetObjet, Vector<Objet> Objets) {

		// --- on r�cup�re d'abord dans autresBilles toutes les billes sauf
		// celle-ci ----

		Vector<Objet> autresObjets = OutilsObjet.autresObjets(cetObjet, Objets);

		// -------------- � pr�sent on r�cup�re les masses et les positions des
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

		// ------------------ � pr�sent on calcule le champ de gravit� exerc�
		// par les autres billes sur cette bille ------------------

		return MecaniquePoint.champGravit�Global(cetObjet.getPosition(), masses, C);
	}
}
