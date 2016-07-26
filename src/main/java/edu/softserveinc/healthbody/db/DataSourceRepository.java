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
                + "/ws", "admindqufr92", "57UAv48D1n2-");
    }

    public DataSource getPostgresJenkins() throws JDBCDriverException {
        return new DataSource(DriverRepository.getInstance().getPostgresDriver(),
                "jdbc:postgresql://127.10.182.2:5432/jenkins", "adminud8ggnm", "6JxTBU-ab6KR");
    }

    public DataSource getPostgresJenkinsByDatabaseName(final String databaseName) throws JDBCDriverException {
        return new DataSource(DriverRepository.getInstance().getPostgresDriver(),
                "jdbc:postgresql://127.10.182.2:5432/" + databaseName, "adminud8ggnm", "6JxTBU-ab6KR");
    }

    public DataSource getPostgresLocalHost() throws JDBCDriverException {
        return new DataSource(DriverRepository.getInstance().getPostgresDriver(),
                "jdbc:postgresql://localhost:5432/healthbodydb", "postgres", "root");
    }

    public DataSource getPostgresLocalHostTest() throws JDBCDriverException {
        return new DataSource(DriverRepository.getInstance().getPostgresDriver(),
                "jdbc:postgresql://localhost:5432/healthbodydbtest", "postgres", "root");
    }

    public DataSource getPostgresLocalHostNoDatabase() throws JDBCDriverException {
        return new DataSource(DriverRepository.getInstance().getPostgresDriver(), "jdbc:postgresql://localhost:5432/",
                "postgres", "root");
    }

    public DataSource getPostgresLocalHostByDatabaseName(final String databaseName) throws JDBCDriverException {
        return new DataSource(DriverRepository.getInstance().getPostgresDriver(), "jdbc:postgresql://localhost:5432/"
                + databaseName, "postgres", "root");
    }

}
