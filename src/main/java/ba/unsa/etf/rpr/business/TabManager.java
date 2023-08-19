package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.exceptions.RecordStoreException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class TabManager {
    public void closeWindow(ActionEvent actionEvent) {
        Node n = (Node) actionEvent.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        stage.close();
    }

    public void changeWindow(String fxmlFileName, String title, Object controller, ActionEvent actionEvent) throws RecordStoreException {
        openWindow(fxmlFileName, title, controller, actionEvent);
        closeWindow(actionEvent);
    }

    public Stage openWindow(String fxmlFileName, String title, Object controller, ActionEvent actionEvent) throws RecordStoreException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/" + fxmlFileName + ".fxml"));
        loader.setController(controller);
        Stage newStage = new Stage();
        try {
            newStage.setScene(new Scene(loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            newStage.setTitle("RecordStore | " + title);
            newStage.setResizable(false);
            newStage.show();
            return newStage;
        } catch (IOException e) {
            throw new RecordStoreException(e.getMessage(), e);
        }
    }
}
