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
import edu.softserveinc.healthbody.exceptions.DataBaseReadingException;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.QueryNotFoundException;
import edu.softserveinc.healthbody.exceptions.TransactionException;
import edu.softserveinc.healthbody.services.IGroupService;

public final class GroupServiceImpl implements IGroupService {
	
	private static volatile GroupServiceImpl instance;
	
	private GroupServiceImpl() {
	}
	
	public static GroupServiceImpl getInstance() {
		if (instance == null) {
			synchronized (GroupServiceImpl.class) {
				if (instance == null) {
					instance = new GroupServiceImpl();
				}
			}
		}
		return instance;
	}	

	@Override
	public List<GroupDTO> getAll(final int partNumber, final int partSize)
			throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException {
		List<GroupDTO> resultGroup = new ArrayList<GroupDTO>();
		for (Group group : GroupDao.getInstance().getAll(partNumber, partSize)) {
			resultGroup.add(new GroupDTO(group.getName(), group.getCount().toString(),
					group.getDescription(),	group.getScoreGroup()));
		}		
		return resultGroup;	
	}
	
	@Override
	public GroupDTO getGroup(final String name)
			throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException {
		 Group group = GroupDao.getInstance().getGroupByName(name);
		 return new GroupDTO(group.getName(), String.valueOf(group.getCount()),
				 group.getDescription(), group.getScoreGroup());
	}	
	
	@Override
	public String getDescriptionOfGroup(final GroupDTO groupDTO) {
		return groupDTO.getDescriptions();
	}
	
	@Override
	public void update(final GroupDTO groupDTO) 
			throws SQLException, JDBCDriverException, TransactionException {
		try {
			ConnectionManager.getInstance().beginTransaction();
			Group group = GroupDao.getInstance().getByFieldName(groupDTO.getName());
			GroupDao.getInstance().editGroup(new Group(0, groupDTO.getName(), Integer.parseInt(groupDTO.getCount()), 
					groupDTO.getDescriptions(), groupDTO.getScoreGroup(), group.getStatus()));
		} catch (JDBCDriverException | DataBaseReadingException | QueryNotFoundException e) {
			ConnectionManager.getInstance().rollbackTransaction();
			throw new TransactionException(ServiceConstants.TRANSACTION_ERROR, e);			
		}
		ConnectionManager.getInstance().commitTransaction();
	}
	

	@Override
	public List<GroupDTO> getAll(final int partNumber, final int partSize, final Map<String, String> filters)
			throws JDBCDriverException, SQLException, TransactionException {
		// TODO Auto-generated method stub
		return null;
	}

}
