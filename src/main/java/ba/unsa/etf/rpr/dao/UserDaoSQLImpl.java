package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Record;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.RecordStoreException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

public class UserDaoSQLImpl extends AbstractDao<User> implements UserDao {
    private Connection conn;

    public UserDaoSQLImpl() { super("users"); }

    @Override
    public User row2Object(ResultSet rs) throws RecordStoreException
    {
        try {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            return user;
        }
        catch (SQLException e)
        {
            throw new RecordStoreException(e.getMessage(),e);
        }
    }

    @Override
    public Map<String,Object> object2Row(User object)
    {
        Map<String, Object> row = new TreeMap<String, Object>();
        row.put("id", object.getId());
        row.put("username", object.getUsername());
        row.put("password", object.getPassword());
        return row;
    }
}
