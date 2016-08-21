package edu.softserveinc.healthbody.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;
import java.util.UUID;

import edu.softserveinc.healthbody.constants.DaoStatementsConstant;
import edu.softserveinc.healthbody.log.Log4jWrapper;

public class DBPopulateManager {

	private static final int USERS = 10;
	private static final int GROUPS = 3;
	private static final int ROLES = 3;
	private static final int COMPETITIONS = 20;
	private static final int AWARDS = 4;
	private static final int CRITERIA = 4;
	private static final int METADATA = 8;
	private static final String[] USER_ID = new String[USERS];
	private static final String[] GROUP_ID = new String[GROUPS];
	private static final String[] COMPETITION_ID = new String[COMPETITIONS];
	private static final String[] AWARD_ID = new String[AWARDS];
	private static final String[] CRITERIA_ID = new String[CRITERIA];
	private static String ROLE_USER_ID;
	private static final int USERGROUPS = 15;
	private static final int USERCOMPETITIONS = 50;
	private static final int GROUPCOMPETITIONS = 8;
	private static volatile DBPopulateManager instance = null;

	private DBPopulateManager() {
	}

	public static DBPopulateManager getInstance() {
		if (instance == null) {
			synchronized (DBPopulateManager.class) {
				if (instance == null) {
					instance = new DBPopulateManager();
				}
			}
		}
		return instance;
	}

    
    public boolean populateDatabaseTables(Connection connection) throws SQLException {
    	boolean result = true;
		result = result && getInstance().populateRolesTable(connection);
		result = result && getInstance().populateUsersTable(connection);
		result = result && getInstance().populateGroupsTable(connection);
		result = result && getInstance().populateUserGroupsTable(connection);
		result = result && getInstance().populateAwardsTable(connection);
		result = result && getInstance().populateCompetitionsTable(connection);
		result = result && getInstance().populateCriteriaTable(connection);
		result = result && getInstance().populateGroupCompetitionsTable(connection);
		result = result && getInstance().populateMetaDataTable(connection);
		result = result && getInstance().populateUserCompetitionsTable(connection);
    	return result;
    }
	
	private boolean populateUsersTable(Connection connection) throws SQLException {
		boolean successfulInsert = false;
		String query = DaoStatementsConstant.UserDBQueries.INSERT.toString();
		try (PreparedStatement pst = connection.prepareStatement(query)) {
			for (int j = 0; j < USERS; j++) {	
				USER_ID[j] = UUID.randomUUID().toString();
				pst.setString(1, USER_ID[j]);				
				pst.setString(2, "Login " + j);
				pst.setString(3, "password " + j);
				pst.setString(4, "Name of " + j + " user");
				pst.setString(5, "LastName of " + j + " user");
				pst.setString(6, "SomeMail" + j + "@gmail.com");
				pst.setInt(7, 25 + j);
				pst.setDouble(8, 55.6 + j);
				pst.setString(9, (j % 2 == 0) ? "m" : "w");
				pst.setString(10, "health " + j);
				pst.setString(11, "urlavatar " + j);
				pst.setString(12, "googleApi " + j);
				pst.setString(13, ROLE_USER_ID);
				pst.setString(14, "active " + j);
				pst.setBoolean(15, false);
				successfulInsert = (pst.executeUpdate() > 0) ? true : false;
				if (!successfulInsert){
					break;
				}
			}
		} catch (SQLException e) {
			Log4jWrapper.get().error("Error populating users table.", e);
			throw e;
		}
		return successfulInsert;
	}

	private boolean populateGroupsTable(Connection connection) throws SQLException {
		boolean successfulInsert = false;
		String query = DaoStatementsConstant.GroupDBQueries.INSERT.toString();
		try (PreparedStatement pst = connection.prepareStatement(query)) {
			for (int j = 0; j < GROUPS; j++){				
				GROUP_ID[j] = UUID.randomUUID().toString();
				pst.setString(1, GROUP_ID[j]);				
				pst.setString(2, "Name group number "+j);
				pst.setInt(3, 5+j*5);
				pst.setString(4, "Description of group "+j);
				pst.setString(5, "1"+j);	
				pst.setString(6, "active");	
				successfulInsert = (pst.executeUpdate() > 0) ? true : false;
				if (!successfulInsert) {
					break;
				}
			}
		} catch (SQLException e) {
			Log4jWrapper.get().error("Error populating groups table.", e);
			throw e;
		}
		return successfulInsert;
	}

