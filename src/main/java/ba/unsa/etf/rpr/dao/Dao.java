package ba.unsa.etf.rpr.dao;

/**
 * Root interface for all DAO classes
 * @author epeljto1
 */
public interface Dao<T> {
    T getById(int id); // get entity from DB by ID
    T add(T item); // saves entity into DB
    T update(T item); // updates entity in DB
}
