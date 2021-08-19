package com.pentalog.exercitiu2;

public class PropriaMeaReprezentanta {
    /*
    .Creaza o clasa numita PropriaMeaReprezentanta care sa contina metoda main. Declara urmatoarele :

◦Creaza o instanta a clasei SUV si initializeaza-l cu toate valorile. Foloseste metoda `super` in constructor pentru initializarea field-urilor din clasa parinte.
◦Creaza doua instante a clasei BMW si initializeaza-l cu toate valorile. Foloseste metoda `super` in constructor pentru initializarea field-urilor din clasa parinte.
◦Creaza o instanta a clasei Masina si initializeaza toate field-urile cu valori corespunzatoare. Afiseaza toate preturile masinilor.
     */

    public static void main(String[] args) {

 SUV suv = new SUV(7);
 BMW bmw = new BMW(1700, 6);
 Masina masina = new Masina(50, "neagru");

    }
}
