package com.pentalog.exercitiu2;

public class Tir extends Masina {
    double greutate;

    public Tir(int carViteza, double carPret, String carCuloare, double carGreutate) {
        super(carViteza, carPret, carCuloare);
        greutate = carGreutate;
    }

    public void calculeazaPret() {
        if (greutate > 2000)
            pret = pret - (pret * 0.15);
        else
            pret = pret - (pret * 0.25);
    }
}
