package com.pentalog.exercitiu1;


import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;


public class Ex1 {

    /**
     * Fiind dat un text, scrie o metoda care sa afiseze primul cuvant care are macar un duplicat.
     * Se va verifica rezultatul utilizand metoda 'verificaCuvantul' si se implementeaza la linia 25
     * Se completeaza la linia 32 expected result (in loc de xxxx)
     * Se acorda punctaj bonus pentru citirea din fisierul Exercitiu1.txt
     */

    static String textDeCitit = "He has a car, but the car it is old but he should sell it.";


    public static void main(String[] args) throws Exception {

        verificaCuvantul(returneazaPrimulCuvantDuplicat());
    }

    public static String readFileAsString(String fileName) throws Exception {
        String data = "";
        data = new String(Files.readAllBytes(Paths.get(fileName)));
        return data;
    }

    static String returneazaPrimulCuvantDuplicat() throws Exception {
        String data = readFileAsString("D:\\PentaStagiu\\p5_qa_auto_test\\src\\main\\java\\com\\pentalog\\exercitiu1\\Exercitiu1.txt");

        int nrAparitii;
        String rezultat = null;

        data = data.toLowerCase();
        String cuvinte[] = data.split(" ");

        for (int i = 0; i < cuvinte.length; i++) {
            nrAparitii = 1;
            for (int j = i + 1; j < cuvinte.length; j++) {
                if (cuvinte[i].equals(cuvinte[j])) {
                    nrAparitii++;
                }
            }
            if (nrAparitii == 2 && cuvinte[i] != "0") {
                rezultat = cuvinte[i];
                break;
            }
        }
        // System.out.println(rezultat);
        return rezultat;
    }


    public static void verificaCuvantul(String rezultat) {

        assertEquals("he", rezultat);
    }
}
