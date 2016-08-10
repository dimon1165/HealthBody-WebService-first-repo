package edu.softserveinc.healthbody.entity;

	/**
	 * Class  edu.softserveinc.healthbody.entity.MetaData is POJO.
	 * Called by: 
	 * edu.softserveinc.healthbody.dao.MetaDataDao - create instance
	 * Return info about synchronization with database. 
	 * 
	 * 
	 * @version 9 August 2016 	
	 * 
	 * */
public class MetaData implements IEntity {

	private String idMetaData;
	private String lastSynch;

	 /**
     *  Constructor of edu.softserveinc.healthbody.entity.MetaData  
     */
	public MetaData(final String idMetaData, final String lastSynch) {
		this.idMetaData = idMetaData;
		this.lastSynch = lastSynch;
	}

	@Override
	public final String getId() {
		return getIdMetaData();
	}

	 /**
     * Getters  
     */
	public final String getIdMetaData() {
		return idMetaData;
	}

	public final String getLastSynch() {
		return lastSynch;
	}

	 /**
     * Setters  
     */
	public final void setIdMetaData(final String idMetaData) {
		this.idMetaData = idMetaData;
	}

	public final void setLastSynch(final String lastSynch) {
		this.lastSynch = lastSynch;
	}
}
