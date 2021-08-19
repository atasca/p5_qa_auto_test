package com.pentalog.exercitiu2;

public class BMW extends Masina{
    int an;
    double discountProducator; /* Discountul e dat ca valoare procentuala: 1% discount = 0.01, 10% discount = 0.1*/

    public BMW(int viteza, double pret, String culoare, int an, double discountProducator) {
        super(viteza, pret, culoare);
        this.an = an;
        this.discountProducator = discountProducator;
    }

    double calculeazaPret(){
        return this.pret*(1-discountProducator);
    }
}
