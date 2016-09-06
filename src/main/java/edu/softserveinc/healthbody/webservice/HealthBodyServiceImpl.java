package edu.softserveinc.healthbody.webservice;

import java.sql.SQLException;
import java.util.List;

import javax.jws.WebService;

import edu.softserveinc.healthbody.dto.CompetitionDTO;
import edu.softserveinc.healthbody.dto.GroupCompetitionsDTO;
import edu.softserveinc.healthbody.dto.GroupDTO;
import edu.softserveinc.healthbody.dto.UserCompetitionsDTO;
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
			Log4jWrapper.get().error("create user failed. ", e);
		}
	}

	@Override
	public final UserDTO getUserByLogin(final String login) {
		try {
			return UserProfileServiceImpl.getInstance().get(login);
		} catch (SQLException | JDBCDriverException | TransactionException e) {
			Log4jWrapper.get().error("get user by login failed. ", e);
		}
		return null;
	}

	@Override
	public final void updateUser(final UserDTO userDTO) {
		try {
			UserProfileServiceImpl.getInstance().update(userDTO);
		} catch (SQLException | JDBCDriverException | TransactionException e) {
			Log4jWrapper.get().error("update user failed ", e);
		}
	}

	@Override
	public final void lockUser(final String login, final boolean isDisabled) {
		try {
			UserDTO userDTO = UserProfileServiceImpl.getInstance().get(login);
			UserProfileServiceImpl.getInstance().lock(userDTO, isDisabled);
		} catch (SQLException | JDBCDriverException | TransactionException e) {
			Log4jWrapper.get().error("lock user failed ", e);
		}
	}

	@Override
	public final List<UserDTO> getAllUsers(final int partNumber, final int partSize) {
		UsersViewServiceImpl user = new UsersViewServiceImpl();
		try {
			return user.getAll(partNumber, partSize);
		} catch (JDBCDriverException | SQLException | TransactionException e) {
			Log4jWrapper.get().error("get all users failed ", e);
		}
		return null;
	}

	@Override
	public final List<UserDTO> getAllUserstoAddInCompetition(final int partNumber, final int partSize) {
		UsersViewServiceImpl user = new UsersViewServiceImpl();
		try {
			return user.getAlltoAddInCompetition(partNumber, partSize);
		} catch (JDBCDriverException | SQLException | TransactionException e) {
			Log4jWrapper.get().error("get all users to add in competition failed ", e);
		}
		return null;
	}

	@Override
	public final List<UserDTO> getAllUsersinCompetition(final int partNumber, final int partSize) {
		UsersViewServiceImpl user = new UsersViewServiceImpl();
		try {
			return user.getAllinCompetition(partNumber, partSize);
		} catch (JDBCDriverException | SQLException | TransactionException e) {
			Log4jWrapper.get().error("get all users in competition failed ", e);
		}
		return null;
	}

	@Override
	public final List<GroupDTO> getAllGroups(final int partNumber, final int partSize) {
		try {
			try {
				return GroupServiceImpl.getInstance().getAll(partNumber, partSize);
			} catch (SQLException | TransactionException e) {
				Log4jWrapper.get().error("get all groups failed ", e);
			}
		} catch (QueryNotFoundException | JDBCDriverException | DataBaseReadingException e) {
			Log4jWrapper.get().error("get all groups failed ", e);
		}
		return null;
	}

	@Override
	public final GroupDTO getGroupByName(final String name) {
		try {
			try {
				return GroupServiceImpl.getInstance().getGroup(name);
			} catch (SQLException | TransactionException e) {
				Log4jWrapper.get().error("get group by name failed ", e);
			}
		} catch (QueryNotFoundException | JDBCDriverException | DataBaseReadingException e) {
			Log4jWrapper.get().error("get group by name failed ", e);
		}
		return null;
	}

	@Override
	public GroupDTO getGroupById(String id) {
		try {
			try {
				return GroupServiceImpl.getInstance().getGroupById(id);
			} catch (SQLException | TransactionException e) {
				Log4jWrapper.get().error("get group by id failed ", e);
			}
		} catch (QueryNotFoundException | JDBCDriverException | DataBaseReadingException e) {
			Log4jWrapper.get().error("get group by id failed ", e);
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
				Log4jWrapper.get().error("get description of group ", e);
			}
			return GroupServiceImpl.getInstance().getDescriptionOfGroup(groupDTO);
		} catch (QueryNotFoundException | JDBCDriverException | DataBaseReadingException e) {
			Log4jWrapper.get().error("get description of group ", e);
		}
		return null;
	}

	@Override
	public void createGroup(GroupDTO groupDTO) {
		try {
			GroupServiceImpl.getInstance().insert(groupDTO);
		} catch (SQLException | JDBCDriverException | DataBaseReadingException | QueryNotFoundException
				| TransactionException e) {
			Log4jWrapper.get().error("update group failed ", e);
		}
	}

	@Override
	public final void updateGroup(final GroupDTO groupDTO) {
		try {
			GroupServiceImpl.getInstance().update(groupDTO);
		} catch (SQLException | JDBCDriverException | DataBaseReadingException | QueryNotFoundException
				| TransactionException e) {
			Log4jWrapper.get().error("update group failed ", e);
		}
	}

	@Override
	public final List<CompetitionDTO> getAllCompetitions(final int partNumber, final int partSize) {
		try {
			return CompetitionsViewServiceImpl.getInstance().getAll(partNumber, partSize);
		} catch (JDBCDriverException | SQLException | TransactionException e) {
			Log4jWrapper.get().error("get all competitions failed ", e);
		}
		return null;
	}

	@Override
	public final List<CompetitionDTO> getAllActiveCompetitions(final int partNumber, final int partSize) {
		try {
			return CompetitionsViewServiceImpl.getInstance().getAllActive(partNumber, partSize);
		} catch (JDBCDriverException | SQLException | TransactionException e) {
			Log4jWrapper.get().error("get all active competitions failed ", e);
		}
		return null;
	}

	@Override
	public final List<CompetitionDTO> getAllCompetitionsByUser(final int partNumber, final int partSize,
			final String login) {
		try {
			return CompetitionsViewServiceImpl.getInstance().getAllByUser(partNumber, partSize, login);
		} catch (IllegalAgrumentCheckedException | SQLException | JDBCDriverException | TransactionException e) {
			Log4jWrapper.get().error("get all competitions by user failed ", e);
		}
		return null;
	}
	
	@Override
	public final List<GroupDTO> getAllGroupsByCompetition(int partNumber, int partSize, String idCompetition) {
		try {
			return CompetitionsViewServiceImpl.getInstance().getAllGroupsByCompetition(partNumber, partSize, idCompetition);
		} catch (IllegalAgrumentCheckedException | SQLException | JDBCDriverException | TransactionException e) {
			Log4jWrapper.get().error("get all groups by competition failed ", e);
		}
		return null;
	}

	@Override
	public final List<CompetitionDTO> getAllActiveCompetitionsByUser(final int partNumber, final int partSize,
			final String login) {
		try {
			return CompetitionsViewServiceImpl.getInstance().getAllActiveByUser(partNumber, partSize, login);
		} catch (IllegalAgrumentCheckedException | SQLException | JDBCDriverException | TransactionException e) {
			Log4jWrapper.get().error("get all active competitions by user failed ", e);
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
				Log4jWrapper.get().error("create competition failed ", e);
			}
		} catch (SQLException | JDBCDriverException | TransactionException e) {
			Log4jWrapper.get().error("create competition failed ", e);
		}
	}

	@Override
	public final void updateCompetition(final CompetitionDTO competitionDTO) {
		CompetitionsServiceImpl competition = new CompetitionsServiceImpl();
		try {
			try {
				competition.update(competitionDTO);
			} catch (DataBaseReadingException | QueryNotFoundException | EmptyResultSetException
					| CloseStatementException e) {
				Log4jWrapper.get().error("update competition failed ", e);
			}
		} catch (SQLException | JDBCDriverException | TransactionException e) {
			Log4jWrapper.get().error("update competition failed ", e);
		}
	}

	@Override
	public List<GroupDTO> getAllGroupsParticipants(int partNumber, int partSize) {
		try {
			try {
				return GroupServiceImpl.getInstance().getAllGroupsParticipants(partNumber, partSize);
			} catch (SQLException | TransactionException e) {
				Log4jWrapper.get().error("get all groups participants failed ", e);
			}
		} catch (QueryNotFoundException | JDBCDriverException | DataBaseReadingException e) {
			Log4jWrapper.get().error("get all groups participants failed ", e);
		}
		return null;
	}

	@Override
	public boolean addUserInCompetition(String nameCompetition, String nameUser) {
		boolean result = false;
		CompetitionsServiceImpl competition = new CompetitionsServiceImpl();
		try {
			try {
				result = competition.addUserInCompetition(nameCompetition, nameUser);
			} catch (DataBaseReadingException | QueryNotFoundException | EmptyResultSetException
					| CloseStatementException e) {
				Log4jWrapper.get().error("Add user " + nameUser + " to competition " + nameCompetition + " failed ", e);
			}
		} catch (SQLException | JDBCDriverException | TransactionException e) {
			Log4jWrapper.get().error("Add user " + nameUser + " to competition " + nameCompetition + " failed ", e);
		}
		return result;
	}

	@Override
	public final CompetitionDTO getCompetitionViewById(String idCompetition) {
		try {
			try {
				return CompetitionsViewServiceImpl.getInstance().getCompetition(idCompetition);
			} catch (SQLException | TransactionException e) {
				Log4jWrapper.get().error("get competition by idCompetition failed ", e);
			}
		} catch (JDBCDriverException e) {
			Log4jWrapper.get().error("get competition by idCompetition failed ", e);
		}
		return null;
	}

	@Override
	public final CompetitionDTO getCompetitionViewByName(String name) {
		try {
			try {
				return CompetitionsViewServiceImpl.getInstance().getCompetitionByName(name);
			} catch (SQLException | TransactionException e) {
				Log4jWrapper.get().error("get competition by name failed ", e);
			}
		} catch (JDBCDriverException e) {
			Log4jWrapper.get().error("get competition by name failed ", e);
		}
		return null;
	}

	@Override
	public boolean addUserInCompetitionView(String idCompetition, String nameUser) {
		boolean result = false;
		try {
			result = CompetitionsViewServiceImpl.getInstance().addUserInCompetition(idCompetition, nameUser);
		} catch (SQLException | JDBCDriverException | TransactionException e) {
			Log4jWrapper.get().error("Add user " + nameUser + " to competition " + idCompetition + " failed ", e);
		}
		return result;
	}
	
	@Override
	public boolean addGroupInCompetition(String idCompetition, String idGroup) {
		boolean result = false;
		try {
			result = CompetitionsViewServiceImpl.getInstance().addGroupInCompetition(idCompetition, idGroup);
		} catch (SQLException | JDBCDriverException | TransactionException e) {
			Log4jWrapper.get().error("Add group " + idGroup + " to competition " + idCompetition + " failed ", e);
		}
		return result;
	}

	@Override
	public boolean removeUserFromCompetition(String idCompetition, String nameUser) {
		boolean result = false;
		try {
			result = CompetitionsViewServiceImpl.getInstance().removeUserFromCompetition(idCompetition, nameUser);
		} catch (SQLException | JDBCDriverException | TransactionException e) {
			Log4jWrapper.get().error("Delete user " + nameUser + " from competition " + idCompetition + " failed ", e);
		}
		return result;
	}

	@Override
	public UserCompetitionsDTO getUserCompetition(String idCompetition, String nameUser) {
		try {
			try {
				return CompetitionsViewServiceImpl.getInstance().getUserCompetition(idCompetition, nameUser);
			} catch (SQLException | TransactionException e) {
				Log4jWrapper.get().error("get UserCompetitionsDTO failed ", e);
			}
		} catch (JDBCDriverException e) {
			Log4jWrapper.get().error("get UserCompetitionsDTO failed ", e);
		}
		return null;
	}
	
	@Override
	public GroupCompetitionsDTO getGroupCompetition(String idCompetition, String idGroup) {
		try {
			try {
				return CompetitionsViewServiceImpl.getInstance().getGroupCompetition(idCompetition, idGroup);
			} catch (SQLException | TransactionException e) {
				Log4jWrapper.get().error("get GroupCompetitionsDTO failed ", e);
			}
		} catch (JDBCDriverException e) {
			Log4jWrapper.get().error("get GroupCompetitionsDTO failed ", e);
		}
		return null;
	}

	@Override
	public void deleteUserCompetition(String idCompetition, String nameUser) {
		try {
			CompetitionsViewServiceImpl.getInstance().deleteUserCompetition(idCompetition, nameUser);
		} catch (SQLException | JDBCDriverException | TransactionException e) {
			Log4jWrapper.get().error("delete user competition failed ", e);
		}
	}
	
	@Override
	public void deleteGroupCompetition(String idCompetition, String idGroup) {
		try {
			CompetitionsViewServiceImpl.getInstance().deleteGroupCompetition(idCompetition, idGroup);
		} catch (SQLException | JDBCDriverException | TransactionException e) {
			Log4jWrapper.get().error("delete group competition failed ", e);
		}
	}

	@Override
	public void updateUserCompetition(UserCompetitionsDTO userCompetition) {
		try {
			CompetitionsViewServiceImpl.getInstance().updateUserCompetition(userCompetition);
		} catch (SQLException | JDBCDriverException | TransactionException | QueryNotFoundException
				| DataBaseReadingException e) {
			Log4jWrapper.get().error("update competition failed ", e);
		}
	}

	@Override
	public void deleteUserFromGroup(UserDTO userDTO, String idGroup) {
		try {
			UserProfileServiceImpl.getInstance().deleteUserFromGroup(userDTO, idGroup);
		} catch (SQLException | JDBCDriverException | TransactionException e) {
			Log4jWrapper.get().error("delete user failed ", e);
		}
	}
}
