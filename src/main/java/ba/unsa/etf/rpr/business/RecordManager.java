package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Record;
import ba.unsa.etf.rpr.exceptions.RecordStoreException;

import java.util.List;

public class RecordManager {
    public Record getById(int id) throws RecordStoreException
    {
        return DaoFactory.recordDao().getById(id);
    }

    public Record add(Record record) throws RecordStoreException
    {
        return DaoFactory.recordDao().add(record);
    }

    public Record update(Record record) throws RecordStoreException
    {
        return DaoFactory.recordDao().update(record);
    }

    public void delete(int id) throws RecordStoreException
    {
        DaoFactory.recordDao().delete(id);
    }

    public List<Record> getAll() throws RecordStoreException
    {
        return DaoFactory.recordDao().getAll();
    }

    public List<Record> searchByName(String name) throws RecordStoreException
    {
        return DaoFactory.recordDao().searchByName(name);
    }
}
