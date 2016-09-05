package edu.softserveinc.healthbody.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import edu.softserveinc.healthbody.constants.Constants.GroupCompetitionsCard;
import edu.softserveinc.healthbody.constants.DaoStatementsConstant.GroupCompetitionsDBQueries;
import edu.softserveinc.healthbody.constants.ErrorConstants;
import edu.softserveinc.healthbody.entity.CompetitionsView;
import edu.softserveinc.healthbody.entity.Group;
import edu.softserveinc.healthbody.entity.GroupCompetitions;
import edu.softserveinc.healthbody.exceptions.CloseStatementException;
import edu.softserveinc.healthbody.exceptions.DataBaseReadingException;
import edu.softserveinc.healthbody.exceptions.EmptyResultSetException;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.QueryNotFoundException;

public final class GroupCompetitionsDao extends AbstractDao<GroupCompetitions> {
	
	private static volatile GroupCompetitionsDao instance;

	private GroupCompetitionsDao() {
		init();
	}

	public static GroupCompetitionsDao getInstance() {
		if (instance == null) {
			synchronized (GroupCompetitionsDao.class) {
				if (instance == null) {
					instance = new GroupCompetitionsDao();
				}
			}
		}
		return instance;
	}

	@Override
	protected void init() {
		for (GroupCompetitionsDBQueries groupCompetitionsDBQueries : GroupCompetitionsDBQueries.values()) {
			sqlQueries.put(groupCompetitionsDBQueries.getDaoQuery(), groupCompetitionsDBQueries);
		}
	}
	
	@Override
	public GroupCompetitions createInstance(final String[] args) {
		return new GroupCompetitions(
				args[GroupCompetitionsCard.ID] == null ? UUID.randomUUID().toString() : args[GroupCompetitionsCard.ID], 
				args[GroupCompetitionsCard.IDGROUP] == null ? UUID.randomUUID().toString() : args[GroupCompetitionsCard.IDGROUP],
				args[GroupCompetitionsCard.IDCOMPETITION] == null ? UUID.randomUUID().toString() : args[GroupCompetitionsCard.IDCOMPETITION]);
	}
	
	public boolean createGroupCompetition (final Connection connection, final Group group, final CompetitionsView competitionview)
			throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException {
		boolean result = false;
		String query = sqlQueries.get(DaoQueries.INSERT).toString();
			if (query == null) {
				throw new QueryNotFoundException(String.format(ErrorConstants.QUERY_NOT_FOUND, DaoQueries.INSERT.name()));
			}
			try (PreparedStatement pst = connection.prepareStatement(query)) {
				int i = 1;
				pst.setString(i++, UUID.randomUUID().toString());
				pst.setString(i++, group.getIdGroup().toString());
				pst.setString(i++, competitionview.getIdCompetition().toString());
				result = pst.execute();
			} catch (SQLException e) {
					throw new DataBaseReadingException(ErrorConstants.DATABASE_READING_ERROR, e);
			}
		return result;
	}
	
	public boolean deleteByGroupCompetitionId(Connection connection, String id)
			throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException {
		boolean result = false;
		String query = sqlQueries.get(DaoQueries.DELETE_BY_ID).toString();
		if (query == null) {
			throw new QueryNotFoundException(String.format(ErrorConstants.QUERY_NOT_FOUND, DaoQueries.DELETE_BY_ID.name()));
		}
		try (PreparedStatement pst = connection.prepareStatement(query)) {
			pst.setString(1, id);
			result = pst.execute();
		} catch (SQLException e) {
			throw new DataBaseReadingException(ErrorConstants.DATABASE_READING_ERROR, e);
		}
		return result;
	}
	
	public List<GroupCompetitions> getGroupCompetitionsByGroupId(final Connection connection, final String id) 
			throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException, CloseStatementException, EmptyResultSetException {		
		return getAllbyId(connection, id);
	}
		
	public List<GroupCompetitions> view(final Connection connection) throws JDBCDriverException, DataBaseReadingException {
		return getAll(connection);
	}

}
