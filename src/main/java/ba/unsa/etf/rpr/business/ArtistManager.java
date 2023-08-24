package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Artist;
import ba.unsa.etf.rpr.exceptions.RecordStoreException;

import java.util.List;

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

    /**
     * Returns all artists from DB
     * @return list of artists
     * @throws RecordStoreException
     */
    public List<Artist> getAll() throws RecordStoreException
    {
        return DaoFactory.artistDao().getAll();
    }

    /**
     * Returns artist from DB based on name
     * @param name
     * @return list of artists
     * @throws RecordStoreException
     */
    public List<Artist> searchByName(String name) throws RecordStoreException
    {
        return DaoFactory.artistDao().searchByName(name);
    }

    /**
     * Returns artist from DB based on label
     * @param label
     * @return list of artists
     * @throws RecordStoreException
     */
    public List<Artist> searchByLabel(String label) throws RecordStoreException
    {
        return DaoFactory.artistDao().searchByLabel(label);
    }

    public void validateArtist(Artist artist) throws RecordStoreException
    {
        if(!artist.getType().equals("Band") || !artist.getType().equals("Singer"))
            throw new RecordStoreException("Artist type has to be either 'Band' or 'Singer'.");
    }
}
