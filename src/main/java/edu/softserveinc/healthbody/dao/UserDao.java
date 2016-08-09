package edu.softserveinc.healthbody.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

import edu.softserveinc.healthbody.constants.Constant.UserCard;
import edu.softserveinc.healthbody.constants.DaoConstants;
import edu.softserveinc.healthbody.constants.DaoStatementsConstant.UserDBQueries;
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
	protected User createInstance(final String[] args) {
		return new User(args[UserCard.ID] == null ? UUID.randomUUID().toString() : args[UserCard.ID], 
				args[UserCard.LOGIN] == null ? new String() : args[UserCard.LOGIN],
				args[UserCard.PASSWORD] == null ? new String() : args[UserCard.PASSWORD], 
				args[UserCard.FIRSTNAME] == null ? new String() : args[UserCard.FIRSTNAME],
				args[UserCard.LASTNAME] == null ? new String() : args[UserCard.LASTNAME], 
				args[UserCard.MAIL] == null ? new String() : args[UserCard.MAIL],
				Integer.parseInt(args[UserCard.AGE] == null ? "0" : args[UserCard.AGE]), 
				Double.parseDouble(args[UserCard.WEIGHT] == null ? "0" : args[UserCard.WEIGHT]),
				args[UserCard.GENDER] == null ? new String() : args[UserCard.GENDER],
				args[UserCard.HEALTH] == null ? new String() : args[UserCard.HEALTH],
				args[UserCard.AVATAR] == null ? new String() : args[UserCard.AVATAR],
				args[UserCard.GOOGLEAPI] == null ? new String() : args[UserCard.GOOGLEAPI], 
				args[UserCard.IDROLE] == null ? UUID.randomUUID().toString() : args[UserCard.IDROLE],
				args[UserCard.STATUS] == null ? "0" : args[UserCard.STATUS],
				Boolean.parseBoolean(args[UserCard.ISDISABLED] == null ? "false" : args[UserCard.ISDISABLED]));
	}

	public User getUserById(final Connection con, final String id) 
			throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException, CloseStatementException {
		return getById(con, id);
	}

	public boolean createUser(final Connection con, final User user)
			throws JDBCDriverException, QueryNotFoundException, DataBaseReadingException {
		boolean result = false;
		String query = sqlQueries.get(DaoQueries.INSERT).toString();
		if (query == null) {
			throw new QueryNotFoundException(String.format(DaoConstants.QUERY_NOT_FOUND, DaoQueries.INSERT.name()));
		}
		try (PreparedStatement pst = con.prepareStatement(query)) {
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

	public boolean updateUser(final Connection con, final User user) 
			throws DataBaseReadingException, JDBCDriverException, QueryNotFoundException {
		boolean result = false;
		String query = sqlQueries.get(DaoQueries.UPDATE).toString();
		if (query == null) {
			throw new QueryNotFoundException(String.format(DaoConstants.QUERY_NOT_FOUND, DaoQueries.UPDATE.name()));
		}
		try (PreparedStatement pst = con.prepareStatement(query)) {
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

	public User getUserByLoginName(final Connection con, final String login)
			throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException {
		return getByFieldName(con, login);
	}

	public boolean lockUser(final Connection con, final boolean isDisabled, final String login)
			throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException {
		boolean result = false;
		String query = sqlQueries.get(DaoQueries.ISDISABLED).toString();
		if (query == null) {
			throw new QueryNotFoundException(String.format(DaoConstants.QUERY_NOT_FOUND, DaoQueries.ISDISABLED.name()));
		}
		try (PreparedStatement pst = con.prepareStatement(query)) {
			pst.setBoolean(1, isDisabled);
			pst.setString(2, login);
			result = pst.execute();
		} catch (SQLException e) {
			throw new DataBaseReadingException(DaoConstants.DATABASE_READING_ERROR, e);
		}
		return result;
	}
	
	public boolean deleteUserForTests(final Connection con, final String id) 
			throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException {
		return deleteById(con, id);
	}
}
