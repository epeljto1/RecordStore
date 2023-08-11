package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Label;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class LabelDaoSQLImpl implements LabelDao {
    private Connection conn;

    public LabelDaoSQLImpl() {
        Properties prop = new Properties();
        try(InputStream input = LabelDaoSQLImpl.class.getResourceAsStream("/application.properties"))
        {
            prop.load(input);
            conn = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("username"), prop.getProperty("password"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
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
    public Label getById(int id) {
        try
        {
            PreparedStatement stmt = this.conn.prepareStatement("SELECT * FROM labels WHERE id = ?");
            stmt.setInt(1 ,id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                Label label = new Label();
                label.setId(rs.getInt("id"));
                label.setName(rs.getString("name"));
                label.setCountry(rs.getString("country"));
                rs.close();
                return label;
            }
            else
            {
                return null;
            }
        }
        catch(SQLException e)
        {
            System.out.println("Problem pri radu sa bazom podataka");
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Label add(Label item)
    {
        int id = getMaxId();
        String name = item.getName();
        String country = item.getCountry();
        try {
            PreparedStatement stmt = this.conn.prepareStatement("INSERT INTO labels (id, name, country) VALUES (?, ?, ?)");
            stmt.setInt(1,id);
            stmt.setString(2,name);
            stmt.setString(3,country);
            stmt.executeUpdate();
            item.setId(id);
            return item;
        } catch (SQLException e) {
            System.out.println("Problem pri radu sa bazom podataka");
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Label update(Label item)
    {
        try{
            PreparedStatement stmt = this.conn.prepareStatement("UPDATE labels SET name=?, country=? WHERE id=?");
            stmt.setString(1, item.getName());
            stmt.setString(2, item.getCountry());
            stmt.setInt(3, item.getId());
            stmt.executeUpdate();
            return item;
        }catch (SQLException e){
            System.out.println("Problem pri radu sa bazom podataka");
            System.out.println(e.getMessage());
        }
        return null;
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
            e.printStackTrace();
        }
        return null;
    }
}
