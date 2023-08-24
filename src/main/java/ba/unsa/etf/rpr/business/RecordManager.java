package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Record;
import ba.unsa.etf.rpr.exceptions.RecordStoreException;

public class RecordManager {
    public Record getById(int id) throws RecordStoreException
    {
        return DaoFactory.recordDao().getById(id);
    }

    public Record add(Record record) throws RecordStoreException
    {
        return DaoFactory.recordDao().add(record);
    }
}
