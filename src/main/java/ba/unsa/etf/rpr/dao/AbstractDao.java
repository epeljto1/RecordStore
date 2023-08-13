package ba.unsa.etf.rpr.dao;

import java.sql.Connection;
import java.sql.DriverManager;
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
            p.load(ClassLoader.getSystemResource("application.properties").openStream());
            this.conn = DriverManager.getConnection(p.getProperty("url"), p.getProperty("username"), p.getProperty("password"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
