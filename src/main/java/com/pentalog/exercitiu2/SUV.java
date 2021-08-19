package com.pentalog.exercitiu2;

public class SUV extends Masina {
    /*4. Creaza o clasa copil al clasei Masina numit  SUV cu următoarele câmpuri și metode.
◦ lungime;
◦ calculeazaPret(); //daca lungimea ii mare mare de 5 metri atunci se primeste un discount de 8% , altfel 14% discount.
*/
    private double lungime;

    @Override
    public double calculeazaPret(double pret) {
        if(lungime > 5){
            return super.calculeazaPret( 8 * pret / 100);
        } else {
            return super.calculeazaPret( 14 * pret / 100);
        }
    }

    public SUV(double lungime){
        super(90,"alb");
        this.lungime = lungime;
    }
}
