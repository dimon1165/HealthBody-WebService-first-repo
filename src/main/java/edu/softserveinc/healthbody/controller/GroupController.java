package edu.softserveinc.healthbody.controller;

import java.util.List;

import edu.softserveinc.healthbody.annotation.Controller;
import edu.softserveinc.healthbody.annotation.Request;
import edu.softserveinc.healthbody.dto.GroupDTO;
import edu.softserveinc.healthbody.exceptions.DataBaseReadingException;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.QueryNotFoundException;
import edu.softserveinc.healthbody.services.impl.GroupServiceImpl;

@Controller
public class GroupController {
	
	@Request(url="/allGroups")
	public List<GroupDTO> getAllGroups() {
		try {
			return GroupServiceImpl.getInstance().getAll(1, 10);
		} catch (QueryNotFoundException | JDBCDriverException | DataBaseReadingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
