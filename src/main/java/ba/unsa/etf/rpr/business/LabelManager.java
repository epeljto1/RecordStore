package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Label;
import ba.unsa.etf.rpr.exceptions.RecordStoreException;

public class LabelManager {
    /**
     * Returns label from DB based on ID
     * @param id
     * @return label
     * @throws RecordStoreException
     */
    public Label getById(int id) throws RecordStoreException
    {
        return DaoFactory.labelDao().getById(id);
    }

    /**
     * Adds a label to DB
     * @param label
     * @return label
     * @throws RecordStoreException
     */
    public Label add(Label label) throws RecordStoreException
    {
        return DaoFactory.labelDao().add(label);
    }

    /**
     * Updates a label in DB
     * @param label
     * @return label
     * @throws RecordStoreException
     */
    public Label update(Label label) throws RecordStoreException
    {
        return DaoFactory.labelDao().update(label);
    }
}