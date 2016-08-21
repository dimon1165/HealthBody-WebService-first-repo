package edu.softserveinc.healthbody.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import edu.softserveinc.healthbody.constants.ErrorConstants;
import edu.softserveinc.healthbody.entity.IEntity;
import edu.softserveinc.healthbody.exceptions.DataBaseReadingException;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.QueryNotFoundException;

abstract class AbstractDao<TEntity extends IEntity> extends AbstractDaoRead<TEntity> implements IBasicDao<TEntity> {

	protected AbstractDao() {
	}

	// delete by id
	@Override
	public boolean deleteById(final Connection connection, final String id) 
			throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException {
		boolean result = false;
		String query = sqlQueries.get(DaoQueries.DELETE_BY_ID).toString();
		if (query == null) {
			throw new QueryNotFoundException(String.format(ErrorConstants.QUERY_NOT_FOUND, DaoQueries.DELETE_BY_ID.name()));
		}
		try (PreparedStatement pst = connection.prepareStatement(query)) {
			pst.setString(1, id);
			result = pst.execute();
		} catch (SQLException e) {
			throw new DataBaseReadingException(ErrorConstants.DATABASE_READING_ERROR, e);
		}
		return result;
	}

	// delete by field
	@Override
	public boolean deleteByField(final Connection connection, final String textCondition)
			throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException {
		boolean result = false;
		String query = sqlQueries.get(DaoQueries.DELETE_BY_FIELD).toString();
		if (query == null) {
			throw new QueryNotFoundException(String.format(ErrorConstants.QUERY_NOT_FOUND, DaoQueries.DELETE_BY_FIELD.name()));
		}
		try (PreparedStatement pst = connection.prepareStatement(query)) {
			pst.setString(1, textCondition);
			result = pst.execute();
		} catch (SQLException e) {
			throw new DataBaseReadingException(ErrorConstants.DATABASE_READING_ERROR, e);
		}
		return result;
	}
	
	// delete by entity
	public boolean delete(final Connection connection, final TEntity entity)
			throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException {
		return deleteById(connection, entity.getId());
	}
}
