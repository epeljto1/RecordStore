package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Artist;
import ba.unsa.etf.rpr.exceptions.RecordStoreException;

import java.util.List;

/**
 * DAO interface for Artist model
 * @author Emina Peljto
 */

public interface ArtistDao extends Dao<Artist> {
    // Returns list of all artists with the same name
    // as search parameter
    List<Artist> searchByName(String name) throws RecordStoreException;
    List<Artist> searchByLabel(String name) throws RecordStoreException;
}
