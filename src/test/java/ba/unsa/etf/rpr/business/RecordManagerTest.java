package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.domain.Artist;
import ba.unsa.etf.rpr.domain.Label;
import ba.unsa.etf.rpr.domain.Record;
import ba.unsa.etf.rpr.exceptions.RecordStoreException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Date;
import java.text.DateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RecordManagerTest {
    private final RecordManager recordManager = Mockito.mock(RecordManager.class);
    private final List<Record> records = new ArrayList<>();

    @BeforeEach
    void setup() {
        records.addAll(Arrays.asList(
                newRecord(1,"Record 1",
                        newArtist(1,"Artist 1",newLabel(1,"Label 1","Country 1"),"Country 1","Band"),
                        Date.valueOf(LocalDate.parse("2001-01-01")),"Genre 1","Country 1"),
                newRecord(2,"Record 2",
                        newArtist(1,"Artist 1",newLabel(1,"Label 1","Country 1"),"Country 1","Band"),
                        Date.valueOf(LocalDate.parse("2011-01-01")),"Genre 2","Country 2")
        ));
    }

    @Test
    public void searchByNameTest() throws RecordStoreException
    {
        Mockito.doAnswer(answer -> {
            String recordName = answer.getArgument(0);
            return records
                    .stream()
                    .filter(record -> record.getName().equals(recordName))
                    .collect(Collectors.toList());
        }).when(recordManager).searchByName(Mockito.anyString());

        Assertions.assertEquals(1,recordManager.searchByName("Record 1").size());
    }

    @Test
    public void searchByArtistTest() throws RecordStoreException
    {
        Mockito.doAnswer(answer -> {
            String artistName = answer.getArgument(0);
            return records
                    .stream()
                    .filter(record -> record.getArtist().getName().equals(artistName))
                    .collect(Collectors.toList());
        }).when(recordManager).searchByArtist(Mockito.anyString());

        Assertions.assertEquals(2,recordManager.searchByArtist("Artist 1").size());
    }

    @Test
    public void searchByDateRangeTest() throws RecordStoreException
    {
        Mockito.doAnswer(answer -> {
            Date startDate = answer.getArgument(0);
            Date endDate = answer.getArgument(1);
            return records.stream()
                    .filter(record -> record.getRelease_date().compareTo(startDate) >= 0
                            && record.getRelease_date().compareTo(endDate) <= 0)
                    .collect(Collectors.toList());
        }).when(recordManager).searchByDateRange(Mockito.any(Date.class), Mockito.any(Date.class));

        try {
            Assertions.assertEquals(1, recordManager.searchByDateRange(DateFormat.getDateInstance().parse("1999-01-01"),
                    DateFormat.getDateInstance().parse("2002-01-01")).size());
        }
        catch(java.text.ParseException e)
        {
            e.getMessage();
        }

        try {
            Assertions.assertEquals(0, recordManager.searchByDateRange(DateFormat.getDateInstance().parse("2005-01-01"),
                    DateFormat.getDateInstance().parse("2010-01-01")).size());
        }
        catch(java.text.ParseException e)
        {
            e.getMessage();
        }
    }

    private Record newRecord(int id, String name, Artist artist, Date release_date, String genre, String country)
    {
        Record record = new Record.Builder(id,name).build();
        record.setArtist(artist);
        record.setRelease_date(release_date);
        record.setGenre(genre);
        record.setCountry(country);
        return record;
    }

    private Artist newArtist(int id, String name, Label label, String country, String type)
    {
        Artist artist = new Artist();
        artist.setId(id);
        artist.setName(name);
        artist.setLabel(label);
        artist.setCountry(country);
        artist.setType(type);
        return artist;
    }

    private Label newLabel(int id, String name, String country)
    {
        Label label = new Label();
        label.setId(id);
        label.setName(name);
        label.setCountry(country);
        return label;
    }
}
