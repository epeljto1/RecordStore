package ba.unsa.etf.rpr.dao;

/**
 * Root interface for all DAO classes
 * @author epeljto1
 */
public interface Dao<T> {
    T getById(int id);
}
