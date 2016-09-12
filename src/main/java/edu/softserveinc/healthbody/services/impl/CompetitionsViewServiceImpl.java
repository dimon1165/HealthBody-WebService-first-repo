package edu.softserveinc.healthbody.services.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.softserveinc.healthbody.constants.ErrorConstants;
import edu.softserveinc.healthbody.dao.AwardDao;
import edu.softserveinc.healthbody.dao.CompetitionsViewDao;
import edu.softserveinc.healthbody.dao.GroupCompetitionsDao;
import edu.softserveinc.healthbody.dao.GroupDao;
import edu.softserveinc.healthbody.dao.UserCompetitionsDao;
import edu.softserveinc.healthbody.dao.UserDao;
import edu.softserveinc.healthbody.db.ConnectionManager;
import edu.softserveinc.healthbody.dto.AwardDTO;
import edu.softserveinc.healthbody.dto.CompetitionDTO;
import edu.softserveinc.healthbody.dto.GroupCompetitionsDTO;
import edu.softserveinc.healthbody.dto.GroupDTO;
import edu.softserveinc.healthbody.dto.UserCompetitionsDTO;
import edu.softserveinc.healthbody.entity.Award;
import edu.softserveinc.healthbody.entity.CompetitionsView;
import edu.softserveinc.healthbody.entity.Group;
import edu.softserveinc.healthbody.entity.GroupCompetitions;
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
					.getAllCompetitionsView(connection, partNumber, partSize)) {
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
					.getActiveCompetitionsView(connection, partNumber, partSize)) {
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
					.getCompetitionsByUserView(connection, partNumber, partSize, login)) {
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
	public List<CompetitionDTO> getAllByGroup(final int partNumber, final int partSize, final String idGroup)
			throws IllegalAgrumentCheckedException, SQLException, JDBCDriverException, TransactionException {
		if (idGroup == null || idGroup.isEmpty()) {
			String errorStr = "Illegal parameter. \"idGroup\" is empty or null.";
			Log4jWrapper.get().error(errorStr);
			throw new IllegalAgrumentCheckedException(errorStr);
		}
		List<CompetitionDTO> competitionDTO = new ArrayList<>();
		Connection connection = ConnectionManager.getInstance().beginTransaction();
		try {
			for (CompetitionsView competitionsView : CompetitionsViewDao.getInstance()
					.getCompetitionsByGroup(connection, partNumber, partSize, idGroup)) {
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
	public List<GroupDTO> getAllGroupsByCompetition(final int partNumber, final int partSize, final String idCompetition)
			throws IllegalAgrumentCheckedException, SQLException, JDBCDriverException, TransactionException {
		if (idCompetition == null || idCompetition.isEmpty()) {
			String errorStr = "Illegal parameter. \"idCompetition\" is empty or null.";
			Log4jWrapper.get().error(errorStr);
			throw new IllegalAgrumentCheckedException(errorStr);
		}
		List<GroupDTO> groupDTO = new ArrayList<>();
		Connection connection = ConnectionManager.getInstance().beginTransaction();
		try {
			for (Group group : GroupDao.getInstance().getGroupsByIdCompetition(connection, partNumber, partSize,
					idCompetition)) {
				groupDTO.add(new GroupDTO(group.getIdGroup(), group.getName(), group.getCount().toString(),
						group.getDescription(), group.getScoreGroup(), group.getStatus(), null, null, null));
			}
		} catch (QueryNotFoundException | DataBaseReadingException e) {
			ConnectionManager.getInstance().rollbackTransaction(connection);
			throw new TransactionException(ErrorConstants.TRANSACTION_ERROR, e);
		}
		ConnectionManager.getInstance().commitTransaction(connection);
		return groupDTO;
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
					.getActiveCompetitionsByUserView(connection, partNumber, partSize, login)) {
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
	public CompetitionDTO getCompetition(String idCompetition)
			throws JDBCDriverException, SQLException, TransactionException {
		CompetitionsView competitionview;
		Connection connection = ConnectionManager.getInstance().beginTransaction();
		try {
			competitionview = CompetitionsViewDao.getInstance().getCompetitionViewById(connection, idCompetition);
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
	public CompetitionDTO getCompetitionByName(String name)
			throws JDBCDriverException, SQLException, TransactionException {
		CompetitionsView competitionview;
		Connection connection = ConnectionManager.getInstance().beginTransaction();
		try {
			competitionview = CompetitionsViewDao.getInstance().getCompetitionViewByName(connection, name);
		} catch (QueryNotFoundException | DataBaseReadingException e) {
			ConnectionManager.getInstance().rollbackTransaction(connection);
			throw new TransactionException(ErrorConstants.TRANSACTION_ERROR, e);
		}
		ConnectionManager.getInstance().commitTransaction(connection);
		if (competitionview == null)
			return null;
		return new CompetitionDTO(competitionview.getIdCompetition(), competitionview.getName(),
				String.valueOf(competitionview.getUsersCount()), competitionview.getStart(),
				competitionview.getFinish(), competitionview.getDescription(), null, null, null);
	}

	@Override
	public boolean addUserInCompetition(String idCompetition, String nameUser)
			throws SQLException, JDBCDriverException, TransactionException {
		boolean result = false;
		Connection connection = ConnectionManager.getInstance().beginTransaction();
		try {
			CompetitionsView competitionview = CompetitionsViewDao.getInstance().getCompetitionViewById(connection,
					idCompetition);
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
	public boolean addGroupInCompetition(String idCompetition, String idGroup)
			throws SQLException, JDBCDriverException, TransactionException {
		boolean result = false;
		Connection connection = ConnectionManager.getInstance().beginTransaction();
		try {
			CompetitionsView competitionview = CompetitionsViewDao.getInstance().getCompetitionViewById(connection,
					idCompetition);
			Group group = GroupDao.getInstance().getGroupById(connection, idGroup);
			GroupCompetitionsDao.getInstance().createGroupCompetition(connection, group, competitionview);
		} catch (QueryNotFoundException | DataBaseReadingException e) {
			ConnectionManager.getInstance().rollbackTransaction(connection);
			throw new TransactionException(ErrorConstants.TRANSACTION_ERROR, e);
		}
		ConnectionManager.getInstance().commitTransaction(connection);
		result = true;
		return result;
	}

	@Override
	public boolean removeUserFromCompetition(String idCompetition, String nameUser)
			throws SQLException, JDBCDriverException, TransactionException {
		boolean result = false;
		Connection connection = ConnectionManager.getInstance().beginTransaction();
		try {
			CompetitionsView competitionview = CompetitionsViewDao.getInstance().getCompetitionViewById(connection,
					idCompetition);
			User user = UserDao.getInstance().getUserByLoginName(connection, nameUser);
			result = UserCompetitionsDao.getInstance().deleteUserFromCompetition(connection, user.getId(),
					competitionview.getId());
		} catch (QueryNotFoundException | DataBaseReadingException | EmptyResultSetException
				| CloseStatementException e) {
			ConnectionManager.getInstance().rollbackTransaction(connection);
			throw new TransactionException(ErrorConstants.TRANSACTION_ERROR, e);
		}
		ConnectionManager.getInstance().commitTransaction(connection);
		return result;
	}

	@Override
	public UserCompetitionsDTO getUserCompetition(String idCompetition, String nameUser)
			throws SQLException, JDBCDriverException, TransactionException {
		Connection connection = ConnectionManager.getInstance().beginTransaction();
		try {
			CompetitionsView competitionview = CompetitionsViewDao.getInstance().getCompetitionViewById(connection,
					idCompetition);
			User user = UserDao.getInstance().getUserByLoginName(connection, nameUser);
			List<UserCompetitions> list = UserCompetitionsDao.getInstance().getAllbyId(connection, user.getIdUser());
			for (UserCompetitions usercompetition : list) {
				if (usercompetition.getIdCompetition().equals(competitionview.getIdCompetition())) {
					return new UserCompetitionsDTO(usercompetition.getIdUserCompetition(), nameUser, null,
							usercompetition.getUserScore().toString(), usercompetition.getIdAwards(),
							usercompetition.getTimeReceived());
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
	public GroupCompetitionsDTO getGroupCompetition(String idCompetition, String idGroup)
			throws SQLException, JDBCDriverException, TransactionException {
		Connection connection = ConnectionManager.getInstance().beginTransaction();
		try {
			List<GroupCompetitions> list = GroupCompetitionsDao.getInstance().getGroupCompetitionsByGroupId(connection, idGroup);
			for (GroupCompetitions groupcompetition : list) {
				if (groupcompetition.getIdCompetition().equals(idCompetition)) {
					return new GroupCompetitionsDTO(groupcompetition.getIdGroupCompetitions(), idCompetition, idGroup);
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
	public void updateUserCompetition(UserCompetitionsDTO userCompetition) throws SQLException, JDBCDriverException,
			TransactionException, QueryNotFoundException, DataBaseReadingException {
		if (userCompetition == null) {
			Log4jWrapper.get().error("You didn't enter userCompetition");
			throw new IllegalArgumentException();
		} else {
			Connection connection = ConnectionManager.getInstance().beginTransaction();
			UserCompetitions userCompetitions = UserCompetitionsDao.getInstance().getByFieldName(connection,
					userCompetition.getIdUserCompetition());
			try {
				UserCompetitionsDao.getInstance().updateUserCompetition(connection,
						new UserCompetitions(userCompetitions.getIdUserCompetition(), userCompetitions.getIdUser(),
								userCompetitions.getIdCompetition(), Integer.parseInt(userCompetition.getUserScore()),
								userCompetition.getAwardsName(), userCompetition.getTimeReceivedAward()));
			} catch (JDBCDriverException | DataBaseReadingException | QueryNotFoundException e) {
				ConnectionManager.getInstance().rollbackTransaction(connection);
				throw new TransactionException(ErrorConstants.TRANSACTION_ERROR, e);
			}
			ConnectionManager.getInstance().commitTransaction(connection);
		}
	}

	@Override
	public void deleteUserCompetition(String idCompetition, String nameUser)
			throws SQLException, JDBCDriverException, TransactionException {
		Connection connection = ConnectionManager.getInstance().beginTransaction();
		try {
			CompetitionsView competitionview = CompetitionsViewDao.getInstance().getCompetitionViewById(connection,
					idCompetition);
			User user = UserDao.getInstance().getUserByLoginName(connection, nameUser);
			List<UserCompetitions> list = UserCompetitionsDao.getInstance().getAllbyId(connection, user.getIdUser());
			for (UserCompetitions usercompetition : list) {
				if (usercompetition.getIdCompetition().equals(competitionview.getIdCompetition())) {
					UserCompetitionsDao.getInstance().deleteByUserCompetitionId(connection,
							usercompetition.getIdUserCompetition());
				}
			}
		} catch (JDBCDriverException | DataBaseReadingException | QueryNotFoundException | CloseStatementException
				| EmptyResultSetException e) {
			ConnectionManager.getInstance().rollbackTransaction(connection);
			throw new TransactionException(ErrorConstants.TRANSACTION_ERROR, e);
		}
		ConnectionManager.getInstance().commitTransaction(connection);
	}

	@Override
	public void deleteGroupCompetition(String idCompetition, String idGroup)
			throws SQLException, JDBCDriverException, TransactionException {
		Connection connection = ConnectionManager.getInstance().beginTransaction();
		try {
			List<GroupCompetitions> list = GroupCompetitionsDao.getInstance().getGroupCompetitionsByGroupId(connection, idGroup);
			for (GroupCompetitions groupcompetition : list) {
				if (groupcompetition.getIdCompetition().equals(idCompetition)) {
					GroupCompetitionsDao.getInstance().deleteByGroupCompetitionId(connection,
							groupcompetition.getIdGroupCompetitions());
				}
			}
		} catch (JDBCDriverException | DataBaseReadingException | QueryNotFoundException | CloseStatementException
				| EmptyResultSetException e) {
			ConnectionManager.getInstance().rollbackTransaction(connection);
			throw new TransactionException(ErrorConstants.TRANSACTION_ERROR, e);
		}
		ConnectionManager.getInstance().commitTransaction(connection);
	}
	
	@Override
	public List<AwardDTO> getAllAwards()
			throws JDBCDriverException, SQLException, TransactionException {
		List<AwardDTO> awardDTO = new ArrayList<>();
		Connection connection = ConnectionManager.getInstance().beginTransaction();
		try {
			for (Award award : AwardDao.getInstance().getAllAwards(connection)) {
				awardDTO.add(new AwardDTO(award.getIdAward(), award.getName()));
			}
		} catch (DataBaseReadingException e) {
			ConnectionManager.getInstance().rollbackTransaction(connection);
			throw new TransactionException(ErrorConstants.TRANSACTION_ERROR, e);
		}
		ConnectionManager.getInstance().commitTransaction(connection);
		return awardDTO;
	}

}
