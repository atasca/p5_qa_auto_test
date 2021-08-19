package com.pentalog.exercitiu2;

public class Tir extends Masina{

      int greutate;
      int discount;


    public void calculeazaPret() {
        if(greutate>2000){
            discount=15/100;}
            else
            {discount=25/100;
        }

    }
}
