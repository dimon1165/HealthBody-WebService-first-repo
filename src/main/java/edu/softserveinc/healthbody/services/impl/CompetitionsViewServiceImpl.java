package edu.softserveinc.healthbody.services.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.softserveinc.healthbody.constants.ErrorConstants;
import edu.softserveinc.healthbody.dao.CompetitionsViewDao;
import edu.softserveinc.healthbody.dao.UserCompetitionsDao;
import edu.softserveinc.healthbody.dao.UserDao;
import edu.softserveinc.healthbody.db.ConnectionManager;
import edu.softserveinc.healthbody.dto.CompetitionDTO;
import edu.softserveinc.healthbody.dto.UserCompetitionsDTO;
import edu.softserveinc.healthbody.entity.CompetitionsView;
import edu.softserveinc.healthbody.entity.User;

import edu.softserveinc.healthbody.entity.UserCompetitions;
import edu.softserveinc.healthbody.exceptions.CloseStatementException;
import edu.softserveinc.healthbody.exceptions.DataBaseReadingException;
import edu.softserveinc.healthbody.exceptions.EmptyResultSetException;
import edu.softserveinc.healthbody.exceptions.IllegalAgrumentCheckedException;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.QueryNotFoundException;
import edu.softserveinc.healthbody.exceptions.TransactionException;
import edu.softserveinc.healthbody.log.Log4jWrapper;
import edu.softserveinc.healthbody.services.ICompetitionsViewService;

public class CompetitionsViewServiceImpl implements ICompetitionsViewService {

	private static volatile CompetitionsViewServiceImpl instance;

	private CompetitionsViewServiceImpl() {
	}

	public static CompetitionsViewServiceImpl getInstance() {
		if (instance == null) {
			synchronized (CompetitionsViewServiceImpl.class) {
				if (instance == null) {
					instance = new CompetitionsViewServiceImpl();
				}
			}
		}
		return instance;
	}

	@Override
	public List<CompetitionDTO> getAll(final int partNumber, final int partSize)
			throws JDBCDriverException, SQLException, TransactionException {
		List<CompetitionDTO> competitionDTO = new ArrayList<>();
		Connection connection = ConnectionManager.getInstance().beginTransaction();
		try {
			for (CompetitionsView competitionsView : CompetitionsViewDao.getInstance()
					.getAllCompetitionsView(partNumber, partSize)) {
				competitionDTO.add(new CompetitionDTO(competitionsView.getIdCompetition(), competitionsView.getName(),
						competitionsView.getUsersCount().toString(), competitionsView.getStart(),
						competitionsView.getFinish(), competitionsView.getDescription(), null, new ArrayList<String>(),
						new ArrayList<String>()));
			}
		} catch (QueryNotFoundException | DataBaseReadingException e) {
			ConnectionManager.getInstance().rollbackTransaction(connection);
			throw new TransactionException(ErrorConstants.TRANSACTION_ERROR, e);
		}
		ConnectionManager.getInstance().commitTransaction(connection);
		return competitionDTO;
	}

	@Override
	public List<CompetitionDTO> getAllActive(final int partNumber, final int partSize)
			throws JDBCDriverException, SQLException, TransactionException {
		List<CompetitionDTO> competitionDTO = new ArrayList<>();
		Connection connection = ConnectionManager.getInstance().beginTransaction();
		try {
			for (CompetitionsView competitionsView : CompetitionsViewDao.getInstance()
					.getActiveCompetitionsView(partNumber, partSize)) {
				competitionDTO.add(new CompetitionDTO(competitionsView.getIdCompetition(), competitionsView.getName(),
						competitionsView.getUsersCount().toString(), competitionsView.getStart(),
						competitionsView.getFinish(), competitionsView.getDescription(), null, new ArrayList<String>(),
						new ArrayList<String>()));
			}
		} catch (QueryNotFoundException | DataBaseReadingException e) {
			ConnectionManager.getInstance().rollbackTransaction(connection);
			throw new TransactionException(ErrorConstants.TRANSACTION_ERROR, e);
		}
		ConnectionManager.getInstance().commitTransaction(connection);
		return competitionDTO;
	}

