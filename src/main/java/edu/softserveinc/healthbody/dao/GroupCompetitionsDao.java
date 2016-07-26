package edu.softserveinc.healthbody.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import edu.softserveinc.healthbody.constants.DaoStatementsConstant.GroupCompetitionsDBQueries;
import edu.softserveinc.healthbody.entity.GroupCompetitions;
import edu.softserveinc.healthbody.exceptions.CloseStatementException;
import edu.softserveinc.healthbody.exceptions.DataBaseReadingException;
import edu.softserveinc.healthbody.exceptions.EmptyResultSetException;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.QueryNotFoundException;

public final class GroupCompetitionsDao extends AbstractDao<GroupCompetitions> {
	
	private static volatile GroupCompetitionsDao instance = null;

	private GroupCompetitionsDao() {
		init();
	}

	public static GroupCompetitionsDao getInstance() {
		if (instance == null) {
			synchronized (GroupCompetitionsDao.class) {
				if (instance == null) {
					instance = new GroupCompetitionsDao();
				}
			}
		}
		return instance;
	}

	protected void init() {
		for (GroupCompetitionsDBQueries groupCompetitionsDBQueries : GroupCompetitionsDBQueries.values()) {
			sqlQueries.put(groupCompetitionsDBQueries.getDaoQuery(), groupCompetitionsDBQueries);
		}
	}
	
	@Override
	public GroupCompetitions createInstance(String[] args) {
		return new GroupCompetitions(
				args[0] == null ? UUID.randomUUID().toString() : args[0], 
				args[1] == null ? UUID.randomUUID().toString() : args[1],
				args[2] == null ? UUID.randomUUID().toString() : args[2]);

	}
	
	@Override
	protected String[] getFields(GroupCompetitions entity) {
		List<String> fields = new ArrayList<>();
		fields.add(entity.getIdGroupCompetitions());
		fields.add(entity.getIdGroup());
		fields.add(entity.getIdCompetition());
		return (String[]) fields.toArray();
	}

	public boolean createGroupCompetitions(GroupCompetitions groupCompetitions) throws JDBCDriverException, QueryNotFoundException, DataBaseReadingException{
		return insert(groupCompetitions);
	}
	
	public boolean editGroupCompetitions(GroupCompetitions groupCompetitions, String idGroupCompetitions, String idGroup, String idCompetition) throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException{
		String[] fields = getFields(groupCompetitions);	
		boolean result = false;
		updateByField(fields[0], idGroupCompetitions, fields[1]	, idGroup);
		updateByField(fields[0], idGroupCompetitions, fields[2]	, idCompetition);
	
		if (fields[3] != null) result = false;
		else if (fields[1] == idGroup && fields[2] == idCompetition){
			result = true;			
		}
		return result;
	}
	
	public List<GroupCompetitions> view() throws JDBCDriverException, DataBaseReadingException, EmptyResultSetException, CloseStatementException{
		return getAll();
	}

}
