package edu.softserveinc.healthbody.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import edu.softserveinc.healthbody.constants.Constant.CompetitionCard;
import edu.softserveinc.healthbody.constants.DaoConstants;
import edu.softserveinc.healthbody.constants.DaoStatementsConstant.CompetitionDBQueries;
import edu.softserveinc.healthbody.db.ConnectionManager;
import edu.softserveinc.healthbody.entity.Competition;
import edu.softserveinc.healthbody.exceptions.DataBaseReadingException;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.QueryNotFoundException;;

public final class CompetitionDao extends AbstractDao<Competition> {
	
	private static volatile CompetitionDao instance;

	private CompetitionDao() {
		init();
	}

	public static CompetitionDao getInstance() {
		if (instance == null) {
			synchronized (CompetitionDao.class) {
				if (instance == null) {
					instance = new CompetitionDao();
				}
			}
		}
		return instance;
	}

	@Override
	protected void init() {
		for (CompetitionDBQueries competitionDBQueries : CompetitionDBQueries.values()) {
			sqlQueries.put(competitionDBQueries.getDaoQuery(), competitionDBQueries);
		}
	}
	
	@Override
	public Competition createInstance(String[] args) {
		return new Competition(
			args[CompetitionCard.ID] == null ? UUID.randomUUID().toString() : args[CompetitionCard.ID],
			args[CompetitionCard.NAME] == null ? new String() : args[CompetitionCard.NAME],
			args[CompetitionCard.DESCRIPTION] == null ? new String() : args[CompetitionCard.DESCRIPTION],
			Date.valueOf(args[CompetitionCard.START] == null ? new Date(System.currentTimeMillis()).toString() : args[CompetitionCard.START]),
			Date.valueOf(args[CompetitionCard.FINISH] == null ? new Date(System.currentTimeMillis()).toString() : args[CompetitionCard.FINISH]),
			args[CompetitionCard.IDCRITERIA] == null ? UUID.randomUUID().toString() : args[CompetitionCard.IDCRITERIA]);
	}
	
	public boolean createCompetition(final Competition competition)
			throws JDBCDriverException, QueryNotFoundException, DataBaseReadingException {
		boolean result = false;
		String query = sqlQueries.get(DaoQueries.INSERT).toString();
		if (query == null) {
			throw new QueryNotFoundException(String.format(DaoConstants.QUERY_NOT_FOUND, DaoQueries.INSERT.name()));
		}
		try (PreparedStatement pst = ConnectionManager.getInstance().getConnection().prepareStatement(query)) {
			int i = 1;
			pst.setString(i++, competition.getId());
			pst.setString(i++, competition.getName());
			pst.setString(i++, competition.getDescription());
			pst.setDate(i++, competition.getStart());
			pst.setDate(i++, competition.getFinish());
			pst.setString(i++, competition.getIdCriteria());
			result = pst.execute();
		} catch (SQLException e) {
			throw new DataBaseReadingException(DaoConstants.DATABASE_READING_ERROR, e);
		}
		return result;
	}
	
	public List<Competition> view() throws JDBCDriverException, DataBaseReadingException {
		return getAll();
	}

	@Override
	public boolean deleteById(String id) throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException {
		return false;
	}


}
