package com.pentalog.exercitiu1;


import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;


public class Ex1 {

    /**
     * Fiind dat un text, scrie o metoda care sa afiseze primul cuvant care are macar un duplicat.
     * Se va verifica rezultatul utilizand metoda 'verificaCuvantul' si se implementeaza la linia 25
     * Se completeaza la linia 32 expected result (in loc de xxxx)
     * Se acorda punctaj bonus pentru citirea din fisierul Exercitiu1.txt
     */

    static String textDeCitit = "He has a car, but the car it is old but he should sell it.";


    public static void main(String[] args) {

        verificaCuvantul(returneazaPrimulCuvantDuplicat());
    }


    static String returneazaPrimulCuvantDuplicat() {
        //rezolvare
        Map<String, String> map = new HashMap<>();
        String cuvinte[] = textDeCitit.split(" ");
        String cuv = "string";
        for (int i = 0; i < cuvinte.length; i++) {
            String cuvant = cuvinte[i].toUpperCase();

            if (map.get(cuvant) != null) {
                System.out.println(cuvant);
            }else{
                map.put(cuv,cuv);
            }
        }
        return cuv;
    }


    public static void verificaCuvantul(String rezultat) {
        assertEquals("car", rezultat);
    }
}
