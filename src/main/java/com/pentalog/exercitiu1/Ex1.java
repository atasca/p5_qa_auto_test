package com.pentalog.exercitiu1;
import org.junit.Test;

import java.io.*;
import java.util.Locale;


import static org.junit.Assert.assertEquals;


public class Ex1 {

  /**
   * Fiind dat un text, scrie o metoda care sa afiseze primul cuvant care are macar un duplicat.
   * Se va verifica rezultatul utilizand metoda 'verificaCuvantul' si se implementeaza la linia 25
   * Se completeaza la linia 32 expected result (in loc de xxxx)
   * Se acorda punctaj bonus pentru citirea din fisierul Exercitiu1.txt
   */

  static String textDeCitit = "He has a car, but the car it is old but he should sell it.";


  public static void main (String[] args) {

    verificaCuvantul(returneazaPrimulCuvantDuplicat());
  }


  static String returneazaPrimulCuvantDuplicat () {
    try {
      int count;
      FileReader file = new FileReader("src/main/java/com/pentalog/exercitiu1/Exercitiu1.txt");
      BufferedReader br = new BufferedReader(file);
      String line;
      while ((line = br.readLine()) != null) {
        line = line.toLowerCase(Locale.ROOT);
        String words[] = line.split(" ");

        for(int i = 0; i < words.length; i++) {
          count = 1;
          for(int j = i+1; j < words.length; j++) {
            if(words[i].equals(words[j])) {
              count++;
            }
          }
          if(count > 1)
            System.out.println(words[i]);
            return words[i];
        }

      }
    } catch (IOException e) {
      e.printStackTrace();
    }


    //rezolvare
    return "rezultat\n";
  }

  public static void verificaCuvantul (String rezultat) {
    assertEquals("he", rezultat);
  }
}
