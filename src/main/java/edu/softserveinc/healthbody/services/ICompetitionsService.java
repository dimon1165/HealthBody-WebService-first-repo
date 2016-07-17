package edu.softserveinc.healthbody.services;

import java.sql.SQLException;

import edu.softserveinc.healthbody.dto.CompetitionDTO;
import edu.softserveinc.healthbody.exceptions.CloseStatementException;
import edu.softserveinc.healthbody.exceptions.DataBaseReadingException;
import edu.softserveinc.healthbody.exceptions.EmptyResultSetException;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.QueryNotFoundException;
import edu.softserveinc.healthbody.exceptions.TransactionException;

public interface ICompetitionsService extends IBaseService<CompetitionDTO> {

	void insert(CompetitionDTO competitionDTO) throws SQLException, JDBCDriverException, DataBaseReadingException,
			QueryNotFoundException, EmptyResultSetException, TransactionException, CloseStatementException;

	CompetitionDTO get(String name) throws SQLException, JDBCDriverException, EmptyResultSetException, TransactionException,
			CloseStatementException;

	void update(CompetitionDTO competitionDTO) throws SQLException, JDBCDriverException, DataBaseReadingException,
			QueryNotFoundException, EmptyResultSetException, TransactionException, CloseStatementException;

	void delete(CompetitionDTO competitionDTO);

}
