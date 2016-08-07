package edu.softserveinc.healthbody.dao;

import java.util.List;
import java.util.UUID;

import edu.softserveinc.healthbody.constants.Constant.AwardCard;
import edu.softserveinc.healthbody.constants.DaoStatementsConstant.GroupDBQueries;
import edu.softserveinc.healthbody.entity.Award;
import edu.softserveinc.healthbody.exceptions.DataBaseReadingException;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.QueryNotFoundException;;

public final class AwardDao extends AbstractDao<Award> {

	private static volatile AwardDao instance;

	private AwardDao() {
		init();
	}

	public static AwardDao getInstance() {
		if (instance == null) {
			synchronized (AwardDao.class) {
				if (instance == null) {
					instance = new AwardDao();
				}
			}
		}
		return instance;
	}

	@Override
	protected void init() {
		for (GroupDBQueries groupDBQueries : GroupDBQueries.values()) {
			sqlQueries.put(groupDBQueries.getDaoQuery(), groupDBQueries);
		}
	}

	@Override
	public Award createInstance(final String[] args) {
		return new Award(args[AwardCard.ID] == null ? UUID.randomUUID().toString() : args[AwardCard.ID],
				args[AwardCard.NAME] == null ? new String() : args[AwardCard.NAME]);
	}

	public boolean deleteAward(final Award award)
			throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException {
		return delete(award);
	}

	public List<Award> view() throws JDBCDriverException, DataBaseReadingException {
		return getAll();
	}

}
