package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.domain.Label;
import ba.unsa.etf.rpr.exceptions.RecordStoreException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class used for testing LabelManager class
 * @author Emina Peljto
 */

public class LabelManagerTest {
    private final LabelManager labelManager = Mockito.mock(LabelManager.class);
    private final List<Label> labels = new ArrayList<>();

    @BeforeEach
    void setup() throws RecordStoreException
    {
        labels.addAll(Arrays.asList(newLabel(1,"Label 1","Country 1"),
                newLabel(2,"Label 2","Country 2"),
                newLabel(3,"Label 3","Country 3")));
    }

    @Test
    public void getAllTest() throws RecordStoreException
    {
        Mockito.when(labelManager.getAll()).thenReturn(labels);
        Assertions.assertEquals(labels,labelManager.getAll());
    }

    @Test
    public void addTest() throws RecordStoreException
    {
        Mockito.doAnswer(answer -> {
            labels.add(answer.getArgument(0));
            return answer.getArgument(0);
        }).when(labelManager).add(Mockito.any());

        List<Label> previous = new ArrayList<>(labels);
        Label added = labelManager.add(newLabel(4,"Label 4","Country 4"));
        previous.add(added);
        Assertions.assertEquals(labels,previous);
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
