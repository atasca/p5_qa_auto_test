public class BMW extends Masina {
    String an;
    double discountProducator;

    @Override
    double calculeazaPret() {
        pret=pret-(discountProducator*pret)/100;
        return super.calculeazaPret();
    }
}
