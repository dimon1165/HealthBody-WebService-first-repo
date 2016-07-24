package edu.softserveinc.healthbody.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.softserveinc.healthbody.constants.DaoConstants;
import edu.softserveinc.healthbody.constants.DaoStatementsConstant.CompetitionsViewQueries;
import edu.softserveinc.healthbody.db.ConnectionManager;
import edu.softserveinc.healthbody.entity.CompetitionsView;
import edu.softserveinc.healthbody.exceptions.DataBaseReadingException;
import edu.softserveinc.healthbody.exceptions.IllegalAgrumentCheckedException;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.QueryNotFoundException;
import edu.softserveinc.healthbody.log.Log4jWrapper;

public class CompetitionsViewDao extends AbstractDao<CompetitionsView> {

	private static volatile CompetitionsViewDao instance;

	private CompetitionsViewDao() {
		init();
	}

	public static CompetitionsViewDao getInstance() {
		if (instance == null) {
			synchronized (CompetitionsViewDao.class) {
				if (instance == null) {
					instance = new CompetitionsViewDao();
				}
			}
		}
		return instance;
	}

	@Override
	protected void init() {
		for (CompetitionsViewQueries competitionsViewQueries : CompetitionsViewQueries.values()) {
			sqlQueries.put(competitionsViewQueries.getDaoQuery(), competitionsViewQueries);
		}
	}

	@Override
	public CompetitionsView createInstance(final String[] args) {
		return new CompetitionsView(Integer.parseInt(args[0] == null ? "0" : args[0]), args[1] == null ? "0" : args[1],
				args[2] == null ? "0" : args[2], args[3] == null ? "0" : args[3], args[4] == null ? "0" : args[4],
				Integer.parseInt(args[5] == null ? "0" : args[5]));
	}

	@Override
	protected String[] getFields(final CompetitionsView entity) {
		List<String> fields = new ArrayList<>();
		fields.add(entity.getIdCompetition().toString());
		fields.add(entity.getName());
		fields.add(entity.getDescription());
		fields.add(entity.getStart());
		fields.add(entity.getFinish());
		fields.add(entity.getUsersCount().toString());
		return (String[]) fields.toArray();
	}

	public List<CompetitionsView> getActiveCompetitionsView(final int partNumber, final int partSize) 
			throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException {
		List<CompetitionsView> result = new ArrayList<>();
		String query = sqlQueries.get(CompetitionsViewQueries.GET_ALL_ACTIVE).toString();
		if (query == null) {
			throw new QueryNotFoundException(
					String.format(DaoConstants.QUERY_NOT_FOUND, CompetitionsViewQueries.GET_ALL_ACTIVE.name()));
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
	
	public List<CompetitionsView> getActiveCompetitionsByUserView(final int partNumber, final int partSize, final String login)
			throws DataBaseReadingException, IllegalAgrumentCheckedException, QueryNotFoundException, JDBCDriverException {
		if (login == null || login.isEmpty()) {
			String errorStr = "Illegal parameter. \"login\" is empty or null.";
			Log4jWrapper.get().error(errorStr);
			throw new IllegalAgrumentCheckedException(errorStr);
		}
		List<CompetitionsView> result = new ArrayList<>();
		String query = sqlQueries.get(CompetitionsViewQueries.GET_ALL_ACTIVE_BY_USER).toString();
		if (query == null) {
			throw new QueryNotFoundException(
					String.format(DaoConstants.QUERY_NOT_FOUND, CompetitionsViewQueries.GET_ALL_ACTIVE_BY_USER.name()));
		}
		if ((partNumber >= 0) && (partSize > 0)) {
			query = query.substring(0, query.lastIndexOf(";")) + SQL_LIMIT;
		}
		try (PreparedStatement pst = createPreparedStatementLogin(query, login, partNumber, partSize);
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

	public List<CompetitionsView> getAllCompetitionsView(final int partNumber, final int partSize)
			throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException {
		List<CompetitionsView> result = new ArrayList<>();
		String query = sqlQueries.get(CompetitionsViewQueries.GET_ALL).toString();
		if (query == null) {
			throw new QueryNotFoundException(String.format(DaoConstants.QUERY_NOT_FOUND, CompetitionsViewQueries.GET_ALL.name()));
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
	
	public List<CompetitionsView> getCompetitionsByUserView(int partNumber, final int partSize, final String login)
			throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException, IllegalAgrumentCheckedException {
		if (login == null || login.isEmpty()) {
			String errorStr = "Illegal parameter. \"login\" is empty or null.";
			Log4jWrapper.get().error(errorStr);
			throw new IllegalAgrumentCheckedException(errorStr);
		}
		List<CompetitionsView> result = new ArrayList<>();
		String query = sqlQueries.get(CompetitionsViewQueries.GET_ALL_BY_USER).toString();
		if (query == null) {
			throw new QueryNotFoundException(
					String.format(DaoConstants.QUERY_NOT_FOUND, CompetitionsViewQueries.GET_ALL_BY_USER.name()));
		}
		if ((partNumber >= 0) && (partSize > 0)) {
			query = query.substring(0, query.lastIndexOf(";")) + SQL_LIMIT;
		}
		try (PreparedStatement pst = createPreparedStatementLogin(query, login, partNumber, partSize);
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
	
	private PreparedStatement createPreparedStatementLogin(final String query, final String login,
			final int partNumber, final int partSize)
			throws SQLException, JDBCDriverException {
		PreparedStatement pst = ConnectionManager.getInstance().getConnection().prepareStatement(query);
		int i = 1;    
		pst.setString(i++, login);
		if ((partNumber >= 0) && (partSize > 0)) {
			pst.setInt(i++, (partNumber - 1) * partSize);
			pst.setInt(i++, partSize);
		}
		return pst;
	}
}
