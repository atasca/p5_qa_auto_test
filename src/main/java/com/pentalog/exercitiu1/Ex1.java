package com.pentalog.exercitiu1;


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
    textDeCitit = textDeCitit.toLowerCase();
    String words[] = textDeCitit.replaceFirst(",", "").split(" ");

    for(int i = 0; i<words.length; i++){
      for(int j=i+1; j<words.length; j++){
        if (words[i].equals(words[j])) {
          return words[i];
        }
      }
    }

    return null;
  }


  public static void verificaCuvantul (String rezultat) {
    assertEquals("he", rezultat);
  }
}
