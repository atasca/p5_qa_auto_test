package com.pentalog.exercitiu2;

public class PropriaMeaReprezentanta {


    public static void main (String[] args) {


        SUV s1 =new SUV(200,10500.9,"Albastru",4.9);
        BMW b1=new BMW(230,15000.3,"GRI",2014,0.8);
        BMW b2 = new BMW(190,8500.8,"Verde",2008,13.2);
        Masina m1= new Masina(200,1234.4,"Negru");

        System.out.println(s1.pret);
        System.out.println(b1.pret);
        System.out.println(b2.pret);
        System.out.println(m1.pret);
    }



}