	@Override
	public List<CompetitionDTO> getAllByUser(final int partNumber, final int partSize, final String login)
			throws IllegalAgrumentCheckedException, SQLException, JDBCDriverException, TransactionException {
		if (login == null || login.isEmpty()) {
			String errorStr = "Illegal parameter. \"login\" is empty or null.";
			Log4jWrapper.get().error(errorStr);
			throw new IllegalAgrumentCheckedException(errorStr);
		}
		List<CompetitionDTO> competitionDTO = new ArrayList<>();
		Connection connection = ConnectionManager.getInstance().beginTransaction();
		try {
			for (CompetitionsView competitionsView : CompetitionsViewDao.getInstance()
					.getCompetitionsByUserView(partNumber, partSize, login)) {
				competitionDTO.add(new CompetitionDTO(competitionsView.getIdCompetition(), competitionsView.getName(),
						competitionsView.getUsersCount().toString(), competitionsView.getStart(),
						competitionsView.getFinish(), competitionsView.getDescription(), null, new ArrayList<String>(),
						new ArrayList<String>()));
			}
		} catch (QueryNotFoundException | DataBaseReadingException e) {
			ConnectionManager.getInstance().rollbackTransaction(connection);
			throw new TransactionException(ErrorConstants.TRANSACTION_ERROR, e);
		}
		ConnectionManager.getInstance().commitTransaction(connection);
		return competitionDTO;
	}

	@Override
	public List<CompetitionDTO> getAllActiveByUser(final int partNumber, final int partSize, final String login)
			throws IllegalAgrumentCheckedException, SQLException, JDBCDriverException, TransactionException {
		if (login == null || login.isEmpty()) {
			String errorStr = "Illegal parameter. \"login\" is empty or null.";
			Log4jWrapper.get().error(errorStr);
			throw new IllegalAgrumentCheckedException(errorStr);
		}
		List<CompetitionDTO> competitionDTO = new ArrayList<>();
		Connection connection = ConnectionManager.getInstance().beginTransaction();
		try {
			for (CompetitionsView competitionsView : CompetitionsViewDao.getInstance()
					.getActiveCompetitionsByUserView(partNumber, partSize, login)) {
				competitionDTO.add(new CompetitionDTO(competitionsView.getIdCompetition(), competitionsView.getName(),
						competitionsView.getUsersCount().toString(), competitionsView.getStart(),
						competitionsView.getFinish(), competitionsView.getDescription(), null, new ArrayList<String>(),
						new ArrayList<String>()));
			}
		} catch (QueryNotFoundException | DataBaseReadingException e) {
			ConnectionManager.getInstance().rollbackTransaction(connection);
			throw new TransactionException(ErrorConstants.TRANSACTION_ERROR, e);
		}
		ConnectionManager.getInstance().commitTransaction(connection);
		return competitionDTO;
	}

	@Override
	public CompetitionDTO getCompetition(String name) throws JDBCDriverException, SQLException, TransactionException {
		CompetitionsView competitionview;
		Connection connection = ConnectionManager.getInstance().beginTransaction();
		try {
			competitionview = CompetitionsViewDao.getInstance().getCompetitionViewByName(connection, name);
		} catch (QueryNotFoundException | DataBaseReadingException e) {
			ConnectionManager.getInstance().rollbackTransaction(connection);
			throw new TransactionException(ErrorConstants.TRANSACTION_ERROR, e);
		}
		ConnectionManager.getInstance().commitTransaction(connection);
		return new CompetitionDTO(competitionview.getIdCompetition(), competitionview.getName(),
				String.valueOf(competitionview.getUsersCount()), competitionview.getStart(),
				competitionview.getFinish(), competitionview.getDescription(), null, null, null);
	}

