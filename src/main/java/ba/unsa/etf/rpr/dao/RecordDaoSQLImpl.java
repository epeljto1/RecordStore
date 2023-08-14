package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Artist;
import ba.unsa.etf.rpr.domain.Label;
import ba.unsa.etf.rpr.domain.Record;
import ba.unsa.etf.rpr.exceptions.RecordStoreException;

import java.io.InputStream;
import java.sql.*;
import java.sql.Date;
import java.util.*;
import java.util.Date;

public class RecordDaoSQLImpl extends AbstractDao<Record> implements RecordDao {
    private Connection conn;

    public RecordDaoSQLImpl() {
      super("records");
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
    public Record row2Object(ResultSet rs) throws RecordStoreException
    {
        try {
            Record record = new Record();
            record.setId(rs.getInt("id"));
            record.setName(rs.getString("name"));
            ArtistDao artistDao = new ArtistDaoSQLImpl();
            record.setArtist(artistDao.getById(rs.getInt("artist_id")));
            record.setRelease_date(rs.getDate("release_date"));
            record.setGenre(rs.getString("genre"));
            record.setCountry(rs.getString("country"));
            return record;
        }
        catch (SQLException e)
        {
            throw new RecordStoreException(e.getMessage(),e);
        }
    }

    @Override
    public Map<String,Object> object2Row(Record object)
    {
        Map<String, Object> row = new TreeMap<String, Object>();
        row.put("id", object.getId());
        row.put("name", object.getName());
        row.put("artist_id", object.getArtist().getId());
        row.put("release_date", object.getRelease_date());
        row.put("genre", object.getGenre());
        row.put("country", object.getCountry());
        return row;
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
        try{
            PreparedStatement stmt = this.conn.prepareStatement("DELETE FROM records WHERE id = ?");
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }catch (SQLException e){
            System.out.println("Problem pri radu sa bazom podataka");
            System.out.println(e.getMessage());
        }

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
