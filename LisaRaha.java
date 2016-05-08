import javax.xml.crypto.Data;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

public class LisaRaha {

    String omanikunimi;
    LocalDate kuupäev;

    long krediitkaardiNumber; //Pikkuse kontroll, numbri kontroll
    int cvv; //Pikkuse kontroll, numbri kontroll
    double lisaRaha; //Number

    MänguAutomaat mängja; //Et saaks siduda Mänguautomaati lisaRahaga
    String sõnum;



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

        switch (asdo[1]){

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


        this.kuupäev = LocalDate.parse(asdo[0]+"-"+asdo[1]+"-01");
        this.krediitkaardiNumber = ((krediitKaardiKontroll(krediitkaardiNumber) ? krediitkaardiNumber : 0L));
        this.omanikunimi = omanikunimi;
        this.cvv = ((cvvKontroll(cvv) ? cvv : 0));
        setLisaRaha(lisaRaha);

        this.mängja = mängja;

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
    }

    public boolean krediitKaardiKontroll(long krediitkaardiNumber){
        return String.valueOf(krediitkaardiNumber).length() == 16;
    }

    public boolean cvvKontroll(int cvv){
        return String.valueOf(cvv).length() == 3;
    }

    public void failiKirjutamine() throws IOException {
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

