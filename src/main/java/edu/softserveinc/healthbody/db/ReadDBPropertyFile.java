package edu.softserveinc.healthbody.db;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import edu.softserveinc.healthbody.log.Log4jWrapper;

public class ReadDBPropertyFile {
	
	public static String DB_URL;
	public static String DB_USERNAME;
	public static String DB_PASSWORD;
	static {
		try{

			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			InputStream input = classLoader.getResourceAsStream("database.properties");		
			Properties properties = new Properties();
			properties.load(input);
			input.close();
			
			DB_URL = properties.getProperty("DB_URL");
			DB_USERNAME = properties.getProperty("DB_USERNAME");
			DB_PASSWORD = properties.getProperty("DB_PASSWORD");
			
		} catch (IOException ex) {
			Log4jWrapper.get().info("Properties file is not found...");
		}
	}
}
