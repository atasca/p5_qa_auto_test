package com.pentalog.exercitiu2;

public class SUV extends Masina {
    double lungime; // 1.00 = 1 metru
    double DISCOUNT_PESTE_5 = 0.08; /* Discountul e dat ca valoare procentuala: 1% discount = 0.01, 10% discount = 0.1*/
    double DISCOUNT_SUB_5 = 0.14;

    public SUV(int viteza, double pret, String culoare, double lungime) {
        super(viteza, pret, culoare);
        this.lungime = lungime;
    }

    double calculeazaPret() {
        if (this.lungime > 5.00) {
            return this.pret * (1 - DISCOUNT_PESTE_5);
        }
        return this.pret * (1 - DISCOUNT_SUB_5);
    }

}
