package edu.softserveinc.healthbody.dao;


import java.util.ArrayList;
import java.util.List;

import edu.softserveinc.healthbody.entity.Group;
import edu.softserveinc.healthbody.entity.User;
import edu.softserveinc.healthbody.entity.UserGroupView;
import edu.softserveinc.healthbody.entity.UserGroupView.UserGroupViewQueries;
import edu.softserveinc.healthbody.exceptions.DataBaseReadingException;
import edu.softserveinc.healthbody.exceptions.EmptyResultSetException;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.QueryNotFoundException;


public final class UserGroupViewDao extends AbstractDao<UserGroupView>{
	
	private static volatile UserGroupViewDao instance;

	
	private UserGroupViewDao() {
		init();
	}
	
	
	public static UserGroupViewDao getInstance() {
		if (instance == null) {
			synchronized (UserGroupViewDao.class) {
				if (instance == null) {
					instance = new UserGroupViewDao();
				}
			}
		}
		return instance;
	}

	@Override
	protected void init() {
		for (UserGroupViewQueries userGroupViewQueries:UserGroupViewQueries.values()) {
			sqlQueries.put(userGroupViewQueries.getDaoQuery(), userGroupViewQueries);
		}
		
	}

	

	@Override
	public UserGroupView createInstance(final String[] args) {
		return new UserGroupView(Integer.parseInt(args[0] == null ? "0" : args[0]),
				Integer.parseInt(args[1] == null ? "0" : args[1]), 
				Integer.parseInt(args[2] == null ? "0" : args[2]),
				Boolean.parseBoolean(args[3] == null ? "false" : args[3]));
	}


	@Override
	protected String[] getFields(final UserGroupView entity) {
		List<String> fields = new ArrayList<>();
		fields.add(entity.getIdUserGroup().toString());
		fields.add(entity.getIdUser().toString());
		fields.add(entity.getIdGroup().toString());
		fields.add(entity.getMemberGgoup().toString());
		return (String[]) fields.toArray();
	}
	
	
	public boolean addUserToGroup(final User user, final Group group)
			throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException, EmptyResultSetException {
		boolean result = false;
		String idUserGroup = getIdByTwoEntities(String.valueOf(user.getIdUser()), String.valueOf(group.getIdGroup().toString()));
		if (idUserGroup != null) {
			updateByField("usersgroups.member_group", "true", "usersgroups.id_user_group", idUserGroup);
		}		
		return result;
	}
}
