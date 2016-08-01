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

    public DataSource getPostgresDatabase() throws JDBCDriverException {
        return new DataSource(DriverRepository.getInstance().getPostgresDriver(),
        		DataSourcePropertiesRepository.getInstance().getConnectionUrl()
        		+ DataSourcePropertiesRepository.getInstance().getDatabase(),
        		DataSourcePropertiesRepository.getInstance().getUsername(),
        		DataSourcePropertiesRepository.getInstance().getPassword());
    }
    
    public DataSource getPostgresTestDatabase() throws JDBCDriverException {
        return new DataSource(DriverRepository.getInstance().getPostgresDriver(),
        		DataSourcePropertiesRepository.getInstance().getConnectionUrl()
        		+ DataSourcePropertiesRepository.getInstance().getTestDatabase(),
        		DataSourcePropertiesRepository.getInstance().getUsername(),
        		DataSourcePropertiesRepository.getInstance().getPassword());
    }

    public DataSource getPostgresNoDatabase() throws JDBCDriverException {
        return new DataSource(DriverRepository.getInstance().getPostgresDriver(),
        		DataSourcePropertiesRepository.getInstance().getConnectionUrl(),
        		DataSourcePropertiesRepository.getInstance().getUsername(),
        		DataSourcePropertiesRepository.getInstance().getPassword());
    }
}
