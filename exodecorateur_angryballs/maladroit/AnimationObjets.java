package exodecorateur_angryballs.maladroit;

import java.util.Vector;

import exodecorateur_angryballs.maladroit.modele.Objet;
import exodecorateur_angryballs.maladroit.vues.VueBillard;

/**
 * responsable de l'animation des objets, c-à-d responsable du mouvement de la
 * liste des objets. met perpétuellement à jour les objets. gère le délai entre
 * 2 mises à jour (deltaT) et prévient la vue responsable du dessin des objets
 * qu'il faut mettre à jour la scène
 * 
 * ICI : IL N'Y A RIEN A CHANGER
 */
public class AnimationObjets implements Runnable {

	Vector<Objet> objets; // la liste de toutes les objets en mouvement
	VueBillard vueBillard; // la vue responsable du dessin des objets
	private Thread thread; // pour lancer et arrêter les objets

	private static final double COEFF = 0.5;

	/**
	 * @param objets
	 * @param vueBillard
	 */
	public AnimationObjets(Vector<Objet> objets, VueBillard vueBillard) {
		this.objets = objets;
		this.vueBillard = vueBillard;
		this.thread = null; // est-ce utile ?
	}

	@Override
	public void run() {
		try {
			double deltaT; // délai entre 2 mises à jour de la liste des objets
			Objet objetCourant;

			double minRayons = AnimationObjets.minRayons(objets); // nécessaire
																	// au calcul
																	// de deltaT
			double minRayons2 = minRayons * minRayons; // nécessaire au calcul
														// de deltaT

			while (!Thread.interrupted()) // gestion du mouvement
			{
				// deltaT = COEFF*minRayons2/(1+maxVitessesCarrées(objets)); //
				// mise à jour deltaT. L'addition + 1 est une astuce pour éviter
				// les divisions par zéro

				// System.err.println("deltaT = " + deltaT);
				deltaT = 5;

				int i;
				for (i = 0; i < objets.size(); ++i) // mise à jour de la liste
													// des objets
				{
					objetCourant = objets.get(i);
					objetCourant.déplacer(deltaT); // mise à jour position et vitesse de cette objet
					objetCourant.gestionAccélération(objets); // calcul de l'accélération subie par cette objet
					objetCourant.gestionCollisionObjetObjet(objets);
					objetCourant.collisionContour(0, 0, vueBillard.largeurBillard(), vueBillard.hauteurBillard()); // System.err.println("objets = " + objets);
				}

				vueBillard.miseAJour(); // on prévient la vue qu'il faut
										// redessiner les objets

				Thread.sleep((int) deltaT); // deltaT peut être considéré comme
											// le délai entre 2 flashes d'un
											// stroboscope qui éclairerait la
											// scène
			}
		}

		catch (InterruptedException e) {
			/* arrêt normal, il n'y a rien à faire dans ce cas */
		}

	}

	/**
	 * calcule le maximum de de la norme carrée (pour faire moins de calcul) des
	 * vecteurs vitesse de la liste de objets
	 * 
	 */
	static double maxVitessesCarrées(Vector<Objet> objets) {
		double vitesse2Max = 0;

		int i;
		double vitesse2Courante;

		for (i = 0; i < objets.size(); ++i)
			if ((vitesse2Courante = objets.get(i).getVitesse().normeCarrée()) > vitesse2Max)
				vitesse2Max = vitesse2Courante;

		return vitesse2Max;
	}

	/**
	 * calcule le minimum des rayons de la liste des objets
	 * 
	 * 
	 */
	static double minRayons(Vector<Objet> objets) {
		double rayonMin, rayonCourant;

		rayonMin = Double.MAX_VALUE;

		int i;
		for (i = 0; i < objets.size(); ++i)
			if ((rayonCourant = objets.get(i).getRayon()) < rayonMin)
				rayonMin = rayonCourant;

		return rayonMin;
	}

	public void lancerAnimation() {
		if (this.thread == null) {
			this.thread = new Thread(this);
			thread.start();
		}
	}

	public void arrêterAnimation() {
		if (thread != null) {
			this.thread.interrupt();
			this.thread = null;
		}
	}
}
