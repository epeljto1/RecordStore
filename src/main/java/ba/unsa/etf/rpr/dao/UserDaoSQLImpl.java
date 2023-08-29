package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.RecordStoreException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

/**
 * Class that implements method from UserDao interface
 * @author Emina Peljto
 */

public class UserDaoSQLImpl extends AbstractDao<User> implements UserDao {
    private Connection conn;
    private static UserDaoSQLImpl instance;

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

    public static UserDaoSQLImpl getInstance()
    {
        if(instance == null) instance = new UserDaoSQLImpl();
        return instance;
    }

    public static void removeInstance()
    {
        if(instance != null) instance = null;
    }

    @Override
    public User getUser(String username, String password) throws RecordStoreException
    {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        try {
            PreparedStatement stmt = getConnection().prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                User user = row2Object(rs);
                return user;
            }
        } catch(SQLException e) {
            throw new RecordStoreException(e.getMessage(), e);
        }
        throw new RecordStoreException("User not found.");
    }
}