	@Override
	public boolean addUserInCompetition(String nameCompetition, String nameUser)
			throws SQLException, JDBCDriverException, TransactionException {
		boolean result = false;
		Connection connection = ConnectionManager.getInstance().beginTransaction();
		try {
			CompetitionsView competitionview = CompetitionsViewDao.getInstance().getCompetitionViewByName(connection,
					nameCompetition);
			User user = UserDao.getInstance().getUserByLoginName(connection, nameUser);
			UserCompetitionsDao.getInstance().createUserCompetition(connection, user, competitionview);
		} catch (QueryNotFoundException | DataBaseReadingException e) {
			ConnectionManager.getInstance().rollbackTransaction(connection);
			throw new TransactionException(ErrorConstants.TRANSACTION_ERROR, e);
		}
		ConnectionManager.getInstance().commitTransaction(connection);
		result = true;
		return result;
	}

	@Override
	public boolean removeUserFromCompetition(String nameCompetition, String nameUser)
			throws SQLException, JDBCDriverException, TransactionException {
		boolean result = false;
		Connection connection = ConnectionManager.getInstance().beginTransaction();
		try {
		CompetitionsView competitionview = CompetitionsViewDao.getInstance().getCompetitionViewByName(connection, nameCompetition);
		User user = UserDao.getInstance().getUserByLoginName(connection, nameUser);	
			result = UserCompetitionsDao.getInstance().deleteUserFromCompetition(connection, user.getId(), competitionview.getId());	
		} catch (QueryNotFoundException | DataBaseReadingException | EmptyResultSetException | CloseStatementException e) {
			ConnectionManager.getInstance().rollbackTransaction(connection);
			throw new TransactionException(ErrorConstants.TRANSACTION_ERROR, e);
		}
		ConnectionManager.getInstance().commitTransaction(connection);				
		return result;
	}
	
	@Override
	public UserCompetitionsDTO getUserCompetition(String nameCompetition, String nameUser)
			throws SQLException, JDBCDriverException, TransactionException {
		Connection connection = ConnectionManager.getInstance().beginTransaction();
		try {
			CompetitionsView competitionview = CompetitionsViewDao.getInstance().getCompetitionViewByName(connection,
					nameCompetition);
			User user = UserDao.getInstance().getUserByLoginName(connection, nameUser);
			List<UserCompetitions> list = UserCompetitionsDao.getInstance().getAllbyId(connection, user.getIdUser());
			for (UserCompetitions usercompetition : list) {
				if (usercompetition.getIdCompetition().equals(competitionview.getIdCompetition())) {
					return new UserCompetitionsDTO(usercompetition.getIdUserCompetition(), nameUser, null, 
							usercompetition.getUserScore().toString(), usercompetition.getIdAwards(), usercompetition.getTimeReceived());
				}
			}
		} catch (JDBCDriverException | DataBaseReadingException | QueryNotFoundException | CloseStatementException
				| EmptyResultSetException e) {
			ConnectionManager.getInstance().rollbackTransaction(connection);
			throw new TransactionException(ErrorConstants.TRANSACTION_ERROR, e);
		}
		ConnectionManager.getInstance().commitTransaction(connection);
		return null;
	}
	
	@Override
	public void deleteUserCompetition(String nameCompetition, String nameUser)
			throws SQLException, JDBCDriverException, TransactionException {
		Connection connection = ConnectionManager.getInstance().beginTransaction();
		try {
			CompetitionsView competitionview = CompetitionsViewDao.getInstance().getCompetitionViewByName(connection,
					nameCompetition);
			User user = UserDao.getInstance().getUserByLoginName(connection, nameUser);
			List<UserCompetitions> list = UserCompetitionsDao.getInstance().getAllbyId(connection, user.getIdUser());
			for (UserCompetitions usercompetition : list) {
				if (usercompetition.getIdCompetition().equals(competitionview.getIdCompetition())) {
					UserCompetitionsDao.getInstance().deleteByUserCompetitionId(connection, usercompetition.getIdUserCompetition());
				}
			}
		} catch (JDBCDriverException | DataBaseReadingException | QueryNotFoundException | CloseStatementException
				| EmptyResultSetException e) {
			ConnectionManager.getInstance().rollbackTransaction(connection);
			throw new TransactionException(ErrorConstants.TRANSACTION_ERROR, e);
		}
		ConnectionManager.getInstance().commitTransaction(connection);
	}
	
}
