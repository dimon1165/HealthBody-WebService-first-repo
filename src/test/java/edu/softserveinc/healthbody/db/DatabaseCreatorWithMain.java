package edu.softserveinc.healthbody.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import edu.softserveinc.healthbody.exceptions.JDBCDriverException;

public class DatabaseCreatorWithMain {
    private final static String DATABASE_NAME = DataSourcePropertiesRepository.getInstance().getDatabase();

    public static void main(String[] args) throws JDBCDriverException, SQLException {
		DataSource ds = DataSourceRepository.getInstance().getPostgresNoDatabase();
		Connection con = ConnectionManager.getInstance(ds).getConnection();
		Statement st = con.createStatement();
		DBCreationManager.getInstance().createDatabase(st, DATABASE_NAME);
		st.close();
		ds = DataSourceRepository.getInstance().getPostgresDatabase();
		con = ConnectionManager.getInstance(ds).getConnection();
		st = con.createStatement();
		for (String query : DBCreationManager.getInstance().getListOfQueries()) {
			DBCreationManager.getInstance().createTable(st, query);
		}
		DBPopulateManager.getInstance().populateRolesTable();
		DBPopulateManager.getInstance().populateUsersTable();
		DBPopulateManager.getInstance().populateGroupsTable();
		DBPopulateManager.getInstance().populateUserGroupsTable();
	    DBPopulateManager.getInstance().populateAwardsTable();
	    DBPopulateManager.getInstance().populateCompetitionsTable();
	    DBPopulateManager.getInstance().populateCriteriaTable();
	    DBPopulateManager.getInstance().populateGroupCompetitionsTable();
	    DBPopulateManager.getInstance().populateMetaDataTable();
	    DBPopulateManager.getInstance().populateUserCompetitionsTable();
	}
}
