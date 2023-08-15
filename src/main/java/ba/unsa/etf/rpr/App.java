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
       LabelDao labelDao = DaoFactory.labelDao();
        Label label = labelDao.getAll().get(0);
        if (label.getName().equals("Mute Records") && labelDao.getAll().size()==2) {
            System.out.println("The strings are equal.");
        } else {
            System.out.println("The strings are not equal.");
        }
    }
}
