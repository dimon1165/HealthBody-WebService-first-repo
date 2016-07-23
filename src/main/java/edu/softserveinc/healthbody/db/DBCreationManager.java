package edu.softserveinc.healthbody.db;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

	public boolean dropDatabase(final Statement statement, final String databaseName) throws SQLException {
		boolean result = false;
		statement.execute("select datname from pg_catalog.pg_database where datname = \'" + databaseName + "\';");
		if (statement.getResultSet().next()) {
			String deleteConnectionsQuery = "SELECT pg_terminate_backend(pg_stat_activity.pid) FROM pg_stat_activity "
					+ "WHERE pg_stat_activity.datname = \'"
					+ databaseName + "\' AND pid <> pg_backend_pid();";
			result = statement.execute(deleteConnectionsQuery + "DROP DATABASE " + databaseName + ";");
			Log4jWrapper.get().info("Database - " + databaseName + " was deleted.");
		} else {
			Log4jWrapper.get().info("Database - " + databaseName + " does not exist.");
			result = false;
		}
		return result;
	}

	public boolean createDatabase(final Statement statement, final String databaseName) throws SQLException {
		boolean result = false;
		statement.execute("select datname from pg_catalog.pg_database where datname = \'" + databaseName + "\';");
		if (statement.getResultSet().next()) {
			Log4jWrapper.get().info("Database - " + databaseName + " exists.");
		} else {
			Log4jWrapper.get().info("Creating database " + databaseName);
			statement.execute("CREATE DATABASE " + databaseName);
			result = true;
			Log4jWrapper.get().info("Database " + databaseName + " was created.");
		}
		return result;
	}

	public boolean createTable(final Statement statement, final String tableQuery) throws SQLException {
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
			Log4jWrapper.get().error("Cannot access to file " + PATH_FILE + e);
		}
		return queries;
	}
}