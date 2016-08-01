package edu.softserveinc.healthbody.db;

import java.sql.Connection;
import java.sql.SQLException;

import edu.softserveinc.healthbody.exceptions.JDBCDriverException;

public class DatabaseCreatorWithMain {
    private final static String DATABASE_NAME = DataSourcePropertiesRepository.getInstance().getDatabase();

    public static void main(String[] args) {
		try {
			DataSource ds = DataSourceRepository.getInstance().getPostgresNoDatabase();
			Connection conn = ConnectionManager.getInstance(ds).getConnection();
			DBCreationManager.getInstance().createDatabaseIfNotExists(conn, DATABASE_NAME);
			ds = DataSourceRepository.getInstance().getPostgresDatabase();
			conn = ConnectionManager.getInstance(ds).getConnection();
			DBCreationManager.getInstance().createDatabaseTables(conn);
			DBPopulateManager.getInstance().populateDatabaseTables(conn);
		} catch (JDBCDriverException | SQLException e) {
			System.out.println("Error while creating and/or populating database " + DATABASE_NAME);
			e.printStackTrace();
		}
	}
}
