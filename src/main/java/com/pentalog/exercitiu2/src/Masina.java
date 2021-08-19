//1. Creați o clasă părinte numita Masina. Clasa Masina are următoarele câmpuri și metode.
//        ◦ viteza;
//        ◦ pret;
//        ◦ culoare;
//        ◦ calculeazaPret();
//
//        2. Creaza o clasa copil al clasei Masina numit Tir cu următoarele câmpuri și metode.
//        ◦ greutate;
//        ◦ calculeazaPret();
//        ◦daca greutate > 2000 , 15% discount. altfel ,25 % discount.
//
//        3. Creaza o clasa copil al clasei Masina numit  BMW cu următoarele câmpuri și metode.
//        ◦ an;
//        ◦ discountProducator;
//        ◦ calculeazaPret();//Din pretul calculat in clasa Masina, scade discountul producatorului
//
//        4. Creaza o clasa copil al clasei Masina numit  SUV cu următoarele câmpuri și metode.
//        ◦ lungime;
//        ◦ calculeazaPret(); //daca lungimea ii mare mare de 5 metri atunci se primeste un discount de 8% , altfel 14% discount.
//
//        5.Creaza o clasa numita PropriaMeaReprezentanta care sa contina metoda main. Declara urmatoarele :
//
//        ◦Creaza o instanta a clasei SUV si initializeaza-l cu toate valorile. Foloseste metoda `super` in constructor pentru initializarea field-urilor din clasa parinte.
//        ◦Creaza doua instante a clasei BMW si initializeaza-l cu toate valorile. Foloseste metoda `super` in constructor pentru initializarea field-urilor din clasa parinte.
//        ◦Creaza o instanta a clasei Masina si initializeaza toate field-urile cu valori corespunzatoare. Afiseaza toate preturile masinilor.

public class Masina {

    int viteza;
    int pret;
    String culoare;

    void calculeazaPret() {
        pret = 5000;
    }

}

class Tir extends Masina {
    int greutate;


    void calculeazaPret() {
        if (greutate > 2000) {
            double pret_tir = pret / 1.15;
        } else {
            double pret_tir = pret / 1.25;
        }
        return pret_tir;

    }
}
class BMW extends Masina {
    int an;
    float discountProducator;

    void calculeazaPret() {
       double pret_bmw = pret * (discountProducator / 100);
    }
}
class SUV extends Masina {
    int lungime;

    void calculeazaPret() {
        if (lungime > 5) {
            int discountProducator = 8;
        } else {
            int discountProducator = 14;
        }


    }
}

class PropriaMeaReprezentanta extends Masina {
    //        5.Creaza o clasa numita PropriaMeaReprezentanta care sa contina metoda main. Declara urmatoarele :
    //
    //        ◦Creaza o instanta a clasei SUV si initializeaza-l cu toate valorile. Foloseste metoda `super` in constructor pentru initializarea field-urilor din clasa parinte.
    //        ◦Creaza doua instante a clasei BMW si initializeaza-l cu toate valorile. Foloseste metoda `super` in constructor pentru initializarea field-urilor din clasa parinte.
    //        ◦Creaza o instanta a clasei Masina si initializeaza toate field-urile cu valori corespunzatoare. Afiseaza toate preturile masinilor.

}
