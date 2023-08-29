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

/**
 * Manages opening, changing and closing of the app tabs and windows
 * @author Emina Peljto
 */

public class TabManager {
    /**
     * Closes the current window
     * @param actionEvent
     */
    public void closeWindow(ActionEvent actionEvent) {
        Node n = (Node) actionEvent.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        stage.close();
    }

    /**
     * Displays a new window
     * @param fxmlFileName
     * @param title
     * @param controller
     * @param actionEvent
     * @throws RecordStoreException
     */
    public void changeWindow(String fxmlFileName, String title, Object controller, ActionEvent actionEvent) throws RecordStoreException {
        openWindow(fxmlFileName, title, controller, actionEvent);
        closeWindow(actionEvent);
    }

    /**
     * Opens a new window
     * @param fxmlFileName
     * @param title
     * @param controller
     * @param actionEvent
     * @return
     * @throws RecordStoreException
     */
    public Stage openWindow(String fxmlFileName, String title, Object controller, ActionEvent actionEvent) throws RecordStoreException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/" + fxmlFileName + ".fxml"));
        loader.setController(controller);
        Stage newStage = new Stage();
        try {
            newStage.setScene(new Scene(loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            newStage.getIcons().add(new Image("/images/recordstore-icon.png"));
            newStage.setTitle("RecordStore | " + title);
            newStage.setResizable(false);
            newStage.show();
            return newStage;
        } catch (IOException e) {
            throw new RecordStoreException(e.getMessage(), e);
        }
    }
}
