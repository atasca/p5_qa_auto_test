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
    int numara;
    String rezultat = "";
    String split_text[] = textDeCitit.split(" ");


    System.out.println("Cuvinte duplicate: ");

    for(int i = 0; i < split_text.length; i++) {
      numara = 1;
      for(int j = i+1; j < split_text.length; j++) {
        if(split_text[i].equals(split_text[j])) {
          numara++;

          split_text[j] = "0";
        }
      }

      if(numara > 1 && split_text[i] != "0") {
        rezultat = split_text[i];
        System.out.println(split_text[i]);
      }
    }

    return rezultat;
  }


  public static void verificaCuvantul (String rezultat) {
    assertEquals("but", rezultat);
  }
}

