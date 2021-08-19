package com.pentalog.exercitiu2;

public class Tir extends Masina{
    private double greutate;

    public Tir(double viteza, double pret, String culoare, double greutate) {
        super(viteza, pret, culoare);
        this.greutate = greutate;
    }

    public double calculeazaPret()
    {
        if(this.greutate > 2000)
        {
            double discount = 0.15 * 2000;
            double preRedus = this.getPret() - discount;
            return preRedus;
        }
        else
        {
            double discount = 0.25 * 2000;
            double preRedus = this.getPret() - discount;
            return preRedus;
        }
    }
}
