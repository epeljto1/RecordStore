package ba.unsa.etf.rpr.dao;

import java.io.InputStream;
import java.sql.*;
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
        String query = "SELECT * FROM ? WHERE id = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, table);
            stmt.setInt(2, id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                // TODO:
                rs.close();
                return item;
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
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
