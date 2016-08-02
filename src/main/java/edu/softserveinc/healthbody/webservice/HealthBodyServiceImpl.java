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
import edu.softserveinc.healthbody.log.Log4jWrapper;
import edu.softserveinc.healthbody.services.impl.CompetitionsServiceImpl;
import edu.softserveinc.healthbody.services.impl.CompetitionsViewServiceImpl;
import edu.softserveinc.healthbody.services.impl.GroupServiceImpl;
import edu.softserveinc.healthbody.services.impl.UserProfileServiceImpl;
import edu.softserveinc.healthbody.services.impl.UsersViewServiceImpl;

@WebService(endpointInterface = "edu.softserveinc.healthbody.webservice.HealthBodyService")
public class HealthBodyServiceImpl implements HealthBodyService {

	@Override
	public final void createUser(final UserDTO userDTO) {
		try {
			UserProfileServiceImpl.getInstance().insert(userDTO);
		} catch (SQLException | JDBCDriverException | TransactionException e) {
			Log4jWrapper.get().error("create user failed. " + e);
		}
	}

	@Override
	public final UserDTO getUserByLogin(final String login) {
		try {
			return UserProfileServiceImpl.getInstance().get(login);
		} catch (SQLException | JDBCDriverException | TransactionException e) {
			Log4jWrapper.get().error("get user by login failed. " + e);
		}
		return null;
	}

	@Override
	public final void updateUser(final String login, final String password, final String age, final String weight) {
		try {
			UserDTO userDTO = UserProfileServiceImpl.getInstance().get(login);
			userDTO.setPassword(password);
			userDTO.setAge(age);
			userDTO.setWeight(weight);
			UserProfileServiceImpl.getInstance().update(userDTO);
		} catch (SQLException | JDBCDriverException | TransactionException e) {
			Log4jWrapper.get().error("update user failed" + e);
		}
	}

	@Override
	public final void lockUser(final String login, final boolean isDisabled) {
		try {
			UserDTO userDTO = UserProfileServiceImpl.getInstance().get(login);
			UserProfileServiceImpl.getInstance().lock(userDTO, isDisabled);
		} catch (SQLException | JDBCDriverException | TransactionException e) {
			Log4jWrapper.get().error("lock user failed" + e);
		}
	}

	@Override
	public final List<UserDTO> getAllUsers(final int partNumber, final int partSize) {
		UsersViewServiceImpl user = new UsersViewServiceImpl();
		try {
			return user.getAll(partNumber, partSize);
		} catch (JDBCDriverException | SQLException | TransactionException e) {
			Log4jWrapper.get().error("get all users failed" + e);
		}
		return null;
	}

	@Override
	public final List<UserDTO> getAllUserstoAddInCompetition(final int partNumber, final int partSize) {
		UsersViewServiceImpl user = new UsersViewServiceImpl();
		try {
			return user.getAlltoAddInCompetition(partNumber, partSize);
		} catch (JDBCDriverException | SQLException | TransactionException e) {
			Log4jWrapper.get().error("get all users to add in competition failed" + e);
		}
		return null;
	}

	@Override
	public final List<UserDTO> getAllUsersinCompetition(final int partNumber, final int partSize) {
		UsersViewServiceImpl user = new UsersViewServiceImpl();
		try {
			return user.getAllinCompetition(partNumber, partSize);
		} catch (JDBCDriverException | SQLException | TransactionException e) {
			Log4jWrapper.get().error("get all users in competition failed" + e);
		}
		return null;
	}

	@Override
	public final List<GroupDTO> getAllGroups(final int partNumber, final int partSize) {
		try {
			try {
				return GroupServiceImpl.getInstance().getAll(partNumber, partSize);
			} catch (SQLException | TransactionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (QueryNotFoundException | JDBCDriverException | DataBaseReadingException e) {
			Log4jWrapper.get().error("get all groups failed" + e);
		}
		return null;
	}

	@Override
	public final GroupDTO getGroupByName(final String name) {
		try {
			try {
				return GroupServiceImpl.getInstance().getGroup(name);
			} catch (SQLException | TransactionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (QueryNotFoundException | JDBCDriverException | DataBaseReadingException e) {
			Log4jWrapper.get().error("get group by name failed" + e);
		}
		return null;
	}

	@Override
	public final String getDescriptionOfGroup(final String name) {
		GroupDTO groupDTO = null;
		try {
			try {
				groupDTO = GroupServiceImpl.getInstance().getGroup(name);
			} catch (SQLException | TransactionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return  GroupServiceImpl.getInstance().getDescriptionOfGroup(groupDTO);
		} catch (QueryNotFoundException | JDBCDriverException | DataBaseReadingException e) {
			Log4jWrapper.get().error("get description of group" + e);
		}
		return null;
	}

	@Override
	public final void updateGroup(final String name, final String count, final String description, final String score) {
		try {
			GroupDTO groupDTO = GroupServiceImpl.getInstance().getGroup(name);
			groupDTO.setCount(count);
			groupDTO.setDescriptions(description);
			groupDTO.setScoreGroup(score);
			GroupServiceImpl.getInstance().update(groupDTO);
		} catch (SQLException | JDBCDriverException | DataBaseReadingException 
				| QueryNotFoundException | TransactionException e) {
			Log4jWrapper.get().error("update group failed" + e);
		}
	}

	@Override
	public final List<CompetitionDTO> getAllCompetitions(final int partNumber, final int partSize) {
		try {
			return CompetitionsViewServiceImpl.getInstance().getAll(partNumber, partSize);
		} catch (JDBCDriverException | SQLException | TransactionException e) {
			Log4jWrapper.get().error("get all competitions failed" + e);
		}
		return null;
	}

	@Override
	public final List<CompetitionDTO> getAllActiveCompetitions(final int partNumber, final int partSize) {
		try {
			return CompetitionsViewServiceImpl.getInstance().getAllActive(partNumber, partSize);
		} catch (JDBCDriverException | SQLException | TransactionException e) {
			Log4jWrapper.get().error("get all active competitions failed" + e);
		}
		return null;
	}

	@Override
	public final List<CompetitionDTO> getAllCompetitionsByUser(final int partNumber,
			final int partSize, final String login) {
		try {
			return CompetitionsViewServiceImpl.getInstance().getAllByUser(partNumber, partSize, login);
		} catch (IllegalAgrumentCheckedException | SQLException | JDBCDriverException | TransactionException e) {
			Log4jWrapper.get().error("get all competitions by user failed" + e);
		}
		return null;
	}

	@Override
	public final List<CompetitionDTO> getAllActiveCompetitionsByUser(final int partNumber,
			final int partSize, final String login) {
		try {
			return CompetitionsViewServiceImpl.getInstance().getAllActiveByUser(partNumber, partSize, login);
		} catch (IllegalAgrumentCheckedException | SQLException | JDBCDriverException | TransactionException e) {
			Log4jWrapper.get().error("get all active competitions by user failed" + e);
		}
		return null;
	}

	@Override
	public final void createCompetition(final CompetitionDTO competitionDTO) {
		CompetitionsServiceImpl competition = new CompetitionsServiceImpl();
		try {
			try {
				competition.insert(competitionDTO);
			} catch (DataBaseReadingException | QueryNotFoundException | EmptyResultSetException
					| CloseStatementException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (SQLException | JDBCDriverException | TransactionException e) {
			Log4jWrapper.get().error("create competition failed" + e);
		}
	}
}
