package com.pentalog.exercitiu2;

public class Suv extends Masina {
    int lungime;

    public Suv(int viteza, int pret, String culoare, int lungime) {
        super(viteza, pret, culoare);
        this.lungime = lungime;
    }

    public int calculeazaPret() {
        int discount = 14;

        if (lungime > 5) {
            discount = 8;
        }

        return pret - (pret * discount) / 100;
    }
}