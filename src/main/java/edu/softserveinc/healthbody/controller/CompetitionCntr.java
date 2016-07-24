package edu.softserveinc.healthbody.controller;

import java.sql.SQLException;
import java.util.List;

import edu.softserveinc.healthbody.annotation.Controller;
import edu.softserveinc.healthbody.annotation.Request;
import edu.softserveinc.healthbody.dto.CompetitionDTO;
import edu.softserveinc.healthbody.exceptions.IllegalAgrumentCheckedException;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.TransactionException;
import edu.softserveinc.healthbody.log.Log4jWrapper;
import edu.softserveinc.healthbody.services.impl.CompetitionsViewServiceImpl;

@Controller
public class CompetitionCntr {

	@Request(url = "/allComp")
	public List<CompetitionDTO> getAllComp(/*HttpServletRequest rqst, HttpServletResponse rspns*/) {
		/* String partNumber = rqst.getParameter("partNumber");
		 String partSize = rqst.getParameter("partSize");*/
		// Integer.parseInt(partNumber), Integer.parseInt(partSize)
		try {
			return new CompetitionsViewServiceImpl().getAll(1, 10);
		} catch (NumberFormatException | JDBCDriverException | SQLException | TransactionException e) {
			Log4jWrapper.get().error("Could't get all compettitions");
		}
		return null;

	}

	@Request(url = "/allUComp")
	public List<CompetitionDTO> getAllUserComp(/*HttpServletRequest request, HttpServletResponse response*/) {		
		try {
			 return  new CompetitionsViewServiceImpl().getAllByUser(1, 10, "Login 2");
		} catch (IllegalAgrumentCheckedException | SQLException | JDBCDriverException | TransactionException e) {
			Log4jWrapper.get().error("Could't get all users compettitions");
		}
		return null;
	}

	@Request(url = "/activeComp")
	public List<CompetitionDTO> getActiveComp(/*HttpServletRequest request, HttpServletResponse response*/) {
		try {
			return new CompetitionsViewServiceImpl().getAllActive(1, 10);
		} catch (JDBCDriverException | SQLException | TransactionException e) {
			Log4jWrapper.get().error("Could't get active compettitions");
		}
		return null;
	}
	
	@Request(url = "/activeUComp")
	public List<CompetitionDTO> getUserActiveComp(/*HttpServletRequest request, HttpServletResponse response*/) {
		try {
			return new CompetitionsViewServiceImpl().getAllActiveByUser(1, 10, "Login 3");
		} catch (IllegalAgrumentCheckedException | SQLException | JDBCDriverException | TransactionException e) {
			Log4jWrapper.get().error("Could't get active user compettitions");
		}
		return null;
	}

}
