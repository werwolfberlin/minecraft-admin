package de.lycantrophia.minecraftadmin.database;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Properties;

public final class DatabaseConnectionHelper
{
    private static DatabaseConnectionHelper INSTANCE;
    private EntityManager entityManager;

    public static DatabaseConnectionHelper getInstance()
    {
        if(INSTANCE != null) return INSTANCE;

        INSTANCE = new DatabaseConnectionHelper();
        return INSTANCE;
    }

    public static DatabaseConnectionHelper getInstance(final Properties properties)
    {
        if(INSTANCE != null) return INSTANCE;

        INSTANCE = new DatabaseConnectionHelper(properties);
        return INSTANCE;
    }

    private final DatabaseConnection connectionInformation;

    private DatabaseConnectionHelper()
    {
        this(null);
    }

    private DatabaseConnectionHelper(final Properties customProperties)
    {
        final Properties properties = new Properties();
        properties.putAll(getPropertiesFromEnvironment());
        properties.putAll(getPropertiesFromConfigFile());
        properties.putAll(getPropertiesFromCommandLine());

        if(customProperties != null) properties.putAll(customProperties);

        connectionInformation = new DatabaseConnection(properties);
    }

    private Properties getPropertiesFromCommandLine() {
        return new Properties();
    }

    private Properties getPropertiesFromConfigFile() {
        return new Properties();
    }

    private Properties getPropertiesFromEnvironment() {
        return new Properties();
    }

    public EntityManagerFactory getEntityManagerFactory()
    {
        return connectionInformation.getEntityManagerFactory();
    }

    public EntityManager getEntityManager()
    {
        if(entityManager == null || !entityManager.isOpen())
        {
            entityManager = getEntityManagerFactory().createEntityManager();
        }

        return entityManager;
    }
}
