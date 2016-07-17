package edu.softserveinc.healthbody.dao;

import java.util.ArrayList;
import java.util.List;

import edu.softserveinc.healthbody.constants.DaoStatementsConstant.CriteriaDBQueries;
import edu.softserveinc.healthbody.entity.Criteria;
import edu.softserveinc.healthbody.exceptions.CloseStatementException;
import edu.softserveinc.healthbody.exceptions.DataBaseReadingException;
import edu.softserveinc.healthbody.exceptions.EmptyResultSetException;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.QueryNotFoundException;

public class CriteriaDao extends AbstractDao<Criteria> {
	
	private static volatile CriteriaDao instance = null;

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

	protected void init() {
		for (CriteriaDBQueries criteriaDBQueries : CriteriaDBQueries.values()) {
			sqlQueries.put(criteriaDBQueries.getDaoQuery(), criteriaDBQueries);
		}
	}

	@Override
	public Criteria createInstance(String[] args) {
		return new Criteria(
				Integer.parseInt(args[0] == null ? "0" : args[0]),
				args[1] == null ? new String() : args[1],
				Double.parseDouble(args[2] == null ? "0" : args[2]),
				args[3] == null ? new String() : args[3]);
	}

	@Override
	protected String[] getFields(Criteria entity) {
		List<String> fields = new ArrayList<>();
		fields.add(entity.getIdCriteria().toString());
		fields.add(entity.getName());
		fields.add(entity.getMetrics().toString());
		fields.add(entity.getGetGoogle());
		return (String[]) fields.toArray();
	}
	
	public boolean createCriteria(Criteria criteria) throws JDBCDriverException, QueryNotFoundException, DataBaseReadingException{
		return insert(criteria);
	}
	
	public boolean editCriteria(Criteria criteria, String idCriteria, String name, String metrics,
			String getGoogle) throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException{
		String[] fields = getFields(criteria);	
		boolean result = false;
		updateByField(fields[0], idCriteria, fields[1]	, name);
		updateByField(fields[0], idCriteria, fields[2]	, metrics);
		updateByField(fields[0], idCriteria, fields[3]	, getGoogle);
		if (fields[1] == name && fields[2] == metrics && fields[3] == getGoogle){
			result = true;			
		}
		return result;
	}
	
	public boolean deleteCriteria(Criteria criteria) throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException{
		return delete(criteria);
	}
	
	public List<Criteria> view() throws JDBCDriverException, DataBaseReadingException, EmptyResultSetException, CloseStatementException{
		return getAll();
	}
	

}
