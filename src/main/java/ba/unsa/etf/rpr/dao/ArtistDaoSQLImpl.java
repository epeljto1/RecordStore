package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Artist;
import ba.unsa.etf.rpr.domain.Label;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ArtistDaoSQLImpl implements ArtistDao {
    private Connection conn;

    public ArtistDaoSQLImpl() {
        Properties prop = new Properties();
        try(InputStream input = LabelDaoSQLImpl.class.getResourceAsStream("/application.properties"))
        {
            prop.load(input);
            conn = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("username"), prop.getProperty("password"));
        }
        catch (Exception e) {
            System.out.println("Greska u radu sa bazom podataka");
            System.out.println(e.getMessage());
        }
    }

    private int getMaxId(){
        int id=1;
        try {
            PreparedStatement stmt = this.conn.prepareStatement("SELECT MAX(id)+1 FROM artists");
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
    public Artist getById(int id) {
        try
        {
            PreparedStatement stmt = this.conn.prepareStatement("SELECT * FROM artists WHERE id = ?");
            stmt.setInt(1 ,id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                Artist artist = new Artist();
                artist.setId(rs.getInt("id"));
                artist.setName(rs.getString("name"));
                LabelDao labelDao = new LabelDaoSQLImpl();
                artist.setLabel(labelDao.getById(rs.getInt("label_id")));
                artist.setCountry(rs.getString("country"));
                artist.setType(rs.getString("type"));
                rs.close();
                return artist;
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
    public Artist add(Artist item) {
        int id = getMaxId();
        String name = item.getName();
        int label_id = item.getLabel().getId();
        String country = item.getCountry();
        String type = item.getType();
        try {
            PreparedStatement stmt = this.conn.prepareStatement("INSERT INTO artists (id, name, label_id, country, type) VALUES (?, ?, ?, ?, ?)");
            stmt.setInt(1, id);
            stmt.setString(2, name);
            stmt.setInt(3, label_id);
            stmt.setString(4, country);
            stmt.setString(5, type);
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
    public Artist update(Artist item)
    {
        try{
            PreparedStatement stmt = this.conn.prepareStatement("UPDATE artists SET name=?, label_id=?, country=?, type=? WHERE id=?");
            stmt.setString(1, item.getName());
            stmt.setInt(2, item.getLabel().getId());
            stmt.setString(3, item.getCountry());
            stmt.setString(4, item.getType());
            stmt.setInt(5, item.getId());
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
            PreparedStatement stmt = this.conn.prepareStatement("DELETE FROM artists WHERE id = ?");
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }catch (SQLException e){
            System.out.println("Problem pri radu sa bazom podataka");
            System.out.println(e.getMessage());
        }

    }

    @Override
    public List<Artist> getAll()
    {
        List<Artist> artists = new ArrayList<>();
        try{
            PreparedStatement stmt = this.conn.prepareStatement("SELECT * FROM artists");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Artist artist = new Artist();
                artist.setId(rs.getInt("id"));
                artist.setName(rs.getString("name"));
                LabelDao labelDao = new LabelDaoSQLImpl();
                artist.setLabel(labelDao.getById(rs.getInt("label_id")));
                artist.setCountry(rs.getString("country"));
                artist.setType(rs.getString("type"));
                artists.add(artist);
            }
            rs.close();
        }catch (SQLException e){
            System.out.println("Problem pri radu sa bazom podataka");
            System.out.println(e.getMessage());
        }
        return artists;
    }

    @Override
    public List<Artist> searchByName(String name)
    {
        String query = "SELECT * FROM artists WHERE name = ?";
        List<Artist> artists = new ArrayList<>();
        try
        {
            PreparedStatement stmt = this.conn.prepareStatement(query);
            stmt.setString(1,name);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                Artist artist = new Artist();
                artist.setId(rs.getInt("id"));
                artist.setName(rs.getString("name"));
                LabelDao labelDao = new LabelDaoSQLImpl();
                artist.setLabel(labelDao.getById(rs.getInt("label_id")));
                artist.setCountry(rs.getString("country"));
                artist.setType(rs.getString("type"));
                artists.add(artist);
            }
            rs.close();
            return artists;
        }
        catch(SQLException e)
        {
            System.out.println("Problem pri radu sa bazom podataka");
            System.out.println(e.getMessage());
        }
        return null;
    }

}
