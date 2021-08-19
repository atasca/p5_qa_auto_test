package com.pentalog.exercitiu2;

public class Masina {

    int viteza;
    double pret;
    String culoare;

    public Masina(int viteza, double pret, String culoare) {
        this.viteza = viteza;
        this.pret = pret;
        this.culoare = culoare;
    }

    public double calculeazaPret()
    {
        return pret;
    }

}
