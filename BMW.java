package com.pentalog.exercitiu2;

public class BMW extends Masina {
    int An=2009;
    int discountProducator=2500;

    private void calculDiscountProducator(){
        discountProducator = pret - discountProducator;
    }
}
