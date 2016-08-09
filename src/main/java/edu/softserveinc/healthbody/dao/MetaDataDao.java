package edu.softserveinc.healthbody.dao;

import java.util.UUID;

import edu.softserveinc.healthbody.constants.Constants.MetaDataCard;
import edu.softserveinc.healthbody.constants.DaoStatementsConstant.MetaDataDBQueries;
import edu.softserveinc.healthbody.entity.MetaData;

public final class MetaDataDao extends AbstractDao<MetaData> {
	
	private static volatile MetaDataDao instance;

	private MetaDataDao() {
		init();
	}
	
	public MetaDataDao getInstance() {
		if (instance == null) {
			synchronized (MetaDataDao.class) {
				if (instance == null) {
					instance = new MetaDataDao();
				}
				
			}
		}
		return instance;
	}

	@Override
	protected void init() {
		for (MetaDataDBQueries metaDataDBQueries : MetaDataDBQueries.values()) {
			sqlQueries.put(metaDataDBQueries.getDaoQuery(), metaDataDBQueries);
		}

	}

	@Override
	public MetaData createInstance(final String[] args) {
		return new MetaData(
				args[MetaDataCard.ID] == null ? UUID.randomUUID().toString() : args[MetaDataCard.ID],
				args[MetaDataCard.LASTSYNCH] == null ? new String() : args[MetaDataCard.LASTSYNCH]);
	}

}
