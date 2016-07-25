package edu.softserveinc.healthbody.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import edu.softserveinc.healthbody.annotation.Controller;
import edu.softserveinc.healthbody.annotation.Request;
import edu.softserveinc.healthbody.dto.GroupDTO;
import edu.softserveinc.healthbody.exceptions.DataBaseReadingException;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.QueryNotFoundException;
import edu.softserveinc.healthbody.services.impl.GroupServiceImpl;

@Controller
public class GroupController {

	@Request(url = "/allGroups")
	public List<GroupDTO> getAllGroups(HttpServletRequest request) {
		try {
			return GroupServiceImpl.getInstance().getAll(ParamUtils.getPartNumber(request),
					ParamUtils.getPartSize(request));
		} catch (QueryNotFoundException | JDBCDriverException | DataBaseReadingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Request(url = "/groupDescr")
	public String getDescription(HttpServletRequest request) {
		try {
			return GroupServiceImpl.getInstance()
					.getDescriptionOfGroup(GroupServiceImpl.getInstance().getGroup("Name group number 2"));
		} catch (QueryNotFoundException | JDBCDriverException | DataBaseReadingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
