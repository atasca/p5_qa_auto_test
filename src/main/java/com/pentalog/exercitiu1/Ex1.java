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
    int count=1;

    for(int i=0;i<=s.length;i++)
    {
      for(int j=i+1;j<s.length;j++) {
        if (s[i].equals(s[j]) && s[i] != "-1") {
          s[j] = "-1";
          count++;
          if (count == 2)
            return s[i];
        }
      }
    }

    if(count>1 && s[i]!="-1")
    {
      System.out.println(s[i]+" "+count);
      s[i]="-1";
    }
    count=1;
  }

    return s[i];
  }


  public static void verificaCuvantul (String rezultat) {
    assertEquals("car", rezultat);
  }
}
