package com.pentalog.exercitiu2;

public class SUV extends Masina{

    double lungime;

    public SUV(int viteza, double pret, String culoare,double lungime) {
        super(viteza, pret, culoare);
        this.lungime=lungime;
    }

    public double calculeazaPret()
    {
        if(lungime > 5)
            pret=pret - pret*0.8;
        else
            pret = pret - pret*0.14;

        return pret;
    }
}
