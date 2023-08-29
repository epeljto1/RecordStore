package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Record;
import ba.unsa.etf.rpr.exceptions.RecordStoreException;

import java.util.Date;
import java.util.List;

/**
 * Business logic layer for records
 * @author Emina Peljto
 */

public class RecordManager {

    /**
     * Returns record from DB based on ID
     * @param id
     * @return record
     * @throws RecordStoreException
     */
    public Record getById(int id) throws RecordStoreException
    {
        return DaoFactory.recordDao().getById(id);
    }

    /**
     * Adds a record to DB
     * @param record
     * @return record
     * @throws RecordStoreException
     */
    public Record add(Record record) throws RecordStoreException
    {
        return DaoFactory.recordDao().add(record);
    }

    /**
     * Updates a record in DB
     * @param record
     * @return record
     * @throws RecordStoreException
     */
    public Record update(Record record) throws RecordStoreException
    {
        return DaoFactory.recordDao().update(record);
    }

    /**
     * Removes record from DB based on ID
     * @param id
     * @throws RecordStoreException
     */
    public void delete(int id) throws RecordStoreException
    {
        DaoFactory.recordDao().delete(id);
    }

    /**
     * Returns all records from DB
     * @return list of records
     * @throws RecordStoreException
     */
    public List<Record> getAll() throws RecordStoreException
    {
        return DaoFactory.recordDao().getAll();
    }

    /**
     * Returns records from DB based on name
     * @param name
     * @return list of records
     * @throws RecordStoreException
     */
    public List<Record> searchByName(String name) throws RecordStoreException
    {
        return DaoFactory.recordDao().searchByName(name);
    }

    /**
     * Returns records from DB based on artist
     * @param name
     * @return list of records
     * @throws RecordStoreException
     */
    public List<Record> searchByArtist(String name) throws RecordStoreException
    {
        return DaoFactory.recordDao().searchByArtist(name);
    }

    /**
     * Returns records from DB based on date range
     * @param start
     * @param end
     * @return list of records
     * @throws RecordStoreException
     */
    public List<Record> searchByDateRange(Date start, Date end) throws RecordStoreException
    {
        return DaoFactory.recordDao().searchByDateRange(start,end);
    }
}
