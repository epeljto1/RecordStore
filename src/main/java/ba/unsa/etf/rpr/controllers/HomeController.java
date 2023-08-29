package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.ArtistManager;
import ba.unsa.etf.rpr.business.RecordManager;
import ba.unsa.etf.rpr.business.TabManager;
import ba.unsa.etf.rpr.domain.Artist;
import ba.unsa.etf.rpr.domain.Record;
import ba.unsa.etf.rpr.exceptions.RecordStoreException;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller for home page
 * @author Emina Peljto
 */

public class HomeController {
    public Label usernameLabel;
    public TextField searchField;
    public TextField artistField;
    public ListView<Record> recordsListView;
    public Label infoLabel;

    private final TabManager tabManager = new TabManager();
    private final RecordManager recordManager = new RecordManager();
    private final List<Record> records;
    private final String username;
    private List<Record> filteredRecords;
    private final List<Artist> artists;

    /**
     * Constructor
     * @param username
     */
    public HomeController(String username)
    {
        try
        {
            records = FXCollections.observableList(recordManager.getAll());
            artists = FXCollections.observableList(new ArtistManager().getAll());
        }
        catch(RecordStoreException e)
        {
            System.out.println("Something went wrong.");
            throw new RuntimeException(e);
        }

        this.username = username;
        filteredRecords = records;
    }

    @FXML
    public void initialize() {
        usernameLabel.setText("WELCOME, " + username);
        recordsListView.setItems(FXCollections.observableList(filteredRecords));

        searchField.textProperty().addListener((observable, o, n) -> refreshRecords());
        artistField.textProperty().addListener((observable, o, n) -> refreshRecords());
    }

    /**
     * Event handler for close
     * @param actionEvent
     */
    public void closeAction(ActionEvent actionEvent) {tabManager.closeWindow(actionEvent);}

    /**
     * Event handler for log out
     * @param actionEvent
     * @throws RecordStoreException
     */
    public void logoutAction(ActionEvent actionEvent) throws RecordStoreException
    {
        tabManager.changeWindow("Login","Log in",new LoginController(),actionEvent);
    }

    /**
     * Event handler for details
     * @param actionEvent
     * @throws RecordStoreException
     */
    public void detailsAction(ActionEvent actionEvent) throws RecordStoreException {
        Record selected = recordsListView.getSelectionModel().getSelectedItem();

        if (selected != null)
            tabManager.openWindow("RecordDetails", "Record details", new RecordDetailsController(selected), actionEvent);
        else
            infoLabel.setText("You need to select a record that you want to view.");
    }

    /**
     * Event handler for add
     * @param actionEvent
     * @throws RecordStoreException
     */
    public void addAction(ActionEvent actionEvent) throws RecordStoreException {
        AddnUpdateController aouController = new AddnUpdateController("add", artists,null);
        Stage newStage = tabManager.openWindow("AddnUpdate", "Add", aouController, actionEvent);

        newStage.setOnHiding(event -> {
            Record newRecord = aouController.getRecord();
            if(newRecord == null) return;
            records.add(newRecord);
            infoLabel.setText("Added a new record successfully.");
            refreshRecords();
        });
    }

    /**
     * Event handler for update
     * @param actionEvent
     * @throws RecordStoreException
     */
    public void updateAction(ActionEvent actionEvent) throws RecordStoreException
    {
        Record selected = recordsListView.getSelectionModel().getSelectedItem();
        if(selected == null) {
            infoLabel.setText("You need to select a record that you want to update.");
            return;
        }

        AddnUpdateController aouController = new AddnUpdateController("update", artists, selected);
        Stage newStage = tabManager.openWindow("AddnUpdate", "Update", aouController, actionEvent);

        newStage.setOnHiding(event -> {
            Record updated = aouController.getRecord();
            if(updated == null) return;
            updated.setId(selected.getId());
            records.set(records.indexOf(selected), updated);
            refreshRecords();
            infoLabel.setText("Updated a new record successfully.");
        });
    }

    /**
     * Event handler for delete
     * @param actionEvent
     */
    public void deleteAction(ActionEvent actionEvent)
    {
        Record selected = recordsListView.getSelectionModel().getSelectedItem();
        if(selected==null)
        {
            infoLabel.setText("You need to select a record that you want to delete.");
            return;
        }
        try {
            recordManager.delete(selected.getId());
            records.remove(selected);
            refreshRecords();
            infoLabel.setText("Record deleted successfully");
        }
        catch(RecordStoreException e)
        {
            infoLabel.setText(e.getMessage());
        }
    }

    /**
     * Event handler for artists
     * @param actionEvent
     * @throws RecordStoreException
     */
    public void artistsAction(ActionEvent actionEvent) throws RecordStoreException
    {
        tabManager.openWindow("Artists","Artists",new ArtistController(),actionEvent);
    }

    /**
     * Updates ListView
     */
    private void refreshRecords() {
        searchRecords();
        recordsListView.setItems(FXCollections.observableList(filteredRecords));
    }

    /**
     * Filters records by title or artist
     */
    private void searchRecords() {
        String searchTitle = searchField.getText();
        String searchArtist = artistField.getText();

        filteredRecords = records.stream().filter(record -> {
            return record.getName().toLowerCase().contains(searchTitle.toLowerCase()) &&
                    record.getArtist().getName().toLowerCase().contains(searchArtist.toLowerCase());
        }).collect(Collectors.toList());
    }
}