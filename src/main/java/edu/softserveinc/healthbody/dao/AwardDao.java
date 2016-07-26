package edu.softserveinc.healthbody.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import edu.softserveinc.healthbody.constants.DaoStatementsConstant.GroupDBQueries;
import edu.softserveinc.healthbody.entity.Award;
import edu.softserveinc.healthbody.exceptions.CloseStatementException;
import edu.softserveinc.healthbody.exceptions.DataBaseReadingException;
import edu.softserveinc.healthbody.exceptions.EmptyResultSetException;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.QueryNotFoundException;;

public final class AwardDao extends AbstractDao<Award> {
	
	private static volatile AwardDao instance = null;

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

		protected void init() {
			for (GroupDBQueries groupDBQueries : GroupDBQueries.values()) {
				sqlQueries.put(groupDBQueries.getDaoQuery(), groupDBQueries);
			}
		}

		@Override
		public Award createInstance(String[] args) {
			return new Award(
					args[0] == null ? UUID.randomUUID().toString() : args[0],
					args[1] == null ? new String() : args[1]);
		}

		@Override
		protected String[] getFields(Award entity) {
			List<String> fields = new ArrayList<>();
			fields.add(entity.getIdAward());
			fields.add(entity.getName());
			return (String[]) fields.toArray();
		}
		
		public boolean createAward(Award award) throws JDBCDriverException, QueryNotFoundException, DataBaseReadingException{
			return insert(award);
		}
		
		public boolean deleteAward(Award award) throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException{
			return delete(award);
		}
		
		public List<Award> view() throws JDBCDriverException, DataBaseReadingException, EmptyResultSetException, CloseStatementException{
			return getAll();
		}

		@Override
		public List<Award> getFilterRange(int partNumber, int partSize, Map<String, String> filters) {
			// TODO Auto-generated method stub
			return null;
		}
		


	}
