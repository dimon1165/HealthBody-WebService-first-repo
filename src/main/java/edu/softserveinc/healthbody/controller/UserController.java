package edu.softserveinc.healthbody.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import edu.softserveinc.healthbody.annotation.Controller;
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
	public List<UserDTO> getAllUsers(HttpServletRequest request) {
		try {
			return new UsersViewServiceImpl().getAll(ParamUtils.getPartNumber(request),
					ParamUtils.getPartSize(request));
		} catch (JDBCDriverException | SQLException | TransactionException e) {
			Log4jWrapper.get().error("Could't get all users");
		}
		return null;
	}

	@Request(url = "/allInCompUsers")
	public List<UserDTO> getAllImCompUsers(HttpServletRequest request) {
		try {
			return new UsersViewServiceImpl().getAllinCompetition(ParamUtils.getPartNumber(request),
					ParamUtils.getPartSize(request));
		} catch (JDBCDriverException | SQLException | TransactionException e) {
			Log4jWrapper.get().error("Could't get all users in competition");
		}
		return null;
	}

	@Request(url = "/allUByAdmin")
	public List<UserDTO> getAllByAdmin(HttpServletRequest request) {
		try {
			return new UsersViewServiceImpl().getAllbyAdmin(ParamUtils.getPartNumber(request),
					ParamUtils.getPartSize(request));
		} catch (JDBCDriverException | SQLException | TransactionException e) {
			Log4jWrapper.get().error("Could't get all users by admin role");
		}
		return null;
	}

	@Request(url = "/allUInGroup")
	public List<UserDTO> getAllinGroup(HttpServletRequest request) {
		try {
			return new UsersViewServiceImpl().getAllinGroup(ParamUtils.getPartNumber(request),
					ParamUtils.getPartSize(request));
		} catch (JDBCDriverException | SQLException | TransactionException e) {
			Log4jWrapper.get().error("Could't get all users in group");
		}
		return null;
	}

	@Request(url = "/UWithNoComp")
	public List<UserDTO> getAllToAddInComp(HttpServletRequest request) {
		try {
			return new UsersViewServiceImpl().getAlltoAddInCompetition(ParamUtils.getPartNumber(request),
					ParamUtils.getPartSize(request));
		} catch (JDBCDriverException | SQLException | TransactionException e) {
			Log4jWrapper.get().error("Could't get all users in group");
		}
		return null;
	}

	@Request(url = "/UByLogin")
	public UserDTO getUserByLogin(HttpServletRequest request) {
		try {
			return UserProfileServiceImpl.getInstance().get(ParamUtils.getLogin(request));
		} catch (JDBCDriverException | SQLException | TransactionException e) {
			Log4jWrapper.get().error("Could't get all users in group");
		}
		return null;
	}
}
