package edu.softserveinc.healthbody.services.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.softserveinc.healthbody.constants.ErrorConstants;
import edu.softserveinc.healthbody.dao.GroupDao;
import edu.softserveinc.healthbody.dao.RoleDao;
import edu.softserveinc.healthbody.dao.UserCompetitionsDao;
import edu.softserveinc.healthbody.dao.UserDao;
import edu.softserveinc.healthbody.dao.UserGroupDao;
import edu.softserveinc.healthbody.db.ConnectionManager;
import edu.softserveinc.healthbody.dto.GroupDTO;
import edu.softserveinc.healthbody.dto.UserDTO;
import edu.softserveinc.healthbody.entity.Group;
import edu.softserveinc.healthbody.entity.Role;
import edu.softserveinc.healthbody.entity.User;
import edu.softserveinc.healthbody.entity.UserGroup;
import edu.softserveinc.healthbody.exceptions.CloseStatementException;
import edu.softserveinc.healthbody.exceptions.DataBaseReadingException;
import edu.softserveinc.healthbody.exceptions.EmptyResultSetException;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.QueryNotFoundException;
import edu.softserveinc.healthbody.exceptions.TransactionException;
import edu.softserveinc.healthbody.log.Log4jWrapper;
import edu.softserveinc.healthbody.services.IBaseService;

public final class UserProfileServiceImpl implements IBaseService<UserDTO> {
	
	private static volatile UserProfileServiceImpl instance;

	private UserProfileServiceImpl() {
	}
	
	public static UserProfileServiceImpl getInstance() {
		if (instance == null) {
			synchronized (UserProfileServiceImpl.class) {
				if (instance == null) {
					instance = new UserProfileServiceImpl();
				}
			}
		}
		return instance;
	}
	
	//create user
	@Override
	public void insert(final UserDTO userDTO)
			throws SQLException, JDBCDriverException, TransactionException {
		if (userDTO == null) {
			Log4jWrapper.get().error("You didn't enter user");
			throw new IllegalArgumentException();
		} else {
			Connection connection = ConnectionManager.getInstance().beginTransaction();
			try {				
				Role roles = RoleDao.getInstance().getRoleByName(connection, userDTO.getRoleName());
				UserDao.getInstance().createUser(connection, new User(userDTO.getIdUser(), userDTO.getLogin(), userDTO.getPassword(),
						userDTO.getFirstname(), userDTO.getLastname(), userDTO.getEmail(),
						Integer.parseInt(userDTO.getAge()), Double.parseDouble(userDTO.getWeight()),
						userDTO.getGender(), userDTO.getHealth(), userDTO.getPhotoURL(),
						userDTO.getGoogleApi(), roles.getIdRole(), userDTO.getStatus(),
						Boolean.parseBoolean(userDTO.getIsDisabled())));
				User user = UserDao.getInstance().getUserByLoginName(connection, userDTO.getLogin());
				Group group = GroupDao.getInstance().getGroupByName(connection, userDTO.getGroups().get(0).getName());
				UserGroupDao.getInstance().createUserGroup(connection, user, group);
			} catch (JDBCDriverException | DataBaseReadingException | QueryNotFoundException e) {
				ConnectionManager.getInstance().rollbackTransaction(connection);
				throw new TransactionException(ErrorConstants.TRANSACTION_ERROR, e);
			}
			ConnectionManager.getInstance().commitTransaction(connection);
		}
	}

	//get user by login
	@Override
	public UserDTO get(final String name)
			throws SQLException, JDBCDriverException, TransactionException {
		if (name == null) {
			Log4jWrapper.get().error("User Login couldn't be null");
			throw new IllegalArgumentException();
		} else {
			User user = null;
			Role role = null;
			Group group = null;
			List<UserGroup> userGroup = new ArrayList<UserGroup>();
			List<GroupDTO> groups = new ArrayList<GroupDTO>();
		
			Connection connection = ConnectionManager.getInstance().beginTransaction();
			try {
				user = UserDao.getInstance().getUserByLoginName(connection, name);
				if (user == null) {
					Log4jWrapper.get().error("User " + name + " doesn't exist");
					return null;
				} else {
					role = RoleDao.getInstance().getRoleById(connection, user.getIdRole());
					userGroup = UserGroupDao.getInstance().getUserGroupbyId(connection, user.getId());
					for (UserGroup usergroup : userGroup) {
						group = GroupDao.getInstance().getById(connection, usergroup.getIdGroup());
						groups.add(new GroupDTO(group.getId(), group.getName(), group.getCount().toString(), group.getDescription(), group.getScoreGroup(),null,null,null,null));
					}
				}
			 } catch (JDBCDriverException | DataBaseReadingException | QueryNotFoundException | CloseStatementException | EmptyResultSetException e) {
				 ConnectionManager.getInstance().rollbackTransaction(connection);
				 throw new TransactionException(ErrorConstants.TRANSACTION_ERROR, e);
			 }
			ConnectionManager.getInstance().commitTransaction(connection);
		return new UserDTO(user.getId(), user.getLogin(), user.getPasswd(), user.getFirsName(), user.getLastName(), user.getMail(),
				user.getAge().toString(), user.getWeight().toString(), user.getGender(), user.getAvatar(),
				role.getName(), user.getStatus(), user.getGoogleApi(),"", user.getHealth(), groups, String.valueOf(user.getIsDisabled()));
		}
	}
	
