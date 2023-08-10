package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Artist;

import java.util.List;

/**
 * DAO interface for Artist model
 */

public interface ArtistDao extends Dao<Artist> {
    // Returns list of all artists with the same name
    // as search parameter
    List<Artist> searchByName(String name);
}
