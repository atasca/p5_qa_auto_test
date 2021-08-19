package com.pentalog.exercitiu2;

public abstract class Masina {
    private double viteza;
    private double pret;
    private String culoare;

    protected double getViteza() {
        return viteza;
    }

    protected double getPret() {
        return pret;
    }

    protected String getCuloare() {
        return culoare;
    }

    public Masina(double viteza, double pret, String culoare) {
        this.viteza = viteza;
        this.pret = pret;
        this.culoare = culoare;
    }

    public abstract double calculeazaPret();
}
