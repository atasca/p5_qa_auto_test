package com.pentalog.exercitiu1;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Locale;
import java.util.Scanner;
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


  public static void main (String[] args) {

    verificaCuvantul(returneazaPrimulCuvantDuplicat());
  }

   static String citesteDinFisier(String numeFisier){
    StringBuilder answer= new StringBuilder();
    try{
      File readFile=new File(numeFisier);
      Scanner reader=new Scanner(readFile);
      while(reader.hasNextLine()){
        answer.append(reader.nextLine());
      }
      reader.close();
      return answer.toString();
    }catch(FileNotFoundException e){
      System.out.println("Error while processing file.");
      e.printStackTrace();
    }
    return answer.toString();
  }

  static String returneazaPrimulCuvantDuplicat () {
    textDeCitit=citesteDinFisier("C:\\Users\\Robert\\OneDrive\\Documents\\GitHub\\p5_qa_auto_test\\src\\main\\java\\com\\pentalog\\exercitiu1\\Exercitiu1.txt");
    textDeCitit=textDeCitit.replaceAll("[^a-zA-Z0-9]"," ");
    System.out.println(textDeCitit);
    String []wordsArray=textDeCitit.split("\\s+");
    Set<String> uniqueWords= new HashSet<>();

    for(String word:wordsArray){
      if(uniqueWords.contains(word.toLowerCase(Locale.ROOT))){
        return word.toLowerCase(Locale.ROOT);
      }else{
        uniqueWords.add(word.toLowerCase(Locale.ROOT));
      }
    }
    return "no duplicate word found";
  }


  public static void verificaCuvantul (String rezultat) {
    assertEquals("car", rezultat);
  }
}
