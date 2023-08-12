package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Artist;
import ba.unsa.etf.rpr.domain.Label;
import ba.unsa.etf.rpr.domain.Record;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Date;

public class RecordDaoSQLImpl implements RecordDao {
    private Connection conn;

    public RecordDaoSQLImpl() {
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
            PreparedStatement stmt = this.conn.prepareStatement("SELECT MAX(id)+1 FROM records");
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
    public Record getById(int id) {
        try {
        PreparedStatement stmt = this.conn.prepareStatement("SELECT * FROM records WHERE id = ?");
        stmt.setInt(1 ,id);
        ResultSet rs = stmt.executeQuery();
        if(rs.next()) {
            Record record = new Record();
            record.setId(rs.getInt("id"));
            record.setName(rs.getString("name"));
            ArtistDao artistDao = new ArtistDaoSQLImpl();
            record.setArtist(artistDao.getById(rs.getInt("artist_id")));
            record.setRelease_date(rs.getDate("release_date"));
            record.setGenre(rs.getString("genre"));
            record.setCountry("country");
            rs.close();
            return record;
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
    public Record add(Record item)
    {
        int id = getMaxId();
        String name = item.getName();
        int artist_id = item.getArtist().getId();
        String country = item.getCountry();
        String genre = item.getGenre();
        java.sql.Date date = item.getRelease_date();
        try {
            PreparedStatement stmt = this.conn.prepareStatement("INSERT INTO records (id, name, artist_id, release_date, genre, country) VALUES (?, ?, ?, ?, ?, ?)");
            stmt.setInt(1, id);
            stmt.setString(2, name);
            stmt.setInt(3, artist_id);
            stmt.setDate(4, date);
            stmt.setString(5, genre);
            stmt.setString(6, country);
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
    public Record update(Record item)
    {
        try{
        PreparedStatement stmt = this.conn.prepareStatement("UPDATE records SET name=?, artist_id=?, release_date=?, genre=?, country=? WHERE id=?");
        stmt.setString(1, item.getName());
        stmt.setInt(2, item.getArtist().getId());
        stmt.setDate(3, item.getRelease_date());
        stmt.setString(4, item.getGenre());
        stmt.setString(5, item.getCountry());
        stmt.setInt(6, item.getId());
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

    }

    @Override
    public List<Record> getAll()
    {
        List<Record> records = new ArrayList<>();
        try{
            PreparedStatement stmt = this.conn.prepareStatement("SELECT * FROM records");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Record record = new Record();
                record.setId(rs.getInt("id"));
                record.setName(rs.getString("name"));
                ArtistDao artistDao = new ArtistDaoSQLImpl();
                record.setArtist(artistDao.getById(rs.getInt("artist_id")));
                record.setRelease_date(rs.getDate("release_date"));
                record.setGenre(rs.getString("genre"));
                record.setCountry(rs.getString("country"));
                records.add(record);
            }
            rs.close();
        }catch (SQLException e){
            System.out.println("Problem pri radu sa bazom podataka");
            System.out.println(e.getMessage());
        }
        return records;
    }

    @Override
    public List<Record> searchByName(String name)
    {
        String query = "SELECT * FROM records WHERE name = ?";
        List<Record> records = new ArrayList<>();
        try
        {
            PreparedStatement stmt = this.conn.prepareStatement(query);
            stmt.setString(1,name);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                Record record = new Record();
                record.setId(rs.getInt("id"));
                record.setName(rs.getString("name"));
                ArtistDao artistDao = new ArtistDaoSQLImpl();
                record.setArtist(artistDao.getById(rs.getInt("artist_id")));
                record.setRelease_date(rs.getDate("release_date"));
                record.setGenre(rs.getString("genre"));
                record.setCountry(rs.getString("country"));
                records.add(record);
            }
            rs.close();
            return records;
        }
        catch(SQLException e)
        {
            System.out.println("Problem pri radu sa bazom podataka");
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Record> searchByArtist(String name)
    {
        String query = "SELECT * FROM records WHERE artist_id = ?";
        List<Record> records = new ArrayList<>();
        try
        {
            ArtistDao artistDao = new ArtistDaoSQLImpl();
            Artist artist = artistDao.searchByName(name).get(0);
            PreparedStatement stmt = this.conn.prepareStatement(query);
            stmt.setInt(1,artist.getId());
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                Record record = new Record();
                record.setId(rs.getInt("id"));
                record.setName(rs.getString("name"));
                record.setArtist(artist);
                record.setRelease_date(rs.getDate("release_date"));
                record.setGenre(rs.getString("genre"));
                record.setCountry(rs.getString("country"));
                records.add(record);
            }
            rs.close();
            return records;
        }
        catch(SQLException e)
        {
            System.out.println("Problem pri radu sa bazom podataka");
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Record> searchByDateRange(Date start, Date end)
    {
        List<Record> records = new ArrayList<>();
        java.sql.Date startDate = new java.sql.Date(start.getTime());
        java.sql.Date endDate = new java.sql.Date(end.getTime());
        try{
            PreparedStatement stmt = this.conn.prepareStatement("SELECT * FROM records WHERE release_date BETWEEN ? AND ?");
            stmt.setDate(1,startDate);
            stmt.setDate(2,endDate);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Record record = new Record();
                record.setId(rs.getInt("id"));
                record.setName(rs.getString("name"));
                ArtistDao artistDao = new ArtistDaoSQLImpl();
                record.setArtist(artistDao.getById(rs.getInt("artist_id")));
                record.setRelease_date(rs.getDate("release_date"));
                record.setGenre(rs.getString("genre"));
                record.setCountry(rs.getString("country"));
                records.add(record);
            }
            rs.close();
        }catch (SQLException e){
            System.out.println("Problem pri radu sa bazom podataka");
            System.out.println(e.getMessage());
        }
        return records;
    }

}
