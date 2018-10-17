package mesmaths.geometrie.formes;

import mesmaths.geometrie.base.Vecteur;



public class Segment
{
//----------------- classe Segment-------------------------------------

private Vecteur debut, fin;

/**
 * @param debut
 * @param fin
 */
public Segment(Vecteur debut, Vecteur fin)
{
this.debut = debut;
this.fin = fin;
}


public Vecteur[] base()   // fournit une base orthornormée directe associée au segment orienté
                // base[0] est un vecteur unitaire colineaire et 
                // de meme sens que le vecteur debut->fin
    {
    Vecteur [] t= new Vecteur[2];
    Vecteur u = fin.difference(debut);
    double d = u.norme();
    t[0] = u.produit(1/d);
    t[1] = new Vecteur(-t[0].y,t[0].x);
    return t;
    }


public Vecteur reflechi(Vecteur v) // calcule le vecteur reflechi de v par rapport a this
    {
    Vecteur I,J;
    Vecteur[] t = base();
    double x, y;
    I = t[0]; J = t[1];
    x = v.produitScalaire(I);
    y = v.produitScalaire(J);
    return Vecteur.combinaisonLinéaire( x, I, -y, J);
    }

//----------------- classe Segment-------------------------------------
}
