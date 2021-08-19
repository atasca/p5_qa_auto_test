package com.pentalog.exercitiu2;

public class Tir extends Masina{
    private int greutate;

    public Tir(int greutate){
        super();
        this.greutate=greutate;
    }
    @Override
    public void calculeazaPret(){
        if(this.greutate>2000)
            this.pret=this.pret-this.pret*(15/100);
        else
            this.pret=this.pret-this.pret*(25/100);
    }

    public int getGreutate() {
        return greutate;
    }

    public void setGreutate(int greutate) {
        this.greutate = greutate;
    }

    @java.lang.Override
    public java.lang.String toString(){
        return "Tir{"+
                "pret=" +this.pret+
                "}";
    }
}
