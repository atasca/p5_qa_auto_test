package com.pentalog.exercitiu2;

public class PropriaMeaReprezentanta {

    public static void main(String[] args) {
        SUV suv =  new SUV(200,5000, "verde", 300);
        BMW bmw1 = new BMW(250,30000, "alb", 2015, 300);
        BMW bmw2 = new BMW(300,4000, "negru", 2001, 100);

        Masina masina = new Tir(140,9999, "galben", 6000);

        System.out.println("Pret suv: " + suv.calculeazaPret());
        System.out.println("Pret bmw1: " + bmw1.calculeazaPret());
        System.out.println("Pret bmw2: " + bmw2.calculeazaPret());

        System.out.println("Pret masina tir: " + masina.calculeazaPret());
    }
}
