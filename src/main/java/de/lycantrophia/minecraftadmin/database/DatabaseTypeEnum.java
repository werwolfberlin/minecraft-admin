package de.lycantrophia.minecraftadmin.database;

public enum DatabaseTypeEnum {

    H2("org.h2.Driver", "jdbc:h2:%s/%s", true),
    H2_SHARED("org.h2.Driver", "jdbc:h2:%s/%s;AUTO_SERVER=TRUE;", true),
    HSQL("org.hsqldb.jdbc.JDBCDriver", "jdbc:hsqldb:%s/%s", true);

    private final String driverName;
    private final String connectString;
    private final boolean embedded;

    DatabaseTypeEnum(final String driverName, final String connectString, final boolean embedded)
    {

        this.driverName = driverName;
        this.connectString = connectString;
        this.embedded = embedded;
    }

    public String getDriverName() {
        return driverName;
    }

    public boolean isEmbedded() {
        return embedded;
    }

    public String getConnectionString(final String server, final String database, final Integer port)
    {
        final String serverString = embedded ||port == null ? server : server + ":" + port;
        return String.format(connectString, serverString, database);
    }
}
