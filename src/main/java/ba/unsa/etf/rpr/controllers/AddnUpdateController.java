package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.ArtistManager;
import ba.unsa.etf.rpr.business.RecordManager;
import ba.unsa.etf.rpr.business.TabManager;
import ba.unsa.etf.rpr.domain.Artist;
import ba.unsa.etf.rpr.domain.Record;
import ba.unsa.etf.rpr.exceptions.RecordStoreException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

/**
 * Controller for add/update a music record
 * @author Emina Peljto
 */

public class AddnUpdateController {
    public Label pageLabel;
    public TextField titleField;
    public TextField artistField;
    public DatePicker rdPicker;
    public TextField genreField;
    public TextField countryField;
    public Label errorMsgLabel;

    private final TabManager tabManager = new TabManager();
    private final RecordManager recordManager = new RecordManager();
    private final ArtistManager artistManager = new ArtistManager();

    private final String operation;
    private final List<Artist> artists;
    private Record record;
    private int recordId;

    /**
     * Constructor
     * @param operation
     * @param artists
     * @param record
     * @throws RecordStoreException
     */
    public AddnUpdateController(String operation, List<Artist> artists, Record record) throws RecordStoreException
    {
        this.operation = operation;
        this.artists = artists;
        this.record = record;
        if(record!=null) recordId=record.getId();
    }

    @FXML
    public void initialize() {
        pageLabel.setText(operation.toUpperCase() + " A RECORD");
        showRecord(record);
        record = null;
    }

    /**
     * Event handler for submit
     * @param actionEvent
     * @throws RecordStoreException
     */
    public void submitAction(ActionEvent actionEvent)  throws RecordStoreException{
        setRecord(titleField.getText(), artistField.getText(),
                rdPicker.getValue(), genreField.getText(),countryField.getText());

        try {
            if (operation.equalsIgnoreCase("add"))
                recordManager.add(record);
            else if (operation.equalsIgnoreCase("update")) {
                record.setId(recordId);
                recordManager.update(record);
            }

            tabManager.closeWindow(actionEvent);
        } catch(RecordStoreException e) {
            errorMsgLabel.setVisible(true);
            errorMsgLabel.setText("Something went wrong. Try again.");
        }
    }

    /**
     * Event handler for cancel
     * @param actionEvent
     */
    public void cancelAction(ActionEvent actionEvent)
    {
        record = null;
        tabManager.closeWindow(actionEvent);
    }

    /**
     * Setter for private record attribute
     * @param title
     * @param artist
     * @param rd
     * @param genre
     * @param country
     * @throws RecordStoreException
     */
    private void setRecord(String title, String artist, LocalDate rd, String genre, String country) throws RecordStoreException {
        if(record == null) record = new Record.Builder(0,title).build();
        try{
            record.setArtist(artistManager.searchByName(artist).get(0)); }
        catch (IndexOutOfBoundsException e)
        {
            System.out.println("Can't find this artist in the database");
        }
            record.setRelease_date(Date.valueOf(rd));
            record.setGenre(genre);
            record.setCountry(country);

    }

    /**
     * Getter for private record attribute
     * @return record
     */
    public Record getRecord() {
        return record;
    }

    /**
     * Displays selected record details or sets default values
     * @param record
     */
    private void showRecord(Record record)
    {

        if(record == null)
        {
            rdPicker.setValue(LocalDate.now());
        }
        else
        {
            titleField.setText(record.getName());
            artistField.setText(record.getArtist().getName());
            rdPicker.setValue(record.getRelease_date().toLocalDate());
            genreField.setText(record.getGenre());
            countryField.setText(record.getCountry());
        }

    }
}
