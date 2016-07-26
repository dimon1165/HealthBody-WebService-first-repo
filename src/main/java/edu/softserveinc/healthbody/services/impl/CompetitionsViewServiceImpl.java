package edu.softserveinc.healthbody.services.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.softserveinc.healthbody.constants.ServiceConstants;
import edu.softserveinc.healthbody.dao.CompetitionsViewDao;
import edu.softserveinc.healthbody.db.ConnectionManager;
import edu.softserveinc.healthbody.dto.CompetitionDTO;
import edu.softserveinc.healthbody.entity.CompetitionsView;
import edu.softserveinc.healthbody.exceptions.CloseStatementException;
import edu.softserveinc.healthbody.exceptions.DataBaseReadingException;
import edu.softserveinc.healthbody.exceptions.EmptyResultSetException;
import edu.softserveinc.healthbody.exceptions.IllegalAgrumentCheckedException;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.QueryNotFoundException;
import edu.softserveinc.healthbody.exceptions.TransactionException;
import edu.softserveinc.healthbody.log.LoggerWrapper;
import edu.softserveinc.healthbody.services.ICompetitionsViewService;

public class CompetitionsViewServiceImpl implements ICompetitionsViewService {

	@Override
	public List<CompetitionDTO> getAll(int partNumber, int partSize) throws JDBCDriverException, SQLException, TransactionException {
		List<CompetitionDTO> competitionDTO = new ArrayList<>();
		ConnectionManager.getInstance().beginTransaction();
		try {
			for (CompetitionsView competitionsView : CompetitionsViewDao.getInstance().getAllCompetitionsView(partNumber,
					partSize)) {
				competitionDTO.add(new CompetitionDTO(competitionsView.getIdCompetition(), competitionsView.getName(),
						competitionsView.getUsersCount().toString(), competitionsView.getStart(),
						competitionsView.getFinish(), competitionsView.getDescription(), null, new ArrayList<String>(),
						new ArrayList<String>()));
			}
		} catch (CloseStatementException | QueryNotFoundException | EmptyResultSetException | DataBaseReadingException e) {
			ConnectionManager.getInstance().rollbackTransaction();
			throw new TransactionException(ServiceConstants.TRANSACTION_ERROR, e);
		}
		ConnectionManager.getInstance().commitTransaction();
		return competitionDTO;
	}

	@Override
	public List<CompetitionDTO> getAllActive(int partNumber, int partSize) throws JDBCDriverException, SQLException, TransactionException {
		List<CompetitionDTO> competitionDTO = new ArrayList<>();
		ConnectionManager.getInstance().beginTransaction();
		try {
			for (CompetitionsView competitionsView : CompetitionsViewDao.getInstance().getActiveCompetitionsView(partNumber,
					partSize)) {
				competitionDTO.add(new CompetitionDTO(competitionsView.getIdCompetition(), competitionsView.getName(),
						competitionsView.getUsersCount().toString(), competitionsView.getStart(),
						competitionsView.getFinish(), competitionsView.getDescription(), null, new ArrayList<String>(),
						new ArrayList<String>()));
			}
		} catch (CloseStatementException | QueryNotFoundException | EmptyResultSetException | DataBaseReadingException e) {
			ConnectionManager.getInstance().rollbackTransaction();
			throw new TransactionException(ServiceConstants.TRANSACTION_ERROR, e);
		}
		ConnectionManager.getInstance().commitTransaction();
		return competitionDTO;
	}

	@Override
	public List<CompetitionDTO> getAllByUser(int partNumber, int partSize, String login) throws IllegalAgrumentCheckedException, SQLException, JDBCDriverException, TransactionException {
		if (login == null || login.isEmpty()) {
			String errorStr = "Illegal parameter. \"login\" is empty or null.";
			LoggerWrapper.error(this.getClass(), errorStr);
			throw new IllegalAgrumentCheckedException(errorStr);
		}
		List<CompetitionDTO> competitionDTO = new ArrayList<>();
		ConnectionManager.getInstance().beginTransaction();
		try {
			for (CompetitionsView competitionsView : CompetitionsViewDao.getInstance().getCompetitionsByUserView(partNumber,
					partSize, login)) {
				competitionDTO.add(new CompetitionDTO(competitionsView.getIdCompetition(), competitionsView.getName(),
						competitionsView.getUsersCount().toString(), competitionsView.getStart(),
						competitionsView.getFinish(), competitionsView.getDescription(), null, new ArrayList<String>(),
						new ArrayList<String>()));
			}
		} catch (CloseStatementException | QueryNotFoundException | EmptyResultSetException | DataBaseReadingException e) {
			ConnectionManager.getInstance().rollbackTransaction();
			throw new TransactionException(ServiceConstants.TRANSACTION_ERROR, e);
		}
		ConnectionManager.getInstance().commitTransaction();
		return competitionDTO;
	}

	@Override
	public List<CompetitionDTO> getAllActiveByUser(int partNumber, int partSize, String login) throws IllegalAgrumentCheckedException, SQLException, JDBCDriverException, TransactionException{
		if (login == null || login.isEmpty()) {
			String errorStr = "Illegal parameter. \"login\" is empty or null.";
			LoggerWrapper.error(this.getClass(), errorStr);
			throw new IllegalAgrumentCheckedException(errorStr);
		}
		List<CompetitionDTO> competitionDTO = new ArrayList<>();
		ConnectionManager.getInstance().beginTransaction();
		try {
			for (CompetitionsView competitionsView : CompetitionsViewDao.getInstance().getActiveCompetitionsByUserView(partNumber,
					partSize, login)) {
				competitionDTO.add(new CompetitionDTO(competitionsView.getIdCompetition(), competitionsView.getName(),
						competitionsView.getUsersCount().toString(), competitionsView.getStart(),
						competitionsView.getFinish(), competitionsView.getDescription(), null, new ArrayList<String>(),
						new ArrayList<String>()));
			}
		} catch (CloseStatementException | QueryNotFoundException | EmptyResultSetException | DataBaseReadingException e) {
			ConnectionManager.getInstance().rollbackTransaction();
			throw new TransactionException(ServiceConstants.TRANSACTION_ERROR, e);
		}
		ConnectionManager.getInstance().commitTransaction();
		return competitionDTO;
	}

}
