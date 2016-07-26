package edu.softserveinc.healthbody.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
			return new Award(
					args[0] == null ? UUID.randomUUID().toString() : args[0],
					args[1] == null ? new String() : args[1]);
		}

		@Override
		protected String[] getFields(final Award entity) {
			List<String> fields = new ArrayList<>();
			fields.add(entity.getIdAward());
			fields.add(entity.getName());
			return (String[]) fields.toArray();
		}
		
		public boolean createAward(final Award award)
				throws JDBCDriverException, QueryNotFoundException, DataBaseReadingException {
			return insert(award);
		}
		
		public boolean deleteAward(final Award award)
				throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException{
			return delete(award);
		}
		
		public List<Award> view()
				throws JDBCDriverException, DataBaseReadingException {
			return getAll();
		}

}
