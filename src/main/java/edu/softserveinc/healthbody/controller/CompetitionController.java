package edu.softserveinc.healthbody.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import edu.softserveinc.healthbody.annotation.Controller;
import edu.softserveinc.healthbody.annotation.Request;
import edu.softserveinc.healthbody.dto.CompetitionDTO;
import edu.softserveinc.healthbody.exceptions.IllegalAgrumentCheckedException;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.TransactionException;
import edu.softserveinc.healthbody.log.Log4jWrapper;
import edu.softserveinc.healthbody.services.impl.CompetitionsViewServiceImpl;

@Controller
public class CompetitionController {

	@Request(url = "/allComp")
	public List<CompetitionDTO> getAllComp(HttpServletRequest request) {
		try {
			return CompetitionsViewServiceImpl.getInstance().getAll(ParamUtils.getPartNumber(request),
					ParamUtils.getPartSize(request));
		} catch (NumberFormatException | JDBCDriverException | SQLException | TransactionException e) {
			Log4jWrapper.get().error("Could't get all compettitions");
		}
		return null;

	}

	@Request(url = "/allUComp")
	public List<CompetitionDTO> getAllUserComp(HttpServletRequest request) {
		try {
			return CompetitionsViewServiceImpl.getInstance().getAllByUser(ParamUtils.getPartNumber(request),
					ParamUtils.getPartSize(request), ParamUtils.getLogin(request));
		} catch (IllegalAgrumentCheckedException | SQLException | JDBCDriverException | TransactionException e) {
			Log4jWrapper.get().error("Could't get all users compettitions");
			e.printStackTrace();
		}
		System.out.println(ParamUtils.getLogin(request));
		return null;
	}

	@Request(url = "/activeComp")
	public List<CompetitionDTO> getActiveComp(HttpServletRequest request) {
		try {
			return CompetitionsViewServiceImpl.getInstance().getAllActive(ParamUtils.getPartNumber(request),
					ParamUtils.getPartSize(request));
		} catch (JDBCDriverException | SQLException | TransactionException e) {
			Log4jWrapper.get().error("Could't get active compettitions");
		}
		return null;
	}

	@Request(url = "/activeUComp")
	public List<CompetitionDTO> getUserActiveComp(HttpServletRequest request) {
		try {
			return CompetitionsViewServiceImpl.getInstance().getAllActiveByUser(ParamUtils.getPartNumber(request),
					ParamUtils.getPartSize(request), ParamUtils.getLogin(request));
		} catch (IllegalAgrumentCheckedException | SQLException | JDBCDriverException | TransactionException e) {
			Log4jWrapper.get().error("Could't get active user compettitions");
		}
		return null;
	}
}
