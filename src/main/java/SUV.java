public class SUV extends Masina{
    double lungime=0;
    double discountLungime=0;

    public void discountLungime(double lungime)
    {
        if(lungime>5)
        {
            discountLungime=8;
            System.out.println("Discountul este 8%");
        }
        else
        {
            discountLungime=14;
            System.out.println("Discountul este 14%");
        }
    }

    @Override
    double calculeazaPret() {
        pret=pret-(discountLungime*pret)/100;
        return super.calculeazaPret();
    }
}
