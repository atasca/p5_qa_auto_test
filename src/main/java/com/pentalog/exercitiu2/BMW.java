package com.pentalog.exercitiu2;

public class BMW extends Masina {
    private int an;
    private Double discountProducator;


    public BMW(String viteza, Double pret, String culoare, int an, Double discountProducator) {
        this.viteza = viteza;
        this.pret = pret;
        this.culoare = culoare;
        this.an = an;
        this.discountProducator = discountProducator;
    }

    public Double calculeazaPret(){
        return pret - pret * discountProducator;
    }
}
