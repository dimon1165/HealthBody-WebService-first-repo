package edu.softserveinc.healthbody.db;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.softserveinc.healthbody.log.LoggerWrapper;

public class DBCreationManager {

	private static final ClassLoader LOADER = Thread.currentThread().getContextClassLoader();
	private static final String PATH_FILE = "tables.txt";
	private static final String TABLES_SPLIT = ";";
	private static volatile DBCreationManager instance = null;

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

	public boolean dropDatabase(Statement statement, String databaseName) throws SQLException {
		boolean result = false;
		statement.execute("select datname from pg_catalog.pg_database where datname = \'" + databaseName + "\';");
		if (statement.getResultSet().next()){
			String deleteConnectionsQuery = "SELECT pg_terminate_backend(pg_stat_activity.pid) FROM pg_stat_activity WHERE pg_stat_activity.datname = \'"
					+ databaseName + "\' AND pid <> pg_backend_pid();";
			result = statement.execute(deleteConnectionsQuery + "DROP DATABASE " + databaseName + ";");
			LoggerWrapper.info(this.getClass(), "Database - " + databaseName + " was deleted.");
		} else {
			LoggerWrapper.info(this.getClass(), "Database - " + databaseName + " does not exist.");
			result = false;
		}
		return result;
	}

	public boolean createDatabase(Statement statement, String databaseName) throws SQLException {
		boolean result = false;
		statement.execute("select datname from pg_catalog.pg_database where datname = \'" + databaseName + "\';");
		if (statement.getResultSet().next()){
			LoggerWrapper.info(this.getClass(), "Database - " + databaseName + " exists.");
		} else {
			LoggerWrapper.info(this.getClass(), "Creating database " + databaseName);
			statement.execute("CREATE DATABASE " + databaseName);
			result = true;
			LoggerWrapper.info(this.getClass(), "Database " + databaseName + " was created.");
		}
		return result;
	}

	public boolean createTable(Statement statement, String tableQuery) throws SQLException {
		boolean result = false;
		result = statement.execute(tableQuery);
		return result;
	}
	
	public List<String> getListOfQueries() {
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
			LoggerWrapper.error(this.getClass(), "Cannot access to file " + PATH_FILE + e);
		}
		return queries;
	}
	

}