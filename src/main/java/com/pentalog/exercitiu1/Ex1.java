package com.pentalog.exercitiu1;


import java.util.HashSet;

import static org.junit.Assert.assertEquals;


public class Ex1 {

  /**
   * Fiind dat un text, scrie o metoda care sa afiseze primul cuvant care are macar un duplicat.
   * Se va verifica rezultatul utilizand metoda 'verificaCuvantul' si se implementeaza la linia 25
   * Se completeaza la linia 32 expected result (in loc de xxxx)
   * Se acorda punctaj bonus pentru citirea din fisierul Exercitiu1.txt
   */

  static String textDeCitit = "He has a car but the car it is old but he should sell it.";


  public static void main (String[] args) {
    String rezultat = returneazaPrimulCuvantDuplicat(textDeCitit);
    System.out.println("Rezultat " + rezultat);
    verificaCuvantul(rezultat);
  }


  static String returneazaPrimulCuvantDuplicat(String textDeCitit) {
    String rezultat = "Niciun cuvant duplicat";
    String splitText[] = textDeCitit.split(" ");
    System.out.println(splitText);
    HashSet words = new HashSet<>();
    for(int i=0; i<splitText.length; i++){
      if(words.contains(splitText[i])){
        rezultat = splitText[i];
        break;
      }
      words.add(splitText[i]);
    }
    return rezultat;
  }

  public static void verificaCuvantul (String rez) {
    assertEquals("car", rez);
  }
}
