import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;


public class LisaRaha {

    private String omanikunimi;
    private LocalDate kuupäev;

    private long krediitkaardiNumber; //Pikkuse kontroll, numbri kontroll
    private int cvv; //Pikkuse kontroll, numbri kontroll
    private double lisaRaha; //Rahasumma, mis lisatakse

    private MänguAutomaat mängja; //Et saaks siduda Mänguautomaati lisaRahaga
    private String sõnum; //Veasõnumid - igaks juhuks.


    public LisaRaha(long krediitkaardiNumber, String omanikunimi, int cvv, String kuupäev2, double lisaRaha, MänguAutomaat mängja) throws IOException {

        String[] asdo = kuupäev2.split(" ");

        switch (asdo[1]) { //Kui sisestatakse kuu nimi, siis muudetakse numbriks. - See on ülejääk eelmisest GUIst - on ka võimalik nii kuu sisestada :D.

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

try {
    this.kuupäev = LocalDate.parse(asdo[0] + "-" + asdo[1] + "-01"); //Muudetakse String localDate'iks
    this.krediitkaardiNumber = ((krediitKaardiKontroll(krediitkaardiNumber) ? krediitkaardiNumber : 0L)); //Kui krediitkaart ei vasta nõudele on ta 0.
    this.omanikunimi = omanikunimi; //Omaniku nimi
    this.cvv = ((cvvKontroll(cvv) ? cvv : 0)); //CVV kontroll, kui see ei läbi on see 0.
    setLisaRaha(lisaRaha);
    this.mängja = mängja;

    if(omanikunimi.length()==0){
        throw new ValeKrediitKaardiInfo("Sisestage palun krediitkaardi omanikunimi!");
    }
        this.mängja.setRaha(mängja.getRaha() + this.lisaRaha);
        failiKirjutamine();

}

        catch (DateTimeParseException e){
            sõnum = "Viga! Vale kuupäev!"; //Et jõuaks otse kasutajani
        }
        catch (NumberFormatException e){
            sõnum = "Viga! Te ei saa lisada sellist numbrit/sümbolit oma bilanssi! ";
        }
        catch (ValeKrediitKaardiInfo e) {
            sõnum = e.toString();
        }

    }

    //konstruktor kontrollib ja siis kirjutab faili kui kõik on OK.

    public MänguAutomaat getMängja() {
        return mängja;
    }

    public String getSõnum() {
        return sõnum;
    }

    public void setLisaRaha(double lisaRaha) {
        if (lisaRaha <= 0) {
            sõnum = "Te ei lisanud raha, negatiivsed numbrid ei ole lubatud.";
            throw new NumberFormatException();
        } else if (lisaRaha > 0) this.lisaRaha = lisaRaha;

    }

    public boolean kuupäevKehtib(LocalDate kuupäev) {
        return kuupäev.isAfter(LocalDate.now());
    } //Kuupäeva kehtivuse kontroll - kuupäev peab olema hilisem tänasest päevast.

    public boolean krediitKaardiKontroll(long krediitkaardiNumber) {
        if (String.valueOf(krediitkaardiNumber).length() != 16) throw new ValeKrediitKaardiInfo("Krediitkaardi number on vale!");
        else return String.valueOf(krediitkaardiNumber).length() == 16; //Krediitkaardil on 16 numbrit :D
    }

    public boolean cvvKontroll(int cvv) {
        if(String.valueOf(cvv).length() != 3) throw new ValeKrediitKaardiInfo("CVV on vale!");
        return String.valueOf(cvv).length() == 3;
    } //CVV on 3 numbriline.

    public void failiKirjutamine() throws IOException { //KrediitkaardiInfo faili kirjutamine.
        File salvestatudfail = new File("salainfo.txt");
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(salvestatudfail.getAbsoluteFile()))) {
            dos.writeUTF(omanikunimi);
            dos.writeUTF(kuupäev.toString());
            dos.writeLong(krediitkaardiNumber);
            dos.writeInt(cvv);
            dos.writeDouble(lisaRaha);
        }


    }

}

