package com.pentalog.exercitiu2;

public abstract class Masina {
    private int viteza;
    private double pret;
    private String culoare;

    public double calculeazaPret(double pret){
        return pret;
    }

    public abstract double calculeazaPret(double pret, double greutate);
}
