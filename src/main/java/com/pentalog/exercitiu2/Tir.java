package com.pentalog.exercitiu2;
//2. Creaza o clasa copil al clasei Masina numit Tir cu următoarele câmpuri și metode.
//        ◦ greutate;
//        ◦ calculeazaPret();
//        ◦daca greutate > 2000 , 15% discount. altfel ,25 % discount.
public class Tir extends Masina{
    int greutate;

    @Override
    public void calculeazaPret() {
        if(greutate>2000)
        {
            this.pret=2000*0.85;
        }
        else{
            this.pret=2000*0.75;
        }

    }
}
