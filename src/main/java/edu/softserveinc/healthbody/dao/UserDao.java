package edu.softserveinc.healthbody.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import edu.softserveinc.healthbody.constants.DaoConstants;
import edu.softserveinc.healthbody.constants.DaoStatementsConstant.UserDBQueries;
import edu.softserveinc.healthbody.db.ConnectionManager;
import edu.softserveinc.healthbody.entity.User;
import edu.softserveinc.healthbody.exceptions.CloseStatementException;
import edu.softserveinc.healthbody.exceptions.DataBaseReadingException;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.QueryNotFoundException;

public final class UserDao extends AbstractDao<User> {
	private static volatile UserDao instance;

	private UserDao() {
		init();
	}

	public static UserDao getInstance() {
		if (instance == null) {
			synchronized (UserDao.class) {
				if (instance == null) {
					instance = new UserDao();
				}
			}
		}
		return instance;
	}

	@Override
	protected void init() {
		for (UserDBQueries userDBQueries : UserDBQueries.values()) {
			sqlQueries.put(userDBQueries.getDaoQuery(), userDBQueries);
		}
	}

	@Override
	protected String[] getFields(final User entity) {
		List<String> fields = new ArrayList<>();
		fields.add(entity.getIdUser().toString());
		fields.add(entity.getLogin());
		fields.add(entity.getPasswd());
		fields.add(entity.getFirsName());
		fields.add(entity.getLastName());
		fields.add(entity.getMail());
		fields.add(entity.getAge().toString());
		fields.add(entity.getWeight().toString());
		fields.add(entity.getGender());
		fields.add(entity.getHealth());
		fields.add(entity.getAvatar());
		fields.add(entity.getGoogleApi());
		fields.add(entity.getIdRole().toString());
		fields.add(entity.getStatus());
		return (String[]) fields.toArray();
	}

	@Override
	protected User createInstance(final String[] args) {
		return new User(args[0] == null ? UUID.randomUUID().toString() : args[0], 
				args[1] == null ? new String() : args[1],
				args[2] == null ? new String() : args[2], 
				args[3] == null ? new String() : args[3],
				args[4] == null ? new String() : args[4], 
				args[5] == null ? new String() : args[5],
				Integer.parseInt(args[6] == null ? "0" : args[6]), 
				Double.parseDouble(args[7] == null ? "0" : args[7]),
				args[8] == null ? new String() : args[8],
				args[9] == null ? new String() : args[9],
				args[10] == null ? new String() : args[10],
				args[11] == null ? new String() : args[11], 
				args[12] == null ? UUID.randomUUID().toString() : args[12],
				args[13] == null ? "0" : args[13],
				Boolean.parseBoolean(args[14] == null ? "false" : args[14]));
	}

	public User getUserByLogin(final String login)
			throws JDBCDriverException, DataBaseReadingException, QueryNotFoundException {
		return getByField(login, login).get(0);
	}
	
	public User getUserById(final String id) 
			throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException, CloseStatementException {
		return getById(id);
	}

	public boolean createUser(final User user)
			throws JDBCDriverException, QueryNotFoundException, DataBaseReadingException {
		boolean result = false;
		String query = sqlQueries.get(DaoQueries.INSERT).toString();
		if (query == null) {
			throw new QueryNotFoundException(String.format(DaoConstants.QUERY_NOT_FOUND, DaoQueries.INSERT.name()));
		}
		try (PreparedStatement pst = ConnectionManager.getInstance().getConnection().prepareStatement(query)) {
			int i = 1;
			pst.setString(i++, user.getId());
			pst.setString(i++, user.getLogin());
			pst.setString(i++, user.getPasswd());
			pst.setString(i++, user.getFirsName());
			pst.setString(i++, user.getLastName());
			pst.setString(i++, user.getMail());
			pst.setInt(i++, user.getAge());
			pst.setDouble(i++, user.getWeight());
			pst.setString(i++, user.getGender());
			pst.setString(i++, user.getHealth());
			pst.setString(i++, user.getAvatar());
			pst.setString(i++, user.getGoogleApi());
			pst.setString(i++, user.getIdRole());
			pst.setString(i++, user.getStatus());
			pst.setBoolean(i++, user.getIsDisabled());
			result = pst.execute();
		} catch (SQLException e) {
			throw new DataBaseReadingException(DaoConstants.DATABASE_READING_ERROR, e);
		}
		return result;
	}

	public boolean updateUser(final User user) 
			throws DataBaseReadingException, JDBCDriverException, QueryNotFoundException {
		boolean result = false;
		String query = sqlQueries.get(DaoQueries.UPDATE).toString();
		if (query == null) {
			throw new QueryNotFoundException(String.format(DaoConstants.QUERY_NOT_FOUND, DaoQueries.UPDATE.name()));
		}
		try (PreparedStatement pst = ConnectionManager.getInstance().getConnection().prepareStatement(query)) {
			int i = 1;
			pst.setString(i++, user.getLogin());
			pst.setString(i++, user.getPasswd());
			pst.setString(i++, user.getFirsName());
			pst.setString(i++, user.getLastName());
			pst.setString(i++, user.getMail());
			pst.setInt(i++, user.getAge());
			pst.setDouble(i++, user.getWeight());
			pst.setString(i++, user.getGender());
			pst.setString(i++, user.getHealth());
			pst.setString(i++, user.getAvatar());			
			pst.setString(i++, user.getLogin());
			result = pst.execute();
		} catch (SQLException e) {
			throw new DataBaseReadingException(DaoConstants.DATABASE_READING_ERROR, e);
		}
		return result;
	}

	public User getUserByLoginName(final String login)
			throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException {
		return getByFieldName(login);
	}

	public boolean lockUser(final boolean isDisabled, final String login)
			throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException {
		boolean result = false;
		String query = sqlQueries.get(DaoQueries.ISDISABLED).toString();
		if (query == null) {
			throw new QueryNotFoundException(String.format(DaoConstants.QUERY_NOT_FOUND, DaoQueries.ISDISABLED.name()));
		}
		try (PreparedStatement pst = ConnectionManager.getInstance().getConnection().prepareStatement(query)) {
			pst.setBoolean(1, isDisabled);
			pst.setString(2, login);
			result = pst.execute();
		} catch (SQLException e) {
			throw new DataBaseReadingException(DaoConstants.DATABASE_READING_ERROR, e);
		}
		return result;
	}
	
	public boolean deleteUserForTests(final String id) 
			throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException {
		return deleteById(id);
	}
}
