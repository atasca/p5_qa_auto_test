package com.pentalog.exercitiu2;

public class SUV extends Masina {
    double lungime;

    public SUV(int carViteza, double carPret, String carCuloare, double carLungime) {
        super(carViteza, carPret, carCuloare);
        lungime = carLungime;
    }

    public void calculeazaPret() {
        if (lungime > 5)
            pret = pret - (pret * 0.08);
        else
            pret = pret - (pret * 0.14);
    }
}
