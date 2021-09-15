package com.pentalog.exercitiu2;

public class PropriaMeaReprezentanta {
    public static void main(String[] args) {
        SUV suv = new SUV(180.00,10000.00, "Black");
        BMW bmw1 = new BMW(180.00,12500.00, "Red");
        BMW bmw2 = new BMW(260.00,19500.99, "Grey");
        Masina masina = new Masina(200.00,15000.99, "White");
        System.out.printf("Suv pret :%f \n", suv.pret );
        System.out.printf("BMW1 pret :%f \n", bmw1.pret );
        System.out.printf("BMW2 pret :%f \n", bmw2.pret );
        System.out.printf("Masina pret :%f \n", masina.pret );
    }
}
