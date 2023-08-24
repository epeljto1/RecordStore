package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.RecordManager;
import ba.unsa.etf.rpr.business.TabManager;
import ba.unsa.etf.rpr.domain.Label;
import ba.unsa.etf.rpr.domain.Record;
import ba.unsa.etf.rpr.exceptions.RecordStoreException;
import javafx.collections.FXCollections;
import javafx.scene.control.ListView;

import java.awt.*;
import java.util.List;

public class HomeController {
    public Label usernameLabel;
    public TextField searchField;
    public TextField artistField;
    public TextField dateField;
    public ListView<Record> recordsListView;
    public Label infoLabel;

    private final TabManager tabManager = new TabManager();
    private final RecordManager recordManager = new RecordManager();
    private final List<Record> records;
    private final String username;
    private final List<Record> filteredRecords;

    public HomeController(String username)
    {
        try
        {
            records = FXCollections.observableList(recordManager.getAll());
        }
        catch(RecordStoreException e)
        {
            System.out.println("Something went wrong.");
            throw new RuntimeException(e);
        }

        this.username = username;
        filteredRecords = records;
    }
}
