package edu.softserveinc.healthbody.webservice;

import java.sql.SQLException;
import java.util.List;

import javax.jws.WebService;

import edu.softserveinc.healthbody.dto.CompetitionDTO;
import edu.softserveinc.healthbody.dto.GroupDTO;
import edu.softserveinc.healthbody.dto.UserDTO;
import edu.softserveinc.healthbody.exceptions.CloseStatementException;
import edu.softserveinc.healthbody.exceptions.DataBaseReadingException;
import edu.softserveinc.healthbody.exceptions.EmptyResultSetException;
import edu.softserveinc.healthbody.exceptions.IllegalAgrumentCheckedException;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.QueryNotFoundException;
import edu.softserveinc.healthbody.exceptions.TransactionException;
import edu.softserveinc.healthbody.log.LoggerWrapper;
import edu.softserveinc.healthbody.services.impl.CompetitionsServiceImpl;
import edu.softserveinc.healthbody.services.impl.CompetitionsViewServiceImpl;
import edu.softserveinc.healthbody.services.impl.GroupServiceImpl;
import edu.softserveinc.healthbody.services.impl.UserProfileServiceImpl;
import edu.softserveinc.healthbody.services.impl.UsersViewServiceImpl;

@WebService(endpointInterface = "edu.softserveinc.healthbody.webservice.HealthBodyService")
public class HealthBodyServiceImpl implements HealthBodyService {

	@Override
	public void createUser(UserDTO userDTO) {
		try {
			UserProfileServiceImpl.getInstance().insert(userDTO);
		} catch (SQLException | JDBCDriverException | DataBaseReadingException | QueryNotFoundException
				| EmptyResultSetException | TransactionException | CloseStatementException e) {
			LoggerWrapper.error(this.getClass(), "create user failed" + e);
		}
	}

	@Override
	public UserDTO getUserByLogin(String login) {
		try {
			return UserProfileServiceImpl.getInstance().get(login);
		} catch (SQLException | JDBCDriverException | EmptyResultSetException | TransactionException
				| CloseStatementException e) {
			LoggerWrapper.error(this.getClass(), "get user by login failed" + e);
		}
		return null;
	}

	@Override
	public void updateUser(String login, String password, String age, String weight) {
		try {
			UserDTO userDTO = UserProfileServiceImpl.getInstance().get(login);
			userDTO.setPassword(password);
			userDTO.setAge(age);
			userDTO.setWeight(weight);
			UserProfileServiceImpl.getInstance().update(userDTO);
		} catch (SQLException | JDBCDriverException | DataBaseReadingException | QueryNotFoundException
				| EmptyResultSetException | TransactionException | CloseStatementException e) {
			LoggerWrapper.error(this.getClass(), "update user failed" + e);
		}
	}

	@Override
	public void lockUser(String login, boolean isDisabled) {
		try {
			UserDTO userDTO = UserProfileServiceImpl.getInstance().get(login);
			UserProfileServiceImpl.getInstance().lock(userDTO, isDisabled);
		} catch (SQLException | JDBCDriverException | QueryNotFoundException | DataBaseReadingException
				| TransactionException | CloseStatementException | EmptyResultSetException e) {
			LoggerWrapper.error(this.getClass(), "lock user failed" + e);
		}
	}

	@Override
	public List<UserDTO> getAllUsers(int partNumber, int partSize) {
		UsersViewServiceImpl user = new UsersViewServiceImpl();
		try {
			return user.getAll(partNumber, partSize);
		} catch (JDBCDriverException | SQLException | TransactionException e) {
			LoggerWrapper.error(this.getClass(), "get all users failed" + e);
		}
		return null;
	}

	@Override
	public List<UserDTO> getAllUserstoAddInCompetition(int partNumber, int partSize) {
		UsersViewServiceImpl user = new UsersViewServiceImpl();
		try {
			return user.getAlltoAddInCompetition(partNumber, partSize);
		} catch (JDBCDriverException | SQLException | TransactionException e) {
			LoggerWrapper.error(this.getClass(), "get all users to add in competition failed" + e);
		}
		return null;
	}

	@Override
	public List<UserDTO> getAllUsersinCompetition(int partNumber, int partSize) {
		UsersViewServiceImpl user = new UsersViewServiceImpl();
		try {
			return user.getAllinCompetition(partNumber, partSize);
		} catch (JDBCDriverException | SQLException | TransactionException e) {
			LoggerWrapper.error(this.getClass(), "get all users in competition failed" + e);
		}
		return null;
	}

