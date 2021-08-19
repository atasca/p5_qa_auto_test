package com.pentalog.exercitiu2;

public class PropriaMeaReprezentanta {

    public static void main (String[] args) {
        SUV mySuv = new SUV(10);
        BMW MyBmw1 = new BMW(2000,10);
        BMW MyBmw2 = new BMW(2010,5);
        Masina myMasina = new Masina(10,10,"galben");
        System.out.println(myMasina);
        System.out.println(mySuv);
        System.out.println(MyBmw1);
        System.out.println(MyBmw2);

    }
}
