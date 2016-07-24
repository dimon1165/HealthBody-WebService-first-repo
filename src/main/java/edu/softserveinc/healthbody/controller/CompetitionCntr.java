package edu.softserveinc.healthbody.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.softserveinc.healthbody.dto.CompetitionDTO;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.TransactionException;
import edu.softserveinc.healthbody.log.Log4jWrapper;
import edu.softserveinc.healthbody.services.impl.CompetitionsViewServiceImpl;

public class CompetitionCntr {

	public  List<CompetitionDTO> getAllComp(HttpServletRequest request, HttpServletResponse response) {
		CompetitionsViewServiceImpl competitionsViewServiceImpl = new CompetitionsViewServiceImpl();
//		String partNumber = request.getParameter("partNumber");
//		String partSize = request.getParameter("partSize");
//Integer.parseInt(partNumber), Integer.parseInt(partSize)
		try {
		return	competitionsViewServiceImpl.getAll(1, 10);
		} catch (NumberFormatException | JDBCDriverException | SQLException | TransactionException e) {

			Log4jWrapper.get().error("Could't get All Compettitions" + e);
		}
		return null;

	}

}
