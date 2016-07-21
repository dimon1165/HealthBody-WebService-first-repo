package edu.softserveinc.healthbody.db;

import java.sql.Driver;

import edu.softserveinc.healthbody.exceptions.JDBCDriverException;

public class DriverRepository {
    private static final String FAILED_JDBC_DRIVER = "Failed to Create JDBC Driver";
    private static volatile DriverRepository instance = null;

    private DriverRepository() {
    }

    public static DriverRepository getInstance() {
        if (instance == null) {
            synchronized (DriverRepository.class) {
                if (instance == null) {
                    instance = new DriverRepository();
                }
            }
        }
        return instance;
    }

    public Driver getPostgresDriver() throws JDBCDriverException {
        Driver jdbcDriver = null;
        try {
            jdbcDriver = new org.postgresql.Driver();
        } catch (Exception e) {
            throw new JDBCDriverException(FAILED_JDBC_DRIVER, e);
        }
        return jdbcDriver;
    }

    public Driver getDefault() throws JDBCDriverException {
        return getPostgresDriver();
    }
}
