package ba.unsa.etf.rpr.dao;

public class DaoFactory {
    private static final LabelDao labelDao = new LabelDaoSQLImpl();
    private static final ArtistDao artistDao = new ArtistDaoSQLImpl();
    private static final RecordDao recordDao = new RecordDaoSQLImpl();

    private DaoFactory(){}

    public static LabelDao labelDao()
    {
        return labelDao;
    }

    public static ArtistDao artistDao()
    {
        return artistDao;
    }

    public static RecordDao recordDao()
    {
        return recordDao;
    }
}
