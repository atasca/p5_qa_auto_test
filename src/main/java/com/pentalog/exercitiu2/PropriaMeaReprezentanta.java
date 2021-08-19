package com.pentalog.exercitiu2;

public class PropriaMeaReprezentanta {
    public static void main(String[] args) {
        Masina Masina = new Masina(240, 15000, "rosu");
        Tir Tir = new Tir(300, 90000, "negru", 10000);
        BMW BMW = new BMW(300, 30000, "alb", 2020, 3000);
        Suv Suv = new Suv(300, 50000, "verde", 4);

        System.out.println("Pretul masinii este de " + Masina.calculeazaPret());
        System.out.println("Pretul tirului cu discount este de " + Tir.calculeazaPret());
        System.out.println("Pretul bmw-ului cu discount este de " + BMW.calculeazaPret());
        System.out.println("Pretul suv-ului cu discount este de " + Suv.calculeazaPret());
    }
}
