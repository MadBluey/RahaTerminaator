
    import java.awt.*;
    import java.awt.event.ActionEvent;

    import javafx.beans.value.ChangeListener;
    import javafx.beans.value.ObservableValue;
    import javafx.embed.swing.SwingFXUtils;
    import javafx.event.Event;

    import java.awt.event.MouseEvent;
    import java.awt.image.BufferedImage;
    import java.awt.image.ImageObserver;
    import java.awt.image.ImageProducer;
    import java.awt.image.VolatileImage;
    import java.io.File;
    import java.io.IOException;
    import java.net.URL;
    import java.util.ArrayList;
    import java.util.ResourceBundle;

    import javafx.event.EventHandler;
    import javafx.fxml.FXML;
    import javafx.fxml.Initializable;
    import javafx.scene.control.*;
    import javafx.scene.control.Button;
    import javafx.scene.control.Label;
    import javafx.scene.image.ImageView;
    import javafx.scene.image.WritableImage;
    import javafx.stage.Stage;

    import javax.imageio.ImageIO;
    import java.awt.Image;



    public class loosiaknaKontroll
            extends avaKontroll implements Initializable {

        @FXML //  fx:id="myButton"
        private Button terminaator; // Value injected by FXMLLoader
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



        @Override // This method is called by the FXMLLoader when initialization is complete
        public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
            rahalabelloosis.setText(String.valueOf(Peaklass.getA().getRaha()));
            kasutajalabelloosis.setText("Mängja 1");

            radioGrupp.selectedToggleProperty().addListener((ov, old_toggle, new_toggle) -> {
                if (radioGrupp.getSelectedToggle() != null) {
                    System.out.println();
                    Peaklass.getA().setPanus(Integer.parseInt(radioGrupp.getSelectedToggle().toString().split("'")[1]));
                }

            assert terminaator != null : "fx:id=\"myButton\" was not injected: check your FXML file 'simple.fxml'.";

            // initialize your logic here: all @FXML variables will have been injected

            terminaator.setOnAction(event -> {
                if (Peaklass.getA().getRaha() >= Peaklass.getA().getPanus()){
                ArrayList<String> a = Peaklass.getA().sümboliGeneratsioon();

                pilt1.setImage(new javafx.scene.image.Image("file:src/Pildid/" + a.get(0)));
                pilt2.setImage(new javafx.scene.image.Image("file:src/Pildid/" + a.get(1)));
                pilt3.setImage(new javafx.scene.image.Image("file:src/Pildid/" + a.get(2)));

                LASõnum.setText(Peaklass.getA().getSõnum());
                rahalabelloosis.setText(String.valueOf(Peaklass.getA().getRaha()));}
                else LASõnum.setText(Peaklass.getA().getSõnumA());


        });




    });
        } @FXML private javafx.scene.control.Button loosiexitnupp;

        @FXML
        private void väljuloosist(){
            // get a handle to the stage
            Stage stage = (Stage) loosiexitnupp.getScene().getWindow();
            // do what you have to do
            stage.close();
        }
    }
