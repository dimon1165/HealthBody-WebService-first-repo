package edu.softserveinc.healthbody.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.softserveinc.healthbody.constants.ErrorConstants;
import edu.softserveinc.healthbody.dao.IBasicDao.DaoQueries;
import edu.softserveinc.healthbody.exceptions.CloseStatementException;
import edu.softserveinc.healthbody.exceptions.DataBaseReadingException;
import edu.softserveinc.healthbody.exceptions.EmptyResultSetException;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.QueryNotFoundException;

abstract class AbstractDaoRead<TEntity> implements IBasicReadDao<TEntity> {

	protected static final String SQL_WHERE = " where";
	protected static final String SQL_AND = " and";
	protected static final String SQL_LIKE = " ? like ?;";
	protected static final String SQL_LIMIT = "  offset ? limit ?;";

	protected final HashMap<Enum<?>, Enum<?>> sqlQueries;

	protected AbstractDaoRead() {
		this.sqlQueries = new HashMap<Enum<?>, Enum<?>>();
	}

	protected abstract void init();

	protected abstract TEntity createInstance(String[] args);

	// executing query

	// loop
	protected String[] getQueryResultArr(final String[] queryResult, final ResultSet resultSet) throws SQLException {
		for (int i = 0; i < queryResult.length; i++) {
			queryResult[i] = resultSet.getString(i + 1);
		}
		return queryResult;
	}

	public TEntity getById(final Connection connection, final String id)
			throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException, CloseStatementException {
		TEntity entity = null;
		String query = sqlQueries.get(DaoQueries.GET_BY_ID).toString();
		if (query == null) {
			throw new QueryNotFoundException(
					String.format(ErrorConstants.QUERY_NOT_FOUND, DaoQueries.GET_BY_ID.name()));
		}
		try (PreparedStatement pst = createPreparedStatement(connection, query, id);
				ResultSet resultSet = pst.executeQuery()) {
			String[] queryResult = new String[resultSet.getMetaData().getColumnCount()];
			while (resultSet.next()) {
				entity = createInstance(getQueryResultArr(queryResult, resultSet));
			}
		} catch (SQLException e) {
			throw new DataBaseReadingException(ErrorConstants.DATABASE_READING_ERROR, e);
		}
		return entity;
	}

	public TEntity getByFieldName(final Connection connection, final String name)
			throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException {
		TEntity entity = null;
		String query = sqlQueries.get(DaoQueries.GET_BY_FIELD_NAME).toString();
		if (query == null) {
			throw new QueryNotFoundException(
					String.format(ErrorConstants.QUERY_NOT_FOUND, DaoQueries.GET_BY_FIELD_NAME.name()));
		}
		try (PreparedStatement pst = createPreparedStatement(connection, query, name);
				ResultSet resultSet = pst.executeQuery()) {
			String[] queryResult = new String[resultSet.getMetaData().getColumnCount()];
			while (resultSet.next()) {
				entity = createInstance(getQueryResultArr(queryResult, resultSet));
			}
		} catch (SQLException e) {
			throw new DataBaseReadingException(ErrorConstants.DATABASE_READING_ERROR, e);
		}
		return entity;
	}

	@Override
	public List<TEntity> getAll(final Connection connection) throws JDBCDriverException, DataBaseReadingException {
		List<TEntity> all = new ArrayList<>();
		String query = sqlQueries.get(DaoQueries.GET_ALL).toString();
		if (query == null) {
			throw new RuntimeException(String.format(ErrorConstants.QUERY_NOT_FOUND, DaoQueries.GET_ALL.name()));
		}
		try (PreparedStatement pst = connection.prepareStatement(query); ResultSet resultSet = pst.executeQuery()) {
			String[] queryResult = new String[resultSet.getMetaData().getColumnCount()];
			while (resultSet.next()) {
				all.add(createInstance(getQueryResultArr(queryResult, resultSet)));
			}
		} catch (SQLException e) {
			throw new DataBaseReadingException(ErrorConstants.DATABASE_READING_ERROR, e);
		}
		return all;
	}

	public List<TEntity> getAllbyId(final Connection connection, final String id) throws QueryNotFoundException,
			JDBCDriverException, DataBaseReadingException, CloseStatementException, EmptyResultSetException {
		List<TEntity> all = new ArrayList<>();
		String query = sqlQueries.get(DaoQueries.GET_BY_ID).toString();
		if (query == null) {
			throw new QueryNotFoundException(
					String.format(ErrorConstants.QUERY_NOT_FOUND, DaoQueries.GET_BY_ID.name()));
		}
		try (PreparedStatement pst = createPreparedStatement(connection, query, id);
				ResultSet resultSet = pst.executeQuery()) {
			String[] queryResult = new String[resultSet.getMetaData().getColumnCount()];
			while (resultSet.next()) {
				all.add(createInstance(getQueryResultArr(queryResult, resultSet)));
			}
		} catch (SQLException e) {
			throw new DataBaseReadingException(ErrorConstants.DATABASE_READING_ERROR, e);
		}
		return all;

	}

	@Override
	public List<TEntity> getFilterRange(final Connection connection, final int partNumber, final int partSize,
			final Map<String, String> filters)
			throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException {
		List<TEntity> all = new ArrayList<>();
		String query = sqlQueries.get(DaoQueries.GET_ALL).toString();
		if (query == null) {
			throw new QueryNotFoundException(
					String.format(ErrorConstants.QUERY_NOT_FOUND, DaoQueries.GET_ID_BY_FIELDS.name()));
		}
		query = makeQuery(partNumber, partSize, query, filters);
		try (PreparedStatement pst = connection.prepareStatement(query); ResultSet resultSet = pst.executeQuery()) {
			String[] queryResult = new String[resultSet.getMetaData().getColumnCount()];
			while (resultSet.next()) {
				all.add(createInstance(getQueryResultArr(queryResult, resultSet)));
			}
		} catch (SQLException e) {
			throw new DataBaseReadingException(ErrorConstants.DATABASE_READING_ERROR, e);
		}
		return all;
	}

	private String makeQuery(final int partNumber, final int partSize, String query,
			final Map<String, String> filters) {
		boolean isWhereFirst = true;
		for (String fieldName : filters.keySet()) {
			if ((filters.get(fieldName) != null) && (!filters.get(fieldName).isEmpty())) {
				if (isWhereFirst) {
					query = query.substring(0, query.lastIndexOf(";")) + SQL_WHERE;
					isWhereFirst = false;
				} else {
					query = query.substring(0, query.lastIndexOf(";")) + SQL_AND;
				}
				query = query + String.format(SQL_LIKE, fieldName, filters.get(fieldName));
			}
		}
		if ((partNumber >= 0) && (partSize > 0)) {
			query = query.substring(0, query.lastIndexOf(";")) + String.format(SQL_LIMIT, partNumber, partSize);
		}
		return query;
	}

	private PreparedStatement createPreparedStatement(final Connection connection, final String query, final String name)
			throws SQLException, JDBCDriverException {
		PreparedStatement pst = connection.prepareStatement(query);
		pst.setString(1, name);
		return pst;
	}

	public boolean deleteById(final Connection connection, String id)
			throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException {
		return false;
	}
}
