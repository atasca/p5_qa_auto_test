package com.pentalog.exercitiu2;

public class PropriaMeaReprezentanta {
    public static void main(String[] args) {
        SUV suv = new SUV("350",75000.0,"alb",4.99);
        BMW bmw1 = new BMW("260",63500.0,"alb", 2020, 0.26);
        BMW bmw2 = new BMW("240",40000.0,"alb", 2021, 0.30);
        Masina masina = new Masina("260", 56250.0, "gri");
        System.out.println(suv.calculeazaPret());
        System.out.println(bmw1.calculeazaPret());
        System.out.println(bmw2.calculeazaPret());

    }
}
