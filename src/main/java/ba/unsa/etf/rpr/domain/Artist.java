package ba.unsa.etf.rpr.domain;

public class Artist {
    private int id;
    private String name;
    private String country;
    private String type;

    public Artist(int id, String name, String country, String type)
    {
        this.id = id;
        this.name = name;
        this.country = country;
        this.type = type;
    }
}
