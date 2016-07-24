package edu.softserveinc.healthbody.controller;

import java.sql.SQLException;
import java.util.List;

import edu.softserveinc.healthbody.annotation.Controller;
import edu.softserveinc.healthbody.annotation.Request;
import edu.softserveinc.healthbody.dto.UserDTO;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.TransactionException;
import edu.softserveinc.healthbody.log.Log4jWrapper;
import edu.softserveinc.healthbody.services.impl.UsersViewServiceImpl;

@Controller
public class UserController {

	@Request(url = "/allUsers")
	public List<UserDTO> getAllUsers() {
		try {
			return new UsersViewServiceImpl().getAll(1, 10);
		} catch (JDBCDriverException | SQLException | TransactionException e) {
			Log4jWrapper.get().error("Could't get all users");
		}
		return null;
	}

	@Request(url = "/allInCompUsers")
	public List<UserDTO> getAllImCompUsers() {
		try {
			return new UsersViewServiceImpl().getAllinCompetition(1, 10);
		} catch (JDBCDriverException | SQLException | TransactionException e) {
			Log4jWrapper.get().error("Could't get all users in competition");
		}
		return null;
	}

}
