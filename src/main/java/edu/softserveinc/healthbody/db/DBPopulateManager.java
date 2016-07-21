package edu.softserveinc.healthbody.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

import edu.softserveinc.healthbody.constants.DaoStatementsConstant;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.log.Log4jWrapper;

public class DBPopulateManager {

    private static volatile DBPopulateManager instance = null;
    private static int users = 10;
    private static int competitions = 20;
    private Connection con = null;

    private DBPopulateManager() {
        try {
            con = ConnectionManager.getInstance().getConnection();
        } catch (JDBCDriverException e) {
            Log4jWrapper.get().error("Error in DBPopulateManager constructor while getting connetion." + e);
        }
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

    public boolean populateUsersTable() {
		boolean successfulInsert = false;
		String query = DaoStatementsConstant.UserDBQueries.INSERT.toString();
		

		try (PreparedStatement pst = con.prepareStatement(query)) {
			for (int j = 1; j <= users; j++) {
				pst.setString(1, "Login " + j);
				pst.setString(2, "password " + j);
				pst.setString(3, "Name of " + j + " user");
				pst.setString(4, "LastName of " + j + " user");
				pst.setString(5, "SomeMail" + j + "@gmail.com");
				pst.setInt(6, 25 + j);
				pst.setDouble(7, 55.6 + j);
				pst.setString(8, (j % 2 == 0) ? "m" : "w");
				pst.setString(9, "health " + j);
				pst.setString(10, "urlavatar " + j);
				pst.setString(11, "googleApi " + j);
				pst.setInt(12, 3);
				pst.setString(13, "active " + j);
				pst.setBoolean(14, false);
				successfulInsert = (pst.executeUpdate() > 0) ? true : false;
				if (!successfulInsert){
					break;
				}
			}
		} catch (SQLException e) {
			Log4jWrapper.get().error("Error populating users table." + e);
		}
		return successfulInsert;
	}

    public boolean populateGroupsTable() {
		boolean successfulInsert = false;
		String query = DaoStatementsConstant.GroupDBQueries.INSERT.toString();

		try (PreparedStatement pst = con.prepareStatement(query)) {
			for (int j = 1; j <= 3; j++){				
				pst.setString(1, "Name group number "+j);
				pst.setInt(2, 5+j*5);
				pst.setString(3, "Description of group "+j);
				pst.setString(4, "1"+j);	
				pst.setString(5, "active");	
				successfulInsert = (pst.executeUpdate() > 0) ? true : false;
				if (!successfulInsert){
					break;
				}
			}
		} catch (SQLException e) {
			Log4jWrapper.get().error("Error populating groups table." + e);
		}
		return successfulInsert;
	}

    public boolean populateUserGroupsTable() {
		boolean successfulInsert = false;
		String query = DaoStatementsConstant.UserGroupQueries.INSERT.toString();

		try (PreparedStatement pst = con.prepareStatement(query)) {
			for (int j = 1; j <= 15; j++) {
				pst.setInt(1, (j % 10 == 0) ? 10 : j % 10);
				pst.setInt(2, (j <= 8) ? 1 : (j <= 12 ? 3 : 2));
				successfulInsert = (pst.executeUpdate() > 0) ? true : false;
				if (!successfulInsert){
					break;
				}
			}
		} catch (SQLException e) {
			Log4jWrapper.get().error("Error populating usergroups table." + e);
		}
		return successfulInsert;
	}

    public boolean populateAwardsTable() {
		boolean successfulInsert = false;
		String query = DaoStatementsConstant.AwardDBQueries.INSERT.toString();


		try (PreparedStatement pst = con.prepareStatement(query)) {
			for (int j = 1; j <= 4; j++) {
				pst.setString(1, "Name award " + j);
				successfulInsert = (pst.executeUpdate() > 0) ? true : false;
				if (!successfulInsert){
					break;
				}
			}
		} catch (SQLException e) {
			Log4jWrapper.get().error("Error populating awards table." + e);
		}
		return successfulInsert;
	}

    public boolean populateCompetitionsTable() {
		boolean successfulInsert = false;
		String query = DaoStatementsConstant.CompetitionDBQueries.INSERT.toString();


		try (PreparedStatement pst = con.prepareStatement(query)) {
			for (int j = 1; j <= competitions; j++) {
				pst.setString(1, "Name competition " + j);
				pst.setString(2, "Description of competition " + j);
				Date startDate = new Date(System.currentTimeMillis() + 
						(long)(System.currentTimeMillis()/2 == 0 ? 1 : -1) * new Random().nextInt(10) * 24 * 60 * 60 * 1000);
				pst.setDate(3, startDate);
				Date endDate = new Date(startDate.getTime() + (long)(new Random().nextInt(20)) * 24 * 60 * 60 * 1000);
				pst.setDate(4, endDate);
				pst.setInt(5, (int) (Math.random() * 2 + 1));
				successfulInsert = (pst.executeUpdate() > 0) ? true : false;
				if (!successfulInsert){
					break;
				}
			}
		} catch (SQLException e) {
			Log4jWrapper.get().error("Error populating competitions table." + e);
		}
		return successfulInsert;
	}

    public boolean populateCriteriaTable() {
		boolean successfulInsert = false;
		String query = DaoStatementsConstant.CriteriaDBQueries.INSERT.toString();

		try (PreparedStatement pst = con.prepareStatement(query)) {
			for (int j = 1; j <= 4; j++) {
				pst.setString(1, "Name criteria " + j);
				pst.setDouble(2, 4.5 + j);
				pst.setString(3, "get google " + j);
				successfulInsert = (pst.executeUpdate() > 0) ? true : false;
				if (!successfulInsert){
					break;
				}
			}
		} catch (SQLException e) {
			Log4jWrapper.get().error("Error populating criteria table." + e);
		}
		return successfulInsert;
	}

    public boolean populateGroupCompetitionsTable() {
		boolean successfulInsert = false;
		String query = DaoStatementsConstant.GroupCompetitionsDBQueries.INSERT.toString();

		try (PreparedStatement pst = con.prepareStatement(query)) {
			for (int j = 1; j <= 5; j++) {
				pst.setInt(1, (j % 2 == 0) ? 1 : (j % 3 == 0 ? 3 : 2));
				pst.setInt(2, (j % 2 == 0) ? 2 : (j % 3 == 0 ? 1 : 3));
				successfulInsert = (pst.executeUpdate() > 0) ? true : false;
				if (!successfulInsert){
					break;
				}
			}
		} catch (SQLException e) {
			Log4jWrapper.get().error("Error populating groupcompetitions table." + e);
		}
		return successfulInsert;
	}

    public boolean populateMetaDataTable() {
		boolean successfulInsert = false;
		String query = DaoStatementsConstant.MetaDataDBQueries.INSERT.toString();

		try (PreparedStatement pst = con.prepareStatement(query)) {
			for (int j = 1; j <= 8; j++) {
				pst.setString(1, "meta data " + j);
				successfulInsert = (pst.executeUpdate() > 0) ? true : false;
				if (!successfulInsert){
					break;
				}
			}
		} catch (SQLException e) {
			Log4jWrapper.get().error("Error populating metadata table." + e);
		}
		return successfulInsert;
	}

    public boolean populateRolesTable() {
		boolean successfulInsert = false;
		String query = DaoStatementsConstant.RoleDBQueries.INSERT.toString();


		try (PreparedStatement pst = con.prepareStatement(query)) {
			for (int j = 1; j <= 3; j++) {
				pst.setString(1, j == 1 ? "admin" : (j == 2 ? "manager" : "user"));
				pst.setString(2, j == 1 ? "admin description" : (j == 2 ? "manager description" : "user description"));
				successfulInsert = (pst.executeUpdate() > 0) ? true : false;
				if (!successfulInsert){
					break;
				}
			}
		} catch (SQLException e) {
			Log4jWrapper.get().error("Error populating roles table." + e);
		}
		return successfulInsert;
	}

    public boolean populateUserCompetitionsTable() {
		boolean successfulInsert = false;
		String query = DaoStatementsConstant.UserCompetitionsDBQueries.INSERT.toString();

		try (PreparedStatement pst = con.prepareStatement(query)) {
			for (int j = 1; j <= 50; j++) {
				pst.setInt(1, new Random().nextInt(users) + 1);
				pst.setInt(2, new Random().nextInt(competitions) + 1);
				pst.setInt(3, (j % 4 == 0) ? 4 : (j % 2 == 0 ? 3 : (j % 3 == 0 ? 1 : 3)));
				pst.setInt(4, (int) (Math.random() * 10 + 1));
				pst.setString(5, "time " + j);
				successfulInsert = (pst.executeUpdate() > 0) ? true : false;
				if (!successfulInsert){
					break;
				}
			}
		} catch (SQLException e) {
			Log4jWrapper.get().error("Error populating usercompetitions table." + e);
		}
		return successfulInsert;
	}

    public boolean deleteAllFromTables() throws SQLException, JDBCDriverException {
		boolean result = false;
		ConnectionManager.getInstance().beginTransaction();
		String query = "drop TABLE if exists usergroups, groupcompetitions, usercompetitions, users, " 
		+ "roles, groups, competitions, awards, criteria, metadata;";
		try (PreparedStatement pst = con.prepareStatement(query)) {
			result = pst.execute();
			ConnectionManager.getInstance().commitTransaction();
		} catch (SQLException e) {
			ConnectionManager.getInstance().rollbackTransaction();
			Log4jWrapper.get().error("Error trancating database tables." + e);
		}
		return result;
	}
}
