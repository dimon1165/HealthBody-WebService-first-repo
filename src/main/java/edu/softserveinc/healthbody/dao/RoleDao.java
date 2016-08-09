package edu.softserveinc.healthbody.dao;

import java.sql.Connection;
import java.util.List;
import java.util.UUID;

import edu.softserveinc.healthbody.constants.Constant.RoleCard;
import edu.softserveinc.healthbody.constants.DaoStatementsConstant.RoleDBQueries;
import edu.softserveinc.healthbody.entity.Role;
import edu.softserveinc.healthbody.exceptions.CloseStatementException;
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
	public Role createInstance(final String[] args) {
		return new Role(
				args[RoleCard.ID] == null ? UUID.randomUUID().toString() : args[RoleCard.ID],
				args[RoleCard.NAME] == null ? new String() : args[RoleCard.NAME],
				args[RoleCard.DESCRIPTION] == null ? new String() : args[RoleCard.DESCRIPTION]);
	}
	
	public boolean deleteRole(final Connection con, final Role role)
			throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException {
		return delete(con, role);
	}
	
	public List<Role> view(final Connection con) throws JDBCDriverException, DataBaseReadingException {
		return getAll(con);
	}
	
	public Role getRoleById(final Connection con, final String id) 
			throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException, CloseStatementException {
		return getById(con, id);
	}
	
	public Role getRoleByName(final Connection con, final String name)
			throws JDBCDriverException, DataBaseReadingException, QueryNotFoundException {
		return getByFieldName(con, name);
	}

}
