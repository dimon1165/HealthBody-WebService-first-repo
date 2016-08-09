package edu.softserveinc.healthbody.dao;

import java.sql.Connection;
import java.util.List;
import java.util.UUID;

import edu.softserveinc.healthbody.constants.Constant.CriteriaCard;
import edu.softserveinc.healthbody.constants.DaoStatementsConstant.CriteriaDBQueries;
import edu.softserveinc.healthbody.entity.Criteria;
import edu.softserveinc.healthbody.exceptions.DataBaseReadingException;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.QueryNotFoundException;

public final class CriteriaDao extends AbstractDao<Criteria> {
	
	private static volatile CriteriaDao instance;

	private CriteriaDao() {
		init();
	}

	public static CriteriaDao getInstance() {
		if (instance == null) {
			synchronized (CriteriaDao.class) {
				if (instance == null) {
					instance = new CriteriaDao();
				}
			}
		}
		return instance;
	}

	@Override
	protected void init() {
		for (CriteriaDBQueries criteriaDBQueries : CriteriaDBQueries.values()) {
			sqlQueries.put(criteriaDBQueries.getDaoQuery(), criteriaDBQueries);
		}
	}

	@Override
	public Criteria createInstance(final String[] args) {
		return new Criteria(
				args[CriteriaCard.ID] == null ? UUID.randomUUID().toString() : args[CriteriaCard.ID],
				args[CriteriaCard.NAME] == null ? new String() : args[CriteriaCard.NAME],
				Double.parseDouble(args[CriteriaCard.METRICS] == null ? "0" : args[CriteriaCard.METRICS]),
				args[CriteriaCard.GETGOOGLE] == null ? new String() : args[CriteriaCard.METRICS]);
	}

	public boolean deleteCriteria(final Connection con, final Criteria criteria) 
			throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException {
		return delete(con, criteria);
	}
	
	public List<Criteria> view(final Connection con) throws JDBCDriverException, DataBaseReadingException {
		return getAll(con);
	}
	

}
