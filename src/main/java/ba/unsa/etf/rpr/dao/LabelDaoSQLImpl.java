package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Label;
import ba.unsa.etf.rpr.exceptions.RecordStoreException;

import java.io.InputStream;
import java.sql.*;
import java.util.*;

public class LabelDaoSQLImpl extends AbstractDao<Label> implements LabelDao {
    private Connection conn;

    public LabelDaoSQLImpl() {
     super("labels");
    }

    private int getMaxId(){
        int id=1;
        try {
            PreparedStatement stmt = this.conn.prepareStatement("SELECT MAX(id)+1 FROM labels");
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                id = rs.getInt(1);
                rs.close();
                return id;
            }
        } catch (SQLException e) {
            System.out.println("Problem pri radu sa bazom podataka");
            System.out.println(e.getMessage());
        }
        return id;
    }

    @Override
    public Label row2Object(ResultSet rs) throws RecordStoreException
    {
        try {
            Label label = new Label();
            label.setId(rs.getInt("id"));
            label.setName(rs.getString("name"));
            label.setCountry(rs.getString("country"));
            return label;
        }
        catch (SQLException e)
        {
            throw new RecordStoreException(e.getMessage(),e);
        }
    }

    @Override
    public Map<String,Object> object2Row(Label object)
    {
        Map<String, Object> row = new TreeMap<String, Object>();
        row.put("id", object.getId());
        row.put("name", object.getName());
        row.put("country", object.getCountry());
        return row;
    }

    @Override
    public void delete(int id)
    {
        try{
            PreparedStatement stmt = this.conn.prepareStatement("DELETE FROM labels WHERE id = ?");
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }catch (SQLException e){
            System.out.println("Problem pri radu sa bazom podataka");
            System.out.println(e.getMessage());
        }

    }

    @Override
    public List<Label> getAll()
    {
        List<Label> labels = new ArrayList<>();
        try{
            PreparedStatement stmt = this.conn.prepareStatement("SELECT * FROM labels");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Label label = new Label();
                label.setId(rs.getInt("id"));
                label.setName(rs.getString("name"));
                label.setCountry(rs.getString("country"));
                labels.add(label);
            }
            rs.close();
        }catch (SQLException e){
            System.out.println("Problem pri radu sa bazom podataka");
            System.out.println(e.getMessage());
        }
        return labels;
    }

    @Override
    public List<Label> searchByCountry(String name)
    {
        String query = "SELECT * FROM labels WHERE country = ?";
        List<Label> labels = new ArrayList<>();
        try
        {
            PreparedStatement stmt = this.conn.prepareStatement(query);
            stmt.setString(1,name);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                Label label = new Label();
                label.setId(rs.getInt("id"));
                label.setName(rs.getString("name"));
                label.setCountry(rs.getString("country"));
                labels.add(label);
            }
            rs.close();
            return labels;
        }
        catch(SQLException e)
        {
            System.out.println("Problem pri radu sa bazom podataka");
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Label> searchByName(String name)
    {
        String query = "SELECT * FROM labels WHERE name = ?";
        List<Label> labels = new ArrayList<>();
        try
        {
            PreparedStatement stmt = this.conn.prepareStatement(query);
            stmt.setString(1,name);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                Label label = new Label();
                label.setId(rs.getInt("id"));
                label.setName(rs.getString("name"));
                label.setCountry(rs.getString("country"));
                labels.add(label);
            }
            rs.close();
            return labels;
        }
        catch(SQLException e)
        {
            System.out.println("Problem pri radu sa bazom podataka");
            System.out.println(e.getMessage());
        }
        return null;
    }

}
