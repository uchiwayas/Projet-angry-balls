package mesmaths.cinematique;

import mesmaths.geometrie.base.Vecteur;

/**
 * repr�sente un choc. D�fini par une position et une intensit�.
 * Cette classe a pour but de diffuser un son occasionn� par un choc
 * */
public class Choc
{
Vecteur position;
double intensit�; // quantit� > 0

/**
 * @param position
 * @param intensit�
 */
public Choc(Vecteur position, double intensit�)
{
this.position = position;
this.intensit� = intensit�;
}

@Override
public String toString()
{
return "Choc [position=" + this.position + ", intensit�=" + this.intensit�
        + "]";
}

public Vecteur getPosition()
{
return this.position;
}

public double getIntensit�()
{
return this.intensit�;
}


}
