package mesmaths.geometrie.formes;

import java.util.Arrays;

import mesmaths.geometrie.base.TransformationAffine;
import mesmaths.geometrie.base.Vecteur;

/** représente un polygone fermé */
public class Polygone
{
Vecteur t[]; // Le dernier point n'est pas recopié, ne contient donc pas d'information redondante

/**
 * @param t : tableau d'au moins 3 points distincts : c'est le tableau contenant les sommets du polygône
 * 
 * On suppose que tous les points sont distincts
 */
public Polygone(Vecteur[] t)
{
this.t = t;
}

public Vecteur centreGravité()
{
Vecteur g;
int i;

for ( i = 0, g = Vecteur.VECTEURNUL.copie(); i < t.length; ++i) g.ajoute(t[i]);

return g.produit(1.0/t.length);
}
/**
 * modifie this en appliquant transfo à tous les points qui composent this
 * 
 * */
public void applique(TransformationAffine transfo)
{
this.t = transfo.applique(t);
}

public void dessine( Dessinateur dessinateur)
{
int i, d;
d = t.length;
for ( i = 0; i < d; ++i)
    dessinateur.dessineSegment(t[i],t[(i+1)%d]);

}

public String toString()
{
return Arrays.toString(t);
}
}
