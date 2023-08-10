package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Artist;

import java.sql.Connection;
import java.sql.DriverManager;
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
}
