package edu.softserveinc.healthbody.db;

import static org.testng.Assert.fail;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.log.Log4jWrapper;

public class CreateDatabaseTestOpenShift extends CreateDropTestDatabase {
 	
 	@BeforeSuite
 	@Override
 	public void createTestDatabase() {
 		String testDatabase = DataSourcePropertiesRepository.getInstance().getTestDatabase();
 		Log4jWrapper.get().info("Setting up database " + testDatabase + ".");
		try (Connection con = ConnectionManager
 				.getInstance(DataSourceRepository.getInstance().getPostgresDatabase()).getConnection();
 				Statement st = con.createStatement()) {
 			if (!DBCreationManager.getInstance().dropDatabase(st, testDatabase)) {
 				String failMessage = "Database " + testDatabase + " does not exist.";
 				Log4jWrapper.get().info(failMessage);
 
 			}
 			if (!DBCreationManager.getInstance().createDatabase(st, testDatabase)) {
 				String failMessage = "Couldn't create database " + testDatabase + ".";
 				Log4jWrapper.get().error(failMessage);
 				fail(failMessage);
 			}
 		} catch (SQLException e) {
 			String failMessage = "Problem with deleting/creating database " + testDatabase + ".";
 			Log4jWrapper.get().error(failMessage + e);
 			fail(failMessage, e);
 		} catch (JDBCDriverException e) {
 			String failMessage = "Couldn't get connection.";
 			Log4jWrapper.get().error(failMessage + e);
 			fail(failMessage, e);
 		}
		try {
			ConnectionManager.getInstance(DataSourceRepository.getInstance().getPostgresDatabase()).getConnection();
		} catch (JDBCDriverException e) {
			String failMessage = "Couldn't get connection.";
			Log4jWrapper.get().error(failMessage + e);
			fail(failMessage, e);
		}		Log4jWrapper.get().info("Setting up database ends successfully...");
	}
 	
 	
 	@AfterSuite
 	@Override
 	public void dropTestDatabase() {
 		String testDatabase = DataSourcePropertiesRepository.getInstance().getTestDatabase();
 		Connection con = null;
 		try {
 			con = ConnectionManager.getInstance(DataSourceRepository.getInstance().getPostgresDatabase())
					.getConnection();
 		} catch (JDBCDriverException e) {
 			String failMessage = "Couldn't get connection.";
 			Log4jWrapper.get().error(failMessage + e);
 			fail(failMessage, e);
 		}
 		try (Statement st = con.createStatement()) {
 			if (!DBCreationManager.getInstance().dropDatabase(st, testDatabase)) {
 				String failMessage = "Couldn't delete database " + testDatabase + ".";
 				Log4jWrapper.get().error(failMessage);
 				fail(failMessage);
 			} else {
 				Log4jWrapper.get().info("Database " + testDatabase + " was deleted.");
 			}
 		} catch (SQLException e) {
			String failMessage = "Problem with deleting database " + testDatabase + ".";
 			Log4jWrapper.get().error(failMessage + e);
 			fail(failMessage, e);
 		}
 	}

}
