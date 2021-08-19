package com.pentalog.exercitiu2;

public class BMW  extends Masina{
    /*
    3. Creaza o clasa copil al clasei Masina numit  BMW cu următoarele câmpuri și metode.
            ◦ an;
◦ discountProducator;
◦ calculeazaPret();//Din pretul calculat in clasa Masina, scade discountul producatorului

     */
    private int an;
    private double discountProducator;

    @Override
    public double calculeazaPret(double pret) {
        return super.calculeazaPret(pret - discountProducator);
    }
  public BMW(int an, double discountProducator){
      super(80,"rosu");
      this.an = an;
        this.discountProducator= discountProducator;

    }
}
