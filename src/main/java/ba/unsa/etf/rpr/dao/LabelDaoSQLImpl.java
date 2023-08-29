package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Label;
import ba.unsa.etf.rpr.exceptions.RecordStoreException;

import java.io.InputStream;
import java.sql.*;
import java.util.*;

public class LabelDaoSQLImpl extends AbstractDao<Label> implements LabelDao {
    private Connection conn;
    private static LabelDaoSQLImpl instance = null;

    public LabelDaoSQLImpl() {
     super("labels");
    }

    @Override
    public Label row2Object(ResultSet rs) throws RecordStoreException
    {
        try {
            Label label = new Label();
            label.setId(rs.getInt("id"));
            label.setName(rs.getString("name"));
            label.setCountry(rs.getString("country"));
            return label;
        }
        catch (SQLException e)
        {
            throw new RecordStoreException(e.getMessage(),e);
        }
    }

    @Override
    public Map<String,Object> object2Row(Label object)
    {
        Map<String, Object> row = new TreeMap<String, Object>();
        row.put("id", object.getId());
        row.put("name", object.getName());
        row.put("country", object.getCountry());
        return row;
    }

    public static LabelDaoSQLImpl getInstance()
    {
        if(instance == null) instance = new LabelDaoSQLImpl();
        return instance;
    }

    public static void removeInstance()
    {
        if(instance!=null) instance = null;
    }

    @Override
    public List<Label> searchByCountry(String name) throws RecordStoreException
    {
        String query = "SELECT * FROM labels WHERE country = ?";
        List<Label> labels = new ArrayList<>();
        try
        {
            PreparedStatement stmt = getConnection().prepareStatement(query);
            stmt.setString(1,name);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                labels.add(row2Object(rs));
            }
            return labels;
        }
        catch(SQLException e)
        {
            throw new RecordStoreException(e.getMessage(),e);
        }
    }

    @Override
    public List<Label> searchByName(String name) throws RecordStoreException
    {
        String query = "SELECT * FROM labels WHERE name = ?";
        List<Label> labels = new ArrayList<>();
        try
        {
            PreparedStatement stmt = getConnection().prepareStatement(query);
            stmt.setString(1,name);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                labels.add(row2Object(rs));
            }
            return labels;
        }
        catch(SQLException e)
        {
            throw new RecordStoreException(e.getMessage(),e);
        }
    }

}
