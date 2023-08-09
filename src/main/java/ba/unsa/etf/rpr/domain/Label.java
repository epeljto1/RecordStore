package ba.unsa.etf.rpr.domain;

import java.util.Objects;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, country);
    }
}
