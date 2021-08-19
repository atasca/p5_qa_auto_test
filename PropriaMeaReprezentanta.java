package com.pentalog.exercitiu2;

public class PropriaMeaReprezentanta {
    SUV suv = new SUV();
    BMW bmw1 = new BMW();
    BMW bmw2 = new BMW();
    Masina masina = new Masina();

    public SUV getSuv() {
        return suv;
    }

    public void setSuv(SUV suv) {
        this.suv = suv;
    }

    public BMW getBmw1() {
        return bmw1;
    }

    public void setBmw1(BMW bmw1) {
        this.bmw1 = bmw1;
    }

    public BMW getBmw2() {
        return bmw2;
    }

    public void setBmw2(BMW bmw2) {
        this.bmw2 = bmw2;
    }

    public Masina getMasina() {
        return masina;
    }

    public void setMasina(Masina masina) {
        this.masina = masina;
    }


    public void main(String[] args) {

        System.out.println(masina.pret);
    }
}
