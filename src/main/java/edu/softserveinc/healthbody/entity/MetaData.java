package edu.softserveinc.healthbody.entity;

public class MetaData implements IEntity {

	private String idMetaData;
	private String lastSynch;

	public MetaData(String idMetaData, String lastSynch) {
		this.idMetaData = idMetaData;
		this.lastSynch = lastSynch;
	}

	@Override
	public String getId() {
		return getIdMetaData();
	}

	// getters
	public String getIdMetaData() {
		return idMetaData;
	}

	public String getLastSynch() {
		return lastSynch;
	}

	// setters
	public void setIdMetaData(String idMetaData) {
		this.idMetaData = idMetaData;
	}

	public void setLastSynch(String lastSynch) {
		this.lastSynch = lastSynch;
	}

}
