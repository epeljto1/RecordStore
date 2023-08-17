package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.User;

import java.sql.Connection;

public class UserDaoSQLImpl extends AbstractDao<User> implements UserDao {
    private Connection conn;

    public UserDaoSQLImpl() { super("users"); }
}
