package com.pentalog.exercitiu2;

public class SUV extends Masina {
    Double lungime; //m

    public SUV(String viteza, Double pret, String culoare, Double lungime) {
        this.viteza = viteza;
        this.pret = pret;
        this.culoare = culoare;
        this.lungime = lungime;

    }
    @Override
    public Double calculeazaPret() {
        if (lungime > 5) {
            pret = pret - pret * 0.08;
        } else {
            pret = pret - pret * 0.14;
        }
        return pret;
    }
}
