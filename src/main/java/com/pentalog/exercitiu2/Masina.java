class Masina {

    private int viteza;
    private int pret;
    private String culoare;
    public int discount;

    public Masina(int viteza, int pret, String culoare) {
        this.viteza = viteza;
        this.pret = pret;
        this.culoare = culoare;
    }

    public float calculeazaPret() {

    }

    public int getViteza() {
        return viteza;
    }

    public void setViteza(int viteza) {
        this.viteza = viteza;
    }

    public int getPret() {
        return pret;
    }

    public void setPret(int pret) {
        this.pret = pret;
    }

    public String getCuloare() {
        return culoare;
    }

    public void setCuloare(String culoare) {
        this.culoare = culoare;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Masina{" +
                "pret=" + this.pret
        '}';
    }
}