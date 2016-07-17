package edu.softserveinc.healthbody.db;

import static org.testng.Assert.fail;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.log.LoggerWrapper;

public class CreateDatabaseTestOpenShift extends CreateDropTestDatabase {
 	
 	@BeforeSuite
 	@Parameters("testDatabase")
 	@Override
 	public void createTestDatabase(@Optional("healthbodydbtest") String testDatabase) {
 		LoggerWrapper.info(this.getClass(), "Setting up database " + testDatabase + ".");
		try (Connection con = ConnectionManager
 				.getInstance(DataSourceRepository.getInstance().getPostgresJenkins()).getConnection();
 				Statement st = con.createStatement()) {
 			if (!DBCreationManager.getInstance().dropDatabase(st, testDatabase)) {
 				String failMessage = "Database " + testDatabase + " does not exist.";
 				LoggerWrapper.info(this.getClass(), failMessage);
 
 			}
 			if (!DBCreationManager.getInstance().createDatabase(st, testDatabase)) {
 				String failMessage = "Couldn't create database " + testDatabase + ".";
 				LoggerWrapper.error(this.getClass(), failMessage);
 				fail(failMessage);
 			}
 		} catch (SQLException e) {
 			String failMessage = "Problem with deleting/creating database " + testDatabase + ".";
 			LoggerWrapper.error(this.getClass(), failMessage + e);
 			fail(failMessage, e);
 		} catch (JDBCDriverException e) {
 			String failMessage = "Couldn't get connection.";
 			LoggerWrapper.error(this.getClass(), failMessage + e);
 			fail(failMessage, e);
 		}
		try {
			ConnectionManager.getInstance(DataSourceRepository.getInstance().getPostgresJenkinsByDatabaseName(testDatabase)).getConnection();
		} catch (JDBCDriverException e) {
			String failMessage = "Couldn't get connection.";
			LoggerWrapper.error(this.getClass(), failMessage + e);
			fail(failMessage, e);
		}		LoggerWrapper.info(this.getClass(), "Setting up database ends successfully...");
	}
 	
 	
 	@AfterSuite
 	@Parameters("testDatabase")
 	@Override
 	public void dropTestDatabase(@Optional("healthbodydbtest") String testDatabase) {
 		Connection con = null;
 		try {
 			con = ConnectionManager.getInstance(DataSourceRepository.getInstance().getPostgresJenkins())
					.getConnection();
 		} catch (JDBCDriverException e) {
 			String failMessage = "Couldn't get connection.";
 			LoggerWrapper.error(this.getClass(), failMessage + e);
 			fail(failMessage, e);
 		}
 		try (Statement st = con.createStatement()) {
 			if (!DBCreationManager.getInstance().dropDatabase(st, testDatabase)) {
 				String failMessage = "Couldn't delete database " + testDatabase + ".";
 				LoggerWrapper.error(this.getClass(), failMessage);
 				fail(failMessage);
 			} else {
 				LoggerWrapper.info(this.getClass(), "Database " + testDatabase + " was deleted.");
 			}
 		} catch (SQLException e) {
			String failMessage = "Problem with deleting database " + testDatabase + ".";
 			LoggerWrapper.error(this.getClass(), failMessage + e);
 			fail(failMessage, e);
 		}
 	}

}
