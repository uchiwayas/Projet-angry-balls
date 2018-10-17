package mesmaths.cinematique;

import mesmaths.geometrie.base.Vecteur;

/**
 * Arc au sens large : rectiligne ou curviligne
 * Représente une partie d'un contour fermé.
 * 
 * 
 * */
public interface Arc
{

/**
 * Calcule une base orthonormale de 2 vecteurs {i,j} au point d'abscisse curviligne t.
 * On doit avoir 0 <= t <= 1. t == 0 <---> début de l' arc et t == 1 <---> fin de l' arc 
 * Cette base est telle que i soit un vecteur tangent à l'arc et j soit un vecteur rentrant dans le contour.
 * j indique où se trouve l'intérieur du contour fermé. 
 * |i| == |j| == 1 et i.j == 0
 * @return un tableau t de 2 éléments tel que t[0] = i et tel que t[1] = j
 * */
public Vecteur [] base(double t);

/**
 * Calcule la distance signée entre le point p et l'arc this. 
 * Si cette distance est > 0, p est à l'intérieur du contour
 * Si cette distance est < 0, p est à l'extérieur du contour
 * Si cette distance est == 0, p est à sur l'arc
 * 
 * */
public double distanceSignée(Vecteur p);

/**
 * Calcule l'abscisse curviligne du projeté orthogonal de p sur this
 * 
 * */
public abstract double projetéOrthogonal(Vecteur p);
/**
 * Calcule l'opposé de p suivant la direction et le sens défini par le projeté orthogonal N au contour C dont this fait partie.
 * Explication : Le point trouvé est unique. 
 * Il est défini comme suit :
 * 
 * Notons d la droite munie du repère orienté {p,n} avec p comme point de passage et n vecteur directeur de la droite orthogonale à this passant par p
 * On suppose que p est dans le contour. n orienté de p vers N.
 * La droite coupe le contour C en 2 points A et B d'éloignement maximal puisque C est fermé et que p est à l'intérieur de C.
 * Supposons que les points A, p et B soient orientés dans le sens A < p < B (suivant n).
 * Notons xA, xp,xB les abscisses respectifs de A, p et B suivant le repère {p,n}
 * 
 * notons deltaX = xB - xp.
 * 
 * Alors retourne le point M tel que vecteur(M ->A) == deltaX * n
 * 
 * */
public Vecteur opposé(Vecteur p);

/**
 * Calcule, sur l'arc this, le point de passage  correspondant à l'abscisse curviligne t.
 * 
 * */
public Vecteur évalue(double t);

}
