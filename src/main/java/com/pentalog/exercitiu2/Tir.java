package com.pentalog.exercitiu2;

public class Tir extends Masina {
    int greutate;
    double DISCOUNT_PESTE_2000 = 0.15;
    double DISCOUNT_SUB_2000 = 0.25;

    public Tir(int viteza, double pret, String culoare, int greutate) {
        super(viteza, pret, culoare);
        this.greutate = greutate;
    }

    double calculeazaPret() {
        if (this.greutate > 2000) {
            return this.pret * (1 - DISCOUNT_PESTE_2000);
        }
        return this.pret * (1 - DISCOUNT_SUB_2000);
    }
}
