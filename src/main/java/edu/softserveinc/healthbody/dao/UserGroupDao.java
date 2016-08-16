package edu.softserveinc.healthbody.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import edu.softserveinc.healthbody.constants.Constants.UserGroupCard;
import edu.softserveinc.healthbody.constants.ErrorConstants;
import edu.softserveinc.healthbody.constants.DaoStatementsConstant.UserGroupQueries;
import edu.softserveinc.healthbody.entity.Group;
import edu.softserveinc.healthbody.entity.User;
import edu.softserveinc.healthbody.entity.UserGroup;
import edu.softserveinc.healthbody.exceptions.CloseStatementException;
import edu.softserveinc.healthbody.exceptions.DataBaseReadingException;
import edu.softserveinc.healthbody.exceptions.EmptyResultSetException;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.QueryNotFoundException;

public final class UserGroupDao extends AbstractDao<UserGroup> {
	
	private static volatile UserGroupDao instance;
	
	private UserGroupDao() {
		init();
	}
	
	public static UserGroupDao getInstance(){
		if (instance == null) {
			synchronized (UserGroupDao.class) {
				if (instance == null) {
					instance = new UserGroupDao();
				}
			}
		}
		return instance;
	}

	@Override
	protected void init() {
		for (UserGroupQueries userGroupViewQueries:UserGroupQueries.values()) {
			sqlQueries.put(userGroupViewQueries.getDaoQuery(), userGroupViewQueries);
		}
	}

	@Override
	public UserGroup createInstance(final String[] args) {
		return new UserGroup(args[UserGroupCard.ID] == null ? UUID.randomUUID().toString() : args[UserGroupCard.ID],
							 args[UserGroupCard.IDUSER] == null ? UUID.randomUUID().toString() : args[UserGroupCard.IDUSER], 
							 args[UserGroupCard.IDGROUP] == null ? UUID.randomUUID().toString() : args[UserGroupCard.IDGROUP]);
	}

	public List<UserGroup> getUserGroupbyId(final Connection con, final String id) 
			throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException, 
			CloseStatementException, EmptyResultSetException {
		return getAllbyId(con, id);
	}
	
	public boolean createUserGroup (final Connection con, final User user, final Group group)
			throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException {
		boolean result = false;
		String query = sqlQueries.get(DaoQueries.INSERT).toString();
			if (query == null) {
				throw new QueryNotFoundException(String.format(ErrorConstants.QUERY_NOT_FOUND, DaoQueries.INSERT.name()));
			}
			try (PreparedStatement pst = con.prepareStatement(query)) {
				int i = 1;
				pst.setString(i++, UUID.randomUUID().toString());
				pst.setString(i++, user.getId());
				pst.setString(i++, group.getIdGroup());					
				result = pst.execute();
			} catch (SQLException e) {
					throw new DataBaseReadingException(ErrorConstants.DATABASE_READING_ERROR, e);
			}
		return result;
	}
	
	public boolean deleteByUserId (final Connection con, final String id) 
			throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException {
		return deleteById(con, id);
	}
}
