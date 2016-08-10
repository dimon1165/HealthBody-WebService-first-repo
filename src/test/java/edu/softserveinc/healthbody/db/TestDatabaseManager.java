package edu.softserveinc.healthbody.db;

import static org.testng.Assert.fail;

import java.sql.Connection;
import java.sql.SQLException;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.log.Log4jWrapper;

public class TestDatabaseManager {

	@BeforeSuite
	public void prepareTestDatabaseBeforeSuite(){
		createTestDatabaseIfNotExists();
		setupTestDatabaseConnection();
		dropTestDatabaseTables();
		createTestDatabaseTables();
		populateTestDatabaseTables();
	}
	
	@AfterSuite
	public void cleanTestDatabaseAfterSuite() {
		dropTestDatabaseTables();
		closeConnection();
	}
	
	public void repopulateTestDatabase() {
		cleanTestDatabaseTables();
		populateTestDatabaseTables();
	}
	
	private void createTestDatabaseIfNotExists() {
		String testDatabase = DataSourcePropertiesRepository.getInstance().getTestDatabase();
		Log4jWrapper.get().info("Test database: " + testDatabase);
		if ("jenkins".equals(testDatabase)){
			Log4jWrapper.get().info("Skipping database creation at jenkins server.");
			return;
		}
		Log4jWrapper.get().info("Start checking database " + testDatabase + ".");
		try {
			Connection con = ConnectionManager.getInstance(DataSourceRepository.getInstance().getPostgresNoDatabase()).getConnection();
			DBCreationManager.getInstance().createDatabaseIfNotExists(con, testDatabase);
		} catch (SQLException e) {
			String failMessage = "Problem with creating database " + testDatabase + ".";
			Log4jWrapper.get().error(failMessage, e);
			fail(failMessage, e);
		} catch (JDBCDriverException e) {
			String failMessage = "Couldn't get connection.";
			Log4jWrapper.get().error(failMessage, e);
			fail(failMessage, e);
		}
		Log4jWrapper.get().info("Checking database " + testDatabase + " ends successfully.");
	}
	
	private void setupTestDatabaseConnection(){
		try {
			Connection con = ConnectionManager.getInstance(DataSourceRepository.getInstance().getPostgresTestDatabase()).getConnection();
			ConnectionManager.getInstance().setUpConnectionForTest(con);
		} catch (JDBCDriverException e) {
			String failMessage = "Couldn't get connection.";
			Log4jWrapper.get().error(failMessage, e);
			fail(failMessage, e);
		}
}
	
	private void dropTestDatabaseTables(){
		Log4jWrapper.get().info("Start dropping tables in database.");
		try {
			Connection con = ConnectionManager.getInstance().getConnectionForTest();
			DBCreationManager.getInstance().dropAllDatabaseTables(con);
		} catch (SQLException | JDBCDriverException e) {
			String failMessage = "Error while dropping tables in database.";
			Log4jWrapper.get().error(failMessage, e);
			fail(failMessage, e);
		}		
		Log4jWrapper.get().info("Dropping tables in database ends successfully.");
	}
	
	private void createTestDatabaseTables(){
		Log4jWrapper.get().info("Start creating tables in database.");
		try {
			Connection con = ConnectionManager.getInstance().getConnectionForTest();
			DBCreationManager.getInstance().createDatabaseTables(con);
		} catch (SQLException | JDBCDriverException e) {
			String failMessage = "Error while creating tables in database.";
			Log4jWrapper.get().error(failMessage, e);
			fail(failMessage, e);
		}
		Log4jWrapper.get().info("Creating tables in database ends successfully.");
	}
	
	private void populateTestDatabaseTables(){
		Log4jWrapper.get().info("Start populating tables in database.");
		try {
			Connection con = ConnectionManager.getInstance().getConnectionForTest();
			DBPopulateManager.getInstance().populateDatabaseTables(con);
		} catch (JDBCDriverException | SQLException e) {
			String failMessage = "Error while populating tables in database.";
			Log4jWrapper.get().error(failMessage, e);
			fail(failMessage, e);
		}
		Log4jWrapper.get().info("Populating tables in database ends successfully.");
	}
	
	public void cleanTestDatabaseTables(){
		Log4jWrapper.get().info("Start cleaning tables in database.");
		try {
			Connection con = ConnectionManager.getInstance().getConnectionForTest();
			DBCreationManager.getInstance().deleteAllDatabaseData(con);
		} catch (JDBCDriverException | SQLException e) {
			String failMessage = "Error while cleaning tables in database.";
			Log4jWrapper.get().error(failMessage, e);
			fail(failMessage, e);
		}
		Log4jWrapper.get().info("Cleaning tables in database ends successfully.");
	}
	
	private void closeConnection() {
		Log4jWrapper.get().info("Clossing connection to test database.");
		try {
			Connection con = ConnectionManager.getInstance().getConnectionForTest();
			ConnectionManager.getInstance().closeTestConnection(con);
		} catch (JDBCDriverException e) {
			String failMessage = "Error while clossing connection to test database.";
			Log4jWrapper.get().error(failMessage, e);
			fail(failMessage, e);
		}
	}
}
