package edu.softserveinc.healthbody.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import edu.softserveinc.healthbody.constants.DaoConstants;
import edu.softserveinc.healthbody.db.ConnectionManager;
import edu.softserveinc.healthbody.entity.IEntity;
import edu.softserveinc.healthbody.exceptions.DataBaseReadingException;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.QueryNotFoundException;

abstract class AbstractDao<TEntity extends IEntity> extends AbstractDaoRead<TEntity> implements IBasicDao<TEntity> {

	protected AbstractDao() {
		super();
	}

	protected abstract String[] getFields(TEntity entity);	

	// Create
	public boolean insert(TEntity entity) throws JDBCDriverException, QueryNotFoundException, DataBaseReadingException {
		boolean result = false;
		String query = sqlQueries.get(DaoQueries.INSERT).toString();
		if (query == null) {
			throw new QueryNotFoundException(String.format(DaoConstants.QUERY_NOT_FOUND, DaoQueries.INSERT.name()));
		}
		try (PreparedStatement pst = ConnectionManager.getInstance().getConnection().prepareStatement(query)) {
			for (int i = 0; i < getFields(entity).length; i++){
				pst.setString(i, getFields(entity)[i]);
			}
			result = pst.execute();
		} catch (SQLException e) {
			throw new DataBaseReadingException(DaoConstants.DATABASE_READING_ERROR, e);
		}
		return result;
	}

	// Update	
	@Override
	public boolean updateByField(String fieldName, String text, String fieldCondition, String textCondition) 
			throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException {
		boolean result = false;
		String query = sqlQueries.get(DaoQueries.UPDATE_BY_FIELD).toString();
		if (query == null) {
			throw new QueryNotFoundException(String.format(DaoConstants.QUERY_NOT_FOUND, DaoQueries.UPDATE_BY_FIELD.name()));
		}
		try (PreparedStatement pst = ConnectionManager.getInstance().getConnection().prepareStatement(query)) {
			pst.setString(1, fieldName);
			pst.setString(2, text);
			pst.setString(3, fieldCondition);
			pst.setString(4, textCondition);
			result = pst.execute();
		} catch (SQLException e) {
			throw new DataBaseReadingException(DaoConstants.DATABASE_READING_ERROR, e);
		}
		return result;
	}

	// delete
	@Override
	public boolean deleteById(String id) throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException {
		boolean result = false;
		String query = sqlQueries.get(DaoQueries.DELETE_BY_ID).toString();
		if (query == null) {
			throw new QueryNotFoundException(String.format(DaoConstants.QUERY_NOT_FOUND, DaoQueries.DELETE_BY_ID.name()));
		}
		try (PreparedStatement pst = ConnectionManager.getInstance().getConnection().prepareStatement(query)) {
			pst.setString(1, id);
			result = pst.execute();
		} catch (SQLException e) {
			throw new DataBaseReadingException(DaoConstants.DATABASE_READING_ERROR, e);
		}
		return result;
	}

	@Override
	public boolean deleteByField(String textCondition)
			throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException {
		boolean result = false;
		String query = sqlQueries.get(DaoQueries.DELETE_BY_FIELD).toString();
		if (query == null) {
			throw new QueryNotFoundException(String.format(DaoConstants.QUERY_NOT_FOUND, DaoQueries.DELETE_BY_FIELD.name()));
		}
		try (PreparedStatement pst = ConnectionManager.getInstance().getConnection().prepareStatement(query)) {
			pst.setString(1, textCondition);
			result = pst.execute();
		} catch (SQLException e) {
			throw new DataBaseReadingException(DaoConstants.DATABASE_READING_ERROR, e);
		}
		return result;
	}
	public boolean delete(TEntity entity) throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException {
		return deleteById(entity.getId());
	}
}