	private boolean populateUserGroupsTable(Connection connection) throws SQLException {
		boolean successfulInsert = false;
		String query = DaoStatementsConstant.UserGroupQueries.INSERT.toString();
		try (PreparedStatement pst = connection.prepareStatement(query)) {
			for (int j = 0; j < USERGROUPS; j++) {
				pst.setString(1, UUID.randomUUID().toString());
				pst.setString(2, USER_ID[(int)(Math.random() * USERS)]);
				pst.setString(3, GROUP_ID[(int)(Math.random() * GROUPS)]);
				successfulInsert = (pst.executeUpdate() > 0) ? true : false;
				if (!successfulInsert) {
					break;
				}
			}
		} catch (SQLException e) {
			Log4jWrapper.get().error("Error populating usergroups table.", e);
			throw e;
		}
		return successfulInsert;
	}

	private boolean populateAwardsTable(Connection connection) throws SQLException {
		boolean successfulInsert = false;
		String query = DaoStatementsConstant.AwardDBQueries.INSERT.toString();
		try (PreparedStatement pst = connection.prepareStatement(query)) {
			for (int j = 0; j < AWARDS; j++) {
				AWARD_ID[j] = UUID.randomUUID().toString();
				pst.setString(1, AWARD_ID[j]);		
				pst.setString(2, "Name award " + j);
				successfulInsert = (pst.executeUpdate() > 0) ? true : false;
				if (!successfulInsert) {
					break;
				}
			}
		} catch (SQLException e) {
			Log4jWrapper.get().error("Error populating awards table.", e);
			throw e;
		}
		return successfulInsert;
	}

	private boolean populateCompetitionsTable(Connection connection) throws SQLException {
		boolean successfulInsert = false;
		String query = DaoStatementsConstant.CompetitionDBQueries.INSERT.toString();
		try (PreparedStatement pst = connection.prepareStatement(query)) {
			for (int j = 0; j < COMPETITIONS; j++) {
				COMPETITION_ID[j] = UUID.randomUUID().toString();
				pst.setString(1, COMPETITION_ID[j]);	
				pst.setString(2, "Name competition " + j);
				pst.setString(3, "Description of competition " + j);
				Date startDate = new Date(System.currentTimeMillis() + 
						(long)(System.currentTimeMillis()/2 == 0 ? 1 : -1) * new Random().nextInt(10) * 24 * 60 * 60 * 1000);
				pst.setDate(4, startDate);
				Date endDate = new Date(startDate.getTime() + (long)(new Random().nextInt(20)) * 24 * 60 * 60 * 1000);
				pst.setDate(5, endDate);
				pst.setString(6, CRITERIA_ID[(int)(Math.random() * CRITERIA)]);
				successfulInsert = (pst.executeUpdate() > 0) ? true : false;
				if (!successfulInsert) {
					break;
				}
			}
		} catch (SQLException e) {
			Log4jWrapper.get().error("Error populating competitions table.", e);
			throw e;
		}
		return successfulInsert;
	}

	private boolean populateCriteriaTable(Connection connection) throws SQLException {
		boolean successfulInsert = false;
		String query = DaoStatementsConstant.CriteriaDBQueries.INSERT.toString();
		try (PreparedStatement pst = connection.prepareStatement(query)) {
			for (int j = 0; j < CRITERIA; j++) {
				CRITERIA_ID[j] = UUID.randomUUID().toString();
				pst.setString(1, CRITERIA_ID[j]);
				pst.setString(2, "Name criteria " + j);
				pst.setDouble(3, 4.5 + j);
				pst.setString(4, "get google " + j);
				successfulInsert = (pst.executeUpdate() > 0) ? true : false;
				if (!successfulInsert) {
					break;
				}
			}
		} catch (SQLException e) {
			Log4jWrapper.get().error("Error populating criteria table.", e);
			throw e;
		}
		return successfulInsert;
	}

