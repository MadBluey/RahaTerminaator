/*
 * Created by OveLiis on 07/03/2016.
 */

//Töö autorid on Ove Liis Mahhov ja Karl Kaspar Haavel.


import java.util.ArrayList;
import java.util.Random;


public class MänguAutomaat {
    private double raha; //summa, mis kasutaja on panustanud täisarvudena
    private double panus; //palju maksab üks mäng/kangitõmme
    private double võimalus; //Võiduvõimaluse kontrollimine.

    public MänguAutomaat(double raha, double panus) {
        setRaha(raha);
        setPanus(panus);
    }

    //Getterid ja Setterid
    public double getRaha() {
        return raha;
    }

    public void setRaha(double raha) {
        this.raha = ((raha > 0) ? raha : 0);
    }

    public double getPanus() {
        return panus;
    }

    public void setPanus(double panus) {
        this.panus = ((panus > 0 && getRaha() >= panus) ? panus : 0); //Panus ei saa olla suurem kui raha on ja panus ei saa olla 0.
    }

    public double getVõimalus() {
        return võimalus;
    }

    //Konstruktor.

    public void setVõimalus(double võimalus) {
        this.võimalus = ((võimalus >= 0.01 && võimalus <= 1) ? võimalus : 0.01); //Võimalus, mis kontrolllib tõenäosust saada samad numbrid.
    }

    //Meetodid

    //Genereerib arraylisti kolm suvalist sümbolit vahemikus A-Jni. ((Siin sees on 3 meetodit -> Lihtsuse mõttes :D))

    //Võib vist Voidiks ka teha selle.
    public ArrayList<String> sümboliGeneratsioon(double võimalus) {
        ArrayList<Integer> numbrid = new ArrayList<Integer>(); //Täisarvude array.
        ArrayList<String> sümbolid = new ArrayList<String>(); //Sümbolite array.
        int kordaja = 1;  // Arv, millega korrutakse panus, kui võidetakse. - et vältida errorit on see üks.
        for (int i = 0, k = 3; i < k; i++) { //Genereerib arraylisti kolm suvalist numbrit vahemikus 0-9ni.
            Random slot = new Random();
            int Number = slot.nextInt(10);
            numbrid.add(Number);
        }

        /*
        Kasutaja võitmise kontrollimine -> Esimese arv on suvaline. Teise ja kolmanda arvu võimalus muuta samaks on ruutjuur
        võiduvõimalusest. Sest kui üldine võiduvõimalus on näiteks 90%, siis et teine oleks sama peab olema u 94% ja kolmas sama
        on ka 94%, sest ~94%*94 = 90%. -> Kui võiduvõimalust ei kontrolli on võiduvõimalus u 1% ?
         Esimene element 100%, teine element 10%, kolmas element sama 10% --> 1%. (Mdea mu loogika ütleb nii)

         */
        //Paremat meetodit vaja.
        //Käsk toimib ainult siis kui kõik kolm numbrit ei ole võrdsed.
        if (!(numbrid.get(0).equals(numbrid.get(1)) && numbrid.get(0).equals(numbrid.get(2)))) {
            double võimalus2 = Math.sqrt(võimalus);

            if (võimalus2 >= Math.random()) { //Kui suvaline arv 0-1 vahel on väiksem ühe elemendi võimalusest muutub teine element samaks esimesega.
                numbrid.set(1, numbrid.get(0));
            }
            if (võimalus2 >= Math.random()) { //Sama asi kolmanda elemendiga.
                numbrid.set(2, numbrid.get(0));
            }
        }

        //For-tsükkel, mis muudab arvud sümboliteks. Praegu on tähed. NB võitmise kontrollist on läbi käidud.
        for (int i = 0, k = 3; i < k; i++) {
            switch (numbrid.get(i)) {

                case 0:
                    sümbolid.add(i, "A");
                    kordaja = 2;
                    break;
                case 1:
                    sümbolid.add(i, "B");
                    kordaja = 3;
                    break;
                case 2:
                    sümbolid.add(i, "C");
                    kordaja = 4;
                    break;
                case 3:
                    sümbolid.add(i, "D");
                    kordaja = 5;
                    break;
                case 4:
                    sümbolid.add(i, "E");
                    kordaja = 6;
                    break;
                case 5:
                    sümbolid.add(i, "F");
                    kordaja = 7;
                    break;
                case 6:
                    sümbolid.add(i, "G");
                    kordaja = 8;
                    break;
                case 7:
                    sümbolid.add(i, "H");
                    kordaja = 9;
                    break;
                case 8:
                    sümbolid.add(i, "I");
                    kordaja = 10;
                    break;
                case 9:
                    sümbolid.add(i, "J");
                    kordaja = 11;
                    break;
            }
        }
        //Prindib kasutaja jaoks sümbolid välja.
        System.out.println(sümbolid);

        //Kui kasutaja võidab, siis ta saab tagasi raha-panus+võidusumma(olenevalt kordajast)

        if (sümbolid.get(0).equals(sümbolid.get(1)) && sümbolid.get(1).equals(sümbolid.get(2))) {
            /*kui inimene võidab, korrutab panustatud raha kordajaga võidusumma lisab olemasolevale rahale,
            tagastab raha summa kokku*/
            System.out.println("Võitsite praegu " + getPanus() * kordaja + " eurot.");
            setRaha(getRaha() + getPanus() * kordaja);

            //kaotuse korral lahutab mängu maksumuse panustatud rahast
        } else setRaha(getRaha() - getPanus());

        return sümbolid;
    }
}
