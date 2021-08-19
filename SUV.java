package com.pentalog.exercitiu2;

public class SUV extends Masina{
    int lungime;
    int discount;

    private void calculeazaPret(){
        if(lungime>5){
            discount=8/100;}
        else{
            discount=14/100;
        }
    }
}
