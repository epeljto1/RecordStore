package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Artist;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ArtistDaoSQLImpl implements ArtistDao {
    private Connection conn;

    public ArtistDaoSQLImpl() {
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
    public Artist getById(int id) {
        return null;
    }

    @Override
    public Artist add(Artist item)
    {
        return null;
    }

    @Override
    public Artist update(Artist item)
    {
        return null;
    }

    @Override
    public void delete(int id)
    {

    }

    @Override
    public List<Artist> getAll()
    {
        return null;
    }

    @Override
    public List<Artist> searchByName(String name)
    {
        String query = "SELECT * FROM artists WHERE name = ?";
        List<Artist> artists = new ArrayList<>();
        try
        {
            PreparedStatement stmt = this.conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                Artist artist = new Artist();
                artist.setId(rs.getInt("id"));
                artist.setName(rs.getString("name"));
                artist.setCountry(rs.getString("country"));
                artist.setCountry(rs.getString("type"));
                artists.add(artist);
            }
            rs.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return artists;
    }
}
