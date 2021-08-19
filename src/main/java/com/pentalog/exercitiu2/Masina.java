package com.pentalog.exercitiu2;

public class Masina {
    int viteza;
    int pret;
    String culoare;

    public Masina(int viteza, int pret, String culoare) {
        this.viteza = viteza;
        this.pret = pret;
        this.culoare = culoare;
    }

    public int calculeazaPret() {
        return this.pret;
    }
}
