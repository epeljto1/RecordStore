package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.ArtistManager;
import ba.unsa.etf.rpr.business.TabManager;
import ba.unsa.etf.rpr.domain.Artist;
import ba.unsa.etf.rpr.exceptions.RecordStoreException;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.util.List;

/**
 * Controller for artists page
 * @author Emina Peljto
 */

public class ArtistController {
    public ListView<Artist> artistsListView;
    private final ArtistManager artistManager = new ArtistManager();
    private final TabManager tabManager = new TabManager();

    private List<Artist> artists;

    /**
     * Constructor
     */
    public ArtistController()
    {
        try
        {
            artists = FXCollections.observableList(artistManager.getAll());
        }
        catch(RecordStoreException e)
        {
            System.out.println("Something went wrong.");
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void initialize()
    {
        artistsListView.setItems(FXCollections.observableList(artists));
    }

    /**
     * Event handler for close
     * @param actionEvent
     */
    public void closeAction(ActionEvent actionEvent) {tabManager.closeWindow(actionEvent);}
}
