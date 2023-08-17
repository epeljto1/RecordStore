package ba.unsa.etf.rpr;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main class for working with JavaFX framework
 */

public class AppFX extends Application {
    @Override
    public void start(Stage stage) throws Exception
    {
        stage.setTitle("RecordStore");
        stage.setResizable(false);
        stage.show();
    }

}