	private boolean populateGroupCompetitionsTable(Connection connection) throws SQLException {
		boolean successfulInsert = false;
		String query = DaoStatementsConstant.GroupCompetitionsDBQueries.INSERT.toString();
		try (PreparedStatement pst = connection.prepareStatement(query)) {
			for (int j = 0; j < GROUPCOMPETITIONS; j++) {
				pst.setString(1, UUID.randomUUID().toString());
				pst.setString(2, GROUP_ID[(int)(Math.random() * GROUPS)]);
				pst.setString(3, COMPETITION_ID[(int)(Math.random() * COMPETITIONS)]);
				successfulInsert = (pst.executeUpdate() > 0) ? true : false;
				if (!successfulInsert) {
					break;
				}
			}
		} catch (SQLException e) {
			Log4jWrapper.get().error("Error populating groupcompetitions table.", e);
			throw e;
		}
		return successfulInsert;
	}

	private boolean populateMetaDataTable(Connection connection) throws SQLException {
		boolean successfulInsert = false;
		String query = DaoStatementsConstant.MetaDataDBQueries.INSERT.toString();
		try (PreparedStatement pst = connection.prepareStatement(query)) {
			for (int j = 0; j < METADATA; j++) {
				pst.setString(1, UUID.randomUUID().toString());
				pst.setString(2, "meta data " + j);
				successfulInsert = (pst.executeUpdate() > 0) ? true : false;
				if (!successfulInsert) {
					break;
				}
			}
		} catch (SQLException e) {
			Log4jWrapper.get().error("Error populating metadata table.", e);
			throw e;
		}
		return successfulInsert;
	}

	private boolean populateRolesTable(Connection connection) throws SQLException {
		boolean successfulInsert = false;
		String query = DaoStatementsConstant.RoleDBQueries.INSERT.toString();
		try (PreparedStatement pst = connection.prepareStatement(query)) {
			for (int j = 0; j < ROLES; j++) {
				if (j == 2){
					ROLE_USER_ID = UUID.randomUUID().toString();
					pst.setString(1, ROLE_USER_ID);
				} else {
					pst.setString(1, UUID.randomUUID().toString());					
				}
				pst.setString(2, j == 0 ? "admin" : (j == 1 ? "manager" : "user"));
				pst.setString(3, j == 0 ? "admin description" : (j == 1 ? "manager description" : "user description"));
				successfulInsert = (pst.executeUpdate() > 0) ? true : false;
				if (!successfulInsert) {
					break;
				}
			}
		} catch (SQLException e) {
			Log4jWrapper.get().error("Error populating roles table.", e);
			throw e;
		}
		return successfulInsert;
	}

	private boolean populateUserCompetitionsTable(Connection connection) throws SQLException {
		boolean successfulInsert = false;
		String query = DaoStatementsConstant.UserCompetitionsDBQueries.INSERT.toString();
		try (PreparedStatement pst = connection.prepareStatement(query)) {
			for (int j = 0; j < USERCOMPETITIONS; j++) {
				pst.setString(1, UUID.randomUUID().toString());
				pst.setString(2, USER_ID[(int)(Math.random() * USERS)]);
				pst.setString(3, COMPETITION_ID[(int)(Math.random() * COMPETITIONS)]);
				pst.setInt(4, (int)(Math.random() * USERCOMPETITIONS)) ;
				pst.setString(5, AWARD_ID[(int)(Math.random() * AWARDS)]);
				pst.setString(6, "time " + j);
				successfulInsert = (pst.executeUpdate() > 0) ? true : false;
				if (!successfulInsert) {
					break;
				}
			}
		} catch (SQLException e) {
			Log4jWrapper.get().error("Error populating usercompetitions table.", e);
			throw e;
		}
		return successfulInsert;
	}
}
