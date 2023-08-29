package ba.unsa.etf.rpr.domain;

/**
 * Interface that forces all POJO beans to have ID field
 * @author Emina Peljto
 */

public interface Identifiable {
    void setId(int id);

    int getId();
}
