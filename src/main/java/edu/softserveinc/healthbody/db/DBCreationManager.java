package edu.softserveinc.healthbody.db;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.softserveinc.healthbody.log.Log4jWrapper;

public final class DBCreationManager {

	private static final ClassLoader LOADER = Thread.currentThread().getContextClassLoader();
	private static final String PATH_FILE = "tables.txt";
	private static final String TABLES_SPLIT = ";";
	private static volatile DBCreationManager instance;

	private DBCreationManager() {
	}

	public static DBCreationManager getInstance() {
		if (instance == null) {
			synchronized (DBCreationManager.class) {
				if (instance == null) {
					instance = new DBCreationManager();
				}
			}
		}
		return instance;
	}

	/**
	 * Drops database if it exists.
	 * 
	 * @param conn {@link Connection} to database
	 * @param databaseName {@link String} name for database
	 * @return
	 * @throws SQLException
	 */
	public boolean dropDatabaseIfExists(final Connection conn, final String databaseName) throws SQLException {
		boolean result = false;
		if (databaseExists(conn, databaseName)){
			result = dropDatabase(conn, databaseName);
			Log4jWrapper.get().info("Database - " + databaseName + " was deleted.");
		} else {
			Log4jWrapper.get().info("Database - " + databaseName + " does not exist.");
		}
		return result;
	}
	
	/**
	 * Kills all active connections to database and drops it.
	 * @param conn
	 * @param databaseName
	 * @return
	 * @throws SQLException
	 */
	private boolean dropDatabase(Connection conn, String databaseName) throws SQLException {
		String terminateActiveDatabaseConnectionsQuery = 
				"SELECT pg_terminate_backend(pg_stat_activity.pid) FROM pg_stat_activity"
				+ " WHERE pg_stat_activity.datname = \'" + databaseName + "\'"
				+ " AND pid <> pg_backend_pid();";
		String dropDatabaseQuery = "DROP DATABASE " + databaseName + ";";
		try (Statement statement = conn.createStatement()){
			return statement.execute(terminateActiveDatabaseConnectionsQuery + dropDatabaseQuery);
		}
	}

	/**
	 * Creates database with if it does not exist.
	 * @param conn {@link Connection} to database
	 * @param databaseName {@link String} name for database
	 * @return <code>true</code> if database was created and <code>false</code> if database already exists.
	 * @throws SQLException if one was thrown while executing {@link Statement statements}
	 */
	public boolean createDatabaseIfNotExists(final Connection conn, final String databaseName) throws SQLException {
		boolean result = false;
		try (Statement statement = conn.createStatement()){
			if (!databaseExists(conn, databaseName)) {
				Log4jWrapper.get().info("Creating database " + databaseName);
				result = createDatabase(conn, databaseName);
				Log4jWrapper.get().info("Database " + databaseName + " was created.");
			} else {
				Log4jWrapper.get().info("Database - " + databaseName + " exists.");
			}
		}
		return result;
	}
	
	/**
	 * Checks if database exists on Postgre SQL server
	 * @param statement
	 * @param databaseName
	 * @return <code>true</code> if database exists and <code>false</code> if database does not exist.
	 * @throws SQLException
	 */
	public boolean databaseExists(Connection conn, String databaseName) throws SQLException {
		try (Statement statement = conn.createStatement()) {
			statement.execute("select datname from pg_catalog.pg_database where datname = \'" + databaseName + "\';");
			return statement.getResultSet().next();
		}
	}
	
	/**
	 * Creates database with specified name
	 * @param conn
	 * @param databaseName
	 * @return
	 * @throws SQLException
	 */
	private boolean createDatabase(Connection conn, String databaseName) throws SQLException {
		try (Statement statement = conn.createStatement()){
			return statement.execute("CREATE DATABASE " + databaseName);
		}
	}


    public boolean dropAllDatabaseTables(Connection conn) throws SQLException {
		boolean result = false;
		String query = "drop TABLE if exists usergroups, groupcompetitions, usercompetitions, users, " 
				+ "roles, groups, competitions, awards, criteria, metadata;";
		try (PreparedStatement pst = conn.prepareStatement(query)) {
			result = pst.execute();
		} catch (SQLException e) {
			Log4jWrapper.get().error("Error while dropping database tables.", e);
			throw e;
		}
		return result;
	}

    public boolean deleteAllDatabaseData(Connection conn) throws SQLException {
		boolean result = false;
		String[] tableNames = {"usergroups", "groupcompetitions", "usercompetitions", "users",
				"roles", "groups", "competitions", "awards", "criteria", "metadata"};
		StringBuilder sb = new StringBuilder();
		for(String tableName:tableNames){
			sb.append("delete from ").append(tableName).append(";");
		}
		String query = sb.toString();
		try (PreparedStatement pst = conn.prepareStatement(query)) {
			result = pst.execute();
		} catch (SQLException e) {
			Log4jWrapper.get().error("Error while deleting data from database.", e);
			throw e;
		}
		return result;
	}
    
    public boolean createDatabaseTables(Connection conn) throws SQLException{
    	boolean result = true;
		for (String query : getInstance().getListOfQueries()) {
			result = result && getInstance().createTable(conn, query);
		}
		return result;
    }
    
	private List<String> getListOfQueries() {
		List<String> queries = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		try (BufferedReader bufReader = new BufferedReader(
				new InputStreamReader(LOADER.getResourceAsStream(PATH_FILE)))) {
			String row;
			while ((row = bufReader.readLine()) != null) {
				sb.append(row);
			}
			queries = Arrays.asList(sb.toString().split(TABLES_SPLIT));
		} catch (IOException e) {
			Log4jWrapper.get().error("Cannot access to file " + PATH_FILE + e);
		}
		return queries;
	}

	private boolean createTable(Connection conn, final String tableQuery) throws SQLException {
		try (Statement statement = conn.createStatement()){
			statement.executeUpdate(tableQuery);
			return true;// .execute(tableQuery);
		} catch (SQLException e){
			throw e;
		}
	}
}