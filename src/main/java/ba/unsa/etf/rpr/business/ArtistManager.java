package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Artist;
import ba.unsa.etf.rpr.exceptions.RecordStoreException;

public class ArtistManager {
    /**
     * Returns artist from DB based on ID
     * @param id
     * @return artist
     * @throws RecordStoreException
     */
    public Artist getById(int id) throws RecordStoreException
    {
        return DaoFactory.artistDao().getById(id);
    }

    /**
     * Adds an artist to DB
     * @param artist
     * @return artist
     * @throws RecordStoreException
     */
    public Artist add(Artist artist) throws RecordStoreException
    {
        return DaoFactory.artistDao().add(artist);
    }

    /**
     * Updates an artist to DB
     * @param artist
     * @return artist
     * @throws RecordStoreException
     */
    public Artist update(Artist artist) throws RecordStoreException
    {
        return DaoFactory.artistDao().update(artist);
    }

    /**
     * Removes artist from DB based on ID
     * @param id
     * @throws RecordStoreException
     */
    public void delete(int id) throws RecordStoreException
    {
        DaoFactory.artistDao().delete(id);
    }
}