package com.pentalog.exercitiu2;

public class BMW extends Masina{
    private int an;
    private int discountProducator;
    @Override
    public void calculeazaPret(){
        this.pret=this.pret-this.pret*(this.discountProducator/100);
    }

    public int getAn() {
        return an;
    }

    public void setAn(int an) {
        this.an = an;
    }

    public int getDiscountProducator() {
        return discountProducator;
    }

    public void setDiscountProducator(int discountProducator) {
        this.discountProducator = discountProducator;
    }

    public BMW(int an,int discountProducator){
        super();
        this.an=an;
        this.discountProducator=discountProducator;
    }

    @java.lang.Override
    public java.lang.String toString(){
        return "BMW{"+
                "pret=" +this.pret+
                "}";
    }
}
