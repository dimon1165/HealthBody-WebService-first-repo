package edu.softserveinc.healthbody.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import edu.softserveinc.healthbody.constants.DaoConstants;
import edu.softserveinc.healthbody.constants.DaoStatementsConstant.GroupDBQueries;
import edu.softserveinc.healthbody.db.ConnectionManager;
import edu.softserveinc.healthbody.entity.Group;
import edu.softserveinc.healthbody.exceptions.CloseStatementException;
import edu.softserveinc.healthbody.exceptions.DataBaseReadingException;
import edu.softserveinc.healthbody.exceptions.EmptyResultSetException;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.QueryNotFoundException;;

public final class GroupDao extends AbstractDao<Group> {

	private static volatile GroupDao instance = null;

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

	protected void init() {
		for (GroupDBQueries groupDBQueries : GroupDBQueries.values()) {
			sqlQueries.put(groupDBQueries.getDaoQuery(), groupDBQueries);
		}
	}

	@Override
	public Group createInstance(String[] args) {
		return new Group(args[0] == null ? UUID.randomUUID().toString() : args[0], 
				args[1] == null ? new String() : args[1],
				Integer.parseInt(args[2] == null ? "0" : args[2]), 
				args[3] == null ? new String() : args[3],
				args[4] == null ? new String() : args[4], 
				args[5] == null ? new String() : args[5]);
	}

	@Override
	protected String[] getFields(Group entity) {
		List<String> fields = new ArrayList<>();
		fields.add(entity.getIdGroup());
		fields.add(entity.getName());
		fields.add(entity.getCount().toString());
		fields.add(entity.getDescription());
		fields.add(entity.getScoreGroup());
		fields.add(entity.getStatus());
		return (String[]) fields.toArray();
	}

	public boolean createGroup(Group group)
			throws JDBCDriverException, QueryNotFoundException, DataBaseReadingException {
		return insert(group);
	}

	public boolean editGroup(Group group) throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException {
		boolean result = false;
		String query = sqlQueries.get(DaoQueries.UPDATE).toString();
		if (query == null) {
			throw new QueryNotFoundException(String.format(DaoConstants.QUERY_NOT_FOUND, DaoQueries.UPDATE.name()));
		}
		try (PreparedStatement pst = ConnectionManager.getInstance().getConnection().prepareStatement(query)) {
			int i = 0;
			pst.setString(i++, group.getId());
			pst.setInt(i++, group.getCount());
			pst.setString(i++, group.getDescription());
			pst.setString(i++, group.getScoreGroup());
			pst.setString(i++, group.getName());
			result = pst.execute();
		} catch (SQLException e) {
			throw new DataBaseReadingException(DaoConstants.DATABASE_READING_ERROR, e);
		}
		return result;
	}

	public boolean deleteGroup(Group group)
			throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException {
		return delete(group);
	}

	public List<Group> getAll(int partNumber, int partSize) throws QueryNotFoundException, JDBCDriverException,
			DataBaseReadingException, EmptyResultSetException, CloseStatementException {
		List<Group> result = new ArrayList<>();
		String query = sqlQueries.get(DaoQueries.GET_ALL).toString();
		if (query == null) {
			throw new QueryNotFoundException(String.format(DaoConstants.QUERY_NOT_FOUND, GroupDBQueries.GET_ALL.name()));
		}
		if ((partNumber >= 0) && (partSize > 0)) {
			query = query.substring(0, query.lastIndexOf(";")) + SQL_LIMIT;
		}
		try (PreparedStatement pst = createPreparedStatement(query, partNumber, partSize);
			ResultSet resultSet = pst.executeQuery()){
			String[] queryResult = new String[resultSet.getMetaData().getColumnCount()];
			while (resultSet.next()) {
				result.add(createInstance(getQueryResultArr(queryResult, resultSet)));
			}
		} catch (SQLException e) {
			throw new DataBaseReadingException(DaoConstants.DATABASE_READING_ERROR, e);
		}
		return result;
	}

	public Group getGroupByName(String name)
			throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException, CloseStatementException {
		return getByFieldName(name);
	}
	
	//methods for try-with-resources
	private PreparedStatement createPreparedStatement(String query, int partNumber, int partSize) throws SQLException, JDBCDriverException {
		PreparedStatement pst = ConnectionManager.getInstance().getConnection().prepareStatement(query);
			if ((partNumber >= 0) && (partSize > 0)) {
				pst.setInt(1, (partNumber - 1) * partSize);
				pst.setInt(2, partSize);
			}
			return pst;
	}

}
