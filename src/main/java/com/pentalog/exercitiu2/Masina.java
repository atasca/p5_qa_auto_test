package com.pentalog.exercitiu2;

public class Masina {
    /*
    1. Creați o clasă părinte numita Masina. Clasa Masina are următoarele câmpuri și metode.
◦  viteza;
◦ pret;
◦ culoare;
◦ calculeazaPret();
    */

    private int viteza;
   // private double pret;
    private String culoare;

    public double calculeazaPret( double pret){
        return pret;
    }

   public Masina( int viteza, String culoare){
        this.viteza = viteza;
        this.culoare = culoare;
   }
}
