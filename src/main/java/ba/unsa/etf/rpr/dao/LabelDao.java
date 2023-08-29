package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Label;
import ba.unsa.etf.rpr.exceptions.RecordStoreException;

import java.util.List;

/**
 * DAO interface for Label model
 * @author Emina Peljto
 */

public interface LabelDao extends Dao<Label> {
    // Returns list of all labels with the same country
    // as search parameter
    List<Label> searchByCountry(String country) throws RecordStoreException;
    List<Label> searchByName(String name) throws RecordStoreException;
}
