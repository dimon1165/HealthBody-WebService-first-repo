package edu.softserveinc.healthbody.services.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import edu.softserveinc.healthbody.constants.ErrorConstants;
import edu.softserveinc.healthbody.dao.GroupDao;
import edu.softserveinc.healthbody.dao.GroupUserViewDao;
import edu.softserveinc.healthbody.db.ConnectionManager;
import edu.softserveinc.healthbody.dto.GroupDTO;
import edu.softserveinc.healthbody.entity.Group;
import edu.softserveinc.healthbody.entity.GroupUserView;
import edu.softserveinc.healthbody.exceptions.CloseStatementException;
import edu.softserveinc.healthbody.exceptions.DataBaseReadingException;
import edu.softserveinc.healthbody.exceptions.EmptyResultSetException;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.QueryNotFoundException;
import edu.softserveinc.healthbody.exceptions.TransactionException;
import edu.softserveinc.healthbody.log.Log4jWrapper;
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
	public List<GroupDTO> getAll(final int partNumber, final int partSize) throws QueryNotFoundException,
			JDBCDriverException, DataBaseReadingException, SQLException, TransactionException {
		List<GroupDTO> resultGroup = new ArrayList<GroupDTO>();
		Connection connection = ConnectionManager.getInstance().beginTransaction();
		try {
			for (Group group : GroupDao.getInstance().getAll(connection, partNumber, partSize)) {
				resultGroup.add(new GroupDTO(group.getIdGroup(), group.getName(), group.getCount().toString(),
						group.getDescription(), group.getScoreGroup(), null, null, null, null));
			}
		} catch (QueryNotFoundException | DataBaseReadingException e) {
			ConnectionManager.getInstance().rollbackTransaction(connection);
			throw new TransactionException(ErrorConstants.TRANSACTION_ERROR, e);
		}
		ConnectionManager.getInstance().commitTransaction(connection);
		return resultGroup;
	}

	@Override
	public GroupDTO getGroup(String name) throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException,
			SQLException, TransactionException {
		Group group;
		Connection connection = ConnectionManager.getInstance().beginTransaction();
		try {
			group = GroupDao.getInstance().getGroupByName(connection, name);
		} catch (QueryNotFoundException | DataBaseReadingException e) {
			ConnectionManager.getInstance().rollbackTransaction(connection);
			throw new TransactionException(ErrorConstants.TRANSACTION_ERROR, e);
		}
		ConnectionManager.getInstance().commitTransaction(connection);
		if (group == null) return null;
		return new GroupDTO(group.getIdGroup(), group.getName(), String.valueOf(group.getCount()),
				group.getDescription(), group.getScoreGroup(), group.getStatus(), null, null, null);
	}

	@Override
	public GroupDTO getGroupById(String id) throws QueryNotFoundException, JDBCDriverException,
			DataBaseReadingException, SQLException, TransactionException {
		Group group;
		Connection connection = ConnectionManager.getInstance().beginTransaction();
		try {
			group = GroupDao.getInstance().getGroupById(connection, id);
		} catch (QueryNotFoundException | DataBaseReadingException e) {
			ConnectionManager.getInstance().rollbackTransaction(connection);
			throw new TransactionException(ErrorConstants.TRANSACTION_ERROR, e);
		}
		ConnectionManager.getInstance().commitTransaction(connection);
		return new GroupDTO(group.getIdGroup(), group.getName(), String.valueOf(group.getCount()),
				group.getDescription(), group.getScoreGroup(), group.getStatus(), null, null, null);
	}

	@Override
	public String getDescriptionOfGroup(final GroupDTO groupDTO) {
		return groupDTO.getDescriptions();
	}

	@Override
	public void insert(GroupDTO groupDTO) throws SQLException, JDBCDriverException, TransactionException,
			QueryNotFoundException, DataBaseReadingException {
		if (groupDTO == null) {
			Log4jWrapper.get().error("You didn't enter competition");
			throw new IllegalArgumentException();
		} else {
			Connection connection = ConnectionManager.getInstance().beginTransaction();
			try {
				GroupDao.getInstance().createGroup(connection,
						new Group(groupDTO.getIdGroup(), groupDTO.getName(), Integer.parseInt(groupDTO.getCount()),
								groupDTO.getDescriptions(), groupDTO.getScoreGroup(), groupDTO.getStatus()));
			} catch (JDBCDriverException | DataBaseReadingException | QueryNotFoundException e) {
				ConnectionManager.getInstance().rollbackTransaction(connection);
				throw new TransactionException(ErrorConstants.TRANSACTION_ERROR, e);
			}
			ConnectionManager.getInstance().commitTransaction(connection);
		}
	}

	@Override
	public void update(GroupDTO groupDTO) throws SQLException, JDBCDriverException, TransactionException,
			QueryNotFoundException, DataBaseReadingException {
		Connection connection = ConnectionManager.getInstance().beginTransaction();
		// Group group = GroupDao.getInstance().getByFieldName(connection,
		// groupDTO.getName());
		try {
			GroupDao.getInstance().editGroup(connection,
					new Group(groupDTO.getIdGroup(), groupDTO.getName(), Integer.parseInt(groupDTO.getCount()),
							groupDTO.getDescriptions(), groupDTO.getScoreGroup(), groupDTO.getStatus()));
		} catch (JDBCDriverException | DataBaseReadingException | QueryNotFoundException e) {
			ConnectionManager.getInstance().rollbackTransaction(connection);
			throw new TransactionException(ErrorConstants.TRANSACTION_ERROR, e);
		}
		ConnectionManager.getInstance().commitTransaction(connection);
	}

	@Override
	public List<GroupDTO> getAll(final int partNumber, final int partSize, final Map<String, String> filters)
			throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException, EmptyResultSetException,
			CloseStatementException, SQLException, TransactionException {
		return null;
	}

	@Override
	public List<GroupDTO> getAllGroupsParticipants(int partNumber, int partSize) throws QueryNotFoundException,
			JDBCDriverException, DataBaseReadingException, SQLException, TransactionException {
		List<GroupDTO> resultGroupParticipants = new ArrayList<GroupDTO>();
		Connection connection = ConnectionManager.getInstance().beginTransaction();
		try {
			for (GroupUserView groupUsers : GroupUserViewDao.getInstance().getAllGroupsParticiapnts(connection,
					partNumber, partSize)) {
				resultGroupParticipants.add(new GroupDTO(groupUsers.getIdGroup(), groupUsers.getName(), null, null,
						null, groupUsers.getStatus(), groupUsers.getUsers().split(";"),
						groupUsers.getFirstname().split(";"), groupUsers.getLastname().split(";")));
			}
		} catch (QueryNotFoundException | DataBaseReadingException e) {
			ConnectionManager.getInstance().rollbackTransaction(connection);
			throw new TransactionException(ErrorConstants.TRANSACTION_ERROR, e);
		}
		ConnectionManager.getInstance().commitTransaction(connection);
		return resultGroupParticipants;
	}

}
