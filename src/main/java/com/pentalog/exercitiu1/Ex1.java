package com.pentalog.exercitiu1;


import static org.junit.Assert.assertEquals;
import java.io.*;
import java.util.*;


public class Ex1 {

  /**
   * Fiind dat un text, scrie o metoda care sa afiseze primul cuvant care are macar un duplicat.
   * Se va verifica rezultatul utilizand metoda 'verificaCuvantul' si se implementeaza la linia 25
   * Se completeaza la linia 32 expected result (in loc de xxxx)
   * Se acorda punctaj bonus pentru citirea din fisierul Exercitiu1.txt
   */

  static String textDeCitit = "He has a car, but the car it is old but he should sell it.";

  public static void main (String[] args) throws IOException{

    /*FileInputStream file=new FileInputStream("Exercitiu1.txt");
    InputStreamReader file_char=new InputStreamReader(file);
    BufferedReader buf=new BufferedReader(file_char);*/

    int numar;
    //String strings[]=buf.readLine();
    textDeCitit = textDeCitit.toLowerCase();
    String cuvinte[] = textDeCitit.split(" ");

    System.out.println("Cuvintele duplicate ");
    for(int i = 0; i < cuvinte.length; i++)
    {
      numar = 1;
      for(int j = i+1; j < cuvinte.length; j++)
      {
        if(cuvinte[i].equals(cuvinte[j]))
        {
          numar++;
          cuvinte[j] = "0";
        }
      }
      if(numar > 1 && cuvinte[i] != "0")
        System.out.println(cuvinte[i]);
    }
    verificaCuvantul(returneazaPrimulCuvantDuplicat());
  }


  static String returneazaPrimulCuvantDuplicat () {
    //rezolvare
    return "rezultat";
  }


  public static void verificaCuvantul (String rezultat) {
    assertEquals("xxxx", rezultat);
  }
}
