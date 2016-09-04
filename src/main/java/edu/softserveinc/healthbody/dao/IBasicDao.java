package edu.softserveinc.healthbody.dao;

import java.sql.Connection;

import edu.softserveinc.healthbody.exceptions.DataBaseReadingException;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.QueryNotFoundException;

public interface IBasicDao<TEntity> extends IBasicReadDao<TEntity> {
	
	enum DaoQueries {
		INSERT,
		GET_BY_ID,
		GET_BY_FIELD,
		GET_BY_FIELD_NAME,
		GET_ID_BY_FIELDS,
		GET_ALL,
		GET_ALL_GROUPS_PARTICIPANTS,
		SEARCH,
		UPDATE,
		UPDATE_BY_FIELD,
		ISDISABLED,
		DELETE_BY_ID,
		DELETE_BY_FIELD, 
		DELETE_USER_FROM_COMPETITION,
		DELETE_BY_ID_USER_COMPETITION;
	}
	
	boolean deleteById(final Connection connection, String id) throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException;
	
	boolean deleteByField(final Connection connection, String textCondition)
			throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException;
}
