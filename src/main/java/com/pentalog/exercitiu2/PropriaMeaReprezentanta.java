package com.pentalog.exercitiu2;

public class PropriaMeaReprezentanta {

    public static void main(String[] args) {

        SUV carSUV = new SUV(250, 40000, "black", 3);
        //  System.out.println("SUV - " + "Viteza: " + carSUV.viteza + " km/h" + " Pret: " + carSUV.pret + " Euro" + " Culoare: " + carSUV.culoare + " Lungime: " + carSUV.lungime + " m ");

        BMW carBMW = new BMW(300, 45000, "red", 2016, 15);
        //  System.out.println("BMW - " + "Viteza: " + carBMW.viteza + " km/h" + " Pret: " + carBMW.pret + " Euro" + " Culoare: " + carBMW.culoare + " An: " + carBMW.an + " Discount producator: " + carBMW.discountProducator + " %");

        BMW carBMW1 = new BMW(250, 50000, "blue", 2018, 10);
        // System.out.println("BMW - " + "Viteza: " + carBMW1.viteza + " km/h" + " Pret: " + carBMW1.pret + " Euro" + " Culoare: " + carBMW1.culoare + " An: " + carBMW1.an + " Discount producator: " + carBMW1.discountProducator + " %");

        Masina msn = new Masina(200, 20000, "black");
        // System.out.println("Masina - " + "Viteza: " + msn.viteza + " km/h" + " Pret: " + msn.pret + " Euro" + " Culoare: " + msn.culoare);

        Tir carTir = new Tir(300, 100000, "Black", 2500);

        msn.calculeazaPret();
        System.out.println("Pret masina: " + msn.pret + " Euro");

        carBMW.calculeazaPret();
        System.out.println("Pret BMW: " + carBMW.pret + " Euro");

        carSUV.calculeazaPret();
        System.out.println("Pret SUV: " + carSUV.pret + " Euro");

        carTir.calculeazaPret();
        System.out.println("Pret tir: " + carTir.pret + " Euro");

    }
}
