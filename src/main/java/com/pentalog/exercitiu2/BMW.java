package com.pentalog.exercitiu2;

public class BMW  extends Masina{
    /*
    3. Creaza o clasa copil al clasei Masina numit  BMW cu următoarele câmpuri și metode.
            ◦ an;
◦ discountProducator;
◦ calculeazaPret();//Din pretul calculat in clasa Masina, scade discountul producatorului

     */
    private String an;
    private double discountProducator;

    @Override
    public double calculeazaPret(double pret) {
        return super.calculeazaPret(pret - discountProducator);
    }
  public BMW(String an, double discountProducator){
        this.an = an;
        this.discountProducator= discountProducator;
        super(9,"maro");
    }
}
