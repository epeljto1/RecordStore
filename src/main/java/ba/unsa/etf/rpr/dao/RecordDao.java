package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Record;

import java.util.List;

/**
 * DAO interface for Record model
 */

public interface RecordDao extends Dao<Record> {
    // Returns list of all records with the same name
    // as search parameter
    List<Record> searchbyName(String name);
}
