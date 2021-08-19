package com.pentalog.exercitiu2;

public class Masina {
    protected String viteza;
    protected Double pret;
    protected String culoare;

    public  Masina(){

    }

    public Masina(String viteza, Double pret, String culoare) {
        this.viteza = viteza;
        this.pret = pret;
        this.culoare = culoare;
    }

    public Double calculeazaPret() {
        return pret;
    }

}
