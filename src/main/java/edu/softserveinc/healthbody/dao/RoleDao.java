package edu.softserveinc.healthbody.dao;

import java.util.ArrayList;
import java.util.List;

import edu.softserveinc.healthbody.constants.DaoStatementsConstant.RoleDBQueries;
import edu.softserveinc.healthbody.entity.Role;
import edu.softserveinc.healthbody.exceptions.DataBaseReadingException;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.QueryNotFoundException;

public final class RoleDao extends AbstractDao<Role> {
	private static volatile RoleDao instance;
	
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

	@Override
	protected void init() {
		for (RoleDBQueries userDBQueries : RoleDBQueries.values()) {
			sqlQueries.put(userDBQueries.getDaoQuery(), userDBQueries);
		}
	}

	@Override
	protected String[] getFields(final Role entity) {
		List<String> fields = new ArrayList<>();
		fields.add(entity.getIdRole().toString());
		fields.add(entity.getName());
		fields.add(entity.getDescription());
		return (String[]) fields.toArray();
	}

	@Override
	public Role createInstance(final String[] args) {
		return new Role(
				Integer.parseInt(args[0] == null ? "0" : args[0]),
				args[1] == null ? new String() : args[1],
				args[2] == null ? new String() : args[2]);
	}
	
	public boolean createRole(final Role role)
			throws JDBCDriverException, QueryNotFoundException, DataBaseReadingException {
		return insert(role);
	}
	
	public boolean deleteRole(final Role role)
			throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException {
		return delete(role);
	}
	
	public List<Role> view() throws JDBCDriverException, DataBaseReadingException {
		return getAll();
	}
	
	public Role getRoleById(final Integer id)
			throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException {
		return getById(id);
	}
	
	public Role getRoleByName(final String name)
			throws JDBCDriverException, DataBaseReadingException, QueryNotFoundException {
		return getByFieldName(name);
	}

}
