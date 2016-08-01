package edu.softserveinc.healthbody.db;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import edu.softserveinc.healthbody.log.Log4jWrapper;

class DataSourcePropertiesRepository {
	
    private static volatile DataSourcePropertiesRepository instance;
    
    private static final String DATABASE_PROPERTIES_FILENAME = "database.properties";
	private static final String DEFAULT_URL = "jdbc:postgresql://localhost:5432/";
	private static final String DEFAULT_USERNAME = "postgres";
	private static final String DEFAULT_PASSWORD = "root";
	private static final String DEFAULT_DATABASE = "healthbodydb";
	private static final String DEFAULT_TEST_DATABASE = "healthbodydbtest";

    private String connectionUrl;
    private String username;
    private String password;
    private String database;
    private String testDatabase;

    private DataSourcePropertiesRepository() {
    	readProperties();
    }

    protected static DataSourcePropertiesRepository getInstance() {
        if (instance == null) {
            synchronized (DataSourcePropertiesRepository.class) {
                if (instance == null) {
                    instance = new DataSourcePropertiesRepository();
                }
            }
        }
        return instance;
    }
    
    private void readProperties(){
    	InputStream in = null;
    	//Reading properties from catalina config folder
    	Log4jWrapper.get().info("Catalina base: " + System.getProperty("catalina.base"));
    	Log4jWrapper.get().info("JENKINS_HOME Property: " + System.getProperty("JENKINS_HOME"));
    	Log4jWrapper.get().info("JENKINS_HOME Environment: " + System.getenv("JENKINS_HOME"));
    	try {
			in = new FileInputStream(System.getProperty("catalina.base")
					+ "/conf/"
					+ DATABASE_PROPERTIES_FILENAME);
			Log4jWrapper.get().info("Using " + DATABASE_PROPERTIES_FILENAME + " from Catalina config folder.");
		} catch (FileNotFoundException e) {
			Log4jWrapper.get().info("File " + DATABASE_PROPERTIES_FILENAME + " was not found in Catalina config folder.");
		}
    	//Reading properties from resources in classpath
    	if (in == null){
    		in = Thread.currentThread().getContextClassLoader().getResourceAsStream(DATABASE_PROPERTIES_FILENAME);
    		if (in != null){
    			Log4jWrapper.get().info("Using " + DATABASE_PROPERTIES_FILENAME + " from resources.");
    		}
    	}
    	if (in != null){
			Properties properties = new Properties();
			try {
				properties.load(in);
				in.close();
			} catch (IOException e) {
				Log4jWrapper.get().info("Couldn't load properties from " + DATABASE_PROPERTIES_FILENAME + ".");
			}
			connectionUrl = properties.getProperty("url");
			username = properties.getProperty("username");
			password = properties.getProperty("password");
			database = properties.getProperty("database");
			testDatabase = properties.getProperty("testdatabase");
    	}
    	
    	//Using default hardcoded properties in case some was not loaded from property file
    	if (connectionUrl == null) {
    		connectionUrl = DEFAULT_URL;
    		Log4jWrapper.get().info("Using default connection URL: " + DEFAULT_URL);
    	}
		if (username == null) {
			username = DEFAULT_USERNAME;
    		Log4jWrapper.get().info("Using default username: " + DEFAULT_USERNAME);
		}
		if (password == null) {
			password = DEFAULT_PASSWORD;
    		Log4jWrapper.get().info("Using default password: " + DEFAULT_PASSWORD);
		}
		if (database == null) {
			database = DEFAULT_DATABASE;
		}
		if (testDatabase == null) {
			testDatabase = DEFAULT_TEST_DATABASE;
    		Log4jWrapper.get().info("Using default test database: " + DEFAULT_TEST_DATABASE);
		}
    }
    
//	protected String getJdbcDriverName() {
//		return null;
//	}

	protected String getConnectionUrl() {
		return connectionUrl;
	}

	protected String getUsername() {
		return username;
	}

	protected String getPassword() {
		return password;
	}

	protected String getDatabase() {
		return database;
	}

	protected String getTestDatabase() {
		return testDatabase;
	}
	
}
