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
   		readProperties(getDataSourcePropertiesInputStream());
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
    
	/**
	 * Gets {@link InputStream} for reading {@link DataSource} properties from
	 * file. Method lookups for file in the following order: 1) Catalina
	 * (Tomcat, JBoss) config directory - <code>${catalina.base}/conf/</code>;
	 * 2) Jenkins home directory using environment variable
	 * <code>$JENKINS_HOME</code>; 3) application classpath resources using
	 * {@link ClassLoader#getResourceAsStream(String) getResourceAsStream
	 * method}. If none of these was found <code>null</code> is returned.
	 * 
	 * @return {@link InputStream} for property file or <code>null</code> if no
	 *         property file was found
	 */
	private InputStream getDataSourcePropertiesInputStream() {
    	InputStream in = getCatalinaDataSourcePropertiesInputStream();
		in = (in == null) ? getJenkinsDataSourcePropertiesInputStream() : in;
		in = (in == null) ? getClasspathDataSourcePropertiesInputStream() : in;
		return in;
    }
    
	/**
	 * Returns an {@link InputStream} for property file located in Catalina
	 * (Tomcat, JBoss) config directory using system property
	 * <code>${catalina.base}/conf/</code>
	 * 
	 * @return {@link InputStream} for property file or <code>null</code> if no
	 *         property file was found
	 */
	private InputStream getCatalinaDataSourcePropertiesInputStream() {
		InputStream in = null;
		// Reading properties from catalina config folder
		String catalinaBase = System.getProperty("catalina.base");
		Log4jWrapper.get().debug("Catalina base: " + catalinaBase);
		if (catalinaBase != null) {
			Log4jWrapper.get().info("Trying to get : " + DATABASE_PROPERTIES_FILENAME + " from Catalina config folder");
			try {
				in = new FileInputStream(catalinaBase + "/conf/" + DATABASE_PROPERTIES_FILENAME);
				Log4jWrapper.get().info("Using " + DATABASE_PROPERTIES_FILENAME + " from Catalina config folder.");
			} catch (FileNotFoundException e) {
				Log4jWrapper.get()
						.info("File " + DATABASE_PROPERTIES_FILENAME + " was not found in Catalina config folder.");
			}
		}
		return in;
	}

	/**
	 * Returns an {@link InputStream} for property file located in Jenkins home
	 * directory using environment variable <code>$JENKINS_HOME</code>.
	 * 
	 * @return {@link InputStream} for property file or <code>null</code> if no
	 *         property file was found
	 */
	private InputStream getJenkinsDataSourcePropertiesInputStream() {
		InputStream in = null;
		// Reading properties from Jenkins home folder
		String jenkinsHome = System.getenv("JENKINS_HOME");
		Log4jWrapper.get().info("JENKINS_HOME Environment: " + jenkinsHome);
		if (jenkinsHome != null) {
			try {
				Log4jWrapper.get().info("Jenkins file path: " + jenkinsHome + "/" + DATABASE_PROPERTIES_FILENAME);
				in = new FileInputStream(jenkinsHome + "/" + DATABASE_PROPERTIES_FILENAME);
				Log4jWrapper.get().info("Using " + DATABASE_PROPERTIES_FILENAME + " from Jenkins home folder.");
			} catch (FileNotFoundException e) {
				Log4jWrapper.get()
						.info("File " + DATABASE_PROPERTIES_FILENAME + " was not found in Jenkins home folder.");
			}
		}
		return in;
	}

	/**
	 * Returns an {@link InputStream} for property file located in appliction
	 * classpath resources using {@link ClassLoader#getResourceAsStream(String)
	 * getResourceAsStream method}
	 * 
	 * @return {@link InputStream} for property file or <code>null</code> if no
	 *         property file was found
	 */
	private InputStream getClasspathDataSourcePropertiesInputStream() {
		InputStream in = null;
   		in = Thread.currentThread().getContextClassLoader().getResourceAsStream(DATABASE_PROPERTIES_FILENAME);
   		if (in != null){
   			Log4jWrapper.get().info("Using " + DATABASE_PROPERTIES_FILENAME + " from classpath resources.");
   		}
		return in;
	}
	
	/**
	 * Reads property file input stream and assigns values to class fields:
	 * <code>connectionUrl</code>, <code>username</code>, <code>password</code>,
	 * <code>database</code>, <code>testDatabase</code> If input stream is empty
	 * (null) or does not contain some property values, class fields are
	 * assigned with default values.
	 * 
	 * @param in {@link InputStream} for property file
	 */
	private void readProperties(InputStream in) {
		Properties properties = new Properties();
		if (in != null) {
			try {
				properties.load(in);
				in.close();
			} catch (IOException e) {
				Log4jWrapper.get().info("Couldn't load properties from " + DATABASE_PROPERTIES_FILENAME + ".");
			}
		} else {
			Log4jWrapper.get().info("No input stream for " + DATABASE_PROPERTIES_FILENAME + " was found. Using default values.");
		}
		connectionUrl = (properties.getProperty("url") != null)
				? properties.getProperty("url")
				: DEFAULT_URL;
		Log4jWrapper.get().debug("connectionUrl: " + connectionUrl);
		username = (properties.getProperty("username") != null)
				? properties.getProperty("username")
				: DEFAULT_USERNAME;
		Log4jWrapper.get().debug("username: " + username);
		password = (properties.getProperty("password") != null)
				? properties.getProperty("password")
				: DEFAULT_PASSWORD;
		Log4jWrapper.get().debug("password: " + password);
		database = (properties.getProperty("database") != null)
				? properties.getProperty("database")
				: DEFAULT_DATABASE;
		Log4jWrapper.get().debug("database: " + database);
		testDatabase = (properties.getProperty("testdatabase") != null)
				? properties.getProperty("testdatabase")
				: DEFAULT_TEST_DATABASE;
		Log4jWrapper.get().debug("testDatabase: " + testDatabase);
	}
	
//	private void checkProperties() {
//
//		// Using default hardcoded properties in case some was not loaded from
//		// property file
//		if (connectionUrl == null) {
//			connectionUrl = DEFAULT_URL;
//			Log4jWrapper.get().info("Using default connection URL: " + DEFAULT_URL);
//		}
//		if (username == null) {
//			username = DEFAULT_USERNAME;
//			Log4jWrapper.get().info("Using default username: " + DEFAULT_USERNAME);
//		}
//		if (password == null) {
//			password = DEFAULT_PASSWORD;
//			Log4jWrapper.get().info("Using default password: " + DEFAULT_PASSWORD);
//		}
//		if (database == null) {
//			database = DEFAULT_DATABASE;
//		}
//		if (testDatabase == null) {
//			testDatabase = DEFAULT_TEST_DATABASE;
//			Log4jWrapper.get().info("Using default test database: " + DEFAULT_TEST_DATABASE);
//		}
//	}

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
