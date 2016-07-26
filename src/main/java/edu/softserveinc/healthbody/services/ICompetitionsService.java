package edu.softserveinc.healthbody.services;

import java.sql.SQLException;

import edu.softserveinc.healthbody.dto.CompetitionDTO;
import edu.softserveinc.healthbody.exceptions.DataBaseReadingException;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.QueryNotFoundException;
import edu.softserveinc.healthbody.exceptions.TransactionException;
import edu.softserveinc.healthbody.exceptions.CloseStatementException;
import edu.softserveinc.healthbody.exceptions.EmptyResultSetException;

public interface ICompetitionsService extends IBaseService<CompetitionDTO> {

	void insert(CompetitionDTO competitionDTO) 
			throws SQLException, JDBCDriverException, TransactionException, DataBaseReadingException, QueryNotFoundException, EmptyResultSetException, CloseStatementException;

	CompetitionDTO get(String name) throws SQLException, JDBCDriverException, TransactionException;

	void update(CompetitionDTO competitionDTO) throws SQLException, JDBCDriverException, TransactionException;

	void delete(CompetitionDTO competitionDTO);

}
