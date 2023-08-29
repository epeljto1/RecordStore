package ba.unsa.etf.rpr.domain;

import java.util.Objects;

/**
 * Bean class for artists
 */

public class Artist implements Identifiable {
    private int id;
    private String name;
    private Label label;
    private String country;
    private String type;

    @Override
    public int getId() {
        return id;
    }

    @Override
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

    @Override
    public String toString()
    {
        return getName();
    }

    @Override
    public boolean equals(Object o)
    {
        if(this == o) return true;
        if(o == null || getClass()!=o.getClass()) return false;
        Artist artist = (Artist) o;
        return id==artist.id && Objects.equals(artist.name,name) && Objects.equals(artist.label,label) && Objects.equals(artist.country,country)
                && Objects.equals(artist.type,type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, label, country, type);
    }
}
