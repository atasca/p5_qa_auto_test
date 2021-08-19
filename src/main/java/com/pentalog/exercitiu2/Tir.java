package com.pentalog.exercitiu2;

public class Tir extends Masina {
    int greutate;

//    public Tir(String viteza, Double pret, String culoare, int greutate) {
//        this.viteza = viteza;
//        this.pret = pret;
//        this.culoare = culoare;
//        this.greutate = greutate;
//    }

    @Override
    public Double calculeazaPret() {
        if (greutate > 2000) {
            pret = pret - pret * .15;
        } else {
            pret = pret - pret * 0.25;
        }
        return pret;
    }
}
