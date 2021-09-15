package com.pentalog.exercitiu1;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

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
    String[] words = textDeCitit.split("[ ,]");
    List<String> repetedWords = new ArrayList<>();
    HashSet<String> uniqueWords = new HashSet<>();
    for(String str:words) {
      if (!uniqueWords.add(str))
        repetedWords.add(str);
    }
    return repetedWords.get(0);
  }


  public static void verificaCuvantul (String rezultat) {
    assertEquals("car", rezultat);
  }
}
