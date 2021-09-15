package com.pentalog.exercitiu2;

public class SUV extends Masina{
    private float lungime;

    public SUV(float lungime){
        super();
        this.lungime=lungime;
    }
    @Override
    public void calculeazaPret(){
        if(this.lungime>5)
            this.pret = this.pret-this.pret*(8/100);
        else
            this.pret=this.pret-this.pret*(14/100);
    }

    public float getLungime() {
        return lungime;
    }

    public void setLungime(float lungime) {
        this.lungime = lungime;
    }

    @java.lang.Override
    public java.lang.String toString(){
        return "SUV{"+
                "pret=" +this.pret+
                "}";
    }
}

