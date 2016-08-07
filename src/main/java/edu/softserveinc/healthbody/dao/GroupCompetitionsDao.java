package edu.softserveinc.healthbody.dao;

import java.util.List;
import java.util.UUID;

import edu.softserveinc.healthbody.constants.Constant.GroupCompetitionsCard;
import edu.softserveinc.healthbody.constants.DaoStatementsConstant.GroupCompetitionsDBQueries;
import edu.softserveinc.healthbody.entity.GroupCompetitions;
import edu.softserveinc.healthbody.exceptions.DataBaseReadingException;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;

public final class GroupCompetitionsDao extends AbstractDao<GroupCompetitions> {
	
	private static volatile GroupCompetitionsDao instance;

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

	@Override
	protected void init() {
		for (GroupCompetitionsDBQueries groupCompetitionsDBQueries : GroupCompetitionsDBQueries.values()) {
			sqlQueries.put(groupCompetitionsDBQueries.getDaoQuery(), groupCompetitionsDBQueries);
		}
	}
	
	@Override
	public GroupCompetitions createInstance(final String[] args) {
		return new GroupCompetitions(
				args[GroupCompetitionsCard.ID] == null ? UUID.randomUUID().toString() : args[GroupCompetitionsCard.ID], 
				args[GroupCompetitionsCard.IDGROUP] == null ? UUID.randomUUID().toString() : args[GroupCompetitionsCard.IDGROUP],
				args[GroupCompetitionsCard.IDCOMPETITION] == null ? UUID.randomUUID().toString() : args[GroupCompetitionsCard.IDCOMPETITION]);
	}
		
	public List<GroupCompetitions> view() throws JDBCDriverException, DataBaseReadingException {
		return getAll();
	}

}
