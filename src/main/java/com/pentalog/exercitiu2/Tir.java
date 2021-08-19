class Tir extends Masina{
    private int greutate;
    public int calculeazaPret(){
        if(this.greutate>2000)
            return this.pret=this.pret-this.pret*(15/100);
        else return this.pret=this.pret-this.pret*(25/100);

    }

    public int getGreutate() {
        return greutate;
    }

    public void setGreutate(int greutate) {
        this.greutate = greutate;
    }

    public Tir(int greutate) {
        super();
        this.greutate = greutate;
    }
}
