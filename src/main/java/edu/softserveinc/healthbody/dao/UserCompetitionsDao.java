package edu.softserveinc.healthbody.dao;

import java.util.ArrayList;
import java.util.List;

import edu.softserveinc.healthbody.constants.DaoStatementsConstant.UserCompetitionsDBQueries;
import edu.softserveinc.healthbody.entity.UserCompetitions;
import edu.softserveinc.healthbody.exceptions.CloseStatementException;
import edu.softserveinc.healthbody.exceptions.DataBaseReadingException;
import edu.softserveinc.healthbody.exceptions.EmptyResultSetException;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.QueryNotFoundException;

public final class UserCompetitionsDao extends AbstractDao<UserCompetitions> {
	
	private static volatile UserCompetitionsDao instance = null;
	
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
	public UserCompetitions createInstance(String[] args) {
		return new UserCompetitions(
				Integer.parseInt(args[0] == null ? "0" : args[0]),
				Integer.parseInt(args[1] == null ? "0" : args[1]),
				Integer.parseInt(args[2] == null ? "0" : args[2]),
				Integer.parseInt(args[3] == null ? "0" : args[3]),
				Integer.parseInt(args[4] == null ? "0" : args[4]),
				args[5] == null ? new String() : args[5]);
	}
	@Override
	protected String[] getFields(UserCompetitions entity) {
		List<String> fields = new ArrayList<>();
		fields.add(entity.getIdUserCompetition().toString());
		fields.add(entity.getIdUser().toString());
		fields.add(entity.getIdCompetition().toString());
		fields.add(entity.getUserScore().toString());
		fields.add(entity.getIdAwards().toString());
		fields.add(entity.getTimeReceived());
		
		return (String[]) fields.toArray();
	}
	
	public boolean createUserCompetitions(UserCompetitions obj) throws JDBCDriverException, QueryNotFoundException, DataBaseReadingException {
		
		return insert(obj);
	}
	
	public boolean updateUserCompetitions(String fieldName, String text, String fieldCondition,
			String textCondition) throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException {
		
		return updateByField(fieldName, text, fieldCondition, textCondition);
	}
	
	public List<UserCompetitions> viewAll() throws JDBCDriverException, DataBaseReadingException, EmptyResultSetException, CloseStatementException {
		
		return getAll();
	}
	
	public List<UserCompetitions> getUCbyId(Integer id) throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException, CloseStatementException, EmptyResultSetException {
		
		return getAllbyId(id);
	}

	public boolean deleteByUserId(Integer id) throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException {
		return deleteById(id);
	}
}
