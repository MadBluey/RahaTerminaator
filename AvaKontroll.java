import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;

import java.net.URL;
import java.util.ResourceBundle;


public class AvaKontroll implements Initializable {

    @FXML //  //fx-id järgi fxml failist imporditud nupud
    private Button exitnupp; //FXML-loaderist saadud väärtus
    @FXML
    private Button playnupp;
    @FXML
    private Button lisarahanupp;
    @FXML
    private Label kasutajalabel;


    @Override // meetod kutsutakse FXMLLoaderi poolt
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        kasutajalabel.setText("Mängja 1");

        assert exitnupp != null : "exitnuppu ei ole, kontrolli Controller-klassi";


        exitnupp.setOnAction(event -> System.exit(0));
        exitnupp.setOnKeyPressed((event) -> { if(event.getCode() == KeyCode.ESCAPE) { //Esc = exit.
            System.exit(0);
        }});

        assert playnupp != null : "playnuppu ei ole, kontrolli Controller-klassi";


        playnupp.setOnAction(event -> ShowStageLoosiaken.showStage()); //kasutaja.setText("Jaanus");

        playnupp.setOnKeyPressed((event) -> { if(event.getCode() == KeyCode.ENTER) { //Enter viib mängima
            ShowStageLoosiaken.showStage();
        }});
        assert lisarahanupp != null : "nuppu ei ole, kontrolli Controller-klassi";
        lisarahanupp.setOnKeyPressed((event) -> { if(event.getCode() == KeyCode.L) { //L täht
            ShowStageRaha.showStage();
        }});

        lisarahanupp.setOnAction(event -> ShowStageRaha.showStage());  //kasutajalabel.setText("Jaanus"));


    }

}
