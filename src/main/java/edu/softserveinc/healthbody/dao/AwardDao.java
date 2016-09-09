package edu.softserveinc.healthbody.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import edu.softserveinc.healthbody.constants.Constants.AwardCard;
import edu.softserveinc.healthbody.constants.DaoStatementsConstant.AwardDBQueries;
import edu.softserveinc.healthbody.constants.ErrorConstants;
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
		for (AwardDBQueries awardDBQueries : AwardDBQueries.values()) {
			sqlQueries.put(awardDBQueries.getDaoQuery(), awardDBQueries);
		}
	}

	@Override
	public Award createInstance(final String[] args) {
		return new Award(args[AwardCard.ID] == null ? UUID.randomUUID().toString() : args[AwardCard.ID],
				args[AwardCard.NAME] == null ? new String() : args[AwardCard.NAME]);
	}

	public boolean deleteAward(final Connection connection, final Award award)
			throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException {
		return delete(connection, award);
	}

	public List<Award> getAllAwards(final Connection connection) throws JDBCDriverException, DataBaseReadingException {
		List<Award> awards = new ArrayList<>();
		String query = sqlQueries.get(DaoQueries.GET_ALL).toString();
		if (query == null) {
			throw new RuntimeException(String.format(ErrorConstants.QUERY_NOT_FOUND, DaoQueries.GET_ALL.name()));
		}
		try (PreparedStatement pst = connection.prepareStatement(query);
			ResultSet resultSet = pst.executeQuery()) {
			String[] queryResult = new String[resultSet.getMetaData().getColumnCount()];
			while (resultSet.next()) {
				awards.add(createInstance(getQueryResultArr(queryResult, resultSet)));
			}
		} catch (SQLException e) {
			throw new DataBaseReadingException(ErrorConstants.DATABASE_READING_ERROR, e);
		}
		return awards;
	}

}
