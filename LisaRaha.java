
import java.io.*;
import java.time.LocalDate;


public class LisaRaha {

    private String omanikunimi;
    private LocalDate kuupäev;

    private long krediitkaardiNumber; //Pikkuse kontroll, numbri kontroll
    private int cvv; //Pikkuse kontroll, numbri kontroll
    private double lisaRaha; //Rahasumma, mis lisatakse

    private MänguAutomaat mängja; //Et saaks siduda Mänguautomaati lisaRahaga
    private String sõnum; //Veasõnumid - igaks juhuks.



    public MänguAutomaat getMängja() {
        return mängja;
    }
    //konstruktor kontrollib ja siis kirjutab faili kui kõik on OK.


    public String getSõnum() {
        return sõnum;
    }

    public void setLisaRaha(double lisaRaha) {
        if(lisaRaha <= 0){
            sõnum = "Te ei lisanud raha, negatiivsed numbrid ei ole lubatud.";
                   this.lisaRaha = 0;}
        else if (lisaRaha>0) this.lisaRaha=lisaRaha;

    }

    public LisaRaha(long krediitkaardiNumber, String omanikunimi, int cvv, String kuupäev2, double lisaRaha, MänguAutomaat mängja) throws IOException {

        String[] asdo = kuupäev2.split(" ");

        switch (asdo[1]){ //Kui sisestatakse kuu nimi, siis muudetakse numbriks. - See on ülejääk eelmisest GUIst.

                case "jaanuar":
                    asdo[1] = "01";
                    break;
                case "veebruar":
                    asdo[1] = "02";
                    break;
                case "märts":
                    asdo[1] = "03";
                    break;
                case "aprill":
                    asdo[1] = "04";
                    break;
                case "mai":
                    asdo[1] = "05";
                    break;
                case "juuni":
                    asdo[1] = "06";
                    break;
                case "juuli":
                    asdo[1] = "07";
                    break;
                case "august":
                    asdo[1] = "08";
                    break;
                case "september":
                    asdo[1] = "09";
                    break;
                case "oktoober":
                    asdo[1] = "10";
                    break;
                case "november":
                    asdo[1] = "11";
                    break;
                case "detsember":
                    asdo[1] = "12";
                    break;
                }


        this.kuupäev = LocalDate.parse(asdo[0]+"-"+asdo[1]+"-01"); //Muudetakse String localDate'iks
        this.krediitkaardiNumber = ((krediitKaardiKontroll(krediitkaardiNumber) ? krediitkaardiNumber : 0L)); //Kui krediitkaart ei vasta nõudele on ta 0.
        this.omanikunimi = omanikunimi; //Omaniku nimi
        this.cvv = ((cvvKontroll(cvv) ? cvv : 0)); //CVV kontroll, kui see ei läbi on see 0.
        setLisaRaha(lisaRaha);

        this.mängja = mängja;

        //Kui ei vasta nõuetele, siis viskab erindeid.
        if (krediitkaardiNumber == 0L) {sõnum = "Sisestati vale krediitkaardinumber."; throw new ValeKrediitKaardiInfo(sõnum);}
        else if (omanikunimi.length()==0) {sõnum = "Sisestage palun nimi."; throw new ValeKrediitKaardiInfo(sõnum);}
        else if (cvv == 0) {sõnum = "Sisestati vale cvv."; throw new ValeKrediitKaardiInfo(sõnum);}
        else if (!(kuupäevKehtib(kuupäev))){ sõnum = "Sisestati vale kuupäev."; throw new ValeKrediitKaardiInfo(sõnum);}
        else if (lisaRaha == 0){ sõnum="Raha ei lisatud juurde, vigane sisestus."; throw new ValeKrediitKaardiInfo(sõnum);}
        else {
            this.mängja.setRaha(mängja.getRaha()+this.lisaRaha);

            failiKirjutamine();
        }
    }

    public boolean kuupäevKehtib(LocalDate kuupäev) {
        return kuupäev.isAfter(LocalDate.now());
    } //Kuupäeva kehtivuse kontroll - kuupäev peab olema hilisem tänasest päevast.

    public boolean krediitKaardiKontroll(long krediitkaardiNumber){
        return String.valueOf(krediitkaardiNumber).length() == 16; //Krediitkaardil on 16 numbrit :D
    }

    public boolean cvvKontroll(int cvv){
        return String.valueOf(cvv).length() == 3;
    } //CVV on 3 numbriline.

    public void failiKirjutamine() throws IOException { //KrediitkaardiInfo faili kirjutamine.
        File salvestatudfail = new File("salainfo.txt");
        if (!salvestatudfail.exists()) {
            salvestatudfail.createNewFile();}
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(salvestatudfail.getAbsoluteFile()))){
            dos.writeUTF(omanikunimi);
            dos.writeUTF(kuupäev.toString());
            dos.writeLong(krediitkaardiNumber);
            dos.writeInt(cvv);
            dos.writeDouble(lisaRaha);
        }


    }

}

