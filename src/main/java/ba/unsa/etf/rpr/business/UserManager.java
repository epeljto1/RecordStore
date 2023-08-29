package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.RecordStoreException;
import ba.unsa.etf.rpr.exceptions.UserException;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

/**
 * Business logic layer for users
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

    /**
     * Checks if user's password satisfies given constraints during sign up
     * @param password
     * @throws UserException
     */
    public void validateSignUp(String password, String confirmed) throws UserException {
        final int minPasswordLength = 5;

        if(password.length() < minPasswordLength)
            throw new UserException("Password needs to be at least " + minPasswordLength + " characters.");
        if(!password.matches("^(?=.*\\d)(?=.*[A-Z]).{8,}$"))
            throw new UserException("Password needs to contain at least one uppercase letter and one number.");
        if(!password.equals(confirmed))
            throw new UserException("Passwords do not match.");
    }

    /**
     * Hashes the password
     * @param password
     * @return hashed password
     * @throws NoSuchAlgorithmException
     */
    public String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        String salt = "maven";
        md.update(salt.getBytes());
        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));
        return new BigInteger(1, hashedPassword).toString(16);
    }
}
