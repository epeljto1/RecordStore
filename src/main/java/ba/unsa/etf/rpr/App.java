package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.dao.LabelDao;
import ba.unsa.etf.rpr.dao.LabelDaoSQLImpl;
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

        // LabelDao labelDao = new LabelDaoSQLImpl();
        // Label label = new Label();
        // label.setId(2);
        // label.setName("Parkwood Entertainment");
        // label.setCountry("US");
        // labelDao.update(label);

        // LabelDao labelDao = new LabelDaoSQLImpl();
        // labelDao.delete(2);

        LabelDao labelDao = new LabelDaoSQLImpl();
        Label label = labelDao.getAll().get(0);
        if (label.getName().equals("Mute Records")) {
        System.out.println("The strings are equal.");
         } else {
         System.out.println("The strings are not equal.");
         }

    }
}
