package edu.softserveinc.healthbody.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import edu.softserveinc.healthbody.constants.DaoConstants;
import edu.softserveinc.healthbody.constants.DaoStatementsConstant.UsersViewQueries;
import edu.softserveinc.healthbody.db.ConnectionManager;
import edu.softserveinc.healthbody.entity.UsersView;
import edu.softserveinc.healthbody.exceptions.CloseStatementException;
import edu.softserveinc.healthbody.exceptions.DataBaseReadingException;
import edu.softserveinc.healthbody.exceptions.EmptyResultSetException;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.QueryNotFoundException;

public class UsersViewDao extends AbstractDaoRead<UsersView> {

	private static volatile UsersViewDao instance = null;

	public UsersViewDao() {
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

	protected void init() {
		for (UsersViewQueries usersViewQueries : UsersViewQueries.values()) {
			sqlQueries.put(usersViewQueries.getDaoQuery(), usersViewQueries);
		}
	}

	@Override
	protected UsersView createInstance(String[] args) {
		return new UsersView(args[0] == null ? UUID.randomUUID().toString() : args[0],
							 args[1] == null ? new String() : args[1], args[2] == null ? new String() : args[2],
							 args[3] == null ? new String() : args[3], args[4] == null ? new String() : args[4],
							 args[5] == null ? new String() : args[5], Integer.parseInt(args[6] == null ? "0" : args[6]),
							 Double.parseDouble(args[7] == null ? "0" : args[7]), args[8] == null ? new String() : args[8],
							 args[9] == null ? new String() : args[9], args[10] == null ? new String() : args[10],
							 args[11] == null ? new String() : args[11], args[12] == null ? "0" : args[12],
							 args[13] == null ? "0" : args[13], Integer.parseInt(args[14] == null ? "0" : args[14]));
	}

	@Override
	protected String[] getFields(UsersView entity) {
		List<String> fields = new ArrayList<>();
		fields.add(entity.getIdUser());
		fields.add(entity.getFirsName());
		fields.add(entity.getLastName());
		fields.add(entity.getLogin());
		fields.add(entity.getPasswd());
		fields.add(entity.getMail());
		fields.add(entity.getAge().toString());
		fields.add(entity.getWeight().toString());
		fields.add(entity.getGender());
		fields.add(entity.getAvatar());
		fields.add(entity.getRoleName());
		fields.add(entity.getHealth());
		fields.add(entity.getGoogleApi());
		fields.add(entity.getStatus());
		fields.add(entity.getScore().toString());
		return (String[]) fields.toArray();
	}

	public List<UsersView> getAllUsersView(int partNumber, int partSize) throws QueryNotFoundException,
			JDBCDriverException, DataBaseReadingException, EmptyResultSetException, CloseStatementException {
		List<UsersView> result = new ArrayList<>();
		String query = sqlQueries.get(UsersViewQueries.GET_ALL).toString();
		if (query == null) {
			throw new QueryNotFoundException(String.format(DaoConstants.QUERY_NOT_FOUND, UsersViewQueries.GET_ALL.name()));
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