package edu.softserveinc.healthbody.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.softserveinc.healthbody.constants.DaoConstants;
import edu.softserveinc.healthbody.constants.DaoStatementsConstant.UserGroupQueries;
import edu.softserveinc.healthbody.db.ConnectionManager;
import edu.softserveinc.healthbody.entity.Group;
import edu.softserveinc.healthbody.entity.User;
import edu.softserveinc.healthbody.entity.UserGroup;
import edu.softserveinc.healthbody.exceptions.CloseStatementException;
import edu.softserveinc.healthbody.exceptions.DataBaseReadingException;
import edu.softserveinc.healthbody.exceptions.EmptyResultSetException;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.QueryNotFoundException;

public class UserGroupDao extends AbstractDao<UserGroup>{
	
	private static volatile UserGroupDao instance = null;

	
	public UserGroupDao() {
		init();
	}
	
	
	public static UserGroupDao getInstance(){
		if (instance == null){
			synchronized (UserGroupDao.class) {
				if (instance == null){
					instance = new UserGroupDao();
				}
			}
		}
		return instance;
	}

	protected void init() {
		for(UserGroupQueries userGroupViewQueries:UserGroupQueries.values()){
			sqlQueries.put(userGroupViewQueries.getDaoQuery(),userGroupViewQueries);
		}
		
	}

	

	@Override
	public UserGroup createInstance(String[] args) {
		return new UserGroup(Integer.parseInt(args[0] == null ? "0" : args[0]),
								   Integer.parseInt(args[1] == null ? "0" : args[1]), 
								   Integer.parseInt(args[2] == null ? "0" : args[2]));
	}


	@Override
	protected String[] getFields(UserGroup entity) {
		List<String> fields = new ArrayList<>();
		fields.add(entity.getIdUserGroup().toString());
		fields.add(entity.getIdUser().toString());
		fields.add(entity.getIdGroup().toString());
		return (String[]) fields.toArray();
	}
	
	
	public boolean addUserToGroup(User user, Group group) throws QueryNotFoundException, JDBCDriverException, EmptyResultSetException, DataBaseReadingException{
		boolean result = false;		
		result = insert(new UserGroup(null, user.getId(), group.getId()));
		return result;
	}
	
	public List<UserGroup> getUGbyId(Integer id) throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException, CloseStatementException, EmptyResultSetException {
		
		return getAllbyId(id);
	}
	
	public boolean createUserGroup (User user, Group group) throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException {
		boolean result = false;
		String query = sqlQueries.get(DaoQueries.INSERT).toString();
			if (query == null) {
				throw new QueryNotFoundException(String.format(DaoConstants.QUERY_NOT_FOUND, DaoQueries.INSERT.name()));
			}
			try (PreparedStatement pst = ConnectionManager.getInstance().getConnection().prepareStatement(query)) {
				int i = 1;
				pst.setInt(i++, user.getId());
				pst.setInt(i++, group.getIdGroup());
					
				result = pst.execute();
			} catch (SQLException e) {
					throw new DataBaseReadingException(DaoConstants.DATABASE_READING_ERROR, e);
			}
		return result;
	}
	
	public boolean deleteByUserId (Integer id) throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException {
		return deleteById(id);
	}
}
