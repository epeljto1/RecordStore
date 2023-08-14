package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Artist;
import ba.unsa.etf.rpr.domain.Label;
import ba.unsa.etf.rpr.exceptions.RecordStoreException;

import java.io.InputStream;
import java.sql.*;
import java.util.*;

public class ArtistDaoSQLImpl extends AbstractDao<Artist> implements ArtistDao {
    private Connection conn;

    public ArtistDaoSQLImpl() {
    super("artists");
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
    public Artist row2Object(ResultSet rs) throws RecordStoreException
    {
        try {
            Artist artist = new Artist();
            artist.setId(rs.getInt("id"));
            artist.setName(rs.getString("name"));
            LabelDao labelDao = new LabelDaoSQLImpl();
            artist.setLabel(labelDao.getById(rs.getInt("label_id")));
            artist.setCountry(rs.getString("country"));
            artist.setType(rs.getString("type"));
            return artist;
        }
        catch (SQLException e)
        {
            throw new RecordStoreException(e.getMessage(),e);
        }
    }

    @Override
    public Map<String,Object> object2Row(Artist object)
    {
        Map<String, Object> row = new TreeMap<String, Object>();
        row.put("id", object.getId());
        row.put("name", object.getName());
        row.put("label_id", object.getLabel().getId());
        row.put("country", object.getCountry());
        row.put("type", object.getType());
        return row;
    }

    @Override
    public List<Artist> searchByName(String name) throws RecordStoreException
    {
        String query = "SELECT * FROM artists WHERE name = ?";
        List<Artist> artists = new ArrayList<>();
        try
        {
            PreparedStatement stmt = getConnection().prepareStatement(query);
            stmt.setString(1,name);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                artists.add(row2Object(rs));
            }
            return artists;
        }
        catch(SQLException e)
        {
            throw new RecordStoreException(e.getMessage(),e);
        }
    }

    @Override
    public List<Artist> searchByLabel(String name) throws RecordStoreException
    {
        String query = "SELECT * FROM artists WHERE label_id = ?";
        List<Artist> artists = new ArrayList<>();
        try
        {
            LabelDao labelDao = new LabelDaoSQLImpl();
            Label label = labelDao.searchByName(name).get(0);
            PreparedStatement stmt = getConnection().prepareStatement(query);
            stmt.setInt(1,label.getId());
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                artists.add(row2Object(rs));
            }
            return artists;
        }
        catch(SQLException e)
        {
            throw new RecordStoreException(e.getMessage(),e);
        }
    }
}
