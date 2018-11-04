package exodecorateur_angryballs.maladroit;

import java.awt.Color;
import java.util.Vector;

import mesmaths.geometrie.base.Vecteur;

import exodecorateur_angryballs.maladroit.modele.Bille;
import exodecorateur_angryballs.maladroit.modele.BilleMvtNewtonArret;
import exodecorateur_angryballs.maladroit.modele.BilleMvtNewtonFrottementRebond;
import exodecorateur_angryballs.maladroit.modele.BilleMvtRUPasseMurailles;
import exodecorateur_angryballs.maladroit.modele.Objet;
import exodecorateur_angryballs.maladroit.modele.*;
import exodecorateur_angryballs.maladroit.modele.BilleMvtPesanteurFrottementRebond;
import exodecorateur_angryballs.maladroit.vues.CadreAngryBalls;

/**
 * Gestion d'une liste de billes en mouvement ayant toutes un comportement
 * différent
 * 
 * Idéal pour mettre en place le DP decorator
 */
public class TestAngryBalls {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// ------------------- création de la liste (pour l'instant vide) des
		// billes -----------------------

		Vector<Objet> billes = new Vector<Objet>();

		// ---------------- création de la vue responsable du dessin des billes
		// -------------------------

		CadreAngryBalls cadre = new CadreAngryBalls("Angry balls",
				"Animation de billes ayant des comportements différents. Situation idéale pour mettre en place le DP Decorator",
				billes);

		cadre.montrer(); // on rend visible la vue

		// ------------- remplissage de la liste avec 4 billes
		// -------------------------------

		double xMax, yMax;
		double vMax = 0.5;
		xMax = cadre.largeurBillard(); // abscisse maximal
		yMax = cadre.hauteurBillard(); // ordonnée maximale

		double rayon = 0.05 * Math.min(xMax, yMax); // rayon des billes : ici
													// toutes les billes ont le
													// même rayon, mais ce n'est
													// pas obligatoire

		Vecteur p0, p1, p2, p3, p4, v0, v1, v2, v3, v4; // les positions des
														// centres des billes et
														// les vecteurs vitesse
														// au démarrage.
														// Elles vont être
														// choisies
														// aléatoirement

		// ------------------- création des vecteurs position des billes
		// ---------------------------------

		p0 = Vecteur.créationAléatoire(0, 0, xMax, yMax);
		p1 = Vecteur.créationAléatoire(0, 0, xMax, yMax);
		p2 = Vecteur.créationAléatoire(0, 0, xMax, yMax);
		p3 = Vecteur.créationAléatoire(0, 0, xMax, yMax);
		p4 = Vecteur.créationAléatoire(0, 0, xMax, yMax);

		// ------------------- création des vecteurs vitesse des billes
		// ---------------------------------

		v0 = Vecteur.créationAléatoire(-vMax, -vMax, vMax, vMax);
		v1 = Vecteur.créationAléatoire(-vMax, -vMax, vMax, 0);
		v2 = Vecteur.créationAléatoire(-vMax, -vMax, vMax, vMax);
		v3 = Vecteur.créationAléatoire(-vMax, -vMax, vMax, vMax);
		v4 = Vecteur.créationAléatoire(-vMax, -vMax, vMax, vMax);

		// --------------- ici commence la partie à changer
		// ---------------------------------

		billes.add(new ObjetAvecEffetDragAndDrop(cadre, new ObjetAvecEffetRebond(new ObjetAvecEffetRU(new Bille(p0, rayon, v0, Color.red))))); // RU et rebond
		//billes.add(new ObjetAvecEffetFrottementVisqueux(new ObjetAvecEffetRebond(new ObjetAvecEffetPesanteur(new Bille(p1, rayon, v1, Color.yellow), new Vecteur(0, 0.001))))); //Pesanteur, rebond, frottement
		//billes.add(new ObjetAvecEffetFrottementVisqueux(new ObjetAvecEffetRebond(new ObjetAvecEffetNewton(new Bille(p2, rayon, v2, Color.green))))); //Newton, frottement, rebond
		//billes.add(new ObjetAvecEffetFranchissement(new ObjetAvecEffetRU(new Bille(p3, rayon, v3, Color.cyan)))); // RU et passe murailles
		//billes.add(new ObjetAvecEffetBloque(new ObjetAvecEffetNewton(new Bille(p4, rayon, v4, Color.black)))); // Newton et Arret

		// ---------------------- ici finit la partie à changer
		// -------------------------------------------------------------

		System.out.println("billes = " + billes);

		// -------------------- création de l'objet responsable de l'animation
		// (c'est un thread séparé) ------------------------------------

		AnimationObjets animationBilles = new AnimationObjets(billes, cadre);

		// ----------------------- mise en place des écouteurs de boutons qui
		// permettent de contrôler l'application ------------------------

		ControllerAngryBalls controller = new ControllerAngryBalls(animationBilles, cadre); // Permet de relier la vue du cadre et l'animation

	}

}
