package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.domain.Artist;
import ba.unsa.etf.rpr.domain.Label;
import ba.unsa.etf.rpr.exceptions.RecordStoreException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArtistManagerTest {
    private final ArtistManager artistManager = Mockito.mock(ArtistManager.class);
    private final List<Artist> artists = new ArrayList<>();

    @BeforeEach
    void setup() {
        artists.addAll(Arrays.asList(
                newArtist(1,"Artist 1",newLabel(1,"Label 1","Country 1"),"Country 1","Band"),
                newArtist(2,"Artist 2",newLabel(1,"Label 1","Country 1"),"Country 2","Singer")
        ));
    }

    @Test
    public void validationTest() throws RecordStoreException
    {
        Mockito.doCallRealMethod().when(artistManager).validateArtist(Mockito.any());
        Artist artist = newArtist(3,"Artist 3",newLabel(3,"Label 2","Country 2"),"Country 3","Type");
        RecordStoreException e = Assertions.assertThrows(RecordStoreException.class,() -> artistManager.validateArtist(artist));
        Assertions.assertEquals(e.getMessage(),"Artist type has to be either 'Band' or 'Singer'.");
    }

    @Test
    public void searchByLabelTest() throws RecordStoreException
    {
        Mockito.doAnswer(answer -> {
            String labelName = answer.getArgument(0);
            return artists
                    .stream()
                    .filter(artist -> artist.getLabel().getName().equals(labelName))
                    .findFirst()
                    .orElse(null);
        }).when(artistManager).searchByLabel(Mockito.anyString());

        Assertions.assertEquals(artists.size(),2);
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
