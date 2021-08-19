package com.pentalog.exercitiu2;

public class BMW extends Masina {
    int an;
    int discountProducator;

    public BMW(int viteza, int pret, String culoare, int an, int discountProducator) {
        super(viteza, pret, culoare);
        this.an = an;
        this.discountProducator = discountProducator;
    }

    public int calculeazaPret() {
        return pret - discountProducator;
    }
}
