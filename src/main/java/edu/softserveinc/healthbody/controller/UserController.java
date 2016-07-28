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

	@Request(url = "/allUsers")
	public List<UserDTO> getAllUsers(@Param(name = "partNumber") int partNumber,
			@Param(name = "partSize") int partSize) {
		try {
			return new UsersViewServiceImpl().getAll(partNumber, partSize);
		} catch (JDBCDriverException | SQLException | TransactionException e) {
			Log4jWrapper.get().error("Could't get all users");
		}
		return null;
	}

	@Request(url = "/allInCompUsers")
	public List<UserDTO> getAllImCompUsers(@Param(name = "partNumber") int partNumber,
			@Param(name = "partSize") int partSize) {
		try {
			return new UsersViewServiceImpl().getAllinCompetition(partNumber, partSize);
		} catch (JDBCDriverException | SQLException | TransactionException e) {
			Log4jWrapper.get().error("Could't get all users in competition");
		}
		return null;
	}

	@Request(url = "/allUByAdmin")
	public List<UserDTO> getAllByAdmin(@Param(name = "partNumber") int partNumber,
			@Param(name = "partSize") int partSize) {
		try {
			return new UsersViewServiceImpl().getAllbyAdmin(partNumber, partSize);
		} catch (JDBCDriverException | SQLException | TransactionException e) {
			Log4jWrapper.get().error("Could't get all users by admin role");
		}
		return null;
	}

	@Request(url = "/allUInGroup")
	public List<UserDTO> getAllinGroup(@Param(name = "partNumber") int partNumber,
			@Param(name = "partSize") int partSize) {
		try {
			return new UsersViewServiceImpl().getAllinGroup(partNumber, partSize);
		} catch (JDBCDriverException | SQLException | TransactionException e) {
			Log4jWrapper.get().error("Could't get all users in group");
		}
		return null;
	}

	@Request(url = "/UWithNoComp")
	public List<UserDTO> getAllToAddInComp(@Param(name = "partNumber") int partNumber,
			@Param(name = "partSize") int partSize) {
		try {
			return new UsersViewServiceImpl().getAlltoAddInCompetition(partNumber, partSize);
		} catch (JDBCDriverException | SQLException | TransactionException e) {
			Log4jWrapper.get().error("Could't get all users in group");
		}
		return null;
	}

	@Request(url = "/UByLogin")
	public UserDTO getUserByLogin(@Param(name = "login") String login) {
		try {
			return UserProfileServiceImpl.getInstance().get(login);
		} catch (JDBCDriverException | SQLException | TransactionException e) {
			Log4jWrapper.get().error("Could't get all users in group");
		}
		return null;
	}
}
