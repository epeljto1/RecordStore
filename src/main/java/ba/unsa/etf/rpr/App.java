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
        // LabelDao labelDao = new LabelDaoSQLImpl();
        // int id = 1;
        // Label label = labelDao.getById(id);
        // if (label.getName().equals("Mute Records")) {
           // System.out.println("The strings are equal.");
        // } else {
           // System.out.println("The strings are not equal.");
        // }

        LabelDao labelDao = new LabelDaoSQLImpl();
        Label label = new Label();
        label.setName("Parkwood Entertainment");
        label.setCountry("United States");
        labelDao.add(label);
    }
}
