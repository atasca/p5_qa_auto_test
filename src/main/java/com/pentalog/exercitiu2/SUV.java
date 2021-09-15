package com.pentalog.exercitiu2;

public class SUV extends Masina{

    Integer lumgime;

    public SUV(double viteza, double pret, String culoare) {
        super(viteza, pret, culoare);
    }

    public double calculeazaPret(){

        if(this.lumgime >5.0){
            return pret*0.92;
        }
        else {
            return pret*0.86;
        }
    }
}
