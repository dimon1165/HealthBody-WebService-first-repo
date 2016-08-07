package edu.softserveinc.healthbody.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import edu.softserveinc.healthbody.constants.Constant.UsersViewCard;
import edu.softserveinc.healthbody.constants.DaoConstants;
import edu.softserveinc.healthbody.constants.DaoStatementsConstant.UsersViewQueries;
import edu.softserveinc.healthbody.db.ConnectionManager;
import edu.softserveinc.healthbody.entity.UsersView;
import edu.softserveinc.healthbody.exceptions.DataBaseReadingException;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.QueryNotFoundException;

public final class UsersViewDao extends AbstractDaoRead<UsersView> {

	private static volatile UsersViewDao instance;

	private UsersViewDao() {
		init();
	}

	public static UsersViewDao getInstance() {
		if (instance == null) {
			synchronized (UsersViewDao.class) {
				if (instance == null) {
					instance = new UsersViewDao();
				}
			}
		}
		return instance;
	}

	@Override
	protected void init() {
		for (UsersViewQueries usersViewQueries : UsersViewQueries.values()) {
			sqlQueries.put(usersViewQueries.getDaoQuery(), usersViewQueries);
		}
	}

	@Override
	protected UsersView createInstance(final String[] args) {
		return new UsersView(args[UsersViewCard.ID] == null ? UUID.randomUUID().toString() : args[UsersViewCard.ID],
							 args[UsersViewCard.FIRSTNAME] == null ? new String() : args[UsersViewCard.FIRSTNAME],
							 args[UsersViewCard.LASTNAME] == null ? new String() : args[UsersViewCard.LASTNAME],
							 args[UsersViewCard.LOGIN] == null ? new String() : args[UsersViewCard.LOGIN],
							 args[UsersViewCard.PASSWORD] == null ? new String() : args[UsersViewCard.PASSWORD],
							 args[UsersViewCard.MAIL] == null ? new String() : args[UsersViewCard.MAIL],
							 Integer.parseInt(args[UsersViewCard.AGE] == null ? "0" : args[UsersViewCard.AGE]),
							 Double.parseDouble(args[UsersViewCard.WEIGHT] == null ? "0" : args[UsersViewCard.WEIGHT]),
							 args[UsersViewCard.GENDER] == null ? new String() : args[UsersViewCard.GENDER],
							 args[UsersViewCard.AVATAR] == null ? new String() : args[UsersViewCard.AVATAR],
							 args[UsersViewCard.ROLENAME] == null ? new String() : args[UsersViewCard.ROLENAME],
							 args[UsersViewCard.HEALTH] == null ? new String() : args[UsersViewCard.HEALTH],
							 args[UsersViewCard.GOOGLEAPI] == null ? "0" : args[UsersViewCard.GOOGLEAPI],
							 args[UsersViewCard.STATUS] == null ? "0" : args[UsersViewCard.STATUS],
							 Integer.parseInt(args[UsersViewCard.SCORE] == null ? "0" : args[UsersViewCard.SCORE]));
	}

	public List<UsersView> getAllUsersView(final int partNumber, final int partSize)
			throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException {
		List<UsersView> result = new ArrayList<>();
		String query = sqlQueries.get(UsersViewQueries.GET_ALL).toString();
		if (query == null) {
			throw new QueryNotFoundException(String.format(DaoConstants.QUERY_NOT_FOUND, UsersViewQueries.GET_ALL.name()));
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
	
	//methods for try-with-resources
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