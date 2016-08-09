package edu.softserveinc.healthbody.dao;

import java.util.List;
import java.util.UUID;

import edu.softserveinc.healthbody.constants.Constants.UserCompetitionsCard;
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
				args[UserCompetitionsCard.ID] == null ? UUID.randomUUID().toString() : args[UserCompetitionsCard.ID],
				args[UserCompetitionsCard.IDUSER] == null ? UUID.randomUUID().toString() : args[UserCompetitionsCard.IDUSER],
				args[UserCompetitionsCard.IDCOMPETITION] == null ? UUID.randomUUID().toString() : args[UserCompetitionsCard.IDCOMPETITION],
				Integer.parseInt(args[UserCompetitionsCard.USERSCORE] == null ? "0" : args[UserCompetitionsCard.USERSCORE]),
				args[UserCompetitionsCard.IDAWARD] == null ? UUID.randomUUID().toString() : args[UserCompetitionsCard.IDAWARD],
				args[UserCompetitionsCard.TIMERECEIVED] == null ? new String() : args[UserCompetitionsCard.TIMERECEIVED]);
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
