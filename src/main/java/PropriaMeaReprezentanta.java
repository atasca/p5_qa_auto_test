public class PropriaMeaReprezentanta {
    public static void main(String args[])
    {
        SUV suv=new SUV();
        suv.viteza=180;
        suv.pret=30000;
        suv.culoare="red";
        suv.lungime=5;
        System.out.println(suv.calculeazaPret());

        BMW bmw_1=new BMW();
        bmw_1.viteza=200;
        bmw_1.pret=25000;
        bmw_1.culoare="black";
        bmw_1.an= "2014";
        bmw_1.discountProducator=15;
        System.out.println(bmw_1.calculeazaPret());

        BMW bmw_2=new BMW();
        bmw_2.viteza=240;
        bmw_2.pret=29000;
        bmw_2.culoare="green";
        bmw_2.an= "2020";
        bmw_2.discountProducator=5;
        System.out.println(bmw_2.calculeazaPret());

        Masina masina=new Masina();
        masina.viteza=300;
        masina.pret=15000;
        masina.culoare="orange";
        System.out.println(masina.calculeazaPret());

    }
}
