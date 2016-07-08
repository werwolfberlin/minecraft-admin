package de.lycantrophia.minecraftadmin.database;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Properties;

public class DatabaseConnection
{
    public static final String DATABASE_TYPE = "dbType";
    public static final String DATABASE_PERSISTENCE_UNIT = "persistenceUnit";
    public static final String DATABASE_USER = "dbUser";
    public static final String DATABASE_PASSWORD = "dbPassword";
    public static final String DATABASE_SERVER = "dbServer";
    public static final String DATABASE_INSTANCE = "dbInstance";
    public static final String DATABASE_PORT = "dbPort";

    private static final String JDBC_DRIVER = "javax.persistence.jdbc.driver";
    private static final String JDBC_URL = "javax.persistence.jdbc.url";
    private static final String JDBC_USER = "javax.persistence.jdbc.user";
    private static final String JDBC_PASSWORD = "javax.persistence.jdbc.password";

    private final String persistenceUnit;
    private final Properties properties;

    private EntityManagerFactory entityManagerFactory;

    public DatabaseConnection(final Properties connectionProperties)
    {
        final DatabaseTypeEnum databaseTypeEnum = DatabaseTypeEnum.valueOf(connectionProperties.getProperty("dbType", "H2_SHARED"));
        persistenceUnit = connectionProperties.getProperty("persistenceUnit", "");
        properties = new Properties(connectionProperties);

        properties.setProperty(JDBC_DRIVER, databaseTypeEnum.getDriverName());

        final String username = connectionProperties.getProperty("dbUser", null);
        if(username != null) properties.setProperty(JDBC_USER, username);

        final String password = connectionProperties.getProperty("dbPassword", "SuperSecretPassword");
        if(password != null) properties.setProperty(JDBC_PASSWORD, password);

        final String server = connectionProperties.getProperty("dbServer", "db");
        final String database = connectionProperties.getProperty("dbInstance", getUsername());
        final Integer port = getIntegerFromString(connectionProperties.getProperty("dbPort", null), null);
        properties.setProperty(JDBC_URL, databaseTypeEnum.getConnectionString(server, database, port));

        properties.remove("dbType");
        properties.remove("persistenceUnit");
        properties.remove("dbUser");
        properties.remove("dbPassword");
        properties.remove("dbServer");
        properties.remove("dbInstance");
        properties.remove("dbPort");
    }

    public EntityManagerFactory getEntityManagerFactory()
    {
        if(entityManagerFactory == null)
        {
            entityManagerFactory = Persistence.createEntityManagerFactory(persistenceUnit, properties);
        }
        return entityManagerFactory;
    }

    private Integer getIntegerFromString(final String dbPort, final Integer defaultValue)
    {
        try
        {
            return Integer.parseInt(dbPort);
        }
        catch (final Exception e)
        {
            return defaultValue;
        }
    }

    public String getUsername() {
        return properties.getProperty(JDBC_USER);
    }

    @Override
    public String toString() {
        return persistenceUnit + " (" + properties.getProperty(JDBC_URL) + "#" + properties.getProperty(JDBC_USER) + '}';
    }
}
