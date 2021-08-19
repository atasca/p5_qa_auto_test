package com.pentalog.exercitiu2;

public class BMW extends Masina {
    private int an;
    private double discountProducator;

    public BMW(double viteza, double pret, String culoare, int an, double discountProducator) {
        super(viteza, pret, culoare);
        this.an = an;
        this.discountProducator = discountProducator;
    }


    public double calculeazaPret(){
        double preRedus = this.getPret() - discountProducator;
        return preRedus;
    }
}
