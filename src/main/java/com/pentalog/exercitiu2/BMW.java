package com.pentalog.exercitiu2;

public class BMW extends Masina{
    Integer an;
    double discountProducator;

    public BMW(double viteza, double pret, String culoare) {
        super(viteza, pret, culoare);
    }

    public double calculeazaPret(){
        // get calculeaza pret from Masina
        return pret*(1-discountProducator);
    }
}
