package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Record;

import java.util.Date;
import java.util.List;

/**
 * DAO interface for Record model
 */

public interface RecordDao extends Dao<Record> {
    // Returns list of all records with the same name
    // as search parameter
    List<Record> searchByName(String name);
    List<Record> searchByArtist(String name);
    List<Record> searchByDateRange(Date start, Date end);
}
