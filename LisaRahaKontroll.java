
import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

import javafx.stage.Stage;


public class LisaRahaKontroll extends avaKontroll implements Initializable {

    @FXML
    private Button lisarahanupp;
    @FXML
    private Label olemasraha;
    @FXML
    private Label KKSõnum;
    @FXML
    private TextField krediit1;
    @FXML
    private TextField krediit2;
    @FXML
    private TextField krediit3;
    @FXML
    private TextField krediit4;
    @FXML
    private TextField CVV;
    @FXML
    private TextField rahakogus;
    @FXML
    private TextField aegumisAasta;
    @FXML
    private TextField aegumisKuu;
    @FXML
    private TextField omanikunimi;


    LisaRaha a;
    @Override // This method is called by the FXMLLoader when initialization is complete
    public void initialize(URL fxmlFileLocation, ResourceBundle resources)  {
        olemasraha.setText(String.valueOf(Peaklass.getA().getRaha()));

        assert lisarahanupp != null : "fx:id=\"myButton\" was not injected: check your FXML file 'simple.fxml'.";

        // initialize your logic here: all @FXML variables will have been injected
        try{failistLugemine();} catch(Exception e){ KKSõnum.setText("Toimus viga andmete sisse lugemisel, vabandame.");}

        lisarahanupp.setOnKeyPressed((event) -> { if(event.getCode() == KeyCode.ENTER) {
           meetod();
        }});


        lisarahanupp.setOnAction(event -> { //Kui vajutatakse nupule, luuakse uus LisaRaha.
            meetod();
        });
    }


    @FXML private javafx.scene.control.Button rahaexit;

    @FXML
    private void väljurahast() {

        Stage stage = (Stage) rahaexit.getScene().getWindow();

        stage.close();
    }

    //Meetod failist lugemiseks - loeb andmed, mis on failis ning paneb need vastavatesse tekstiväljadesse. ((Kodutöö tingimus)).
    public void failistLugemine() throws Exception {
        try(DataInputStream dos = new DataInputStream(new FileInputStream(new File("salainfo.txt")))){
            String Nimi = dos.readUTF();
            String kuupäev4 = dos.readUTF();
            String krediitKaart = String.valueOf(dos.readLong());
            int cvv4 = dos.readInt();
            double lisatudRaha = dos.readDouble();

            omanikunimi.setText(Nimi);
            krediit1.setText(krediitKaart.substring(0,4));
            krediit2.setText(krediitKaart.substring(4,8));
            krediit3.setText(krediitKaart.substring(8,12));
            krediit4.setText(krediitKaart.substring(12,16));
            CVV.setText(String.valueOf(cvv4));
            rahakogus.setText(String.valueOf(lisatudRaha));
            aegumisAasta.setText(kuupäev4.substring(0,4));
            aegumisKuu.setText(kuupäev4.substring(5,7));

        }
    }

    public void meetod(){
        try {
            a = new LisaRaha(Long.parseLong(krediit1.getText()+krediit2.getText()+krediit3.getText()+krediit4.getText()),
                    omanikunimi.getText(),
                    Integer.parseInt(CVV.getText()),
                    aegumisAasta.getText() + " " + aegumisKuu.getText(),
                    Double.parseDouble(rahakogus.getText()),
                    Peaklass.getA());
            KKSõnum.setText(a.getSõnum()); //Sõnum LisaRahast.
            olemasraha.setText(String.valueOf(Peaklass.getA().getRaha())); //Raha olek.
        } catch (IOException e) {
            KKSõnum.setText("Toimus viga andmete sisestusel. Palun proovige uuesti.");
        }

    }
}

