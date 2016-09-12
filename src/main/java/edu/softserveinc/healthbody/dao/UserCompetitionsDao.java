package edu.softserveinc.healthbody.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import edu.softserveinc.healthbody.constants.Constants.UserCompetitionsCard;
import edu.softserveinc.healthbody.constants.DaoStatementsConstant.UserCompetitionsDBQueries;
import edu.softserveinc.healthbody.constants.ErrorConstants;
import edu.softserveinc.healthbody.entity.CompetitionsView;
import edu.softserveinc.healthbody.entity.User;
import edu.softserveinc.healthbody.entity.UserCompetitions;
import edu.softserveinc.healthbody.exceptions.CloseStatementException;
import edu.softserveinc.healthbody.exceptions.DataBaseReadingException;
import edu.softserveinc.healthbody.exceptions.EmptyResultSetException;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.QueryNotFoundException;

public final class UserCompetitionsDao extends AbstractDao<UserCompetitions> {
	
	private static volatile UserCompetitionsDao instance;
	
	private UserCompetitionsDao() {
		init();
	}
	
	public static UserCompetitionsDao getInstance() {
		if (instance == null) {
			synchronized (UserCompetitionsDao.class) {
				if (instance == null) {
					instance = new UserCompetitionsDao();
				}
			}
		}
		return instance;
	}
	
	@Override
	protected void init() {
		for (UserCompetitionsDBQueries userCompetitionsDBQueries : UserCompetitionsDBQueries.values()) {
			sqlQueries.put(userCompetitionsDBQueries.getDaoQuery(), userCompetitionsDBQueries);
		}
	}

	@Override
	public UserCompetitions createInstance(final String[] args) {
		return new UserCompetitions(
				args[UserCompetitionsCard.ID] == null ? UUID.randomUUID().toString() : args[UserCompetitionsCard.ID],
				args[UserCompetitionsCard.IDUSER] == null ? UUID.randomUUID().toString() : args[UserCompetitionsCard.IDUSER],
				args[UserCompetitionsCard.IDCOMPETITION] == null ? UUID.randomUUID().toString() : args[UserCompetitionsCard.IDCOMPETITION],
				Integer.parseInt(args[UserCompetitionsCard.USERSCORE] == null ? "0" : args[UserCompetitionsCard.USERSCORE]),
				args[UserCompetitionsCard.IDAWARD] == null ? UUID.randomUUID().toString() : args[UserCompetitionsCard.IDAWARD],
				args[UserCompetitionsCard.TIMERECEIVED] == null ? new String() : args[UserCompetitionsCard.TIMERECEIVED]);
	}
	
	public boolean createUserCompetition (final Connection connection, final User user, final CompetitionsView competitionview)
			throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException {
		boolean result = false;
		String query = sqlQueries.get(DaoQueries.INSERT).toString();
			if (query == null) {
				throw new QueryNotFoundException(String.format(ErrorConstants.QUERY_NOT_FOUND, DaoQueries.INSERT.name()));
			}
			try (PreparedStatement pst = connection.prepareStatement(query)) {
				int i = 1;
				pst.setString(i++, UUID.randomUUID().toString());
				pst.setString(i++, user.getId().toString());
				pst.setString(i++, competitionview.getIdCompetition().toString());
				pst.setInt(i++, 0);
				pst.setString(i++, null);
				pst.setString(i++, null);
				result = pst.execute();
			} catch (SQLException e) {
					throw new DataBaseReadingException(ErrorConstants.DATABASE_READING_ERROR, e);
			}
		return result;
	}
	public boolean updateUserCompetition (final Connection connection, final UserCompetitions userCompetition)
			throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException {
		boolean result = false;
		String query = sqlQueries.get(DaoQueries.UPDATE).toString();
			if (query == null) {
				throw new QueryNotFoundException(String.format(ErrorConstants.QUERY_NOT_FOUND, DaoQueries.UPDATE.name()));
			}
			try (PreparedStatement pst = connection.prepareStatement(query)) {
				int i = 1;
				pst.setInt(i++, userCompetition.getUserScore());
				pst.setString(i++, userCompetition.getIdAwards());
				pst.setString(i++, userCompetition.getTimeReceived());
				pst.setString(i++, userCompetition.getIdUserCompetition());
				result = pst.execute();
			} catch (SQLException e) {
					throw new DataBaseReadingException(ErrorConstants.DATABASE_READING_ERROR, e);
			}
		return result;
	}

	public List<UserCompetitions> viewAll(final Connection connection) 
			throws JDBCDriverException, DataBaseReadingException, EmptyResultSetException, CloseStatementException {		
		return getAll(connection);
	}
	
	public List<UserCompetitions> getUserCompetitionsByUserId(final Connection connection, final String id) 
			throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException, CloseStatementException, EmptyResultSetException {		
		return getAllbyId(connection, id);
	}

	public boolean deleteByUserId(final Connection connection, final String id) throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException {
		return deleteById(connection, id);
	}

	public boolean deleteUserFromCompetition(Connection connection, String userId, String competitionId) throws DataBaseReadingException, QueryNotFoundException, JDBCDriverException, EmptyResultSetException, CloseStatementException {
		boolean result = false;
		String query = sqlQueries.get(DaoQueries.DELETE_USER_FROM_COMPETITION).toString();
			if (query == null) {
				throw new QueryNotFoundException(String.format(ErrorConstants.QUERY_NOT_FOUND, DaoQueries.DELETE_USER_FROM_COMPETITION.name()));
			}
			UserCompetitions userCompetition = getUserCompetitionByIds(connection, userId, competitionId);
			try (PreparedStatement pst = connection.prepareStatement(query)) {
				pst.setString(1, userCompetition.getId());				
				result = pst.execute();
			} catch (SQLException e) {
					throw new DataBaseReadingException(ErrorConstants.DATABASE_READING_ERROR, e);
			}
		return result;		
	}
	
	public UserCompetitions getUserCompetitionByIds(Connection connection, String userId, String competitionId) throws JDBCDriverException, DataBaseReadingException, EmptyResultSetException, CloseStatementException{
		UserCompetitions userCompetition = null;
		List<UserCompetitions> userCompetitions = viewAll(connection);
		for (UserCompetitions userComp:userCompetitions){
			if (userComp.getIdUser().equals(userId) && userComp.getIdCompetition().equals(competitionId)){
				userCompetition = userComp;
			}
		}
		return userCompetition;
	}
		
	public boolean deleteByUserCompetitionId(Connection connection, String id)
			throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException {
		boolean result = false;
		String query = sqlQueries.get(DaoQueries.DELETE_BY_ID_USER_COMPETITION).toString();
		if (query == null) {
			throw new QueryNotFoundException(String.format(ErrorConstants.QUERY_NOT_FOUND, DaoQueries.DELETE_BY_ID_USER_COMPETITION.name()));
		}
		try (PreparedStatement pst = connection.prepareStatement(query)) {
			pst.setString(1, id);
			result = pst.execute();
		} catch (SQLException e) {
			throw new DataBaseReadingException(ErrorConstants.DATABASE_READING_ERROR, e);
		}
		return result;

	}
}
