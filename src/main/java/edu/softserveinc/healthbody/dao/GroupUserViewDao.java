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
import edu.softserveinc.healthbody.entity.GroupUserView;
import edu.softserveinc.healthbody.exceptions.DataBaseReadingException;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.QueryNotFoundException;

public class GroupUserViewDao extends AbstractDao<GroupUserView> {

	private static volatile GroupUserViewDao instance;

	private GroupUserViewDao() {
		init();
	}

	public static GroupUserViewDao getInstance() {
		if (instance == null) {
			synchronized (GroupDao.class) {
				if (instance == null) {
					instance = new GroupUserViewDao();
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
	public GroupUserView createInstance(final String[] args) {
		return new GroupUserView(args[0] == null ? UUID.randomUUID().toString() : args[0], 
				args[1] == null ? new String() : args[1],
				Integer.parseInt(args[2] == null ? "0" : args[2]), 
				args[3] == null ? new String() : args[3],
				args[4] == null ? new String() : args[4],
				args[5] == null ? new String() : args[5],		
				args[6] == null ? new String() : args[6],
				args[7] == null ? new String() : args[7],
				args[8] == null ? new String() : args[8]);
		
	}
	

	@Override
	protected String[] getFields(final GroupUserView entity) {
		List<String> fields = new ArrayList<>();
		fields.add(entity.getIdGroup());
		fields.add(entity.getName());
		fields.add(entity.getCount().toString());
		fields.add(entity.getDescription());
		fields.add(entity.getScoreGroup());
		fields.add(entity.getStatus());
		fields.add(entity.getUsers());
		fields.add(entity.getFirstname());
		fields.add(entity.getLastname());
		return (String[]) fields.toArray();
	}
	
public List<GroupUserView> getAllGroupsParticiapnts(final int partNumber, final int partSize) 
			throws QueryNotFoundException, JDBCDriverException,	DataBaseReadingException {
		List<GroupUserView> result = new ArrayList<>();
		String query = sqlQueries.get(DaoQueries.GET_ALL_GROUPS_PARTICIPANTS).toString();
		if (query == null) {
			throw new QueryNotFoundException(String.format(DaoConstants.QUERY_NOT_FOUND, GroupDBQueries.GET_ALL_GROUPS_PARTICIPANTS.name()));
		}
		if ((partNumber >= 0) && (partSize > 0)) {
			query = query.substring(0, query.lastIndexOf(";")) + SQL_LIMIT;
		}
		try (PreparedStatement pst = createPreparedStatement(query, partNumber, partSize);
			ResultSet resultSet = pst.executeQuery()) {
			String[] queryResult = new String[resultSet.getMetaData().getColumnCount()];
			while (resultSet.next()) {
				result.add(createInstance(getQueryResultArr(queryResult, resultSet)));
			}
		} catch (SQLException e) {
			throw new DataBaseReadingException(DaoConstants.DATABASE_READING_ERROR, e);
		}
		return result;
	}

	private PreparedStatement createPreparedStatement(final String query, final int partNumber, final int partSize)
			throws SQLException, JDBCDriverException {
		PreparedStatement pst = ConnectionManager.getInstance().getConnection().prepareStatement(query);
		if ((partNumber >= 0) && (partSize > 0)) {
			pst.setInt(1, (partNumber - 1) * partSize);
			pst.setInt(2, partSize);
		}
		return pst;
}
}
