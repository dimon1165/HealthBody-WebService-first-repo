package edu.softserveinc.healthbody.controller;

import java.sql.SQLException;
import java.util.List;

import edu.softserveinc.healthbody.annotation.Controller;
import edu.softserveinc.healthbody.annotation.Param;
import edu.softserveinc.healthbody.annotation.Request;
import edu.softserveinc.healthbody.dto.CompetitionDTO;
import edu.softserveinc.healthbody.exceptions.IllegalAgrumentCheckedException;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.TransactionException;
import edu.softserveinc.healthbody.log.Log4jWrapper;
import edu.softserveinc.healthbody.services.impl.CompetitionsViewServiceImpl;

@Controller
public class CompetitionController {

	@Request(url = "/Competitions")
	public List<CompetitionDTO> getAllCompetitions(@Param(name = "partNumber") int partNumber,
			@Param(name = "partSize") int partSize) {
		try {
			return CompetitionsViewServiceImpl.getInstance().getAll(partNumber, partSize);

		} catch (NumberFormatException | JDBCDriverException | SQLException | TransactionException e) {
			Log4jWrapper.get().error("Could't get all compettitions");
		}
		return null;

	}

	@Request(url = "/UserCompetitions")
	public List<CompetitionDTO> getAllUserCompetitions(@Param(name = "partNumber") int partNumber,
			@Param(name = "partSize") int partSize, @Param(name = "login") String login) {
		try {
			return CompetitionsViewServiceImpl.getInstance().getAllByUser(partNumber, partSize, login);
		} catch (IllegalAgrumentCheckedException | SQLException | JDBCDriverException | TransactionException e) {
			Log4jWrapper.get().error("Could't get all users compettitions");
		}
		return null;
	}

	@Request(url = "/activeCompetitions")
	public List<CompetitionDTO> getActiveCompetitions(@Param(name = "partNumber") int partNumber,
			@Param(name = "partSize") int partSize) {
		try {
			return CompetitionsViewServiceImpl.getInstance().getAllActive(partNumber, partSize);
		} catch (JDBCDriverException | SQLException | TransactionException e) {
			Log4jWrapper.get().error("Could't get active compettitions");
		}
		return null;
	}

	@Request(url = "/activeUserCompetitions")
	public List<CompetitionDTO> getUserActiveCompetitions(@Param(name = "partNumber") int partNumber,
			@Param(name = "partSize") int partSize, @Param(name = "login") String login) {
		try {
			return CompetitionsViewServiceImpl.getInstance().getAllActiveByUser(partNumber, partSize, login);
		} catch (IllegalAgrumentCheckedException | SQLException | JDBCDriverException | TransactionException e) {
			Log4jWrapper.get().error("Could't get active user compettitions");
		}
		return null;
	}

}
