package ba.unsa.etf.rpr.business;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

public class TabManager {
    public void closeWindow(ActionEvent actionEvent) {
        Node n = (Node) actionEvent.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        stage.close();
    }
}
