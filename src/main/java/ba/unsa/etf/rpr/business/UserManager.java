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
     * @return user
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

    /**
     * Adds a user to DB
     * @param user
     * @return user
     * @throws RecordStoreException
     */
    public User add(User user) throws RecordStoreException
    {
        return DaoFactory.userDao().add(user);
    }

    /**
     * Updates a user in DB
     * @param user
     * @return
     * @throws RecordStoreException
     */
    public User update(User user) throws RecordStoreException
    {
        return DaoFactory.userDao().update(user);
    }

    /**
     * Removes user from DB based on ID
     * @param id
     * @throws RecordStoreException
     */
    public void delete(int id) throws RecordStoreException
    {
        DaoFactory.userDao().delete(id);
    }
}
