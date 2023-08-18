package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.RecordStoreException;

/**
 * Business logic layer for User
 * @author Emina Peljto
 */

public class UserManager {
    /**
     * Returns a user from DB based on ID
     * @param id
     * @return User
     * @throws RecordStoreException
     */
    public User getById(int id) throws RecordStoreException
    {
        return DaoFactory.userDao().getById(id);
    }

    public User getUser(String username, String password) throws RecordStoreException
    {
        return DaoFactory.userDao().getUser(username,password);
    }

    public User add(User user) throws RecordStoreException
    {
        return DaoFactory.userDao().add(user);
    }
}
