package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.dao.ArtistDao;
import ba.unsa.etf.rpr.dao.ArtistDaoSQLImpl;
import ba.unsa.etf.rpr.dao.LabelDao;
import ba.unsa.etf.rpr.dao.LabelDaoSQLImpl;
import ba.unsa.etf.rpr.domain.Artist;
import ba.unsa.etf.rpr.domain.Label;

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

        ArtistDao artistDao = new ArtistDaoSQLImpl();
        Label label = new Label();
        label.setId(3);
        label.setName("Columbia Records");
        label.setCountry("United States");
        Artist artist = new Artist();
        artist.setId(2);
        artist.setName("Beyoncé");
        artist.setLabel(label);
        artist.setCountry("US");
        artist.setType("Singer");
        artistDao.update(artist);

        // LabelDao labelDao = new LabelDaoSQLImpl();
        // labelDao.delete(2);

        // LabelDao labelDao = new LabelDaoSQLImpl();
        // Label label = labelDao.getAll().get(0);
        // if (label.getName().equals("Mute Records")) {
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

        // Test getById - ArtistDao
        // ArtistDao artistDao = new ArtistDaoSQLImpl();
        // int id = 1;
        // Artist artist = artistDao.getById(id);
        // if (artist.getName().equals("Depeche Mode") && artist.getLabel().getName().equals("Mute Records")) {
         // System.out.println("The strings are equal.");
         // } else {
        // System.out.println("The strings are not equal.");
         // }


    }
}
