package edu.softserveinc.healthbody.services.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.softserveinc.healthbody.constants.ErrorConstants;
import edu.softserveinc.healthbody.dao.CompetitionDao;
import edu.softserveinc.healthbody.dao.CriteriaDao;
import edu.softserveinc.healthbody.dao.UserCompetitionsDao;
import edu.softserveinc.healthbody.dao.UserDao;
import edu.softserveinc.healthbody.db.ConnectionManager;
import edu.softserveinc.healthbody.dto.CompetitionDTO;
import edu.softserveinc.healthbody.entity.Competition;
import edu.softserveinc.healthbody.entity.UserCompetitions;
import edu.softserveinc.healthbody.exceptions.CloseStatementException;
import edu.softserveinc.healthbody.exceptions.DataBaseReadingException;
import edu.softserveinc.healthbody.exceptions.EmptyResultSetException;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.QueryNotFoundException;
import edu.softserveinc.healthbody.exceptions.TransactionException;
import edu.softserveinc.healthbody.log.Log4jWrapper;
import edu.softserveinc.healthbody.services.ICompetitionsService;

public class CompetitionsServiceImpl implements ICompetitionsService {

	@Override
	public void insert(CompetitionDTO competitionDTO)
			throws SQLException, JDBCDriverException, DataBaseReadingException, QueryNotFoundException,
			EmptyResultSetException, TransactionException, CloseStatementException {
		if (competitionDTO == null) {
			Log4jWrapper.get().error("You didn't enter competition");
			throw new IllegalArgumentException();
		} else {
			Connection connection = ConnectionManager.getInstance().beginTransaction();
			try {
				CompetitionDao.getInstance().createCompetition(connection,
						new Competition(competitionDTO.getIdCompetition(), competitionDTO.getName(),
								competitionDTO.getDescription(), Date.valueOf(competitionDTO.getStartDate()),
								Date.valueOf(competitionDTO.getFinishDate()), null));
			} catch (JDBCDriverException | DataBaseReadingException | QueryNotFoundException e) {
				ConnectionManager.getInstance().rollbackTransaction(connection);
				throw new TransactionException(ErrorConstants.TRANSACTION_ERROR, e);
			}
			ConnectionManager.getInstance().commitTransaction(connection);
		}
	}

	@Override
	public boolean addUserInCompetition(String nameCompetition, String nameUser)
			throws SQLException, JDBCDriverException, TransactionException, DataBaseReadingException,
			QueryNotFoundException, EmptyResultSetException, CloseStatementException {
		boolean result = false;
		CompetitionDTO competitionDTO = get(nameCompetition);
		int count = Integer.parseInt(competitionDTO.getCount());
		count++;
		competitionDTO.setCount(String.valueOf(count));
		List<String> logins = competitionDTO.getLogins();
		logins.add(nameUser);
		competitionDTO.setLogins(logins);
		update(competitionDTO);
		result = true;
		return result;
	}

	@Override
	public CompetitionDTO get(String name) throws SQLException, JDBCDriverException, TransactionException {
		Connection connection = ConnectionManager.getInstance().beginTransaction();
		Competition competition;
		String idCriteria;
		List<String> logins;
		int count = 0;
		try {
			competition = CompetitionDao.getInstance().getCompetitionByName(connection, name);
			idCriteria = CriteriaDao.getInstance().getByFieldName(connection, competition.getIdCriteria())
					.getIdCriteria();
			logins = new ArrayList<>();
			List<UserCompetitions> userCompetitions = UserCompetitionsDao.getInstance().viewAll(connection);
			for (UserCompetitions usComp : userCompetitions) {
				if (usComp.getIdCompetition().equals(competition.getId())) {
					logins.add(UserDao.getInstance().getUserById(connection, usComp.getIdUser()).getLogin());
					count++;
				}
			}
		} catch (QueryNotFoundException | DataBaseReadingException | CloseStatementException
				| EmptyResultSetException e) {
			ConnectionManager.getInstance().rollbackTransaction(connection);
			throw new TransactionException(ErrorConstants.TRANSACTION_ERROR, e);
		}
		ConnectionManager.getInstance().commitTransaction(connection);
		return new CompetitionDTO(competition.getIdCompetition(), competition.getName(), String.valueOf(count),
				competition.getStart().toString(), competition.getFinish().toString(), competition.getDescription(),
				idCriteria, null, logins);
	}

	@Override
	public void update(final CompetitionDTO competitionDTO)
			throws SQLException, JDBCDriverException, DataBaseReadingException, QueryNotFoundException,
			EmptyResultSetException, TransactionException, CloseStatementException {
		if (competitionDTO == null) {
			Log4jWrapper.get().error("You didn't enter competition");
			throw new IllegalArgumentException();
		} else {
			Connection connection = ConnectionManager.getInstance().beginTransaction();
			try {
				CompetitionDao.getInstance().editCompetition(connection,
						new Competition(competitionDTO.getIdCompetition(), competitionDTO.getName(),
								competitionDTO.getDescription(), Date.valueOf(competitionDTO.getStartDate()),
								Date.valueOf(competitionDTO.getFinishDate()), null));
			} catch (JDBCDriverException | DataBaseReadingException | QueryNotFoundException e) {
				ConnectionManager.getInstance().rollbackTransaction(connection);
				throw new TransactionException(ErrorConstants.TRANSACTION_ERROR, e);
			}
			ConnectionManager.getInstance().commitTransaction(connection);
		}
	}

	// TODO
	@Override
	public void delete(final CompetitionDTO competitionDTO) {

	}

	// use just for test
	@Override
	public void test_delete(final CompetitionDTO baseDTO)
			throws SQLException, JDBCDriverException, TransactionException {
		// TODO Auto-generated method stub
	}
}
