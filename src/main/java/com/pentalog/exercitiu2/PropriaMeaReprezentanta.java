package com.pentalog.exercitiu2;

public class PropriaMeaReprezentanta {
    public static void main(String[] args) {
        SUV cevaSuv = new SUV(120, 10_000.00, "Rosu", 5.24);
        BMW tigaie = new BMW(200, 999.00, "Albastru", 1999, 0.55);
        BMW tigaieNoua = new BMW(250, 80_000, "Negru", 2020, 0.03);
        Masina golf = new Masina(150, 2000, "Gri");
        Masina[] carArray = {cevaSuv, tigaie, tigaieNoua, golf};
        for (Masina car : carArray) {
            System.out.println(car.calculeazaPret());
        }

    }
}
