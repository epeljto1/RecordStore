package ba.unsa.etf.rpr.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class RecordDaoSQLImpl {
    private Connection conn;

    public RecordDaoSQLImpl() {
        Properties prop = new Properties();
        try(InputStream input = LabelDaoSQLImpl.class.getResourceAsStream("/application.properties"))
        {
            prop.load(input);
            conn = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("username"), prop.getProperty("password"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
