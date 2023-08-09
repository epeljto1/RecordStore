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

    public int getId() {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Label getLabel()
    {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}