package edu.softserveinc.healthbody.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import edu.softserveinc.healthbody.constants.DaoStatementsConstant.UserCompetitionsDBQueries;
import edu.softserveinc.healthbody.entity.UserCompetitions;
import edu.softserveinc.healthbody.exceptions.CloseStatementException;
import edu.softserveinc.healthbody.exceptions.DataBaseReadingException;
import edu.softserveinc.healthbody.exceptions.EmptyResultSetException;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.QueryNotFoundException;

public final class UserCompetitionsDao extends AbstractDao<UserCompetitions> {
	
	private static volatile UserCompetitionsDao instance;
	
	private UserCompetitionsDao() {
		init();
	}
	
	public static UserCompetitionsDao getInstance() {
		if (instance == null) {
			synchronized (UserCompetitionsDao.class) {
				if (instance == null) {
					instance = new UserCompetitionsDao();
				}
			}
		}
		return instance;
	}
	
	@Override
	protected void init() {
		for (UserCompetitionsDBQueries userCompetitionsDBQueries : UserCompetitionsDBQueries.values()) {
			sqlQueries.put(userCompetitionsDBQueries.getDaoQuery(), userCompetitionsDBQueries);
		}
	}

	@Override
	public UserCompetitions createInstance(final String[] args) {
		return new UserCompetitions(
				args[0] == null ? UUID.randomUUID().toString() : args[0],
				args[1] == null ? UUID.randomUUID().toString() : args[1],
				args[2] == null ? UUID.randomUUID().toString() : args[2],
				Integer.parseInt(args[3] == null ? "0" : args[3]),
				args[4] == null ? UUID.randomUUID().toString() : args[4],
				args[5] == null ? new String() : args[5]);
	}
	
	@Override
	protected String[] getFields(final UserCompetitions entity) {
		List<String> fields = new ArrayList<>();
		fields.add(entity.getIdUserCompetition());
		fields.add(entity.getIdUser());
		fields.add(entity.getIdCompetition());
		fields.add(entity.getUserScore().toString());
		fields.add(entity.getIdAwards());
		fields.add(entity.getTimeReceived());		
		return (String[]) fields.toArray();
	}
	
	public boolean createUserCompetitions(final UserCompetitions obj) throws JDBCDriverException, QueryNotFoundException, DataBaseReadingException {
		return insert(obj);
	}
	
	public boolean updateUserCompetitions(final String fieldName, final String text,
			final String fieldCondition, final String textCondition)
					throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException {
		return updateByField(fieldName, text, fieldCondition, textCondition);
	}
	
	public List<UserCompetitions> viewAll() 
			throws JDBCDriverException, DataBaseReadingException, EmptyResultSetException, CloseStatementException {		
		return getAll();
	}
	
	public List<UserCompetitions> getUCbyId(final String id) 
			throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException, CloseStatementException, EmptyResultSetException {		
		return getAllbyId(id);
	}

	public boolean deleteByUserId(final String id) throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException {
		return deleteById(id);
	}
}
