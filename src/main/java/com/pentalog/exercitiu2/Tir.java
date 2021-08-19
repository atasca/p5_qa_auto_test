package com.pentalog.exercitiu2;

public class Tir  extends Masina{
    /*
    2. Creaza o clasa copil al clasei Masina numit Tir cu următoarele câmpuri și metode.
◦ greutate;
◦ calculeazaPret();
◦daca greutate > 2000 , 15% discount. altfel ,25 % discount.

     */

    private double greutate;

    @Override
    public double calculeazaPret(double pret) {
        if(greutate > 2000){
        return super.calculeazaPret( 15 * pret / 100);
    } else {
            return super.calculeazaPret( 25 * pret / 100);
        }
    }

     public Tir(){
        super(90,"verde");
        this.greutate = greutate;
     }

}

