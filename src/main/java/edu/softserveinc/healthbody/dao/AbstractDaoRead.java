package edu.softserveinc.healthbody.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import edu.softserveinc.healthbody.constants.DaoConstants;
import edu.softserveinc.healthbody.dao.IBasicDao.DaoQueries;
import edu.softserveinc.healthbody.db.ConnectionManager;
import edu.softserveinc.healthbody.exceptions.CloseStatementException;
import edu.softserveinc.healthbody.exceptions.DataBaseReadingException;
import edu.softserveinc.healthbody.exceptions.EmptyResultSetException;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.QueryNotFoundException;

abstract class AbstractDaoRead<TEntity> implements IBasicReadDao<TEntity> {
	
//	protected final static String EMPTY_RESULTSET = "Empty ResultSet by Query %s";
	
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

	protected abstract String[] getFields(TEntity entity);

	// executing query

	// loop
	protected String[] getQueryResultArr(final String[] queryResult, final ResultSet resultSet) throws SQLException {
		for (int i = 0; i < queryResult.length; i++) {
			queryResult[i] = resultSet.getString(i + 1);
		}
		return queryResult;
	}

	public TEntity getById(final String id)
			throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException, CloseStatementException {
		TEntity entity = null;
		String query = sqlQueries.get(DaoQueries.GET_BY_ID).toString();
		if (query == null) {
			throw new QueryNotFoundException(String.format(DaoConstants.QUERY_NOT_FOUND, DaoQueries.GET_BY_ID.name()));
		}
		try (PreparedStatement pst = createPreparedStatement(query, id);
				ResultSet resultSet = pst.executeQuery()) {
			String[] queryResult = new String[resultSet.getMetaData().getColumnCount()];
			while (resultSet.next()) {
				entity = createInstance(getQueryResultArr(queryResult, resultSet));
			}
		} catch (SQLException e) {
			throw new DataBaseReadingException(DaoConstants.DATABASE_READING_ERROR, e);
		}
		return entity;
	}

	public TEntity getByFieldName(final String name)
			throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException {
		TEntity entity = null;
		String query = sqlQueries.get(DaoQueries.GET_BY_FIELD_NAME).toString();
		if (query == null) {
			throw new QueryNotFoundException(String.format(DaoConstants.QUERY_NOT_FOUND, DaoQueries.GET_BY_FIELD_NAME.name()));
		}
		try (PreparedStatement pst = createPreparedStatement(query, name); 
				ResultSet resultSet = pst.executeQuery()) {
			String[] queryResult = new String[resultSet.getMetaData().getColumnCount()];
			while (resultSet.next()) {
				entity = createInstance(getQueryResultArr(queryResult, resultSet));
			}
		} catch (SQLException e) {
			throw new DataBaseReadingException(DaoConstants.DATABASE_READING_ERROR, e);
		}
		return entity;
	}

	@Override
	public List<TEntity> getByField(final String fieldname, final String text) 
			throws JDBCDriverException, DataBaseReadingException, QueryNotFoundException {
		List<TEntity> all = new ArrayList<>();
		String query = sqlQueries.get(DaoQueries.GET_BY_FIELD).toString();
		if (query == null) {
			throw new QueryNotFoundException(String.format(DaoConstants.QUERY_NOT_FOUND, DaoQueries.GET_BY_FIELD.name()));
		}
		try (PreparedStatement pst = createPreparedStatement(query, fieldname, text);
				ResultSet resultSet = pst.executeQuery()) {
			String[] queryResult = new String[resultSet.getMetaData().getColumnCount()];
			while (resultSet.next()) {
				all.add(createInstance(getQueryResultArr(queryResult, resultSet)));
			}
		} catch (SQLException e) {

			throw new DataBaseReadingException(DaoConstants.DATABASE_READING_ERROR, e);
		}
		return all;
	}

	@Override
	public List<TEntity> getAll()
			throws JDBCDriverException, DataBaseReadingException {
		List<TEntity> all = new ArrayList<>();
		String query = sqlQueries.get(DaoQueries.GET_ALL).toString();
		if (query == null) {
			throw new RuntimeException(String.format(DaoConstants.QUERY_NOT_FOUND, DaoQueries.GET_ALL.name()));
		}
		try (PreparedStatement pst = ConnectionManager.getInstance().getConnection().prepareStatement(query);
				ResultSet resultSet = pst.executeQuery()) {
			String[] queryResult = new String[resultSet.getMetaData().getColumnCount()];
			while (resultSet.next()) {
				all.add(createInstance(getQueryResultArr(queryResult, resultSet)));
			}
		} catch (SQLException e) {
			throw new DataBaseReadingException(DaoConstants.DATABASE_READING_ERROR, e);
		}
		return all;
	}

