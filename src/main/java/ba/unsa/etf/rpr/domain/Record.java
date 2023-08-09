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
}
