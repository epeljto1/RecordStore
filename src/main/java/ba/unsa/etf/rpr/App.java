package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.dao.*;
import ba.unsa.etf.rpr.domain.Artist;
import ba.unsa.etf.rpr.domain.Record;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        // Test getById - LabelDao
        // LabelDao labelDao = new LabelDaoSQLImpl();
        // int id = 1;
        // Label label = labelDao.getById(id);
        // if (label.getName().equals("Mute Records")) {
           // System.out.println("The strings are equal.");
        // } else {
           // System.out.println("The strings are not equal.");
        // }

        // Test add - LabelDao
        // LabelDao labelDao = new LabelDaoSQLImpl();
        // Label label = new Label();
        // label.setName("Parkwood Entertainment");
        // label.setCountry("United States");
        // labelDao.add(label);



         // ArtistDao artistDao = new ArtistDaoSQLImpl();
         // Artist artist = artistDao.searchByLabel("Parkwood Entertainment").get(0);
        // if (artist.getName().equals("Beyoncé") && artist.getLabel().getCountry().equals("United States")) {
         // System.out.println("The strings are equal.");
          // } else {
         // System.out.println("The strings are not equal.");
          // }

        // LabelDao labelDao = new LabelDaoSQLImpl();
        // if (labelDao.searchByCountry("United States").size()==2 && labelDao.searchByCountry("United States").get(1).getName().equals("Columbia Records")) {
        // System.out.println("Okay.");
        // } else {
         // System.out.println("Not okay.");
         // }

        RecordDao recordDao = new RecordDaoSQLImpl();
        String str1 = "1990-01-19";
        String str2 = "2023-08-12";
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date datum = format.parse(str1);
            Date datum2 = format.parse(str2);
            java.sql.Date datumsql = new java.sql.Date(datum.getTime());
            Record record = recordDao.searchByDateRange(datum,datum2).get(0);
            if(recordDao.searchByDateRange(datum,datum2).size()==2 && record.getName().equals("RENAISSANCE"))
            {
                System.out.println("Okay");
            }
            else
            {
                System.out.println("Not okay");
            }
        }
        catch(java.text.ParseException e)
        {
            e.getMessage();
        }



    }
}
