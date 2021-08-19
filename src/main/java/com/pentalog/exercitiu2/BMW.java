package com.pentalog.exercitiu2;

public class BMW extends Masina {

    int an;
    double discountProducator;


    public BMW(int viteza, double pret, String culoare,int an,double discountProducator) {
        super(viteza, pret, culoare);
        this.an= an;
        this.discountProducator=discountProducator;
    }

    public double calculeazaPret()
    {

        return pret-pret*discountProducator;
    }
}
