package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.RecordStoreException;

/**
 * DAO interface for User model
 * @author Emina Peljto
 */
public interface UserDao extends Dao<User> {
    User getUser(String username, String password) throws RecordStoreException;
}
