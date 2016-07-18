package edu.softserveinc.healthbody.services.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.softserveinc.healthbody.constants.ServiceConstants;
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
import edu.softserveinc.healthbody.log.LoggerWrapper;
import edu.softserveinc.healthbody.services.IBaseService;

public class UserProfileServiceImpl implements IBaseService<UserDTO> {
	
	private static volatile UserProfileServiceImpl instance = null;

	private UserProfileServiceImpl() {
	}
	
	public static UserProfileServiceImpl getInstance() {
		if(instance == null) {
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
	public void insert(UserDTO userDTO) throws SQLException, JDBCDriverException, DataBaseReadingException,
							QueryNotFoundException, EmptyResultSetException, TransactionException, CloseStatementException {
		if (userDTO == null) {
			LoggerWrapper.error(this.getClass(), "You didn't enter user");
			throw new IllegalArgumentException();
		}
		else {
			ConnectionManager.getInstance().beginTransaction();
			Role roles = RoleDao.getInstance().getRoleByName(userDTO.getRoleName());
		
			try {
				UserDao.getInstance().createUser(new User(0, userDTO.getLogin(), userDTO.getPassword(), userDTO.getFirstname(), userDTO.getLastname(),
					 userDTO.getEmail(), Integer.parseInt(userDTO.getAge()), Double.parseDouble(userDTO.getWeight()), userDTO.getGender(),
					 userDTO.getHealth(), userDTO.getPhotoURL(), userDTO.getGoogleApi(), roles.getIdRole(), userDTO.getStatus(), Boolean.parseBoolean(userDTO.getIsDisabled())));
				User user = UserDao.getInstance().getUserByLoginName(userDTO.getLogin());
				Group group = GroupDao.getInstance().getGroupByName(userDTO.getGroups().get(0).getName());
				UserGroupDao.getInstance().createUserGroup(user, group);
			} catch (JDBCDriverException | DataBaseReadingException | QueryNotFoundException e) {
				ConnectionManager.getInstance().rollbackTransaction();
				throw new TransactionException(ServiceConstants.TRANSACTION_ERROR, e);
			}
			ConnectionManager.getInstance().commitTransaction();
		}
	}

	//get user by login
	@Override
	public UserDTO get(String name) throws SQLException, JDBCDriverException, EmptyResultSetException, TransactionException, CloseStatementException {
		if (name == null) {
			LoggerWrapper.error(this.getClass(), "User Login couldn't be null");
			throw new IllegalArgumentException();
		}
		else {
			User user = null;
			Role role = null;
			Group group = null;
			List<UserGroup> ugs = new ArrayList<UserGroup>();
			List<GroupDTO> groups = new ArrayList<GroupDTO>();
		
			ConnectionManager.getInstance().beginTransaction();
			try {
				user = UserDao.getInstance().getUserByLoginName(name);
				if (user == null) {
					LoggerWrapper.error(this.getClass(), "User " + name + " doesn't exist");
					return null;
				}
				else {
					role = RoleDao.getInstance().getRoleById(user.getIdRole());
					ugs = UserGroupDao.getInstance().getUGbyId(user.getId());
					for( UserGroup ug : ugs ){
						group = GroupDao.getInstance().getById(ug.getIdGroup());
						groups.add(new GroupDTO(group.getName(), "", "", ""));
					}
				}
			 } catch (JDBCDriverException | DataBaseReadingException | QueryNotFoundException e) {
				 ConnectionManager.getInstance().rollbackTransaction();
				 throw new TransactionException(ServiceConstants.TRANSACTION_ERROR, e);
			 }
			ConnectionManager.getInstance().commitTransaction();
		
			return new UserDTO(user.getLogin(), user.getPasswd(), user.getFirsName(), user.getLastName(), user.getMail(),
				user.getAge().toString(), user.getWeight().toString(), user.getGender(), user.getAvatar(), role.getName(), user.getStatus(), "", groups, String.valueOf(user.getIsDisabled()));
		}
	}
	
	//get user by id
	public UserDTO getById(Integer id) throws SQLException, JDBCDriverException, TransactionException, CloseStatementException, EmptyResultSetException {
		User user = null;
		Role role = null;
		Group group = null;
		List<UserGroup> ugs = new ArrayList<UserGroup>();
		List<GroupDTO> groups = new ArrayList<GroupDTO>();
		
		ConnectionManager.getInstance().beginTransaction();
		try {
			 user = UserDao.getInstance().getUserById(id);
			 role = RoleDao.getInstance().getRoleById(user.getIdRole());
			 ugs = UserGroupDao.getInstance().getUGbyId(user.getId());
			 for( UserGroup ug : ugs ){
				 group = GroupDao.getInstance().getById(ug.getIdGroup());
				 groups.add(new GroupDTO(group.getName(), "", "", ""));
			}
		} catch (JDBCDriverException | DataBaseReadingException | QueryNotFoundException e) {
			ConnectionManager.getInstance().rollbackTransaction();
			throw new TransactionException(ServiceConstants.TRANSACTION_ERROR, e);
		}
		ConnectionManager.getInstance().commitTransaction();
		
		return new UserDTO(user.getLogin(), user.getPasswd(), user.getFirsName(), user.getLastName(), user.getMail(),
				user.getAge().toString(), user.getWeight().toString(), user.getGender(), user.getAvatar(), role.getName(), user.getStatus(), "", groups, String.valueOf(user.getIsDisabled()));
	}

	//Update user
	@Override
	public void update(UserDTO userDTO) throws SQLException, JDBCDriverException, DataBaseReadingException, QueryNotFoundException, EmptyResultSetException, TransactionException, CloseStatementException {
		if (userDTO == null) {
			LoggerWrapper.error(this.getClass(), "You didn't enter user");
			throw new IllegalArgumentException();
		}
		else {
			ConnectionManager.getInstance().beginTransaction();
			Role role = RoleDao.getInstance().getByFieldName(userDTO.getRoleName());
			try {	
				UserDao.getInstance().updateUser(new User(0, userDTO.getLogin(), userDTO.getPassword(), userDTO.getFirstname(), userDTO.getLastname(),
					 userDTO.getEmail(), Integer.parseInt(userDTO.getAge()), Double.parseDouble(userDTO.getWeight()), userDTO.getGender(),
					 userDTO.getHealth(), userDTO.getPhotoURL(), userDTO.getGoogleApi(), role.getIdRole(), userDTO.getStatus(), Boolean.parseBoolean(userDTO.getIsDisabled())));
			}catch (JDBCDriverException | DataBaseReadingException | QueryNotFoundException e) {
				ConnectionManager.getInstance().rollbackTransaction();
				throw new TransactionException(ServiceConstants.TRANSACTION_ERROR, e);
			}
			ConnectionManager.getInstance().commitTransaction();
		}
	}

	//use just for test
	@Override
	public void test_delete(UserDTO userDTO) throws SQLException, JDBCDriverException, QueryNotFoundException, DataBaseReadingException, CloseStatementException, TransactionException {
		ConnectionManager.getInstance().beginTransaction();
		try {
			User user = UserDao.getInstance().getUserByLoginName(userDTO.getLogin());
			UserGroupDao.getInstance().deleteByUserId(user.getId());
			UserCompetitionsDao.getInstance().deleteByUserId(user.getId());
			UserDao.getInstance().deleteUserForTests(user.getId());
		}catch (JDBCDriverException | DataBaseReadingException | QueryNotFoundException e) {
			ConnectionManager.getInstance().rollbackTransaction();
			throw new TransactionException(ServiceConstants.TRANSACTION_ERROR, e);
		}
		ConnectionManager.getInstance().commitTransaction();
	}
	
	//lock and unlock user (for lock - isDisabled = true, for unlock - isDisabled = false)
	public void lock(UserDTO userDTO, boolean isDisabled) throws SQLException, JDBCDriverException, QueryNotFoundException, DataBaseReadingException, TransactionException, CloseStatementException {
		if (userDTO == null) {
			LoggerWrapper.error(this.getClass(), "You didn't enter user");
			throw new IllegalArgumentException();
		}
		else {
			ConnectionManager.getInstance().beginTransaction();
			try {
				UserDao.getInstance().lockUser(isDisabled, userDTO.getLogin());
			}catch (JDBCDriverException | DataBaseReadingException | QueryNotFoundException e) {
				ConnectionManager.getInstance().rollbackTransaction();
				throw new TransactionException(ServiceConstants.TRANSACTION_ERROR, e);
			}
		ConnectionManager.getInstance().commitTransaction();
		}
	}
}
