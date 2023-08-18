package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.RecordStoreException;

import java.util.List;

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

    /**
     * Returns all users from DB
     * @return list of users
     * @throws RecordStoreException
     */
    public List<User> getAll() throws RecordStoreException
    {
        return DaoFactory.userDao().getAll();
    }

    /**
     * Returns user from DB
     * @param username
     * @param password
     * @return user
     * @throws RecordStoreException
     */
    public User getUser(String username, String password) throws RecordStoreException
    {
        return DaoFactory.userDao().getUser(username,password);
    }
}
