package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Artist;
import ba.unsa.etf.rpr.domain.Label;
import ba.unsa.etf.rpr.domain.Record;
import ba.unsa.etf.rpr.exceptions.RecordStoreException;

import java.io.InputStream;
import java.sql.*;
import java.sql.Date;
import java.util.*;

public class RecordDaoSQLImpl extends AbstractDao<Record> implements RecordDao {
    private Connection conn;

    public RecordDaoSQLImpl() {
      super("records");
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
    public List<Record> searchByName(String name) throws RecordStoreException
    {
        String query = "SELECT * FROM records WHERE name = ?";
        List<Record> records = new ArrayList<>();
        try
        {
            PreparedStatement stmt = getConnection().prepareStatement(query);
            stmt.setString(1,name);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                records.add(row2Object(rs));
            }
            return records;
        }
        catch(SQLException e)
        {
            throw new RecordStoreException(e.getMessage(),e);
        }
    }

    @Override
    public List<Record> searchByArtist(String name) throws RecordStoreException
    {
        String query = "SELECT * FROM records WHERE artist_id = ?";
        List<Record> records = new ArrayList<>();
        try
        {
            ArtistDao artistDao = DaoFactory.artistDao();
            Artist artist = artistDao.searchByName(name).get(0);
            PreparedStatement stmt = getConnection().prepareStatement(query);
            stmt.setInt(1,artist.getId());
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                records.add(row2Object(rs));
            }
            return records;
        }
        catch(SQLException e)
        {
            throw new RecordStoreException(e.getMessage(),e);
        }
    }

    @Override
    public List<Record> searchByDateRange(java.util.Date start, java.util.Date end) throws RecordStoreException
    {
        List<Record> records = new ArrayList<>();
        java.sql.Date startDate = new java.sql.Date(start.getTime());
        java.sql.Date endDate = new java.sql.Date(end.getTime());
        try{
            PreparedStatement stmt = getConnection().prepareStatement("SELECT * FROM records WHERE release_date BETWEEN ? AND ?");
            stmt.setDate(1,startDate);
            stmt.setDate(2,endDate);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                records.add(row2Object(rs));
            }
            return records;
        }catch (SQLException e){
            throw new RecordStoreException(e.getMessage(),e);
        }
    }

}
