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

public class CreateDropTestDatabase {

	@BeforeSuite
	@Parameters("testDatabase")
	public void createTestDatabase(@Optional("healthbodydbtest") String testDatabase) {
		LoggerWrapper.info(this.getClass(), "Setting up database " + testDatabase + ".");
		try (Connection con = ConnectionManager
				.getInstance(DataSourceRepository.getInstance().getPostgresLocalHostNoDatabase()).getConnection();
				Statement st = con.createStatement()) {
			if (!DBCreationManager.getInstance().dropDatabase(st, testDatabase)) {
				String failMessage = "Database " + testDatabase + " does not exist.";
				LoggerWrapper.info(this.getClass(), failMessage);
				// Not epic fail :) Let's go ahead.
				// fail(failMessage);
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
			ConnectionManager.getInstance(DataSourceRepository.getInstance().getPostgresLocalHostByDatabaseName(testDatabase)).getConnection();
		} catch (JDBCDriverException e) {
			String failMessage = "Couldn't get connection.";
			LoggerWrapper.error(this.getClass(), failMessage + e);
			fail(failMessage, e);
		}		LoggerWrapper.info(this.getClass(), "Setting up database ends successfully...");
	}
	
	public void populateDBTables(){
		Connection con = null;
		try {
			con = ConnectionManager.getInstance().getConnection();
		} catch (JDBCDriverException e) {
			String failMessage = "Couldn't get connection.";
			LoggerWrapper.error(this.getClass(), failMessage + e);
			fail(failMessage, e);
		}
		try (Statement st = con.createStatement()){
			DBPopulateManager.getInstance().deleteAllFromTables();
		} catch (SQLException | JDBCDriverException e) {
			String failMessage = "Problem with deleting tables in database.";
			LoggerWrapper.error(this.getClass(), failMessage + e);
			fail(failMessage, e);
		} 
		try (Statement st = con.createStatement()) {
			DBCreationManager dbCReationManager = DBCreationManager.getInstance();
			for (String query : dbCReationManager.getListOfQueries()) {
				LoggerWrapper.info(this.getClass(), "Creating table " + query.split("\"")[1]);
				dbCReationManager.createTable(st, query);
			}
		} catch (SQLException e) {
			String failMessage = "Problem with creating tables in database.";
			LoggerWrapper.error(this.getClass(), failMessage + e);
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
		LoggerWrapper.info(this.getClass(), "End of tables population");
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
