package edu.softserveinc.healthbody.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import edu.softserveinc.healthbody.constants.DaoConstants;
import edu.softserveinc.healthbody.entity.IEntity;
import edu.softserveinc.healthbody.exceptions.DataBaseReadingException;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.QueryNotFoundException;

abstract class AbstractDao<TEntity extends IEntity> extends AbstractDaoRead<TEntity> implements IBasicDao<TEntity> {

	protected AbstractDao() {
	}

	// delete by id
	@Override
	public boolean deleteById(final Connection con, final String id) 
			throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException {
		boolean result = false;
		String query = sqlQueries.get(DaoQueries.DELETE_BY_ID).toString();
		if (query == null) {
			throw new QueryNotFoundException(String.format(DaoConstants.QUERY_NOT_FOUND, DaoQueries.DELETE_BY_ID.name()));
		}
		try (PreparedStatement pst = con.prepareStatement(query)) {
			pst.setString(1, id);
			result = pst.execute();
		} catch (SQLException e) {
			throw new DataBaseReadingException(DaoConstants.DATABASE_READING_ERROR, e);
		}
		return result;
	}

	// delete by field
	@Override
	public boolean deleteByField(final Connection con, final String textCondition)
			throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException {
		boolean result = false;
		String query = sqlQueries.get(DaoQueries.DELETE_BY_FIELD).toString();
		if (query == null) {
			throw new QueryNotFoundException(String.format(DaoConstants.QUERY_NOT_FOUND, DaoQueries.DELETE_BY_FIELD.name()));
		}
		try (PreparedStatement pst = con.prepareStatement(query)) {
			pst.setString(1, textCondition);
			result = pst.execute();
		} catch (SQLException e) {
			throw new DataBaseReadingException(DaoConstants.DATABASE_READING_ERROR, e);
		}
		return result;
	}
	
	// delete by entity
	public boolean delete(final Connection con, final TEntity entity)
			throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException {
		return deleteById(con, entity.getId());
	}
}
