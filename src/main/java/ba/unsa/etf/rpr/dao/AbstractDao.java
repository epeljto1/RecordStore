package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.exceptions.RecordStoreException;

import java.io.InputStream;
import java.sql.*;
import java.util.List;
import java.util.Map;
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

    public abstract T row2Object(ResultSet rs) throws RecordStoreException;
    public abstract Map<String, T> object2Row(T object);

    @Override
    public T getById(int id) throws RecordStoreException {
        String query = "SELECT * FROM ? WHERE id = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, table);
            stmt.setInt(2, id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                T item = row2Object(rs);
                rs.close();
                return item;
            }
            else
            {
                throw new RecordStoreException("Object not found");
            }
        } catch(SQLException e) {
            throw new RecordStoreException(e.getMessage(),e);
        }
    }

    @Override
    public T add(T item) throws RecordStoreException {
        return null;
    }

    @Override
    public T update(T item) throws RecordStoreException {
        return null;
    }

    @Override
    public void delete(int id) throws RecordStoreException {

    }

    @Override
    public List<T> getAll() throws RecordStoreException {
        return null;
    }

}
