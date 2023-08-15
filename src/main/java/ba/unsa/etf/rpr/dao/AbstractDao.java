package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Identifiable;
import ba.unsa.etf.rpr.exceptions.RecordStoreException;

import java.io.InputStream;
import java.sql.*;
import java.util.*;

/**
 * Abstract class for DAO CRUD methods
 */

public abstract class AbstractDao<T extends Identifiable> implements Dao<T> {
    private Connection conn;
    private String table;

    public AbstractDao(String table) {
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
    public String getTable()
    {
        return this.table;
    }

    public abstract T row2Object(ResultSet rs) throws RecordStoreException;
    public abstract Map<String, Object> object2Row(T object);

    @Override
    public T getById(int id) throws RecordStoreException {
        String query = "SELECT * FROM "+getTable()+" WHERE id = ?";
        try {
            PreparedStatement stmt = getConnection().prepareStatement(query);
            stmt.setInt(1, id);
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
        builder.append("INSERT INTO ").append(getTable());
        builder.append(" (").append(columns.getKey()).append(") ");
        builder.append("VALUES (").append(columns.getValue()).append(")");

        try{
            PreparedStatement stmt = getConnection().prepareStatement(builder.toString(), Statement.RETURN_GENERATED_KEYS);
            int counter = 1;
            for (Map.Entry<String, Object> entry: row.entrySet()) {
                if (entry.getKey().equals("id")) continue;
                stmt.setObject(counter, entry.getValue());
                counter++;
            }
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            item.setId(rs.getInt(1));
            return item;
        }catch (SQLException e){
            throw new RecordStoreException(e.getMessage(), e);
        }
    }

    @Override
    public T update(T item) throws RecordStoreException {
        Map<String, Object> row = object2Row(item);
        String updateColumns = prepareUpdateParts(row);
        StringBuilder builder = new StringBuilder();
        builder.append("UPDATE ")
                .append(getTable())
                .append(" SET ")
                .append(updateColumns)
                .append(" WHERE id = ?");

        try{
            PreparedStatement stmt = getConnection().prepareStatement(builder.toString());
            int counter = 1;
            for (Map.Entry<String, Object> entry: row.entrySet()) {
                if (entry.getKey().equals("id")) continue;
                stmt.setObject(counter, entry.getValue());
                counter++;
            }
            stmt.setObject(counter, item.getId());
            stmt.executeUpdate();
            return item;
        }catch (SQLException e){
            throw new RecordStoreException(e.getMessage(), e);
        }
    }

    @Override
    public void delete(int id) throws RecordStoreException {
        String sql = "DELETE FROM "+getTable()+" WHERE id = ?";
        try{
            PreparedStatement stmt = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1, id);
            stmt.executeUpdate();
        }catch (SQLException e){
            throw new RecordStoreException(e.getMessage(), e);
        }
    }

    @Override
    public List<T> getAll() throws RecordStoreException {
        String query = "SELECT * FROM "+ table;
        List<T> results = new ArrayList<T>();
        try{
            PreparedStatement stmt = getConnection().prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                T object = row2Object(rs);
                results.add(object);
            }
            rs.close();
            return results;
        }catch (SQLException e){
            throw new RecordStoreException(e.getMessage(), e);
        }
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
