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
}
