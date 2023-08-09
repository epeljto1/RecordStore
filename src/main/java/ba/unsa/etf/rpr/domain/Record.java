package ba.unsa.etf.rpr.domain;

import java.util.Date;

/**
 * Bean class for records
 */

public class Record {
    private int id;
    private String name;
    private Artist artist;
    private Date release_date;
    private String genre;
    private String country;

    public Record(int id, String name, Artist artist, Date release_date,
                  String genre, String country) {
        this.id = id;
        this.name = name;
        this.artist = artist;
        this.release_date = release_date;
        this.genre = genre;
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
}