package edu.softserveinc.healthbody.controller;

import java.sql.SQLException;
import java.util.List;

import edu.softserveinc.healthbody.annotation.Controller;
import edu.softserveinc.healthbody.annotation.Param;
import edu.softserveinc.healthbody.annotation.Request;
import edu.softserveinc.healthbody.dto.UserDTO;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.TransactionException;
import edu.softserveinc.healthbody.log.Log4jWrapper;
import edu.softserveinc.healthbody.services.impl.UserProfileServiceImpl;
import edu.softserveinc.healthbody.services.impl.UsersViewServiceImpl;

@Controller
public class UserController {

	@Request(url = "/Users")
	public List<UserDTO> getAllUsers(@Param(name = "partNumber") int partNumber,
			@Param(name = "partSize") int partSize) {
		try {
			return new UsersViewServiceImpl().getAll(partNumber, partSize);
		} catch (JDBCDriverException | SQLException | TransactionException e) {
			Log4jWrapper.get().error("Could't get all users");
		}
		return null;
	}

	@Request(url = "/UsersInCompetition")
	public List<UserDTO> getAllUsersInCompetition(@Param(name = "partNumber") int partNumber,
			@Param(name = "partSize") int partSize) {
		try {
			return new UsersViewServiceImpl().getAllinCompetition(partNumber, partSize);
		} catch (JDBCDriverException | SQLException | TransactionException e) {
			Log4jWrapper.get().error("Could't get all users in competition");
		}
		return null;
	}

	@Request(url = "/UsersByAdmin")
	public List<UserDTO> getAllUsersByAdmin(@Param(name = "partNumber") int partNumber,
			@Param(name = "partSize") int partSize) {
		try {
			return new UsersViewServiceImpl().getAllbyAdmin(partNumber, partSize);
		} catch (JDBCDriverException | SQLException | TransactionException e) {
			Log4jWrapper.get().error("Could't get all users by admin role");
		}
		return null;
	}

	@Request(url = "/UsersInGroup")
	public List<UserDTO> getAllUsersinGroup(@Param(name = "partNumber") int partNumber,
			@Param(name = "partSize") int partSize) {
		try {
			return new UsersViewServiceImpl().getAllinGroup(partNumber, partSize);
		} catch (JDBCDriverException | SQLException | TransactionException e) {
			Log4jWrapper.get().error("Could't get all users in group");
		}
		return null;
	}

	@Request(url = "/UsersWithNoCompetition")
	public List<UserDTO> getUsersToAddInCompetition(@Param(name = "partNumber") int partNumber,
			@Param(name = "partSize") int partSize) {
		try {
			return new UsersViewServiceImpl().getAlltoAddInCompetition(partNumber, partSize);
		} catch (JDBCDriverException | SQLException | TransactionException e) {
			Log4jWrapper.get().error("Could't get all users in group");
		}
		return null;
	}

	@Request(url = "/UserByLogin")
	public UserDTO getUserByLogin(@Param(name = "login") String login) {
		try {
			return UserProfileServiceImpl.getInstance().get(login);
		} catch (JDBCDriverException | SQLException | TransactionException e) {
			Log4jWrapper.get().error("Could't get all users in group");
		}
		return null;
	}
	/*For RestClient POST method **/
	@Request(url = "/updateUser")
	public void updateUser(@Param(name = "userDTO") UserDTO userDTO) {
		try {
			UserProfileServiceImpl.getInstance().update(userDTO);
		} catch (SQLException | JDBCDriverException | TransactionException e) {
			Log4jWrapper.get().error("update user failed ", e);
		}
	}
}
