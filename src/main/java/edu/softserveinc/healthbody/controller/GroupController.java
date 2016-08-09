package edu.softserveinc.healthbody.controller;

import java.sql.SQLException;
import java.util.List;

import edu.softserveinc.healthbody.annotation.Controller;
import edu.softserveinc.healthbody.annotation.Param;
import edu.softserveinc.healthbody.annotation.Request;
import edu.softserveinc.healthbody.dto.GroupDTO;
import edu.softserveinc.healthbody.exceptions.DataBaseReadingException;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.QueryNotFoundException;
import edu.softserveinc.healthbody.exceptions.TransactionException;
import edu.softserveinc.healthbody.log.Log4jWrapper;
import edu.softserveinc.healthbody.services.impl.GroupServiceImpl;

@Controller
public class GroupController {

	@Request(url = "/Groups")
	public List<GroupDTO> getAllGroups(@Param(name = "partNumber") int partNumber,
			@Param(name = "partSize") int partSize) {
		try {
			return GroupServiceImpl.getInstance().getAll(partNumber, partSize);
		} catch (QueryNotFoundException | JDBCDriverException | DataBaseReadingException | SQLException 
				| TransactionException e) {
			Log4jWrapper.get().error("Could't get all groups");
		} 
		return null;
	}

	@Request(url = "/groupDescription")
	public String getDescriptionOfGroup(@Param(name = "groupName") String groupName) {
		try {
			return GroupServiceImpl.getInstance()
					.getDescriptionOfGroup(GroupServiceImpl.getInstance().getGroup(groupName));
		} catch (QueryNotFoundException | JDBCDriverException | DataBaseReadingException | SQLException | 
				TransactionException e) {
			Log4jWrapper.get().error("Could't get description of group");
		}
		return null;
	}
	
	@Request(url = "/GroupsParticipants")
	public List<GroupDTO> getAllGroupsParticipants(@Param(name = "partNumber") int partNumber,
			@Param(name = "partSize") int partSize) {
		try {
			return GroupServiceImpl.getInstance().getAllGroupsParticipants(partNumber, partSize);
		} catch (QueryNotFoundException | JDBCDriverException | DataBaseReadingException | SQLException 
				| TransactionException e) {
			Log4jWrapper.get().error("Could't get group's participants");
		} 
		return null;
	}
}
