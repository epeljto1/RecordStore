package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.dao.RecordDaoSQLImpl;
import ba.unsa.etf.rpr.exceptions.RecordStoreException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ba.unsa.etf.rpr.domain.Record;

public class HomeController {
    private RecordDaoSQLImpl recordDaoSQL;
    private ObservableList<Record> records;

    public HomeController() {
        try {
            recordDaoSQL = new RecordDaoSQLImpl();
            records = FXCollections.observableArrayList(recordDaoSQL.getAll());
        }
        catch(RecordStoreException e)
        {
            System.out.println("Something is not right with table of records");
            throw new RuntimeException(e);
        }

    }
}
