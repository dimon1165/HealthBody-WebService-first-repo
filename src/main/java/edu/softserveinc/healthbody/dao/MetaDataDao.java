package edu.softserveinc.healthbody.dao;

import java.util.ArrayList;
import java.util.List;

import edu.softserveinc.healthbody.constants.DaoStatementsConstant.MetaDataDBQueries;
import edu.softserveinc.healthbody.entity.MetaData;

public class MetaDataDao extends AbstractDao<MetaData> {
	
	private static volatile MetaDataDao instance = null;
	
	

	private MetaDataDao() {
		init();
	}
	
	public MetaDataDao getInstance() {
		if(instance == null) {
			synchronized (MetaDataDao.class) {
				if(instance == null) {
					instance = new MetaDataDao();
				}
				
			}
		}
		return instance;
	}

	protected void init() {
		for (MetaDataDBQueries metaDataDBQueries : MetaDataDBQueries.values()) {
			sqlQueries.put(metaDataDBQueries.getDaoQuery(), metaDataDBQueries);
		}

	}
	@Override
	protected String[] getFields(MetaData entity) {
		List<String> fields = new ArrayList<>();
		fields.add(entity.getIdMetaData().toString());
		fields.add(entity.getLastSynch());
		return (String[])fields.toArray();
	}

	@Override
	public MetaData createInstance(String[] args) {
		return new MetaData(
				Integer.parseInt(args[0] == null ? "0" : args[0]),
				args[1] == null ? new String() : args[1]);
	}

}
