package edu.softserveinc.healthbody.services;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import edu.softserveinc.healthbody.exceptions.DataBaseReadingException;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.QueryNotFoundException;
import edu.softserveinc.healthbody.exceptions.TransactionException;
import edu.softserveinc.healthbody.exceptions.EmptyResultSetException;
import edu.softserveinc.healthbody.exceptions.CloseStatementException;

public interface IBaseFilterService<TBaseDTO> {
	
	List<TBaseDTO> getAll(int partNumber, int partSize, Map<String, String> filters)
			throws JDBCDriverException, SQLException, TransactionException, QueryNotFoundException, DataBaseReadingException, 
			EmptyResultSetException, CloseStatementException;

}
