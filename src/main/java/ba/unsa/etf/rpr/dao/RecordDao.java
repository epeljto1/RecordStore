package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Record;
import ba.unsa.etf.rpr.exceptions.RecordStoreException;

import java.util.Date;
import java.util.List;

/**
 * DAO interface for Record model
 * @author Emina Peljto
 */

public interface RecordDao extends Dao<Record> {
    // Returns list of all records with the same name
    // as search parameter
    List<Record> searchByName(String name) throws RecordStoreException;
    List<Record> searchByArtist(String name) throws RecordStoreException;
    List<Record> searchByDateRange(Date start, Date end) throws RecordStoreException;
}
