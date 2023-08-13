package ba.unsa.etf.rpr.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.Properties;

/**
 * Abstract class for DAO CRUD methods
 */

public abstract class AbstractDao<T> implements Dao<T> {
    private Connection conn;
    private String table;

    public AbstractDao(String tableName) {
        try {
            this.table = table;
            Properties p = new Properties();
            InputStream input = LabelDaoSQLImpl.class.getResourceAsStream("/application.properties");
            p.load(input);
            this.conn = DriverManager.getConnection(p.getProperty("url"), p.getProperty("username"), p.getProperty("password"));
        } catch (Exception e) {
            System.out.println("Greska u radu sa bazom podataka");
            System.out.println(e.getMessage());
        }
    }

    @Override
    public T getById(int id) {
        return null;
    }

    @Override
    public T add(T item) {
        return null;
    }

    @Override
    public T update(T item) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<T> getAll() {
        return null;
    }

}
