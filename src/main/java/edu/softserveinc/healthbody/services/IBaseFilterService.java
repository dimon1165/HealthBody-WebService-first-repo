package edu.softserveinc.healthbody.services;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.TransactionException;

public interface IBaseFilterService<TBaseDTO> {
	
	List<TBaseDTO> getAll(int partNumber, int partSize, Map<String, String> filters)
			throws JDBCDriverException, SQLException, TransactionException;

}
