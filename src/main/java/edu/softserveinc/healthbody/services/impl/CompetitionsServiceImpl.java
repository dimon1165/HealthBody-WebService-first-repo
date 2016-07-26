package edu.softserveinc.healthbody.services.impl;

import java.sql.Date;
import java.sql.SQLException;
import edu.softserveinc.healthbody.dao.CompetitionDao;
import edu.softserveinc.healthbody.dao.CriteriaDao;
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
		String idCriteria = CriteriaDao.getInstance().getByFieldName(competitionDTO.getNameCriteria()).getIdCriteria();
		CompetitionDao.getInstance().createCompetition(new Competition(competitionDTO.getIdCompetition(), competitionDTO.getName(), competitionDTO.getDescription(), Date.valueOf(competitionDTO.getStartDate()), Date.valueOf(competitionDTO.getFinishDate()), idCriteria));

	}
	// TODO 
	@Override
	public CompetitionDTO get(String name) throws SQLException, JDBCDriverException, EmptyResultSetException,
			TransactionException, CloseStatementException {
		return null;
	}
	// TODO 
	@Override
	public void update(CompetitionDTO competitionDTO)
			throws SQLException, JDBCDriverException, DataBaseReadingException, QueryNotFoundException,
			EmptyResultSetException, TransactionException, CloseStatementException {
	}
	// TODO
	@Override
	public void delete(CompetitionDTO competitionDTO) {
				
	}
	//use just for test
	@Override
	public void test_delete(CompetitionDTO baseDTO) throws SQLException, JDBCDriverException, QueryNotFoundException,
			DataBaseReadingException, CloseStatementException, TransactionException {
		// TODO Auto-generated method stub
		
	}

}
