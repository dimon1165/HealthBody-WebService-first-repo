package edu.softserveinc.healthbody.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import edu.softserveinc.healthbody.constants.DaoStatementsConstant.RoleDBQueries;
import edu.softserveinc.healthbody.entity.Role;
import edu.softserveinc.healthbody.exceptions.CloseStatementException;
import edu.softserveinc.healthbody.exceptions.DataBaseReadingException;
import edu.softserveinc.healthbody.exceptions.EmptyResultSetException;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.QueryNotFoundException;

public class RoleDao extends AbstractDao<Role>{
	private static volatile RoleDao instance = null;
	
	private RoleDao() {
		init();
	}

	public static RoleDao getInstance() {
		if (instance == null) {
			synchronized (UserDao.class) {
				if (instance == null) {
					instance = new RoleDao();
				}
			}
		}
		return instance;
	}

	protected void init() {
		for (RoleDBQueries userDBQueries : RoleDBQueries.values()) {
			sqlQueries.put(userDBQueries.getDaoQuery(), userDBQueries);
		}
	}

	@Override
	protected String[] getFields(Role entity) {
		List<String> fields = new ArrayList<>();
		fields.add(entity.getIdRole());
		fields.add(entity.getName());
		fields.add(entity.getDescription());
		return (String[]) fields.toArray();
	}

	@Override
	public Role createInstance(String[] args) {
		return new Role(
				args[0] == null ? UUID.randomUUID().toString() : args[0],
				args[1] == null ? new String() : args[1],
				args[2] == null ? new String() : args[2]);
	}
	
	public boolean createRole(Role role) throws JDBCDriverException, QueryNotFoundException, DataBaseReadingException{
		return insert(role);
	}
	
	public boolean deleteRole(Role role) throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException{
		return delete(role);
	}
	
	public List<Role> view() throws JDBCDriverException, DataBaseReadingException, EmptyResultSetException, CloseStatementException{
		return getAll();
	}
	
	public Role getRoleById(String id) throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException, CloseStatementException {
		return getById(id);
	}
	
	public Role getRoleByName(String name) throws JDBCDriverException, DataBaseReadingException, QueryNotFoundException, EmptyResultSetException, CloseStatementException {
		return getByFieldName(name);
	}

}
