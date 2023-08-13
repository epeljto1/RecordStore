package ba.unsa.etf.rpr.dao;

import java.sql.Connection;

/**
 * Abstract class for DAO CRUD methods
 */

public abstract class AbstractDao<T> implements Dao<T> {
    private Connection conn;
    private String table;
}
