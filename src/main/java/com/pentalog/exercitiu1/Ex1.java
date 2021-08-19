package com.pentalog.exercitiu1;


import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;


public class Ex1 {

  /**
   * Fiind dat un text, scrie o metoda care sa afiseze primul cuvant care are macar un duplicat.
   * Se va verifica rezultatul utilizand metoda 'verificaCuvantul' si se implementeaza la linia 25
   * Se completeaza la linia 32 expected result (in loc de xxxx)
   * Se acorda punctaj bonus pentru citirea din fisierul Exercitiu1.txt
   */

  static String filename = "C:\\Facultate\\Pentastagiu\\TestPractic\\p5_qa_auto_test\\src\\main\\java\\com\\pentalog\\exercitiu1\\Exercitiu1.txt";


  public static void main (String[] args) {
    verificaCuvantul(returneazaPrimulCuvantDuplicat());
  }


  static String returneazaPrimulCuvantDuplicat () {
    //rezolvare folosind Map
    StringBuilder textBuilder = new StringBuilder();

    try
    {

      BufferedReader reader = new BufferedReader(new FileReader(new File(filename)));
      String line;
      while ((line = reader.readLine()) != null)
      {
        textBuilder.append(line);
      }
      reader.close();
    }
    catch (Exception e)
    {
      System.out.println("Nu s-a putut citi din fisier!");
      e.printStackTrace();
    }

    String textDeCitit = textBuilder.toString();
    String[] words = textDeCitit.split("\\W+");   // orice nu este cuvant se face split


    //Map<String, Integer> wordsMap = new HashMap<>();
    List<String> wordsList= new ArrayList<>();
    for ( String w : words) {
      /*if(!wordsMap.containsKey(w))
      {
        wordsMap.put(w,1);
      }
      else
      {
        return w;
      }*/
      if(!wordsList.contains(w))
      {
        wordsList.add(w);
      }
      else
      {
        return w;
      }
    }

    return "";
  }


  public static void verificaCuvantul (String rezultat) {
    System.out.println("Primul duplicat:" + rezultat);
    assertEquals("car", rezultat);
  }
}
