package ba.unsa.etf.rpr.domain;

import ba.unsa.etf.rpr.dao.DaoFactory;

import java.sql.Date;
import java.util.Objects;

/**
 * Bean class for records
 * @author Emina Peljto
 */

public class Record implements Identifiable {
    private int id;
    private String name;
    private Artist artist;
    private Date release_date;
    private String genre;
    private String country;

    private Record(Builder builder)
    {
        id = builder.id;
        name = builder.name;
        artist = builder.artist;
        release_date = builder.release_date;
        genre = builder.genre;
        country = builder.country;
    }

    public static class Builder {
        private int id;
        private String name;
        private Artist artist = new Artist();
        private Date release_date = new Date(System.currentTimeMillis());
        private String genre = "";
        private String country = "";

        public Builder(int id, String name)
        {
            this.id = id;
            this.name = name;
        }

        public Builder artist(Artist val)
        {
            artist = val; return this;
        }

        public Builder release_date(Date val)
        {
            release_date = val; return this;
        }

        public Builder genre(String val)
        {
            genre = val; return this;
        }

        public Builder country(String val)
        {
            country = val; return this;
        }

        public Record build()
        {
            return new Record(this);
        }
    }

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

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Date getRelease_date() {
        return release_date;
    }

    public void setRelease_date(Date release_date) {
        this.release_date = release_date;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
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
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Record record = (Record) o;
        return id==record.id && Objects.equals(record.name,name) && Objects.equals(record.artist,artist)
                && Objects.equals(record.release_date,release_date) && Objects.equals(genre,record.genre) && Objects.equals(country,record.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, artist, release_date, genre, country);
    }
}
