package ba.unsa.etf.rpr.dao;

public class DaoFactory {
    private static final LabelDao labelDao = LabelDaoSQLImpl.getInstance();
    private static final ArtistDao artistDao = ArtistDaoSQLImpl.getInstance();
    private static final RecordDao recordDao = RecordDaoSQLImpl.getInstance();

    private static final UserDao userDao = UserDaoSQLImpl.getInstance();

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

    public static UserDao userDao() { return userDao; }
}
