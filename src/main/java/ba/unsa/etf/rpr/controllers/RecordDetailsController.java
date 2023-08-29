package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.TabManager;
import ba.unsa.etf.rpr.domain.Record;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Controller for record details
 * @author Emina Peljto
 */

public class RecordDetailsController {
    public Label titleLabel;
    public Label artistLabel;
    public Label labelLabel;
    public Label typeLabel;
    public Label dateLabel;
    public Label genreLabel;
    public Label countryLabel;

    private Record record;

    private final TabManager tabManager = new TabManager();

    /**
     * Constructor
     * @param record
     */
    public RecordDetailsController(Record record){this.record = record;}

    @FXML
    public void initialize() {
        getDetails();
    }

    /**
     * Event handler for close action
     * @param actionEvent
     */
    public void closeAction(ActionEvent actionEvent) {tabManager.closeWindow(actionEvent);}

    /**
     * Getter for record details
     */
    private void getDetails()
    {
        titleLabel.setText("TITLE "+record.getName());
        artistLabel.setText("ARTIST "+record.getArtist().getName());
        labelLabel.setText("LABEL "+record.getArtist().getLabel().getName());
        typeLabel.setText("TYPE "+record.getArtist().getType());
        dateLabel.setText("RELEASE DATE "+record.getRelease_date());
        genreLabel.setText("GENRE "+record.getGenre());
        countryLabel.setText("COUNTRY "+record.getCountry());
    }
}
