package com.pentalog.exercitiu2;

public class BMW extends Masina {
    int an;
    double discountProducator;

    public BMW(int carViteza, double carPret, String carCuloare, int carAn, double carDiscountProducator) {
        super(carViteza, carPret, carCuloare);
        an = carAn;
        discountProducator = carDiscountProducator;
    }

    public void calculeazaPret() {
        pret = pret - (discountProducator / 100 * pret);
    }
}
