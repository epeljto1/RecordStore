package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Artist;
import ba.unsa.etf.rpr.domain.Label;
import ba.unsa.etf.rpr.exceptions.RecordStoreException;

import java.io.InputStream;
import java.sql.*;
import java.util.*;

/**
 * Class that implements methods from ArtistDao interface
 * @author Emina Peljto
 */
public class ArtistDaoSQLImpl extends AbstractDao<Artist> implements ArtistDao {
    private Connection conn;
    private static ArtistDaoSQLImpl instance;

    public ArtistDaoSQLImpl() {
    super("artists");
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

    public static ArtistDaoSQLImpl getInstance()
    {
        if(instance == null) instance = new ArtistDaoSQLImpl();
        return instance;
    }

    public static void removeInstance()
    {
        if(instance != null) instance = null;
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
            LabelDao labelDao = DaoFactory.labelDao();
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
