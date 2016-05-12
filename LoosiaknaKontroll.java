import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class LoosiaknaKontroll
        extends AvaKontroll implements Initializable {

    @FXML //  dx:id järgi nupud
    private Button terminaator; // väärtus FXML-st
    @FXML
    private Label rahalabelloosis;
    @FXML
    private Label LASõnum;
    @FXML
    private Label kasutajalabelloosis;
    @FXML
    private ImageView pilt1;
    @FXML
    private ImageView pilt2;
    @FXML
    private ImageView pilt3;
    @FXML
    private ToggleGroup radioGrupp;
    @FXML
    private javafx.scene.control.Button loosiexitnupp;

    @Override 
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        rahalabelloosis.setText(String.valueOf(Peaklass.getA().getRaha()));
        kasutajalabelloosis.setText("Mängja 1");

        radioGrupp.selectedToggleProperty().addListener((ov, old_toggle, new_toggle) -> {
            if (radioGrupp.getSelectedToggle() != null) {
                System.out.println();
                Peaklass.getA().setPanus(Integer.parseInt(radioGrupp.getSelectedToggle().toString().split("'")[1]));
            }

            assert terminaator != null : "nuppu ei ole, kontrolli controller-klassi";
            //terminaator.setDefaultButton(true);

            terminaator.setOnKeyPressed((event) -> { if(event.getCode() == KeyCode.ENTER) { //Enter viib mängima
                meetod();
            }});
            terminaator.setOnAction(event -> {
                meetod();
            });


        });
    }

    @FXML
    private void väljuloosist() {
        // toon endale stage
        Stage stage = (Stage) loosiexitnupp.getScene().getWindow();
        stage.close();
    }


    //Meetod sümbolite genereerimiseks ja uue rahasumma arvutamiseks (Raha - panus (+ võit kui võidab)).
    public void meetod(){
        if (Peaklass.getA().getRaha() >= Peaklass.getA().getPanus()) {

            ArrayList<String> a = Peaklass.getA().sümboliGeneratsioon();

            pilt1.setImage(new javafx.scene.image.Image("file:src/Pildid/" + a.get(0)));
            pilt2.setImage(new javafx.scene.image.Image("file:src/Pildid/" + a.get(1)));
            pilt3.setImage(new javafx.scene.image.Image("file:src/Pildid/" + a.get(2)));

            LASõnum.setText(Peaklass.getA().getSõnum());
            rahalabelloosis.setText(String.valueOf(Peaklass.getA().getRaha()));
        } else LASõnum.setText(Peaklass.getA().getSõnumA()); //Kui raha on puudu tagastab sõnumi.
    }
}
