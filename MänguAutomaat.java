import java.util.ArrayList;
import java.util.Random;


public class MänguAutomaat {
    private double raha; //summa, mis kasutaja on panustanud täisarvudena
    private double võimalus; //Võiduvõimaluse kontrollimine.
    private int panus; //Kui palju panustatakse
    private ArrayList<String> sümbolid; //Sümbolid, mis genereeritakse
    private String sõnum; //VeaSõnum

    public String getSõnum() {
        return sõnum;
    }

    public String getSõnumA(){
        return "Teil ei ole raha lisage seda palun menüüst 'Lisa Raha'! ";
    }

    public ArrayList<String> getSümbolid() {
        return sümbolid;
    }

    public MänguAutomaat(double raha) {
        setRaha(raha);
    }

    //Getterid ja Setterid
    public double getRaha() {
        return raha;
    }

    public void setRaha(double raha) {
        this.raha = ((raha > 0) ? raha : 0);
    }

    public int getPanus() {return panus;
    }

    public void setPanus(int panus) {
        this.panus = ((panus > 0 && getRaha() >= panus) ? panus : 0);//Panus ei saa olla suurem kui raha on ja panus ei saa olla 0.
        if (panus == 5) setVõimalus(0.05);
        else if (panus == 10) setVõimalus(0.10);
        else if (panus == 20) setVõimalus(0.2);
        else if (panus == 50) setVõimalus(0.3);
        else if (panus == 100) setVõimalus(0.4);
        else if (panus == 200) setVõimalus(0.5);
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
    public ArrayList<String> sümboliGeneratsioon() {

            ArrayList<Integer> numbrid = new ArrayList<Integer>(); //Täisarvude array.
            this.sümbolid = new ArrayList<>(); //Sümbolite array. TÜHI!
            double kaal = 1;  // Arv, millega korrutakse panus, kui võidetakse. - et vältida errorit on see üks.
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
                double võimalus2 = Math.sqrt(getVõimalus());

                if (võimalus2 >= Math.random()) { //Kui suvaline arv 0-1 vahel on väiksem ühe elemendi võimalusest muutub teine element samaks esimesega.
                    numbrid.set(1, numbrid.get(0));
                }
                if (võimalus2 >= Math.random()) { //Sama asi kolmanda elemendiga.
                    numbrid.set(2, numbrid.get(0));
                }
            }

            //For-tsükkel, mis muudab arvud sümboliteks. Piltide pealkirjad. NB võitmise kontrollist on läbi käidud.
            for (int i = 0, k = 3; i < k; i++) {
                switch (numbrid.get(i)) {

                    case 0:
                        sümbolid.add(i, "pilt0.jpg");
                        kaal = 0.5;
                        break;
                    case 1:
                        sümbolid.add(i, "pilt1.jpeg");
                        kaal = 0.7;
                        break;
                    case 2:
                        sümbolid.add(i, "pilt2.png");
                        kaal = 0.9;
                        break;
                    case 3:
                        sümbolid.add(i, "pilt3.jpg");
                        kaal = 1.3;
                        break;
                    case 4:
                        sümbolid.add(i, "pilt4.jpg");
                        kaal = 1.5;
                        break;
                    case 5:
                        sümbolid.add(i, "pilt5.jpg");
                        kaal = 1.7;
                        break;
                    case 6:
                        sümbolid.add(i, "pilt6.jpg");
                        kaal = 1.9;
                        break;
                    case 7:
                        sümbolid.add(i, "pilt7.png");
                        kaal = 3;
                        break;
                    case 8:
                        sümbolid.add(i, "pilt8.jpg");
                        kaal = 5;
                        break;
                    case 9:
                        sümbolid.add(i, "pilt9.jpg");
                        kaal = 10;
                        break;
                }
            }
            //Prindib kasutaja jaoks sümbolid välja.

            //Kui kasutaja võidab, siis ta saab tagasi raha-panus+võidusumma(olenevalt kordajast)



            if (sümbolid.get(0).equals(sümbolid.get(1)) && sümbolid.get(1).equals(sümbolid.get(2))) {
            /*kui inimene võidab, korrutab panustatud raha kordajaga võidusumma lisab olemasolevale rahale,
            tagastab raha summa kokku*/
                sõnum = "Võitsite praegu " + getPanus() * kaal + " eurot.";
                setRaha(getRaha() + getPanus() * kaal - getPanus());

                //kaotuse korral lahutab mängu maksumuse panustatud rahast
            } else {
                sõnum = "Kaotasite praegu " + getPanus() + " eurot.";
                setRaha(getRaha() - getPanus());
            }

         return sümbolid;

    }
}






