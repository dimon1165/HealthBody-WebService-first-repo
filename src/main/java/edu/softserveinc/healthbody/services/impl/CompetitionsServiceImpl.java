package edu.softserveinc.healthbody.services.impl;

import java.sql.Date;
import java.sql.SQLException;

import edu.softserveinc.healthbody.constants.ServiceConstants;
import edu.softserveinc.healthbody.dao.CompetitionDao;
import edu.softserveinc.healthbody.dao.CriteriaDao;
import edu.softserveinc.healthbody.db.ConnectionManager;
import edu.softserveinc.healthbody.dto.CompetitionDTO;
import edu.softserveinc.healthbody.entity.Competition;
import edu.softserveinc.healthbody.exceptions.DataBaseReadingException;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.QueryNotFoundException;
import edu.softserveinc.healthbody.exceptions.TransactionException;
import edu.softserveinc.healthbody.services.ICompetitionsService;

public class CompetitionsServiceImpl implements ICompetitionsService {

	@Override
	public final void insert(final CompetitionDTO competitionDTO)
			throws SQLException, JDBCDriverException, TransactionException {
		try {
			ConnectionManager.getInstance().beginTransaction();
			int idCriteria = CriteriaDao.getInstance().getByFieldName(competitionDTO.getNameCriteria()).getIdCriteria();
			CompetitionDao.getInstance()
					.createCompetition(new Competition(0, competitionDTO.getName(), competitionDTO.getDescription(),
							Date.valueOf(competitionDTO.getStartDate()), Date.valueOf(competitionDTO.getFinishDate()),
							idCriteria));
		} catch (JDBCDriverException | DataBaseReadingException | QueryNotFoundException e) {
			ConnectionManager.getInstance().rollbackTransaction();
			throw new TransactionException(ServiceConstants.TRANSACTION_ERROR, e);
		}
		ConnectionManager.getInstance().commitTransaction();
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
