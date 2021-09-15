package com.pentalog.exercitiu2;

public class Masina {
    double viteza;
    double pret;
    String culoare;

    public Masina(double viteza, double pret, String culoare) {
        this.viteza = viteza;
        this.pret = pret;
        this.culoare = culoare;
    }

    public double calculeazaPret(){

        return  pret;
    }
}
