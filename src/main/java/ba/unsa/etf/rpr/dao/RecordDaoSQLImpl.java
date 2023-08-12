package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Artist;
import ba.unsa.etf.rpr.domain.Record;

import java.io.InputStream;
import java.sql.*;
import java.util.List;
import java.util.Properties;

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

    @Override
    public Record getById(int id) {
        try {
        PreparedStatement stmt = this.conn.prepareStatement("SELECT * FROM records WHERE id = ?");
        stmt.setInt(1 ,id);
        ResultSet rs = stmt.executeQuery();
        if(rs.next()) {
            Record record = new Record();
            record.setId(rs.getInt("id"));
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
        return null;
    }

    @Override
    public Record update(Record item)
    {
        return null;
    }

    @Override
    public void delete(int id)
    {

    }

    @Override
    public List<Record> getAll()
    {
        return null;
    }

    @Override
    public List<Record> searchByName(String name)
    {
        return null;
    }
}
