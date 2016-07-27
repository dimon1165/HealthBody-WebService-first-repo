package edu.softserveinc.healthbody.db;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import edu.softserveinc.healthbody.log.Log4jWrapper;

public class ReadDBPropertyFile {
	
	public static String DB_LOCALHOST_URL;
	public static String DB_LOCALHOST_USERNAME;
	public static String DB_LOCALHOST_PASSWORD;
	public static String DB_USERNAME_JENKINS;
	public static String DB_PASSWORD_JENKINS;
	public static String DB_USERNAME_OPENSHIFT;
	public static String DB_PASSWORD_OPENSHIFT;
	static {
		try{

			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			InputStream input = classLoader.getResourceAsStream("database.properties");		
			Properties properties = new Properties();
			properties.load(input);
			input.close();
			
			//LocalHost DB			
			DB_LOCALHOST_URL = properties.getProperty("DB_LOCALHOST_URL");
			DB_LOCALHOST_USERNAME = properties.getProperty("DB_LOCALHOST_USERNAME");
			DB_LOCALHOST_PASSWORD = properties.getProperty("DB_LOCALHOST_PASSWORD");
			
			//Jenkins DB
			DB_USERNAME_JENKINS = properties.getProperty("DB_USERNAME_JENKINS");
			DB_PASSWORD_JENKINS = properties.getProperty("DB_PASSWORD_JENKINS");
		
			//Openshift DB
			DB_USERNAME_OPENSHIFT = properties.getProperty("DB_USERNAME_OPENSHIFT");
			DB_PASSWORD_OPENSHIFT = properties.getProperty("DB_PASSWORD_OPENSHIFT");
			
			
		} catch (IOException ex) {
			Log4jWrapper.get().info("Properties file is not found...");
		}
	}
}
