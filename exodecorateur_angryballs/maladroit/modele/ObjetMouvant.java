package exodecorateur_angryballs.maladroit.modele;

import mesmaths.geometrie.base.Vecteur;

public abstract class ObjetMouvant extends Objet {

	public  Vecteur position;   // centre de l'objet
	public  Vecteur vitesse;
	public  Vecteur accélération;
	public int clef; 
	
}
