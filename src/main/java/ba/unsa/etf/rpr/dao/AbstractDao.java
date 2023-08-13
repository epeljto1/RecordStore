package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.exceptions.RecordStoreException;

import java.io.InputStream;
import java.sql.*;
import java.util.AbstractMap;
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

    public Connection getConnection(){
        return this.conn;
    }

    public abstract T row2Object(ResultSet rs) throws RecordStoreException;
    public abstract Map<String, Object> object2Row(T object);

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
        Map<String,Object>row = object2Row(item);
        Map.Entry<String, String> columns = prepareInsertParts(row);

        StringBuilder builder = new StringBuilder();
        builder.append("INSERT INTO ").append(table);
        builder.append(" (").append(columns.getKey()).append(") ");
        builder.append("VALUES (").append(columns.getValue()).append(")");
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

    private Map.Entry<String, String> prepareInsertParts(Map<String, Object> row){
        StringBuilder columns = new StringBuilder();
        StringBuilder questions = new StringBuilder();

        int counter = 0;
        for (Map.Entry<String, Object> entry: row.entrySet()) {
            counter++;
            if (entry.getKey().equals("id")) continue;
            columns.append(entry.getKey());
            questions.append("?");
            if (row.size() != counter) {
                columns.append(",");
                questions.append(",");
            }
        }
        return new AbstractMap.SimpleEntry<String,String>(columns.toString(), questions.toString());
    }

    private String prepareUpdateParts(Map<String, Object> row){
        StringBuilder columns = new StringBuilder();

        int counter = 0;
        for (Map.Entry<String, Object> entry: row.entrySet()) {
            counter++;
            if (entry.getKey().equals("id")) continue;
            columns.append(entry.getKey()).append("= ?");
            if (row.size() != counter) {
                columns.append(",");
            }
        }
        return columns.toString();
    }

}
