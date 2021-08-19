package com.pentalog.exercitiu2;

public class Tir extends Masina {
    int greutate;

    public Tir(int viteza, int pret, String culoare, int greutate) {
        super(viteza, pret, culoare);
        this.greutate = greutate;
    }

    public int calculeazaPret() {
        if (greutate > 2000) {
            return pret - (pret * 15) / 100;
        } else {
            return pret - (pret * 25) / 100;
        }
    }
}