	@Override
	public List<GroupDTO> getAllGroups(int partNumber, int partSize) {
		try {
			return GroupServiceImpl.getInstance().getAll(partNumber, partSize);
		} catch (QueryNotFoundException | JDBCDriverException | DataBaseReadingException | EmptyResultSetException
				| CloseStatementException e) {
			LoggerWrapper.error(this.getClass(), "get all groups failed" + e);
		}
		return null;
	}

	@Override
	public GroupDTO getGroupByName(String name) {
		try {
			return GroupServiceImpl.getInstance().getGroup(name);
		} catch (QueryNotFoundException | JDBCDriverException | DataBaseReadingException | CloseStatementException e) {
			LoggerWrapper.error(this.getClass(), "get group by name failed" + e);
		}
		return null;
	}

	@Override
	public String getDescriptionOfGroup(String name) {
		GroupDTO groupDTO;
		try {
			groupDTO = GroupServiceImpl.getInstance().getGroup(name);
			return  GroupServiceImpl.getInstance().getDescriptionOfGroup(groupDTO);
		} catch (QueryNotFoundException | JDBCDriverException | DataBaseReadingException | CloseStatementException e) {
			LoggerWrapper.error(this.getClass(), "get description of group" + e);
		}
		return null;
	}

	@Override
	public void updateGroup(String name, String count, String description, String score) {
		try {
			GroupDTO groupDTO = GroupServiceImpl.getInstance().getGroup(name);
			groupDTO.setCount(count);
			groupDTO.setDescriptions(description);
			groupDTO.setScoreGroup(score);
			GroupServiceImpl.getInstance().update(groupDTO);
		} catch (SQLException | JDBCDriverException | DataBaseReadingException | QueryNotFoundException
				| EmptyResultSetException | TransactionException | CloseStatementException e) {
			LoggerWrapper.error(this.getClass(), "update group failed" + e);
		}
	}

	@Override
	public List<CompetitionDTO> getAllCompetitions(int partNumber, int partSize) {
		CompetitionsViewServiceImpl competitionView = new CompetitionsViewServiceImpl();
		try {
			return competitionView.getAll(partNumber, partSize);
		} catch (JDBCDriverException | SQLException | TransactionException e) {
			LoggerWrapper.error(this.getClass(), "get all competitions failed" + e);
		}
		return null;
	}

	@Override
	public List<CompetitionDTO> getAllActiveCompetitions(int partNumber, int partSize) {
		CompetitionsViewServiceImpl competitionView = new CompetitionsViewServiceImpl();
		try {
			return competitionView.getAllActive(partNumber, partSize);
		} catch (JDBCDriverException | SQLException | TransactionException e) {
			LoggerWrapper.error(this.getClass(), "get all active competitions failed" + e);
		}
		return null;
	}

	@Override
	public List<CompetitionDTO> getAllCompetitionsByUser(int partNumber, int partSize, String login) {
		CompetitionsViewServiceImpl competitionView = new CompetitionsViewServiceImpl();
		try {
			return competitionView.getAllByUser(partNumber, partSize, login);
		} catch (IllegalAgrumentCheckedException | SQLException | JDBCDriverException | TransactionException e) {
			LoggerWrapper.error(this.getClass(), "get all competitions by user failed" + e);
		}
		return null;
	}

	@Override
	public List<CompetitionDTO> getAllActiveCompetitionsByUser(int partNumber, int partSize, String login) {
		CompetitionsViewServiceImpl competitionView = new CompetitionsViewServiceImpl();
		try {
			return competitionView.getAllActiveByUser(partNumber, partSize, login);
		} catch (IllegalAgrumentCheckedException | SQLException | JDBCDriverException | TransactionException e) {
			LoggerWrapper.error(this.getClass(), "get all active competitions by user failed" + e);
		}
		return null;
	}

	@Override
	public void createCompetition(CompetitionDTO competitionDTO) {
		CompetitionsServiceImpl competition = new CompetitionsServiceImpl();
		try {
			competition.insert(competitionDTO);
		} catch (SQLException | JDBCDriverException | DataBaseReadingException | QueryNotFoundException
				| EmptyResultSetException | TransactionException | CloseStatementException e) {
			LoggerWrapper.error(this.getClass(), "create competition failed" + e);
		}

	}

}
