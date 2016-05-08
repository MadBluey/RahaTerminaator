import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by OveLiis on 26/04/2016.
 */
public class Peaklass extends Application {


    private static MänguAutomaat a;
    private static LisaRaha b;

    public static MänguAutomaat getA() {
        return a;
    } //Get klass, et saaks läbi peaklassi kutsuda mänguautomaadi meetodeid.


    public static void main(String[] args) throws IOException {

        //Luuakse algseisud.
        a = new MänguAutomaat(0.0);
        a.setPanus(5); //Min panus.
        b = new LisaRaha(1234567890123456L,"Algbilanss", 100, "9999 jaanuar", 500, a);

        //GUI algus.
            Application.launch(Peaklass.class, (java.lang.String[])null);
        }

        @Override
        public void start(Stage primaryStage) {
            try {
                AnchorPane page = (AnchorPane) FXMLLoader.load(Peaklass.class.getResource("aken.fxml"));
                Scene scene = new Scene(page);
                primaryStage.setScene(scene);
                primaryStage.setTitle("Mängupõrgu");
                primaryStage.initStyle(StageStyle.UNDECORATED);
                primaryStage.show();



            } catch (Exception ex) {
                Logger.getLogger(Peaklass.class.getName()).log(Level.SEVERE, null, ex);
            }
        }


    }


