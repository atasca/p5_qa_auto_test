package com.pentalog.exercitiu2;
//3. Creaza o clasa copil al clasei Masina numit  BMW cu următoarele câmpuri și metode.
//        ◦ an;
//        ◦ discountProducator;
//        ◦ calculeazaPret();//Din pretul calculat in clasa Masina, scade discountul producatorului
public class BMW extends Masina{
    int an;
    int discountProducator = 100;

    public void calculeazaPret()
    {
        this.pret=pret-discountProducator;
    }
}
