package com.pentalog.exercitiu1;


import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
 /*
    // variabila pentru numararea cuvintelor
    int count;
    // string to lowerCase
    textDeCitit = textDeCitit.toLowerCase();
    // impartim stringul in cuvinte
    String cuvinte[] = textDeCitit.split(" ");
    for (int i = 0; i < cuvinte.length; i++) {
      count = 1;
      for (int j = i + 1; j < cuvinte.length; j++) {
        if (cuvinte[i].equals(cuvinte[j])) {
          count++;
          cuvinte[j] = "0";
        }
      }
      if (count > 1 && cuvinte[i] != "0") {
        System.out.println(cuvinte[i]);
      }
    }
  */
    // am incercat  dar mi-a dat cu virgula,   cred ca trebuie sa-ti adaptezi codul la Business requirements
    InputStream fraza = Ex1.class.getClassLoader().getResourceAsStream("Exercitiu1.txt");

    String[] words = textDeCitit.split("\\W");

    Map<String, Integer> cuvinteMapate = new HashMap<>();

    for (String word : words) {

      if (cuvinteMapate.get(word) != null) {
        cuvinteMapate.put(word, cuvinteMapate.get(word) + 1);
      }
      else {
        cuvinteMapate.put(word, 1);
      }
    }
    Set<String> cuvinteSet = cuvinteMapate.keySet();

    for (String word : cuvinteSet) {

      if (cuvinteMapate.get(word) > 1)

        System.out.println(word);
    }

      return "rezultat";
    }

  public static void verificaCuvantul (String rezultat) {
    assertEquals(returneazaPrimulCuvantDuplicat(), rezultat);
  }
}
