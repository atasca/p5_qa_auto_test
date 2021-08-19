package com.pentalog.exercitiu2;

public class Tir extends Masina{
    private double greutate;

    @Override
    public double calculeazaPret(double pret, double greutate){
        if(greutate>2000){
            return pret - (0.15*pret);
        } else{
            return pret - (0.25*pret);
        }
    }
}
