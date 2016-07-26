package edu.softserveinc.healthbody.services.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import edu.softserveinc.healthbody.constants.ServiceConstants;
import edu.softserveinc.healthbody.dao.GroupDao;
import edu.softserveinc.healthbody.db.ConnectionManager;
import edu.softserveinc.healthbody.dto.GroupDTO;
import edu.softserveinc.healthbody.entity.Group;
import edu.softserveinc.healthbody.exceptions.CloseStatementException;
import edu.softserveinc.healthbody.exceptions.DataBaseReadingException;
import edu.softserveinc.healthbody.exceptions.EmptyResultSetException;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.QueryNotFoundException;
import edu.softserveinc.healthbody.exceptions.TransactionException;
import edu.softserveinc.healthbody.services.IGroupService;

public class GroupServiceImpl implements IGroupService{
	
	private static volatile GroupServiceImpl instance = null;
	
	private GroupServiceImpl() {
	}
	
	public static GroupServiceImpl getInstance() {
		if(instance == null) {
			synchronized (GroupServiceImpl.class) {
				if (instance == null) {
					instance = new GroupServiceImpl();
				}
			}
		}
		return instance;
	}	

	@Override
	public List<GroupDTO> getAll(int partNumber, int partSize) throws QueryNotFoundException, 
					JDBCDriverException, DataBaseReadingException, EmptyResultSetException, CloseStatementException {
		List<GroupDTO> resultGroup = new ArrayList<GroupDTO>();
		for (Group group : GroupDao.getInstance().getAll(partNumber, partSize)){
			resultGroup.add(new GroupDTO(group.getIdGroup(), group.getName(), group.getCount().toString(), group.getDescription(),
					group.getScoreGroup()));
		}		
		return resultGroup;	
	}
	
	@Override
	public GroupDTO getGroup(String name) throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException, CloseStatementException{
		 Group group = GroupDao.getInstance().getGroupByName(name);
		 return new GroupDTO(group.getIdGroup(), group.getName(), String.valueOf(group.getCount()), group.getDescription(), group.getScoreGroup());
	}	
	
	@Override
	public String getDescriptionOfGroup(GroupDTO groupDTO) {
		return groupDTO.getDescriptions();
	}
	
	@Override
	public void update(GroupDTO groupDTO) throws SQLException, JDBCDriverException, DataBaseReadingException, QueryNotFoundException, EmptyResultSetException, TransactionException, CloseStatementException {
		ConnectionManager.getInstance().beginTransaction();
		Group group = GroupDao.getInstance().getByFieldName(groupDTO.getName());
		try {	
			GroupDao.getInstance().editGroup(new Group(groupDTO.getIdGroup(), groupDTO.getName(), Integer.parseInt(groupDTO.getCount()), 
					groupDTO.getDescriptions(), groupDTO.getScoreGroup(), group.getStatus()));
		}catch (JDBCDriverException | DataBaseReadingException | QueryNotFoundException e) {
			ConnectionManager.getInstance().rollbackTransaction();
			throw new TransactionException(ServiceConstants.TRANSACTION_ERROR, e);			
		}
		ConnectionManager.getInstance().commitTransaction();
	}
	

	@Override
	public List<GroupDTO> getAll(int partNumber, int partSize, Map<String, String> filters)
			throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException, EmptyResultSetException,
			CloseStatementException, SQLException, TransactionException {
		return null;
	}

}
