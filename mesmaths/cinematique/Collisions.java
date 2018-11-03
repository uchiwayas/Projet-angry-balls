package mesmaths.cinematique;


import mesmaths.geometrie.base.Geop;
import mesmaths.geometrie.base.Vecteur;


public class Collisions
{

static double EPSILON = 1.0E-6;

/**
 * gestion de la collision avec traversement de la paroi (et r�apparition sur le cot� oppos�) de la bille d�finie par (position) 
 *  avec un contour rectangulaire de l'�cran. 
 *  Ce rectangle est d�fini par (abscisseCoinHautGauche, Ordonn�eCoinHautGauche,largeur,hauteur)
 *  si il n'y a pas collision, le vecteur position n'est pas modifi�
 * si il y a collision, le vecteur position est modifi� : la bille "ressort" sur le bord oppos� au bord qu'elle p�n�tre.
 * @param position : vecteur position de la bille
 * 
 * */
public static void collisionBilleContourPasseMuraille(Vecteur position, 
        double abscisseCoinHautGauche, double ordonn�eCoinHautGauche, double largeur, double hauteur)
{
position.x = Collisions.traverseBord(position.x,abscisseCoinHautGauche, largeur);
position.y = Collisions.traverseBord(position.y,ordonn�eCoinHautGauche, hauteur);
}

/*
 * utile � la m�thode collisionBilleContourPasseMuraille
 * */
private static double traverseBord(double x, double xMin, double largeur)
{
double xMax = xMin + largeur;

if (x > xMax)
   {
   double d = (x-xMin)/largeur;
   double d1 = Math.floor(d);
   int q = (int)d1;
   return x - q*largeur;
   }
   
else
   if (x < xMin)
      {
      double d = (xMax-x)/largeur;
      double d1 = Math.floor(d);
      int q = (int)d1;
      return x + q*largeur;
      }
   else
      return x;
}

/**
 * gestion de la collision avec arr�t suivant la direction horizontale  de la bille d�finie par (position,rayon,vitesse) avec un contour rectangulaire de l'�cran.
 * 
 *  Ce rectangle est d�fini partiellement par (abscisseCoinHautGauche,largeur)
 * 
 * si il n'y a pas de collision avec un bord vertical, position et vitesse sont inchang�s par la m�thode
 * 
 *  si il y a collision avec un bord vertical
 * alors vitesse est modifi� et  position reste intact
 * 
 * D�s qu'il y a collision avec un bord vertical, la composante horizontale du vecteur vitesse est annul�e, de sorte que la bille continue � glisser le long de
 * la bande qui l'a arr�t�e
 * 
 */
public static void collisionBilleContourAvecArretHorizontal(Vecteur position, double rayon, Vecteur vitesse,
        double abscisseCoinHautGauche, double largeur)
{
//vitesse.x = Collisions.arretSurBord(vitesse.x, position.x, rayon, abscisseCoinHautGauche, largeur);
double t[] = Collisions.arretSurBord(vitesse.x, position.x, rayon, abscisseCoinHautGauche, largeur);
vitesse.x = t[0];
position.x = t[1];
}

/**
 * gestion de la collision avec arr�t suivant la direction verticale  de la bille d�finie par (position,rayon,vitesse) avec un contour rectangulaire de l'�cran.
 * 
 *  Ce rectangle est d�fini partiellement par (ordonn�eCoinHautgauche,hauteur)
 * 
 * si il n'y a pas de collision avec un bord horizontal, position et vitesse sont inchang�s par la m�thode
 * 
 *  si il y a collision avec un bord horizontal
 * alors vitesse est modifi� et  position reste intact
 * 
 * D�s qu'il y a collision avec un bord horizontal, la composante verticale du vecteur vitesse est annul�e, de sorte que la bille continue � glisser le long de
 * la bande qui l'a arr�t�e
 * 
 */
public static void collisionBilleContourAvecArretVertical(Vecteur position, double rayon, Vecteur vitesse,
        double ordonn�eCoinHautGauche, double hauteur)
{
//vitesse.y = Collisions.arretSurBord(vitesse.y, position.y, rayon, ordonn�eCoinHautGauche, hauteur);
double t[] = Collisions.arretSurBord(vitesse.y, position.y, rayon, ordonn�eCoinHautGauche, hauteur);
vitesse.y = t[0];
position.y = t[1];
}



/*
 * utile � collisionBilleContourAvecArretHorizontal et � collisionBilleContourAvecArretVertical
 * */
private static double [] arretSurBord(double v, double x, double rayon, double xMin, double largeur)
{
double [] t = new double[2]; //  nouvelle composante_vitesse dans t[0] et nouvelle composante_position dans t[1]

double xMax = xMin+largeur;


if (x+rayon > xMax)
    {
    t[0] = 0;
    t[1] = xMax-rayon-Collisions.EPSILON;
    }
else
    if (x-rayon < xMin)
        {
        t[0] = 0;
        t[1] = xMin + Collisions.EPSILON + rayon;
        } 
    else
        {
        t[0] = v; t[1] = x; 
        }

return t;
}

/**
 * gestion de la collision avec rebond de la bille d�finie par (position,rayon,vitesse) avec le segment orient� [P0P1]
 * 
 * @return false si il n'y a pas de collision
 * 
 * @return true si il y a collision et dans ce cas modifie vitesse
 * 
 * 
 * c-�-d que en entr�e on consid�re que (position,vitesse) sont le vecteur position et le vecteur vitesse de la bille imm�diatement avant le choc
 * et en sortie (position,vitesse) sont le vecteur position et le vecteur vitesse de la bille imm�diatement apr�s le choc
 * 
 * le vecteur vitesse est modifi� par la collision (comme une boule de billard l'est par une bande)
 * La m�thode laisse le vecteur position intact 
 * @param position : vecteur position de la bille imm�diatement avant la collision avec le segment [P0P1]
 * @param rayon : rayon de la bille
 * @param vitesse : vecteur vitesse de la bille imm�diatement avant la collision avec le segment [P0P1]
 * @param P0 : le d�but du segment
 * @param P1 : la fin du segment
 * 
 * */
public static boolean collisionBilleSegmentAvecRebond( final Vecteur position,  double rayon, Vecteur vitesse, final Vecteur P0, final Vecteur P1)
{
Vecteur[] base = Geop.base( P0,  P1);
//Vecteur I = base[0];
Vecteur N = base[1];

double distanceSign�e = position.difference(P0).produitScalaire(N);

if (distanceSign�e >= rayon)  return false; // il n'y a pas collision entre la bille (position,rayon) et le segment [P0P1]

double vN = vitesse.produitScalaire(N);

if (vN >= 0) return false; // la bille est � l'ext�rieur et revient vers le cadre ou est � l'int�rieur et s'�loigne du bord

// � pr�sent distanceSign�e < rayon et vN < 0

// -vN est l'intensit� du choc !!!!

Vecteur deltaV = N.produit(-2*vN);  // calcul du changement de trajectoire

vitesse.ajoute(deltaV); // la bille rebondit

return true;

}                           // collisionBilleSegmentAvecRebond

/**
 * gestion de la collision avec rebond de la bille d�finie par (position,rayon,vitesse) avec un contour rectangulaire de l'�cran.
 * 
 *  Ce rectangle est d�fini par (abscisseCoinHautGauche, Ordonn�eCoinHautGauche,largeur,hauteur)
 * 
 * @return false si il n'y a pas de collision
 * 
 * @return true si il y a collision et dans ce cas modifie vitesse
 * 
 * c-�-d que en entr�e on consid�re que (position,vitesse) sont le vecteur position et le vecteur vitesse de la bille imm�diatement avant le choc
 * et en sortie (position,vitesse) sont le vecteur position et le vecteur vitesse de la bille imm�diatement apr�s le choc
 * 
 * le vecteur vitesse est modifi� par la collision (comme une boule de billard l'est par une bande)
 * La m�thode laisse le vecteur position intact 
 * 
 * @param position : vecteur position de la bille imm�diatement avant la collision avec le contour
 * @param rayon : rayon de la bille
 * @param vitesse : vecteur vitesse de la bille imm�diatement avant la collision avec le contour
 * @param abscisseCoinHautGauche : abscisse minimale du contour rectangulaire
 * @param ordonn�eCoinHautGauche : ordonn�e minimale du contour rectangulaire
 * @param largeur : largeur du contour rectangulaire
 * @param hauteur : hauteur du contour rectangulaire
 * 
 * 
 * */
public static boolean collisionObjetContourAvecRebond( final Vecteur position,  double rayon, Vecteur vitesse, 
        double abscisseCoinHautGauche, double ordonn�eCoinHautGauche, double largeur, double hauteur)
{
Vecteur min = new Vecteur(abscisseCoinHautGauche,ordonn�eCoinHautGauche);
Vecteur diago = new Vecteur(largeur, hauteur);
Vecteur max = min.somme(diago); 


Vecteur coins[] = new Vecteur[5];

coins[0] = min;         // le coin haut gauche du rectangle d�fini par le composant
coins[1] = new Vecteur(max.x,min.y);
coins[2] = max;
coins[3] = new Vecteur(min.x, max.y);
coins[4] = coins[0];                    // pour refermer le contour !

int i;
for ( i = 1; i < coins.length; ++i)
    if (Collisions.collisionBilleSegmentAvecRebond(position, rayon, vitesse, coins[i-1], coins[i])) 
       return true;

return false;
}

/**
 * Effectue les calculs correspondants � la collision entre la bille b1(G1,rayon1,v1,m1) et la bille b2(G2,rayon2,v2,m2)
 * 
 * La m�thode laisse toujours G1 et G2 intacts.
 *
 * @param G1 : centre de la bille b1
 * @param rayon1 : rayon de la bille b1
 * @param v1 : vecteur vitesse de la bille b1
 * @param m1 : masse de la bille b1
 * 
 * @param G2 : centre de la bille b2
 * @param rayon2 : rayon de la bille b2
 * @param v2 : vecteur vitesse de la bille b2
 * @param m2 : masse de la bille b2
 * 
 * @return true si il y a collision et false si il n'y a pas collision
 * 
 * si retourne true, modifie les vecteurs vitesse v1 et v2
 */
public static boolean CollisionObjetObjet( final Vecteur G1, double rayon1, Vecteur v1, double m1, 
                                           final Vecteur G2, double rayon2, Vecteur v2, double m2)   
//modifie b1 et b2
//donn�es : b1 et b2 avant le choc
//r�sultats : b1 et b2 apr�s le choc
{
Vecteur G1G2 = Vecteur.difference(G2, G1);

double nG1G2_2 = G1G2.normeCarr�e();

double r = rayon1+rayon2;
double r2 = r*r;

if ( nG1G2_2 >= r2 )  return false;   // il n'y a pas de collision entre les 2 billes car elles sont trop �loign�es l'une de l'autre

double  nG1G2 = Math.sqrt(nG1G2_2);

Vecteur N = G1G2.produit(1 / nG1G2);    // vecteur unitaire sur le segment [G1 G2]

double v1N, v2N;

v1N = v1.produitScalaire(N);
v2N = v2.produitScalaire(N);

double a = v1N - v2N;

Son son = new Son();
Son.sonCollision(a, G1);
   
if (a <= 0) return false;       // les billes s�loignent l'une de l'autre, la collision a donc d�j� �t� trait�e

// � pr�sent a > 0. a repr�sente l'intensit� du choc entre les 2 billes !!!!

// calculons les nouveaux vecteurs vitesse imm�diatement apr�s le choc

double masseTotale = m1+m2;
double alfa = (m1-m2)/masseTotale;
double deuxSurM = 2/masseTotale;

double v1Np =          alfa * v1N + m2 * deuxSurM * v2N;
double v2Np = m1 * deuxSurM * v1N -          alfa * v2N;

Vecteur U = N.rotationQuartDeTour();

double v1T = v1.produitScalaire(U);
double v2T = v2.produitScalaire(U);

v1.set(Vecteur.combinaisonLin�aire(v1Np,  N , v1T, U));
v2.set(Vecteur.combinaisonLin�aire(v2Np,  N , v2T, U));

return true;

} // collisionBilleBille

} // Collisions
