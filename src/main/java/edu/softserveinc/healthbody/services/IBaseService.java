package edu.softserveinc.healthbody.services;

import java.sql.SQLException;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.TransactionException;

public interface IBaseService<TBaseDTO> {

	void insert(TBaseDTO baseDTO) throws SQLException, JDBCDriverException,	TransactionException;

	TBaseDTO get(String name) throws SQLException, JDBCDriverException, TransactionException;

	void update(TBaseDTO baseDTO) throws SQLException, JDBCDriverException, TransactionException;
	
	// use just for test
	void test_delete(TBaseDTO baseDTO) throws SQLException, JDBCDriverException, TransactionException;
}
