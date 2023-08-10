package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Label;

import java.util.List;

/**
 * DAO interface for Label model
 */

public interface LabelDao extends Dao<Label> {
    // Returns list of all labels with the same name
    // as search parameter
    List<Label> searchByName(String name);
}
