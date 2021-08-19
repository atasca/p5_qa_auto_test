package com.pentalog.exercitiu2;

public class SUV extends Masina {
    private double lungime;

    public SUV(double viteza, double pret, String culoare, double lungime) {
        super(viteza, pret, culoare);
        this.lungime = lungime;
    }


    public double calculeazaPret() {
        if(lungime > 8)
        {
            double discount = 0.08 * 2000;
            double preRedus = this.getPret() - discount;
            return preRedus;
        }
        else
        {
            double discount = 0.15 * 2000;
            double preRedus = this.getPret() - discount;
            return preRedus;
        }
    }
}
