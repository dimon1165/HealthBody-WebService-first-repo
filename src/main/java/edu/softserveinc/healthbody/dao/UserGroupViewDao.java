package edu.softserveinc.healthbody.dao;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import edu.softserveinc.healthbody.entity.Group;
import edu.softserveinc.healthbody.entity.User;
import edu.softserveinc.healthbody.entity.UserGroupView;
import edu.softserveinc.healthbody.entity.UserGroupView.UserGroupViewQueries;
import edu.softserveinc.healthbody.exceptions.CloseStatementException;
import edu.softserveinc.healthbody.exceptions.DataBaseReadingException;
import edu.softserveinc.healthbody.exceptions.EmptyResultSetException;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.QueryNotFoundException;


public class UserGroupViewDao extends AbstractDao<UserGroupView>{
	
	private static volatile UserGroupViewDao instance = null;

	
	public UserGroupViewDao() {
		init();
	}
	
	
	public static UserGroupViewDao getInstance(){
		if (instance == null){
			synchronized (UserGroupViewDao.class) {
				if (instance == null){
					instance = new UserGroupViewDao();
				}
			}
		}
		return instance;
	}

	protected void init() {
		for(UserGroupViewQueries userGroupViewQueries:UserGroupViewQueries.values()){
			sqlQueries.put(userGroupViewQueries.getDaoQuery(),userGroupViewQueries);
		}
		
	}

	

	@Override
	public UserGroupView createInstance(String[] args) {
		return new UserGroupView(args[0] == null ? UUID.randomUUID().toString() : args[0],
								 args[1] == null ? UUID.randomUUID().toString() : args[1], 
								 args[2] == null ? UUID.randomUUID().toString() : args[2],
								 Boolean.parseBoolean(args[3] == null ? "false" : args[3]));
	}


	@Override
	protected String[] getFields(UserGroupView entity) {
		List<String> fields = new ArrayList<>();
		fields.add(entity.getIdUserGroup());
		fields.add(entity.getIdUser());
		fields.add(entity.getIdGroup());
		fields.add(entity.getMemberGgoup().toString());
		return (String[]) fields.toArray();
	}
	
	
	public boolean addUserToGroup(User user, Group group) throws QueryNotFoundException, JDBCDriverException, EmptyResultSetException, DataBaseReadingException, CloseStatementException{
		boolean result = false;
		String id_user_group = getIdByTwoEntities(String.valueOf(user.getIdUser()), String.valueOf(group.getIdGroup().toString()));
		if (id_user_group != null){
			updateByField("usersgroups.member_group", "true", "usersgroups.id_user_group", id_user_group);
		}		
		return result;
	}

}
