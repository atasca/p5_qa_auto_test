package com.pentalog.exercitiu2;

public class Tir extends Masina{
    double greutate;

    public Tir(double viteza, double pret, String culoare) {
        super(viteza, pret, culoare);
    }

    public double calculeazaPret(){

        if(this.greutate >200){
            return pret*0.85;
        }
        else {
            return pret*0.75;
        }
    }
}
