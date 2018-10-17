package mesmaths.cinematique;

import mesmaths.geometrie.base.Vecteur;

/**
 * Arc au sens large : rectiligne ou curviligne
 * Repr�sente une partie d'un contour ferm�.
 * 
 * 
 * */
public interface Arc
{

/**
 * Calcule une base orthonormale de 2 vecteurs {i,j} au point d'abscisse curviligne t.
 * On doit avoir 0 <= t <= 1. t == 0 <---> d�but de l' arc et t == 1 <---> fin de l' arc 
 * Cette base est telle que i soit un vecteur tangent � l'arc et j soit un vecteur rentrant dans le contour.
 * j indique o� se trouve l'int�rieur du contour ferm�. 
 * |i| == |j| == 1 et i.j == 0
 * @return un tableau t de 2 �l�ments tel que t[0] = i et tel que t[1] = j
 * */
public Vecteur [] base(double t);

/**
 * Calcule la distance sign�e entre le point p et l'arc this. 
 * Si cette distance est > 0, p est � l'int�rieur du contour
 * Si cette distance est < 0, p est � l'ext�rieur du contour
 * Si cette distance est == 0, p est � sur l'arc
 * 
 * */
public double distanceSign�e(Vecteur p);

/**
 * Calcule l'abscisse curviligne du projet� orthogonal de p sur this
 * 
 * */
public abstract double projet�Orthogonal(Vecteur p);
/**
 * Calcule l'oppos� de p suivant la direction et le sens d�fini par le projet� orthogonal N au contour C dont this fait partie.
 * Explication : Le point trouv� est unique. 
 * Il est d�fini comme suit :
 * 
 * Notons d la droite munie du rep�re orient� {p,n} avec p comme point de passage et n vecteur directeur de la droite orthogonale � this passant par p
 * On suppose que p est dans le contour. n orient� de p vers N.
 * La droite coupe le contour C en 2 points A et B d'�loignement maximal puisque C est ferm� et que p est � l'int�rieur de C.
 * Supposons que les points A, p et B soient orient�s dans le sens A < p < B (suivant n).
 * Notons xA, xp,xB les abscisses respectifs de A, p et B suivant le rep�re {p,n}
 * 
 * notons deltaX = xB - xp.
 * 
 * Alors retourne le point M tel que vecteur(M ->A) == deltaX * n
 * 
 * */
public Vecteur oppos�(Vecteur p);

/**
 * Calcule, sur l'arc this, le point de passage  correspondant � l'abscisse curviligne t.
 * 
 * */
public Vecteur �value(double t);

}