	public String getIdByTwoEntities(final String idFirstEntity, final String idSecondEntity)
			throws JDBCDriverException, QueryNotFoundException, EmptyResultSetException {
		String id = null;
		String query = sqlQueries.get(DaoQueries.GET_ID_BY_FIELDS).toString();
		if (query == null) {
			throw new QueryNotFoundException(String.format(DaoConstants.QUERY_NOT_FOUND, DaoQueries.GET_ID_BY_FIELDS.name()));
		}
		try (PreparedStatement pst = createPreparedStatement(query, idFirstEntity, idSecondEntity);
				ResultSet resultSet = pst.executeQuery()) {
			String[] queryResult = new String[resultSet.getMetaData().getColumnCount()];
			id = getFields(createInstance(getQueryResultArr(queryResult, resultSet)))[0];
		} catch (SQLException e) {
			throw new EmptyResultSetException(DaoConstants.DATABASE_READING_ERROR, e);
		}
		return id;
	}

	public List<TEntity> getAllbyId(final String id) throws QueryNotFoundException, JDBCDriverException,
			DataBaseReadingException, CloseStatementException, EmptyResultSetException {
		List<TEntity> all = new ArrayList<>();
		String query = sqlQueries.get(DaoQueries.GET_BY_ID).toString();
		if (query == null) {
			throw new QueryNotFoundException(String.format(DaoConstants.QUERY_NOT_FOUND, DaoQueries.GET_BY_ID.name()));
		}
		try (PreparedStatement pst = createPreparedStatement(query, id); 
				ResultSet resultSet = pst.executeQuery()) {
			String[] queryResult = new String[resultSet.getMetaData().getColumnCount()];
			while (resultSet.next()) {
				all.add(createInstance(getQueryResultArr(queryResult, resultSet)));
			}
		} catch (SQLException e) {
			throw new DataBaseReadingException(DaoConstants.DATABASE_READING_ERROR, e);
		}
		return all;

	}

	@Override
	public List<TEntity> getFilterRange(final int partNumber, final int partSize, final Map<String, String> filters)
			throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException {
		List<TEntity> all = new ArrayList<>();
		String query = sqlQueries.get(DaoQueries.GET_ALL).toString();
		if (query == null) {
			throw new QueryNotFoundException(String.format(DaoConstants.QUERY_NOT_FOUND, DaoQueries.GET_ID_BY_FIELDS.name()));
		}
		query = makeQuery(partNumber, partSize, query, filters);
		try (PreparedStatement pst = ConnectionManager.getInstance().getConnection().prepareStatement(query);
				ResultSet resultSet = pst.executeQuery()) {
			String[] queryResult = new String[resultSet.getMetaData().getColumnCount()];
			while (resultSet.next()) {
				all.add(createInstance(getQueryResultArr(queryResult, resultSet)));
			}
		} catch (SQLException e) {
			throw new DataBaseReadingException(DaoConstants.DATABASE_READING_ERROR, e);
		}
		return all;
	}

	private String makeQuery(final int partNumber, final int partSize, String query, final Map<String, String> filters) {
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

	// methods try-with-resources
	private PreparedStatement createPreparedStatementId(final String query, final String id)
			throws SQLException, JDBCDriverException {
		PreparedStatement pst = ConnectionManager.getInstance().getConnection().prepareStatement(query);
		pst.setString(1, id);
		return pst;
	}

	private PreparedStatement createPreparedStatement(final String query, final String fieldname, final String text)
			throws SQLException, JDBCDriverException {
		PreparedStatement pst = ConnectionManager.getInstance().getConnection().prepareStatement(query);
		pst.setString(1, fieldname);
		pst.setString(2, text);
		return pst;
	}
	private PreparedStatement createPreparedStatement(final String query, final String name)
			throws SQLException, JDBCDriverException {
		PreparedStatement pst = ConnectionManager.getInstance().getConnection().prepareStatement(query);
		pst.setString(1, name);
		return pst;
	}

	public boolean deleteById(String id) throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException {
		return false;
	}
}
