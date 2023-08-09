package ba.unsa.etf.rpr.domain;

/**
 * Bean class for artists
 */

public class Artist {
    private int id;
    private String name;
    private Label label;
    private String country;
    private String type;

    public Artist(int id, String name, Label label,
                  String country, String type)
    {
        this.id = id;
        this.name = name;
        this.label = label;
        this.country = country;
        this.type = type;
    }
}
