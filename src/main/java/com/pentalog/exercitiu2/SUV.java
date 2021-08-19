package com.pentalog.exercitiu2;
//4. Creaza o clasa copil al clasei Masina numit  SUV cu următoarele câmpuri și metode.
//        ◦ lungime;
//        ◦ calculeazaPret(); //daca lungimea ii mare mare de 5 metri atunci se primeste un discount de 8% , altfel 14% discount.
public class SUV extends Masina{
    double lungime;

    @Override
    public void calculeazaPret() {
        if(lungime>5)
        {
            this.pret=pret*0.92;
        }else{
            this.pret=pret*0.86;
        }
    }
}
