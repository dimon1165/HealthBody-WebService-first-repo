package edu.softserveinc.healthbody.services.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

import edu.softserveinc.healthbody.constants.ServiceConstants;
import edu.softserveinc.healthbody.dao.CompetitionDao;
import edu.softserveinc.healthbody.dao.CriteriaDao;
import edu.softserveinc.healthbody.db.ConnectionManager;
import edu.softserveinc.healthbody.dto.CompetitionDTO;
import edu.softserveinc.healthbody.entity.Competition;
import edu.softserveinc.healthbody.exceptions.CloseStatementException;
import edu.softserveinc.healthbody.exceptions.DataBaseReadingException;
import edu.softserveinc.healthbody.exceptions.EmptyResultSetException;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.QueryNotFoundException;
import edu.softserveinc.healthbody.exceptions.TransactionException;
import edu.softserveinc.healthbody.services.ICompetitionsService;

public class CompetitionsServiceImpl implements ICompetitionsService {

	@Override
	public void insert(CompetitionDTO competitionDTO)
			throws SQLException, JDBCDriverException, DataBaseReadingException, QueryNotFoundException,
			EmptyResultSetException, TransactionException, CloseStatementException {
		Connection con = ConnectionManager.getInstance().beginTransaction();
		try {
			String idCriteria = CriteriaDao.getInstance().getByFieldName(con, competitionDTO.getNameCriteria()).getIdCriteria();
			CompetitionDao.getInstance().createCompetition(new Competition(competitionDTO.getIdCompetition(), competitionDTO.getName(),
					competitionDTO.getDescription(), Date.valueOf(competitionDTO.getStartDate()), Date.valueOf(competitionDTO.getFinishDate()), 
					idCriteria));			
		} catch (JDBCDriverException | DataBaseReadingException | QueryNotFoundException e) {
			ConnectionManager.getInstance().rollbackTransaction(con);
			throw new TransactionException(ServiceConstants.TRANSACTION_ERROR, e);
		}
		ConnectionManager.getInstance().commitTransaction(con);
	}
	// TODO 
	@Override
	public final CompetitionDTO get(final String name)
			throws SQLException, JDBCDriverException, TransactionException {
		return null;
	}
	// TODO 
	@Override
	public void update(final CompetitionDTO competitionDTO)
			throws SQLException, JDBCDriverException, TransactionException {
	}
	// TODO
	@Override
	public void delete(final CompetitionDTO competitionDTO) {
				
	}
	//use just for test
	@Override
	public void test_delete(final CompetitionDTO baseDTO)
			throws SQLException, JDBCDriverException, TransactionException {
		// TODO Auto-generated method stub
	}
}
