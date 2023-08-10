package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Artist;
import ba.unsa.etf.rpr.domain.Label;

import java.sql.Connection;
import java.sql.DriverManager;
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
}
