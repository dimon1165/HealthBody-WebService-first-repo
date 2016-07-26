package edu.softserveinc.healthbody.db;

import java.io.FileInputStream;
import java.io.IOException;
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
		try {
			Properties proproperties = new Properties();
			proproperties.load(new FileInputStream("src/main/resources/database.properties"));
			
			//LocalHost DB			
			DB_LOCALHOST_URL = proproperties.getProperty("DB_LOCALHOST_URL");
			DB_LOCALHOST_USERNAME = proproperties.getProperty("DB_LOCALHOST_USERNAME");
			DB_LOCALHOST_PASSWORD = proproperties.getProperty("DB_LOCALHOST_PASSWORD");
			
			//Jenkins DB
			DB_USERNAME_JENKINS = proproperties.getProperty("DB_USERNAME_JENKINS");
			DB_PASSWORD_JENKINS = proproperties.getProperty("DB_PASSWORD_JENKINS");
		
			//Openshift DB
			DB_USERNAME_OPENSHIFT = proproperties.getProperty("DB_USERNAME_OPENSHIFT");
			DB_PASSWORD_OPENSHIFT = proproperties.getProperty("DB_PASSWORD_OPENSHIFT");
			
		} catch (IOException ex) {
			Log4jWrapper.get().info("Properties file is not found...");
		}
	}
}
