package exodecorateur_angryballs.maladroit.modele;

import java.util.Vector;

import mesmaths.cinematique.Collisions;
import mesmaths.geometrie.base.Vecteur;
import mesmaths.mecanique.MecaniquePoint;

/**
 * 
 * 
 * Op�rations utiles sur les objets
 * 
 * ICI : IL N'Y A RIEN A CHANGER
 * 
 */

public class OutilsObjet {
	/**
	 * @param objets
	 *            est la liste de TOUTES les objets en mouvement
	 * @param cetteObjet
	 *            est l'une d'entre elles.
	 * @return la liste des autres objets que cetteObjet, c'est-�-dire la liste
	 *         de toutes les objets sauf cetteObjet
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
	 * @param cetteObjet
	 *            : une objet particuli�re
	 * @param objets
	 *            : une liste de objets, cette liste peut contenir cetteobjet
	 *
	 *            gestion de l'�ventuelle collision de cette objet avec les
	 *            autres objets
	 *
	 *            objets est la liste de toutes les objets en mouvement
	 * 
	 *            Le comportement par d�faut est le choc parfaitement �lastique
	 *            (c-�-d rebond sans amortissement)
	 * 
	 * @return true si il y a collision et dans ce cas les positions et vecteurs
	 *         vitesses des 2 objets impliqu�es dans le choc sont modifi�es si
	 *         renvoie false, il n'y a pas de collision et les objets sont
	 *         laiss�es intactes
	 */
	public static boolean gestionCollisionObjetObjet(Objet cetObjet, Vector<Objet> Objets, double width) {
		// --- on r�cup�re d'abord dans autresObjets toutes les objets sauf
		// cetteObjet ----

		Vector<Objet> autresObjets = OutilsObjet.autresObjets(cetObjet, Objets);

		// --- on cherche � pr�sent la 1�re des autres objets avec laquelle
		// cetteObjet est en collision ---------------------
		// -------------- on suppose qu'il ne peut y avoir de collision qui
		// implique plus de deux objets � la fois ---------------

		Objet objetCourant;

		int i;

		for (i = 0; i < autresObjets.size(); ++i) {
			objetCourant = autresObjets.get(i);
			if (Collisions.CollisionObjetObjet(cetObjet.getPosition(), cetObjet.getRayon(), cetObjet.getVitesse(),
					cetObjet.masse(), objetCourant.getPosition(), objetCourant.getRayon(), objetCourant.getVitesse(),
					objetCourant.masse(), width))
				return true;
		}
		return false;
	}

	/**
	 * @param cetteObjet
	 *            : une objet particuli�re
	 * @param objets
	 *            : une liste de objets, cette liste peut contenir cetteobjet
	 * 
	 *            On suppose que cetteObjet subit l'attraction gravitationnelle
	 *            due aux objets contenues dans "objets" autres que cetteObjet.
	 * 
	 *            t�che : calcule a, le vecteur acc�l�ration subi par cetteObjet
	 *            r�sultant de l'attraction par les autres objets de la liste.
	 * 
	 * @return a : le vecteur acc�l�ration r�sultant
	 * 
	 */
	public static Vecteur gestionAcc�l�rationNewton(Objet cetObjet, Vector<Objet> Objets) {

		// --- on r�cup�re d'abord dans autresObjets toutes les objets sauf
		// celle-ci ----

		Vector<Objet> autresObjets = OutilsObjet.autresObjets(cetObjet, Objets);

		// -------------- � pr�sent on r�cup�re les masses et les positions des
		// autres objets ------------------
		int i;
		Objet objetCourant;

		int d = autresObjets.size();

		double masses[] = new double[d]; // les masses des autres objets
		Vecteur C[] = new Vecteur[d]; // les positions des autres objets

		for (i = 0; i < d; ++i) {
			objetCourant = autresObjets.get(i);
			masses[i] = objetCourant.masse();
			C[i] = objetCourant.getPosition();
		}

		// ------------------ � pr�sent on calcule le champ de gravit� exerc�
		// par les autres objets sur cette objet ------------------

		return MecaniquePoint.champGravit�Global(cetObjet.getPosition(), masses, C);
	}
	
}
