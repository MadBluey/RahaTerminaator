import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Kaspar on 19/05/16.
 */
public class ShowPeaKlass {

    public static void meetod(String fxml){
        try {
            Stage newStage = new Stage();
            AnchorPane page = (AnchorPane) FXMLLoader.load(Peaklass.class.getResource(fxml));
            Scene scene = new Scene(page);
            newStage.setScene(scene);
            newStage.setTitle("Mängupõrgu");
            newStage.initStyle(StageStyle.UNDECORATED);
            newStage.show();
        } catch (Exception ex) {
            Logger.getLogger(Peaklass.class.getName()).log(Level.SEVERE, null, ex);
    }
}
}
