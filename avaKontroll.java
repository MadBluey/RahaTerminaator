import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;

import java.net.URL;
import java.util.ResourceBundle;


public class avaKontroll implements Initializable {

    @FXML //  fx:id="myButton"
    private Button exitnupp; // Value injected by FXMLLoader
    @FXML
    private Button playnupp;
    @FXML
    private Button lisarahanupp;
    @FXML
    private Label kasutajalabel;


    @Override // This method is called by the FXMLLoader when initialization is complete
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        kasutajalabel.setText("Mängja 1");

        assert exitnupp != null : "fx:id=\"myButton\" was not injected: check your FXML file 'simple.fxml'.";


        exitnupp.setOnAction(event -> System.exit(0));
        exitnupp.setOnKeyPressed((event) -> { if(event.getCode() == KeyCode.ESCAPE) { //Esc = exit.
            System.exit(0);
        }});

        assert playnupp != null : "fx:id=\"myButton\" was not injected: check your FXML file 'simple.fxml'.";


        playnupp.setOnAction(event -> showstageLoosiaken.showStage()); //kasutaja.setText("Jaanus");

        playnupp.setOnKeyPressed((event) -> { if(event.getCode() == KeyCode.ENTER) { //Enter viib mängima
            showstageLoosiaken.showStage();
        }});
        assert lisarahanupp != null : "fx:id=\"myButton\" was not injected: check your FXML file 'simple.fxml'.";
        lisarahanupp.setOnKeyPressed((event) -> { if(event.getCode() == KeyCode.L) { //L täht
            showstageraha.showStage();
        }});

        lisarahanupp.setOnAction(event -> showstageraha.showStage());  //kasutajalabel.setText("Jaanus"));


    }

}
