package edu.softserveinc.healthbody.db;

import edu.softserveinc.healthbody.exceptions.JDBCDriverException;

public final class DataSourceRepository {

    private static volatile DataSourceRepository instance;

    private DataSourceRepository() {
    }

    public static DataSourceRepository getInstance() {
        if (instance == null) {
            synchronized (DataSourceRepository.class) {
                if (instance == null) {
                    instance = new DataSourceRepository();
                }
            }
        }
        return instance;
    }
    

    public DataSource getPostgresOpenShift() throws JDBCDriverException {
        return new DataSource(DriverRepository.getInstance().getPostgresDriver(), "jdbc:postgresql://"
                + System.getenv("OPENSHIFT_POSTGRESQL_DB_HOST") + ":" + System.getenv("OPENSHIFT_POSTGRESQL_DB_PORT")
                + "/ws", ReadDBPropertyFile.DB_USERNAME_OPENSHIFT, ReadDBPropertyFile.DB_PASSWORD_OPENSHIFT);
    }

    public DataSource getPostgresJenkins() throws JDBCDriverException {
        return new DataSource(DriverRepository.getInstance().getPostgresDriver(),
        		"jdbc:postgresql://"
                        + System.getenv("OPENSHIFT_POSTGRESQL_DB_HOST") + ":" + System.getenv("OPENSHIFT_POSTGRESQL_DB_PORT")
                        + "/jenkins", ReadDBPropertyFile.DB_USERNAME_JENKINS, ReadDBPropertyFile.DB_PASSWORD_JENKINS);
    }

    public DataSource getPostgresJenkinsByDatabaseName(final String databaseName) throws JDBCDriverException {
        return new DataSource(DriverRepository.getInstance().getPostgresDriver(),
        		"jdbc:postgresql://"
                        + System.getenv("OPENSHIFT_POSTGRESQL_DB_HOST") + ":" + System.getenv("OPENSHIFT_POSTGRESQL_DB_PORT")
                        + "/"+databaseName, ReadDBPropertyFile.DB_USERNAME_JENKINS, ReadDBPropertyFile.DB_PASSWORD_JENKINS);
                
    }

    public DataSource getPostgresLocalHost() throws JDBCDriverException {
        return new DataSource(DriverRepository.getInstance().getPostgresDriver(),
        		ReadDBPropertyFile.DB_LOCALHOST_URL +"healthbodydb", ReadDBPropertyFile.DB_LOCALHOST_USERNAME , ReadDBPropertyFile.DB_LOCALHOST_PASSWORD);
    }

    public DataSource getPostgresLocalHostTest() throws JDBCDriverException {
        return new DataSource(DriverRepository.getInstance().getPostgresDriver(),
                ReadDBPropertyFile.DB_LOCALHOST_URL +"healthbodydbtest", ReadDBPropertyFile.DB_LOCALHOST_USERNAME , ReadDBPropertyFile.DB_LOCALHOST_PASSWORD);
    }

    public DataSource getPostgresLocalHostNoDatabase() throws JDBCDriverException {
        return new DataSource(DriverRepository.getInstance().getPostgresDriver(), ReadDBPropertyFile.DB_LOCALHOST_URL,
        		ReadDBPropertyFile.DB_LOCALHOST_USERNAME , ReadDBPropertyFile.DB_LOCALHOST_PASSWORD);
    }

    public DataSource getPostgresLocalHostByDatabaseName(final String databaseName) throws JDBCDriverException {
        return new DataSource(DriverRepository.getInstance().getPostgresDriver(), ReadDBPropertyFile.DB_LOCALHOST_URL
                + databaseName, ReadDBPropertyFile.DB_LOCALHOST_USERNAME , ReadDBPropertyFile.DB_LOCALHOST_PASSWORD);
    }

}
