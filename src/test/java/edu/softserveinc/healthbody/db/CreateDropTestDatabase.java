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
import edu.softserveinc.healthbody.log.Log4jWrapper;

public class CreateDropTestDatabase {

	@BeforeSuite
	@Parameters("testDatabase")
	public void createTestDatabase(@Optional("healthbodydbtest") String testDatabase) {
		Log4jWrapper.get().info("Setting up database " + testDatabase + ".");
		try (Connection con = ConnectionManager
				.getInstance(DataSourceRepository.getInstance().getPostgresLocalHostNoDatabase()).getConnection();
				Statement st = con.createStatement()) {
			if (!DBCreationManager.getInstance().dropDatabase(st, testDatabase)) {
				String failMessage = "Database " + testDatabase + " does not exist.";
				Log4jWrapper.get().info(failMessage);
				// Not epic fail :) Let's go ahead.
				// fail(failMessage);
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
			ConnectionManager.getInstance(DataSourceRepository.getInstance().getPostgresLocalHostByDatabaseName(testDatabase)).getConnection();
		} catch (JDBCDriverException e) {
			String failMessage = "Couldn't get connection.";
			Log4jWrapper.get().error(failMessage + e);
			fail(failMessage, e);
		}		Log4jWrapper.get().info("Setting up database ends successfully...");
	}
	
	public void populateDBTables(){
		Connection con = null;
		try {
			con = ConnectionManager.getInstance().getConnection();
		} catch (JDBCDriverException e) {
			String failMessage = "Couldn't get connection.";
			Log4jWrapper.get().error(failMessage + e);
			fail(failMessage, e);
		}
		try (Statement st = con.createStatement()){
			DBPopulateManager.getInstance().deleteAllFromTables();
		} catch (SQLException | JDBCDriverException e) {
			String failMessage = "Problem with deleting tables in database.";
			Log4jWrapper.get().error(failMessage + e);
			fail(failMessage, e);
		} 
		try (Statement st = con.createStatement()) {
			DBCreationManager dbCReationManager = DBCreationManager.getInstance();
			for (String query : dbCReationManager.getListOfQueries()) {
				Log4jWrapper.get().info("Creating table " + query.split("\"")[1]);
				dbCReationManager.createTable(st, query);
			}
		} catch (SQLException e) {
			String failMessage = "Problem with creating tables in database.";
			Log4jWrapper.get().error(failMessage + e);
			fail(failMessage, e);
		}
		DBPopulateManager.getInstance().populateUsersTable();
		DBPopulateManager.getInstance().populateGroupsTable();
		DBPopulateManager.getInstance().populateUserGroupsTable();
	    DBPopulateManager.getInstance().populateAwardsTable();
	    DBPopulateManager.getInstance().populateCompetitionsTable();
	    DBPopulateManager.getInstance().populateCriteriaTable();
	    DBPopulateManager.getInstance().populateGroupCompetitionsTable();
	    DBPopulateManager.getInstance().populateMetaDataTable();
	    DBPopulateManager.getInstance().populateRolesTable();
	    DBPopulateManager.getInstance().populateUserCompetitionsTable();
		Log4jWrapper.get().info("End of tables population");
	}

	
	@AfterSuite
	@Parameters("testDatabase")
	public void dropTestDatabase(@Optional("healthbodydbtest") String testDatabase) {
		Connection con = null;
		try {
			con = ConnectionManager.getInstance(DataSourceRepository.getInstance().getPostgresLocalHostNoDatabase())
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
