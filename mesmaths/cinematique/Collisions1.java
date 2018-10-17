package mesmaths.cinematique;

import java.util.Observable;
import java.util.Vector;

import mesmaths.MesMaths;
import mesmaths.geometrie.base.Vecteur;

public class Collisions1 extends Observable
{
public static final Collisions1 collisions1;    // pour mettre en oeuvre Observable, il nous faut au moins une instance de Collisions1
static
{
collisions1 = new Collisions1();
}

public static  double COEFF_ELASTICITE_BILLE = 450;
public static  double COEFF_ELASTICITE_PAROI = 450;
static double EPSILON = 1.0E-6;
static final double EPSILON_CHOC_BILLE = 1.0E-2;  // en de�a de cette valeur pour |u.(v1-v2)| le choc entre 2 billes est consid�r� statique
static final double EPSILON_CHOC_PAROI = 1.0E-2;  // en de�a de cette valeur pour |J.v| le choc entre une bille et la paroi est consid�r� statique 
public static final int PAS_DE_CHOC = 0;
public static final int CHOC_DYNAMIQUE = 1;
public static final int CHOC_MOU = 2;
/**
 * gestion de la collision avec traversement de la paroi (et r�apparition sur le cot� oppos�) de la bille d�finie par (position) 
 *  avec un contour rectangulaire de l'�cran. 
 *  Ce rectangle est d�fini par (abscisseCoinHautGauche, Ordonn�eCoinHautGauche,largeur,hauteur)
 *  si il n'y a pas collision, le vecteur position n'est pas modifi�
 * si il y a collision, le vecteur position est modifi� : la bille "ressort" sur le bord oppos� au bord qu'elle p�n�tre.
 * @param position : vecteur position de la bille
 * 
 * */
public static boolean actionReactionArcPasseMuraille(Vecteur position, double rayon, Vecteur vitesse, Arc arc)
{
double t = arc.projet�Orthogonal(position);
if (arc.distanceSign�e(position) <= rayon && vitesse.produitScalaire(arc.base(t)[1]) <= 0)
   {
   position.set(arc.oppos�(position));
   return true;
   }
else
   return false;
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
public static boolean actionReactionArcArret(Vecteur position, double rayon, Vecteur vitesse, Arc arc)
{
double t = arc.projet�Orthogonal(position);
Vecteur [] base = arc.base(t);
double vN = vitesse.produitScalaire(base[1]);
if (arc.distanceSign�e(position) <= rayon && vN <= 0)
   {
   Vecteur vs1 = base[1].produit(vN);
   vitesse.retire(vs1);
   return true;
   }
else
   return false;
}

public static int actionReactionArcTousRebonds( final Vecteur position, final double rayon, final Vecteur vitesse, final double w [], 
        Vecteur acc�l�ration, final double masse, 
       final Arc arc, Vector<Choc> intensit�Choc, Vecteur [] base, double vNp[], double vT[],  double rw[], double vC[])
{
double d = arc.distanceSign�e(position);  // d est la distance sign�e entre le segment et la bille

if (d >= rayon) return PAS_DE_CHOC; // il n'y a pas de collision

//� pr�sent, d < rayon

double x = arc.projet�Orthogonal(position);
Vecteur[] base1 = arc.base(x);
base[0] = base1[0];
base[1] = base1[1];
Vecteur N = base[1];

double vN = vitesse.produitScalaire(N);

if (vN >= 0) return PAS_DE_CHOC; // la bille revient vers le bord depuis l'ext�rieur ou bien s'�loigne du bord depuis l'int�rieur: il n'y a pas de collision

//� pr�sent, on a forc�ment : vN < 0 et d < rayon

Vecteur i = base[0];
vT[0] = vitesse.produitScalaire(i);
rw[0] = rayon*w[0];
double c = vT[0]+rw[0];
vC[0] = Math.abs(c);

if (vN >= -EPSILON_CHOC_PAROI && vC[0] <= EPSILON_CHOC_PAROI)          // -EPSILON_CHOC_PAROI <= vN[0] < 0  : le choc est mou
   {
   // calcul de la force de rappel
    
   double e = rayon - d;                    // profondeur de la pr�n�tration de la bille dans le mur, on a e > 0
    
   double forceRappel = COEFF_ELASTICITE_PAROI*e;
   
   Vecteur a = N.produit(forceRappel/masse);
   acc�l�ration.ajoute(a);              
    
   return CHOC_MOU;
   }

else                // le choc est dynamique
   {
   // d'abord calcul du vecteur vitesse r�fl�chi
   vNp[0] = -vN;
   //intensit�Choc.add( vNp[0]);
   intensit�Choc.add( new Choc( arc.�value(x),vNp[0]));
   return CHOC_DYNAMIQUE;
   }
}

/**
 * gestion de la collision dynamique (avec rebond) ou statique  de la bille d�finie par (position,rayon,vitesse,acc�l�ration) avec un contour rectangulaire de l'�cran.
 * 
 *  Ce rectangle est d�fini par (abscisseCoinHautGauche, Ordonn�eCoinHautGauche,largeur,hauteur)
 * 
 * @return une valeur <= 0 si il n'y a pas de collision
 * 
 * @return une valeur > 0 si il y a collision et dans ce cas modifie vitesse et acc�l�ration. Dans ce cas aussi, la valeur retourn�e mesure l'intensit� du choc
 * 
 * c-�-d que en entr�e on consid�re que (position,vitesse,acc�l�ration) sont le vecteur position, le vecteur vitesse et le vecteur acc�l�ration de la bille imm�diatement avant le choc
 * et en sortie (position,vitesse,acc�l�ration) sont le vecteur position, le vecteur vitesse et le vecteur acc�l�ration de la bille imm�diatement apr�s le choc
 * 
 * si le choc est parfaitement dynamique, le vecteur vitesse est modifi� par la collision (comme une boule de billard l'est par une bande)
 * si le choc est parfaitement statique, le vecteur acc�l�ration est modifi� (on consid�re que le choc est �lastique et une force de rappel est appliqu�e � la bille)
 * Dans toutes les situations interm�diaires, le vecteur vitesse et le vecteur acc�l�ration sont modifi�s suivant une pond�ration qui cherche un compromis entre
 * situation dynamique et situation statique
 * 
 * @param position : vecteur position de la bille imm�diatement avant la collision avec le contour
 * @param rayon : rayon de la bille
 * @param vitesse : vecteur vitesse de la bille imm�diatement avant la collision avec le contour
 * @param acc�l�ration : vecteur acc�l�ration de la bille imm�diatement avant la collision avec le contour
 * @param abscisseCoinHautGauche : abscisse minimale du contour rectangulaire
 * @param ordonn�eCoinHautGauche : ordonn�e minimale du contour rectangulaire
 * @param largeur : largeur du contour rectangulaire
 * @param hauteur : hauteur du contour rectangulaire
 * 
 * 
 * */
public static boolean actionReactionArcAvecRebond( Vecteur position, double rayon, Vecteur vitesse, Vecteur acc�l�ration, double masse, 
        Arc arc, Vector<Choc> intensit�Choc)
{
/*double d = arc.distanceSign�e(position);  // d est la distance sign�e entre le segment et la bille

if (d >= rayon) return false; // il n'y a pas de collision

//� pr�sent, d < rayon

double x = arc.projet�Orthogonal(position);
Vecteur[] base = arc.base(x);
Vecteur N = base[1];

double vN = vitesse.produitScalaire(N);

if (vN>=0) return false; // la bille revient vers le bord depuis l'ext�rieur ou bien s'�loigne du bord depuis l'int�rieur: il n'y a pas de collision

//� pr�sent, on a forc�ment : vN < 0 et d < rayon

Vecteur i = base[0];
double vT = vitesse.produitScalaire(i);
double c = vT;
double vc = Math.abs(c);

if (vN >= -EPSILON_CHOC_PAROI && vc <= EPSILON_CHOC_PAROI)          // -EPSILON_CHOC_PAROI <= vN < 0  : le choc est mou
   {
   // calcul de la force de rappel
    
   double e = rayon - d;                    // profondeur de la pr�n�tration de la bille dans le mur, on a e > 0
    
   double forceRappel = COEFF_ELASTICITE_PAROI*e;
   
   Vecteur a = N.produit(forceRappel/masse);
   acc�l�ration.ajoute(a);              
    
   return true;
   }

else                // le choc est dynamique
   {
   // d'abord calcul du vecteur vitesse r�fl�chi
   double vN1 = -vN;*/

double [] w = {0};
Vecteur [] base = new Vecteur[2];
double [] vNp = new double[1];
double [] vT = new double[1];
double [] rw = new double[1];
double [] vC = new double[1];

int ok = actionReactionArcTousRebonds(position, rayon, vitesse, w, acc�l�ration, masse, arc, intensit�Choc, base, vNp, vT, rw, vC);

if (ok == PAS_DE_CHOC) return false;
else
   if (ok == CHOC_MOU) return true;
   else
      {
      Vecteur deltaV = base[1].produit(2*vNp[0]);      // deltaV = vecteur vitesse r�fl�chi - vecteur vitesse incident
      vitesse.ajoute(deltaV);
      return true;
      }
}                   // actionReactionBilleContourAvecRebond

/*public static boolean actionReactionArcAvecRebond( Vecteur position, double rayon, Vecteur vitesse, Vecteur acc�l�ration, double masse, 
        Arc arc, Vector<Choc> intensit�Choc)*/

public static boolean actionReactionBilleArcAvecRebondEtEffet( final Vecteur position, final double rayon, Vecteur vitesse, double w [], 
         Vecteur acc�l�ration, final double masse, final double J,
        final Arc arc, final double coeffFrottement, Vector<Choc> intensit�Choc)
{
/*double d = arc.distanceSign�e(position);  // d est la distance sign�e entre le segment et la bille

if (d >= rayon) return false; // il n'y a pas de collision

//� pr�sent, d < rayon

double x = arc.projet�Orthogonal(position);
Vecteur[] base = arc.base(x);
Vecteur N = base[1];

double vN = vitesse.produitScalaire(N);

if (vN>=0) return false; // la bille revient vers le bord depuis l'ext�rieur ou bien s'�loigne du bord depuis l'int�rieur: il n'y a pas de collision

// � pr�sent, on a forc�ment vN < 0 

// � pr�sent, on a forc�ment : vN < 0 et d < rayon

if (vN >= -EPSILON_CHOC_PAROI)          // -EPSILON_CHOC_PAROI <= vN < 0  : le choc est mou
   {
   // calcul de la force de rappel
    
   double e = rayon - d;                    // profondeur de la pr�n�tration de la bille dans le mur, on a e > 0
    
   double forceRappel = COEFF_ELASTICITE_PAROI*e;
   
   Vecteur a = N.produit(forceRappel/masse);
   acc�l�ration.ajoute(a);              
    
   return true;
   }

// � pr�sent, on a forc�ment : vN < 0 et d < rayon

Vecteur i = base[0];
double vT = vitesse.produitScalaire(i);
double rw = rayon*w[0];
double c = vT + rw;
double vc = Math.abs(c);
if (vN < -EPSILON_CHOC_PAROI || vc > EPSILON_CHOC_PAROI)            // le choc est dynamique
   {
   double vN1 = -vN;*/

Vecteur [] base = new Vecteur[2];
double [] vNp = new double[1];
double [] vT = new double[1];
double [] rw = new double[1];
double [] vC = new double[1];

int ok = actionReactionArcTousRebonds(position, rayon, vitesse, w, acc�l�ration, masse, arc, intensit�Choc, base, vNp, vT, rw, vC);

if (ok == PAS_DE_CHOC) return false;
else
   if (ok == CHOC_MOU) return true;
   else
      {
      double vTp;
   
      if (vC[0] <= coeffFrottement*vNp[0]) // roulement pur, alors on a : R*w' + vT[0]' = 0
         {
         // calcul de |w'| gr�ce � la conservation de l'�nergie cin�tique tangentielle et de l'�quation R*w' + vT[0]' = 0
         double mvT2, Jw2;
      
         mvT2 = masse*vT[0]*vT[0];
         Jw2 = J*w[0]*w[0];
         double deuxEcT = mvT2 + Jw2;   // 2 * Energie cin�tique tangentielle incidente
         double q = deuxEcT/(J+masse*rayon*rayon);
         double rq = Math.sqrt(q);                     // rq est |w'| = la nouvelle vitesse angulaire sans le signe
         double Eps = 1.0e-1;
      
         //if (Math.abs(vT[0])<Eps*Math.abs(rw[0]))
         if ( mvT2 < Jw2)
          
            w[0] = ( w[0] >= 0 ? rq : -rq);
            
         else    
            w[0] = ( vT[0] >= 0 ? -rq : rq);
          
         vTp = -rayon*w[0];
      
      // d�terminons le signe de la nouvelle vitesse angulaire w'
      
      /*if (vT[0]*w[0] < 0 || Jw2 >= mvT2)
         w[0] = (w[0] >= 0 ? rq: -rq);                              // signe(w') =  signe(w)      
      
      else    
         w[0] = (w[0] < 0 ? rq: -rq);                               // signe(w') = -signe(w)
          
      // A pr�sent on d�termine w' gr�ce � :  Rw' +vT[0]' = 0 car roulement pur
      vT1 = -rayon*w[0];     */                                // 
         } 
      else                                     // glissement pur, w ne change pas, vT[0] ne change pas
         vTp = vT[0]; 
   
      Vecteur vS = Vecteur.combinaisonLin�aire(vTp, base[0], vNp[0], base[1]);
      vitesse.set(vS);  
   
      return true;
      }

}                           // actionReactionBilleArcAvecRebondEtEffet

public /*static*/ int actionReactionBilleBilleTousRebonds(
  final Vecteur position1, final double rayon1, final Vecteur vitesse1, final double w1[], Vecteur acc�l�ration1, final double masse1,
  final Vecteur position2, final double rayon2, final Vecteur vitesse2, final double w2[], Vecteur acc�l�ration2, final double masse2,
  double [] nG1G2, double [] a, Vecteur [] base, double [] v1T, double v2T[], double [] deltavT, double [] M, double [] v1Np, double [] v2Np)
{
Vecteur G1G2;
double nG1G2_2;
G1G2 = Vecteur.difference(position2, position1);
nG1G2_2 = G1G2.normeCarr�e();

double r = rayon1+rayon2;

double r2 = r*r;

if (nG1G2_2 >= r2) return PAS_DE_CHOC;           // on a donc nG1G2 >= rayon1 + rayon2, il n'y a donc pas de choc

// � pr�sent nG1G2_2 < r2

nG1G2[0] = Math.sqrt(nG1G2_2);

// on a donc nG1G2 < r

Vecteur N = base[0] = G1G2.produit(1 / nG1G2[0]);

double v1N, v2N;

v1N = vitesse1.produitScalaire(N);
v2N = vitesse2.produitScalaire(N);

a[0] = v1N - v2N;


if (a[0] <= 0) return PAS_DE_CHOC;       // il n'y a pas de collision, les billes s'�loignent l'une de l'autre

// � pr�sent,  a[0] > 0 et nG1G2 < r 

Vecteur u = base[1] = N.rotationQuartDeTour();

v1T[0] = vitesse1.produitScalaire(u);
v2T[0] = vitesse2.produitScalaire(u);

deltavT[0] = (v1T[0] + rayon1*w1[0]) - ( v2T[0]+ rayon2*w2[0]); 


if (a[0] < EPSILON_CHOC_BILLE && Math.abs(deltavT[0]) < EPSILON_CHOC_BILLE) // le choc est mou car a[0] ~= 0
   {
   double e = r - nG1G2[0];       // e est la profondeur de la p�n�tration d'une bille dans l'autre

   double forceRappel = COEFF_ELASTICITE_BILLE * e;

   Vecteur a12, a21;
   a12 = N.produit(forceRappel / masse2);
   a21 = N.produit(-forceRappel / masse1);

   acc�l�ration1.ajoute(a21);
   acc�l�ration2.ajoute(a12);
   return CHOC_MOU;
   }
else        // � pr�sent, a[0] >= EPSILON_CHOC_BILLE et nG1G2 < r, le choc est donc dynamique
   { 
   double m1 = masse1;
   double m2 = masse2;
   M[0] = m1+m2;
    
   double alfa = (m1-m2)/M[0];
   double deuxSurM = 2/M[0];
    
   v1Np[0] =          alfa * v1N + deuxSurM *m2 * v2N; 
   v2Np[0] = deuxSurM * m1 * v1N -         alfa * v2N;
   
   //intensit�Choc.add( a[0]);
   //intensit�Choc.add( new Choc( position1.somme(N.produit(rayon1)), a[0]));
   this.setChanged();
   this.notifyObservers(new Choc( position1.somme(N.produit(rayon1)), a[0]));
   return CHOC_DYNAMIQUE;
   }

} //actionReactionBilleBilleTousRebonds


/**
 * g�re la collision dynamique ou statique des 2 billes d�finies respectivement par 
 * ( position1, rayon1, vitesse1, acc�l�ration1, masse1 ) et par ( position2, rayon2, vitesse2, acc�l�ration1, masse2)
 * 
 * si il n'y a pas de collision
 * renvoie une valeur < = 0 et ne modifie rien
 * 
 * si il y a collision dynamique :
 * renvoie une valeur > 0 repr�sentant l'intensit� du choc  et modifie  vitesse1 et vitesse2.
 * 
 * si il y a collision statique :
 * renvoie ? et modifie  acc�l�ration1 et acc�l�ration2. (une force de rappel �lastique est appliqu�e aux deux billes)
 * 
 * Les nouvelles acc�l�rations ou les nouveaux vecteurs vitesses sont calcul�s pour repr�senter l'�tat des billes imm�diatement apr�s le choc  
 * 
 * 
 * 
 * */

public static boolean actionReactionBilleBille( Vecteur position1, double rayon1, Vecteur vitesse1, Vecteur acc�l�ration1, double masse1, 
                                                Vecteur position2, double rayon2, Vecteur vitesse2, Vecteur acc�l�ration2, double masse2)   
//modifie b1 et b2
//donn�es : b1 et b2 avant le choc
//r�sultats : b1 et b2 apr�s le choc
{
/*Vecteur G1G2;
double nG1G2, nG1G2_2;
G1G2 = Vecteur.difference(position2, position1);
nG1G2_2 = G1G2.normeCarr�e();

double r = rayon1+rayon2;

double r2 = r*r;

if (nG1G2_2 >= r2) return false;           // on a donc nG1G2 >= rayon1 + rayon2, il n'y a donc pas de choc

// � pr�sent nG1G2_2 < r2

nG1G2 = Math.sqrt(nG1G2_2);

// on a donc nG1G2 < r

Vecteur N = G1G2.produit(1 / nG1G2);

double v1N, v2N;

v1N = vitesse1.produitScalaire(N);
v2N = vitesse2.produitScalaire(N);

double a = v1N - v2N;


if (a <= 0) return false;       // il n'y a pas de collision, les billes s'�loignent l'une de l'autre

// � pr�sent,  a > 0 et nG1G2 < r 

if (a < EPSILON_CHOC_BILLE) // le choc est mou car a ~= 0
   {
   double e = r - nG1G2;       // e est la profondeur de la p�n�tration d'une bille dans l'autre

   double forceRappel = COEFF_ELASTICITE_BILLE * e;

   Vecteur a12, a21;
   a12 = N.produit(forceRappel / masse2);
   a21 = N.produit(-forceRappel / masse1);

   acc�l�ration1.ajoute(a21);
   acc�l�ration2.ajoute(a12);
   return true;
   }
else        // � pr�sent, a >= EPSILON_CHOC_BILLE et nG1G2 < r, le choc est donc dynamique
   { 
   double m1 = masse1;
   double m2 = masse2;
   double M = m1+m2;
    
   double alfa = (m1-m2)/M;
   double deuxSurM = 2/M;
    
   double v1Np =          alfa * v1N + deuxSurM *m2 * v2N; 
   double v2Np = deuxSurM * m1 * v1N -         alfa * v2N;
   
   Vecteur u = N.rotationQuartDeTour();
   
   double v1T = vitesse1.produitScalaire(u);
   double v2T = vitesse2.produitScalaire(u);*/
double [] w1 = {0};
double [] w2 = {0};
double [] nG1G2 = new double[1];
double [] a = new double[1];
Vecteur [] base = new Vecteur[2];
double [] v1T = new double[1];
double [] v2T = new double[1];
double [] deltavT = new double[1];
double [] M = new double[1];
double [] v1Np = new double[1];
double [] v2Np = new double[1];
int ok = Collisions1.collisions1.actionReactionBilleBilleTousRebonds( position1, rayon1, vitesse1, w1, acc�l�ration1, masse1, 
                                              position2, rayon2, vitesse2, w2, acc�l�ration2, masse2, 
                                                 nG1G2, a, base, v1T, v2T, deltavT, M, v1Np, v2Np);

if (ok == PAS_DE_CHOC) return false;
else
   if (ok == CHOC_MOU) return true;
   else
      { 
      Vecteur v1S = Vecteur.combinaisonLin�aire(v1Np[0], base[0], v1T[0], base[1]);
      Vecteur v2S = Vecteur.combinaisonLin�aire(v2Np[0], base[0], v2T[0], base[1]);
   
      vitesse1.set(v1S);                  // vecteur vitesse de la bille 1 apr�s le choc
      vitesse2.set(v2S);                  // vecteur vitesse de la bille 2 apr�s le choc
    
      return true;
      }
   
} // actionReactionBilleBille



/**
 * g�re la collision dynamique ou statique des 2 billes d�finies respectivement par 
 * ( position1, rayon1, vitesse1, acc�l�ration1, masse1 ) et par ( position2, rayon2, vitesse2, acc�l�ration1, masse2)
 * 
 * si il n'y a pas de collision
 * renvoie une valeur < = 0 et ne modifie rien
 * 
 * si il y a collision dynamique :
 * renvoie une valeur > 0 repr�sentant l'intensit� du choc  et modifie  vitesse1 et vitesse2.
 * 
 * si il y a collision statique :
 * renvoie ? et modifie  acc�l�ration1 et acc�l�ration2. (une force de rappel �lastique est appliqu�e aux deux billes)
 * 
 * Les nouvelles acc�l�rations ou les nouveaux vecteurs vitesses sont calcul�s pour repr�senter l'�tat des billes imm�diatement apr�s le choc  
 * 
 * 
 * 
 * */

public static boolean actionReactionBilleBilleAvecEffet( 
          Vecteur position1, double rayon1, Vecteur vitesse1, double w1[], Vecteur acc�l�ration1, double m1, final double J1,
          Vecteur position2, double rayon2, Vecteur vitesse2, double w2[], Vecteur acc�l�ration2, double m2, final double J2, 
                                                 final double coeffFrottement)   
//modifie b1 et b2
//donn�es : b1 et b2 avant le choc
//r�sultats : b1 et b2 apr�s le choc
{
                            //System.err.println("actionReactionBilleBilleAvecEffet, d�but ");
/*Vecteur G1G2;
double nG1G2, nG1G2_2;
G1G2 = Vecteur.difference(position2, position1);
nG1G2_2 = G1G2.normeCarr�e();

double r = rayon1+rayon2;

double r2 = r*r;

if (nG1G2_2 >= r2) return false;           // on a donc nG1G2 >= rayon1 + rayon2, il n'y a donc pas de choc

// � pr�sent nG1G2_2 < r2

nG1G2 = Math.sqrt(nG1G2_2);

// on a donc nG1G2 < r

Vecteur n = G1G2.produit(1 / nG1G2);
                                                            
Vecteur v1 = vitesse1;
Vecteur v2 = vitesse2;

double v1N = v1.produitScalaire(n), v2N = v2.produitScalaire(n); 

double a = v1N-v2N;

if (a <= 0) return false;       // il n'y a pas de collision, les billes s'�loignent l'une de l'autre

//� pr�sent,  a > 0 et nG1G2 < r

Vecteur u = n.rotationQuartDeTour();
double v1T = v1.produitScalaire(u), v2T = v2.produitScalaire(u);

double deltavT = (v1T + rayon1*w1[0]) - ( v2T+ rayon2*w2[0]); 
double vdeltavT = Math.abs(deltavT);
 
if (a < EPSILON_CHOC_BILLE && vdeltavT < EPSILON_CHOC_BILLE) // le choc est mou car a ~= 0
   {                                System.err.println("actionReactionBilleBilleAvecEffet, d�but choc mou");
   double e = r - nG1G2;       // e est la profondeur de la p�n�tration d'une bille dans l'autre

   double forceRappel = COEFF_ELASTICITE_BILLE * e;

   Vecteur a12, a21;
   a12 = n.produit(forceRappel / masse2);
   a21 = n.produit(-forceRappel / masse1);

   acc�l�ration1.ajoute(a21);
   acc�l�ration2.ajoute(a12);
   
   //  intensit�Choc.add( 0.0); // � revoir
   
   }
else 
   { 
            // � pr�sent, (a >= EPSILON_CHOC_BILLE ou |(v1T +r1*w1) - (v2T+r2*w2)| >= EPSILON_CHOC_BILLE) et nG1G2 < r, le choc est donc dynamique
   double v1Np, v2Np, v1Tp, v2Tp;
   Vecteur  vs1, vs2;
   double m1 = masse1;
   double m2 = masse2;
   double M = m1+m2;
   double m1_m2 = m1-m2;
   double alfa = m1_m2/M;
   double deuxSurM = 2/M;
   
   v1Np = alfa*v1N + deuxSurM*m2*v2N;
   v2Np = deuxSurM*m1*v1N -alfa*v2N;
   */
double [] nG1G2 = new double[1];
double [] a = new double[1];
Vecteur [] base = new Vecteur[2];
double [] v1T = new double[1];
double [] v2T = new double[1];
double [] deltavT = new double[1];
double [] M = new double[1];
double [] v1Np = new double[1];
double [] v2Np = new double[1];
int ok = Collisions1.collisions1.actionReactionBilleBilleTousRebonds( position1, rayon1, vitesse1, w1, acc�l�ration1, m1, 
                                              position2, rayon2, vitesse2, w2, acc�l�ration2, m2, 
                                                 nG1G2, a, base, v1T, v2T, deltavT, M, v1Np, v2Np);

if (ok == PAS_DE_CHOC) return false;
else
   if (ok == CHOC_MOU) return true;
   else
      { 
      double v1Tp = v1T[0], v2Tp = v2T[0];
      
      if (Math.abs(deltavT[0]) > coeffFrottement*a[0]) // il y a glissement pur
     
         System.err.println("actionReactionBilleBilleAvecEffet, choc dynamique, glissement pur");
        
      else                                          // il y a roulement pur
         {
         double d = nG1G2[0];
         double D = rayon1*J2+J1*rayon2;
         double A = deltavT[0];
         double P = m1*m2;
          
         double a1 = (M[0]*J2 - P*d*rayon2)/D;
         double b1 = -A*J2/D;
         double a2 = -(M[0]*J1+P*d*rayon1)/D;
         double b2 = A*J1/D;
          
         double c2 = P*M[0] + J1*a1*a1 + J2*a2*a2;
         double c1 = 2*( P*(v2T[0]-v1T[0]) + J1*a1*(b1+w1[0]) + 
                                        J2*a2*(b2+w2[0]) ); 
         double c0 = J1*b1*(b1+2*w1[0]) + 
                      J2*b2*(b2+2*w2[0]); 
          
         double[] lambda = MesMaths.r�soudre(  c2,  c1,  c0);
          
         if (lambda.length != 0)
            {
            double lam = (Math.abs(lambda[0]) > Math.abs(lambda[1]) ? lambda[0] : lambda[1]);
            double delta1 = lam * a1 + b1;
            double delta2 = lam * a2 + b2;
            w1[0] += delta1;
            w2[0] += delta2;
            v1Tp = v1T[0] - lam * m2;
            v2Tp = v2T[0] + lam * m1;
            
            System.err.println("actionReactionBilleBilleAvecEffet, choc dynamique, roulement pur");
            }
          
         }                          // roulement pur
      
      Vecteur v1S = Vecteur.combinaisonLin�aire(v1Np[0], base[0], v1Tp, base[1]);
      Vecteur v2S = Vecteur.combinaisonLin�aire(v2Np[0], base[0], v2Tp, base[1]);
   
      vitesse1.set(v1S);                  // vecteur vitesse de la bille 1 apr�s le choc
      vitesse2.set(v2S);                  // vecteur vitesse de la bille 2 apr�s le choc
    
      return true;
      }                     // choc dynamique

} // actionReactionBilleBilleAvecEffet


} // Collisions1


