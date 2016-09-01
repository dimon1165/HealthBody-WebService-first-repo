package edu.softserveinc.healthbody.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import edu.softserveinc.healthbody.constants.Constants.GroupCard;
import edu.softserveinc.healthbody.constants.ErrorConstants;
import edu.softserveinc.healthbody.constants.DaoStatementsConstant.GroupDBQueries;
import edu.softserveinc.healthbody.entity.Group;
import edu.softserveinc.healthbody.exceptions.DataBaseReadingException;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.QueryNotFoundException;;

public final class GroupDao extends AbstractDao<Group> {

	private static volatile GroupDao instance;

	private GroupDao() {
		init();
	}

	public static GroupDao getInstance() {
		if (instance == null) {
			synchronized (GroupDao.class) {
				if (instance == null) {
					instance = new GroupDao();
				}
			}
		}
		return instance;
	}

	@Override
	protected void init() {
		for (GroupDBQueries groupDBQueries : GroupDBQueries.values()) {
			sqlQueries.put(groupDBQueries.getDaoQuery(), groupDBQueries);
		}
	}

	@Override
	public Group createInstance(final String[] args) {
		return new Group(args[GroupCard.ID] == null ? UUID.randomUUID().toString() : args[GroupCard.ID], 
				args[GroupCard.NAME] == null ? new String() : args[GroupCard.NAME],
				Integer.parseInt(args[GroupCard.COUNT] == null ? "0" : args[GroupCard.COUNT]), 
				args[GroupCard.DESCRIPTION] == null ? new String() : args[GroupCard.DESCRIPTION],
				args[GroupCard.SCOREGROUP] == null ? new String() : args[GroupCard.SCOREGROUP], 
				args[GroupCard.STATUS] == null ? new String() : args[GroupCard.STATUS]);
	}

	public boolean  createGroup(final Connection connection, final Group group)
			throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException {
		boolean result = false;
		String query = sqlQueries.get(DaoQueries.INSERT).toString();
		if (query == null) {
			throw new QueryNotFoundException(String.format(ErrorConstants.QUERY_NOT_FOUND, DaoQueries.UPDATE.name()));
		}
		try (PreparedStatement pst = connection.prepareStatement(query)) {
			int i = 1;
			pst.setString(i++, group.getId());
			pst.setString(i++, group.getName());
			pst.setInt(i++, group.getCount());
			pst.setString(i++, group.getDescription());
			pst.setString(i++, group.getScoreGroup());
			pst.setString(i++, group.getStatus());
			result = pst.execute();
		} catch (SQLException e) {
			throw new DataBaseReadingException(ErrorConstants.DATABASE_READING_ERROR, e);
		}
		return result;
	}
	public boolean editGroup(final Connection connection, final Group group)
			throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException {
		boolean result = false;
		String query = sqlQueries.get(DaoQueries.UPDATE).toString();
		if (query == null) {
			throw new QueryNotFoundException(String.format(ErrorConstants.QUERY_NOT_FOUND, DaoQueries.UPDATE.name()));
		}
		try (PreparedStatement pst = connection.prepareStatement(query)) {
			int i = 1;
			pst.setString(i++, group.getName());
			pst.setInt(i++, group.getCount());
			pst.setString(i++, group.getDescription());
			pst.setString(i++, group.getScoreGroup());
			pst.setString(i++, group.getId());
			result = pst.execute();
		} catch (SQLException e) {
			throw new DataBaseReadingException(ErrorConstants.DATABASE_READING_ERROR, e);
		}
		return result;
	}

	public boolean deleteGroup(final Connection connection, final Group group)
			throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException {
		return delete(connection, group);
	}

	public List<Group> getAll(final Connection connection, final int partNumber, final int partSize) 
			throws QueryNotFoundException, JDBCDriverException,	DataBaseReadingException {
		List<Group> result = new ArrayList<>();
		String query = sqlQueries.get(DaoQueries.GET_ALL).toString();
		if (query == null) {
			throw new QueryNotFoundException(String.format(ErrorConstants.QUERY_NOT_FOUND, GroupDBQueries.GET_ALL.name()));
		}
		if ((partNumber >= 0) && (partSize > 0)) {
			query = query.substring(0, query.lastIndexOf(";")) + SQL_LIMIT;
		}
		try (PreparedStatement pst = createPreparedStatement(connection, query, partNumber, partSize);
			ResultSet resultSet = pst.executeQuery()) {
			String[] queryResult = new String[resultSet.getMetaData().getColumnCount()];
			while (resultSet.next()) {
				result.add(createInstance(getQueryResultArr(queryResult, resultSet)));
			}
		} catch (SQLException e) {
			throw new DataBaseReadingException(ErrorConstants.DATABASE_READING_ERROR, e);
		}
		return result;
	}

	public Group getGroupByName(final Connection connection, final String name)
			throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException {
		return getByFieldName(connection, name);
	}
	
	public Group getGroupById(final Connection connection, final String id)
		throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException {
		return getById(connection, id);
	}
	//methods for try-with-resources
	private PreparedStatement createPreparedStatement(final Connection connection, final String query, final int partNumber, final int partSize) 
			throws SQLException, JDBCDriverException {
		PreparedStatement pst = connection.prepareStatement(query);
			if ((partNumber >= 0) && (partSize > 0)) {
				pst.setInt(1, (partNumber - 1) * partSize);
				pst.setInt(2, partSize);
			}
		return pst;
	}
}
