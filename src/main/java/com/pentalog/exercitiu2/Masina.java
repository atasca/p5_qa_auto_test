package com.pentalog.exercitiu2;

public class Masina {
    int viteza;
    int pret;
    String culoare;
    public Masina(){}
    public Masina(int viteza, int pret, String culoare){
        this.viteza=viteza;
        this.pret=pret;
        this.culoare=culoare;
    }

    public void calculeazaPret(){
    }

    public int getViteza(){
        return viteza;
    }

    public void setViteza(int viteza){
        this.viteza = viteza;
    }

    public double getPret(){
        return pret;
    }

    public int setPret(int pret){
        return this.pret=pret;
    }

    public String getCuloare(){
        return culoare;
    }

    public void setCuloare(String culoare) {
        this.culoare = culoare;
    }

    @java.lang.Override
    public java.lang.String toString(){
        return "Masina{"+
                "pret=" +this.pret+
                "}";
    }
}