	//get user by id
	public UserDTO getById(final String id) 
			throws SQLException, JDBCDriverException, TransactionException, CloseStatementException, EmptyResultSetException {
		User user = null;
		Role role = null;
		Group group = null;
		List<UserGroup> userGroup = new ArrayList<UserGroup>();
		List<GroupDTO> groups = new ArrayList<GroupDTO>();
		
		Connection connection = ConnectionManager.getInstance().beginTransaction();
		try {
			 user = UserDao.getInstance().getUserById(connection, id);
			 role = RoleDao.getInstance().getRoleById(connection, user.getIdRole());
			 userGroup = UserGroupDao.getInstance().getUserGroupbyId(connection, user.getId());
			 for (UserGroup usergroup : userGroup) {
				 group = GroupDao.getInstance().getById(connection, usergroup.getIdGroup());
				 groups.add(new GroupDTO(group.getId(), group.getName(), group.getCount().toString(), group.getDescription(), group.getScoreGroup(),null,null,null,null));
			}
		} catch (JDBCDriverException | DataBaseReadingException | QueryNotFoundException e) {
			ConnectionManager.getInstance().rollbackTransaction(connection);
			throw new TransactionException(ErrorConstants.TRANSACTION_ERROR, e);
		}
		ConnectionManager.getInstance().commitTransaction(connection);
		
		return new UserDTO(user.getId(), user.getLogin(), user.getPasswd(), user.getFirsName(), user.getLastName(), user.getMail(),
				user.getAge().toString(), user.getWeight().toString(), user.getGender(), user.getAvatar(),
				role.getName(), user.getStatus(), user.getGoogleApi(),"", user.getHealth(), groups, String.valueOf(user.getIsDisabled()));
	}

	//Update user
	@Override
	public void update(final UserDTO userDTO)
			throws SQLException, JDBCDriverException, TransactionException {
		if (userDTO == null) {
			Log4jWrapper.get().error("You didn't enter user");
			throw new IllegalArgumentException();
		} else {			
			Connection connection = ConnectionManager.getInstance().beginTransaction();
			try {
				Role role = RoleDao.getInstance().getByFieldName(connection, userDTO.getRoleName());
				User user = new User(userDTO.getIdUser(), userDTO.getLogin(), userDTO.getPassword(),
						userDTO.getFirstname(), userDTO.getLastname(), userDTO.getEmail(),
						Integer.parseInt(userDTO.getAge()), Double.parseDouble(userDTO.getWeight()),
						userDTO.getGender(), userDTO.getHealth(), userDTO.getPhotoURL(),
						userDTO.getGoogleApi(), role.getIdRole(), userDTO.getStatus(),
						Boolean.parseBoolean(userDTO.getIsDisabled()));
				if (UserGroupDao.getInstance().getById(connection, user.getId()) != null) {
					UserGroupDao.getInstance().deleteById(connection, user.getId());
					for (GroupDTO group : userDTO.getGroups()) {
						UserGroupDao.getInstance().createUserGroup(connection, user, GroupDao.getInstance().getGroupByName(connection, group.getName()));
					}
				} else {
					for (GroupDTO group : userDTO.getGroups()) {
						UserGroupDao.getInstance().createUserGroup(connection, user, GroupDao.getInstance().getGroupByName(connection, group.getName()));
					}
				}
				UserDao.getInstance().updateUser(connection, user);
			} catch (JDBCDriverException | DataBaseReadingException | QueryNotFoundException e) {
				ConnectionManager.getInstance().rollbackTransaction(connection);
				throw new TransactionException(ErrorConstants.TRANSACTION_ERROR, e);
			}
			ConnectionManager.getInstance().commitTransaction(connection);
		}
	}
	
	//delete user from group
	public void deleteUserFromGroup(final UserDTO userDTO, String idGroup)
			throws SQLException, JDBCDriverException, TransactionException {
		Connection connection = ConnectionManager.getInstance().beginTransaction();
		try {
			UserGroupDao.getInstance().deleteUserGroup(connection, userDTO.getIdUser(), idGroup);
		} catch (DataBaseReadingException | QueryNotFoundException e) {
			ConnectionManager.getInstance().rollbackTransaction(connection);
			throw new TransactionException(ErrorConstants.TRANSACTION_ERROR, e);
		}
		ConnectionManager.getInstance().commitTransaction(connection);
	}

	//use just for test
	@Override
	public void test_delete(final UserDTO userDTO)
			throws SQLException, JDBCDriverException, TransactionException {
		Connection connection = ConnectionManager.getInstance().beginTransaction();
		try {
			User user = UserDao.getInstance().getUserByLoginName(connection, userDTO.getLogin());
			UserGroupDao.getInstance().deleteByUserId(connection, user.getId());
			UserCompetitionsDao.getInstance().deleteByUserId(connection, user.getId());
			UserDao.getInstance().deleteUserForTests(connection, user.getId());
		} catch (JDBCDriverException | DataBaseReadingException | QueryNotFoundException e) {
			ConnectionManager.getInstance().rollbackTransaction(connection);
			throw new TransactionException(ErrorConstants.TRANSACTION_ERROR, e);
		}
		ConnectionManager.getInstance().commitTransaction(connection);
	}
	
	//lock and unlock user (for lock - isDisabled = true, for unlock - isDisabled = false)
	public void lock(final UserDTO userDTO, final boolean isDisabled)
			throws SQLException, JDBCDriverException, TransactionException {
		if (userDTO == null) {
			Log4jWrapper.get().error("You didn't enter user");
			throw new IllegalArgumentException();
		} else {
			Connection connection = ConnectionManager.getInstance().beginTransaction();
			try {
				UserDao.getInstance().lockUser(connection, isDisabled, userDTO.getLogin());
			} catch (JDBCDriverException | DataBaseReadingException | QueryNotFoundException e) {
				ConnectionManager.getInstance().rollbackTransaction(connection);
				throw new TransactionException(ErrorConstants.TRANSACTION_ERROR, e);
			}
		ConnectionManager.getInstance().commitTransaction(connection);
		}
	}
}
