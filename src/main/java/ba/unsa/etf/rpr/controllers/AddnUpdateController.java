package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.RecordManager;
import ba.unsa.etf.rpr.business.TabManager;
import ba.unsa.etf.rpr.domain.Artist;
import ba.unsa.etf.rpr.domain.Record;
import ba.unsa.etf.rpr.exceptions.RecordStoreException;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.util.List;

public class AddnUpdateController {
    public Label pageLabel;
    public TextField titleField;
    public ComboBox<Artist> artistCBox;
    public DatePicker rdPicker;
    public TextField genreField;
    public TextField countryField;

    private final TabManager tabManager = new TabManager();
    private final RecordManager recordManager = new RecordManager();

    private final String operation;
    private final List<Artist> artists;
    private Record record;

    public AddnUpdateController(String operation, List<Artist> artists, Record record) throws RecordStoreException
    {
        this.operation = operation;
        this.artists = artists;
        this.record = record;
    }

    @FXML
    public void initialize() {
        pageLabel.setText(operation + " a record");
        showRecord(record);
        record = null;
    }

    private void showRecord(Record record)
    {
        artistCBox.setItems(FXCollections.observableList(artists));

        if(record == null)
        {
            artistCBox.getSelectionModel().selectFirst();
            rdPicker.setValue(LocalDate.now());
        }
        else
        {
            titleField.setText(record.getName());
            artistCBox.getSelectionModel().select(record.getArtist());
            rdPicker.setValue(record.getRelease_date().toLocalDate());
            genreField.setText(record.getGenre());
            countryField.setText(record.getCountry());
        }

    }
}
