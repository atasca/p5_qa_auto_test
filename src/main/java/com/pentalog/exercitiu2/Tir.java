package com.pentalog.exercitiu2;


public class Tir extends Masina {

    double greutate;


    public Tir(int viteza, double pret, String culoare,double greutate) {
        super(viteza, pret, culoare);
        this.greutate= greutate;
    }

    public double calculeazaPret()
    {
        if(greutate > 2000)
            pret = pret - 0.15*pret;
        else
            pret =pret-  0.25*pret;

        return pret;
    }
}
