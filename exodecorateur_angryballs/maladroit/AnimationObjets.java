package exodecorateur_angryballs.maladroit;

import java.util.Vector;

import exodecorateur_angryballs.maladroit.modele.Objet;
import exodecorateur_angryballs.maladroit.vues.VueBillard;

/**
 * responsable de l'animation des objets, c-�-d responsable du mouvement de la
 * liste des objets. met perp�tuellement � jour les objets. g�re le d�lai entre
 * 2 mises � jour (deltaT) et pr�vient la vue responsable du dessin des objets
 * qu'il faut mettre � jour la sc�ne
 * 
 * ICI : IL N'Y A RIEN A CHANGER
 */
public class AnimationObjets implements Runnable {

	Vector<Objet> objets; // la liste de toutes les objets en mouvement
	VueBillard vueBillard; // la vue responsable du dessin des objets
	private Thread thread; // pour lancer et arr�ter les objets

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
			double deltaT; // d�lai entre 2 mises � jour de la liste des objets
			Objet objetCourant;

			double minRayons = AnimationObjets.minRayons(objets); // n�cessaire
																	// au calcul
																	// de deltaT
			double minRayons2 = minRayons * minRayons; // n�cessaire au calcul
														// de deltaT

			while (!Thread.interrupted()) // gestion du mouvement
			{
				// deltaT = COEFF*minRayons2/(1+maxVitessesCarr�es(objets)); //
				// mise � jour deltaT. L'addition + 1 est une astuce pour �viter
				// les divisions par z�ro

				// System.err.println("deltaT = " + deltaT);
				deltaT = 5;

				int i;
				for (i = 0; i < objets.size(); ++i) // mise � jour de la liste
													// des objets
				{
					objetCourant = objets.get(i);
					objetCourant.d�placer(deltaT); // mise � jour position et vitesse de cette objet
					objetCourant.gestionAcc�l�ration(objets); // calcul de l'acc�l�ration subie par cette objet
					objetCourant.gestionCollisionObjetObjet(objets);
					objetCourant.collisionContour(0, 0, vueBillard.largeurBillard(), vueBillard.hauteurBillard()); // System.err.println("objets = " + objets);
				}

				vueBillard.miseAJour(); // on pr�vient la vue qu'il faut
										// redessiner les objets

				Thread.sleep((int) deltaT); // deltaT peut �tre consid�r� comme
											// le d�lai entre 2 flashes d'un
											// stroboscope qui �clairerait la
											// sc�ne
			}
		}

		catch (InterruptedException e) {
			/* arr�t normal, il n'y a rien � faire dans ce cas */
		}

	}

	/**
	 * calcule le maximum de de la norme carr�e (pour faire moins de calcul) des
	 * vecteurs vitesse de la liste de objets
	 * 
	 */
	static double maxVitessesCarr�es(Vector<Objet> objets) {
		double vitesse2Max = 0;

		int i;
		double vitesse2Courante;

		for (i = 0; i < objets.size(); ++i)
			if ((vitesse2Courante = objets.get(i).getVitesse().normeCarr�e()) > vitesse2Max)
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

	public void arr�terAnimation() {
		if (thread != null) {
			this.thread.interrupt();
			this.thread = null;
		}
	}
}
