package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.exceptions.RecordStoreException;

import java.util.List;

/**
 * Root interface for all DAO classes
 * @author Emina Peljto
 */
public interface Dao<T> {
    T getById(int id) throws RecordStoreException; // get entity from DB by ID
    T add(T item) throws RecordStoreException; // saves entity into DB
    T update(T item) throws RecordStoreException; // updates entity in DB
    void delete(int id) throws RecordStoreException; // deletes item from DB based on ID
    List<T> getAll() throws RecordStoreException;
}
