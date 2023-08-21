package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Label;
import ba.unsa.etf.rpr.exceptions.RecordStoreException;

import java.util.List;

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

    /**
     * Removes label from DB based on ID
     * @param id
     * @throws RecordStoreException
     */
    public void delete(int id) throws RecordStoreException
    {
        DaoFactory.labelDao().delete(id);
    }

    /**
     * Returns all labels from DB
     * @return list of labels
     * @throws RecordStoreException
     */
    public List<Label> getAll() throws RecordStoreException
    {
        return DaoFactory.labelDao().getAll();
    }

    /**
     * Returns label from DB based on country
     * @param country
     * @return
     * @throws RecordStoreException
     */
    public List<Label> searchByCountry(String country) throws RecordStoreException
    {
        return DaoFactory.labelDao().searchByCountry(country);
    }
}
