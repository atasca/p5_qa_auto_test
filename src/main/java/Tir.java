public class Tir extends Masina {
    double greutate=0;
    double discountGreutate=0;

    public void discountGreutate(double greutate)
    {
        if(greutate>2000)
        {
            discountGreutate = 15;
            System.out.println("Discountul este 15%");
        }
        else
        {
            discountGreutate = 25;
            System.out.println("Discountul este 25%");
        }
    }

    @Override
    double calculeazaPret() {
        pret=pret-(discountGreutate*pret)/100;
        return super.calculeazaPret();
    }
}
