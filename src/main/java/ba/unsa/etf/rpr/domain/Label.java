package ba.unsa.etf.rpr.domain;

/**
 * Bean class for labels
 */

public class Label {
    private int id;
    private String name;
    private String country;

    public Label(int id, String name, String country)
    {
        this.id = id;
        this.name = name;
        this.country = country;
    }
}
