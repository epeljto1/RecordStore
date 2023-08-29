package ba.unsa.etf.rpr.domain;

import java.util.Objects;

/**
 * Bean class for labels
 * @author Emina Peljto
 */

public class Label implements Identifiable {
    private int id;
    private String name;
    private String country;

    @Override
    public int getId() {
        return id;
    }

    @Override
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
    public String toString()
    {
        return getName();
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if(o == null || getClass()!=o.getClass()) return false;
        Label label = (Label) o;
        return id == label.id && Objects.equals(name,label.name) && Objects.equals(country,label.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, country);
    }
}
