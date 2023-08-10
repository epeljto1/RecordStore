package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Artist;
import ba.unsa.etf.rpr.domain.Label;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class LabelDaoSQLImpl implements LabelDao {
    private Connection conn;

    public LabelDaoSQLImpl() {
        Properties prop = new Properties();
        try
        {
            this.conn = DriverManager.getConnection("jdbc:mysql://" +
                            prop.getProperty("db.host") + "/" +
                            prop.getProperty("db.scheme") + "/" +
                            prop.getProperty("db.port"),
                    prop.getProperty("db.username"), prop.getProperty("db.password"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Label getById(int id) {
        return null;
    }

    @Override
    public Label add(Label item)
    {
        return null;
    }

    @Override
    public Label update(Label item)
    {
        return null;
    }

    @Override
    public void delete(int id)
    {

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
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return labels;
    }
}
