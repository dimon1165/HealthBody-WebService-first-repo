package edu.softserveinc.healthbody.dao;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import edu.softserveinc.healthbody.exceptions.CloseStatementException;
import edu.softserveinc.healthbody.exceptions.DataBaseReadingException;
import edu.softserveinc.healthbody.exceptions.EmptyResultSetException;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.QueryNotFoundException;

public interface IBasicReadDao<TEntity> {
	
	TEntity getById(String id) throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException, CloseStatementException;

	List<TEntity> getByField(String fieldname, String text) throws JDBCDriverException, DataBaseReadingException, QueryNotFoundException, EmptyResultSetException, CloseStatementException;

	List<TEntity> getAll() throws JDBCDriverException, DataBaseReadingException, EmptyResultSetException, CloseStatementException;
	
	List<TEntity> getFilterRange(int partNumber, int partSize, Map<String, String> filters) throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException, EmptyResultSetException, CloseStatementException;

	boolean deleteById(String id) throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException;
}
