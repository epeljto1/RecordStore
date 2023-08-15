package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.dao.*;
import ba.unsa.etf.rpr.domain.Artist;
import ba.unsa.etf.rpr.domain.Label;
import ba.unsa.etf.rpr.domain.Record;
import ba.unsa.etf.rpr.exceptions.RecordStoreException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws RecordStoreException
    {
       Label label = new Label();
       label.setId(5);
       label.setName("Columbia Records");
       label.setCountry("US");
       LabelDao labelDao = DaoFactory.labelDao();
       labelDao.update(label);
    }
}
