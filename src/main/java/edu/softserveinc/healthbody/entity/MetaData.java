package edu.softserveinc.healthbody.entity;

public class MetaData implements IEntity {

	private String idMetaData;
	private String lastSynch;

	public MetaData(final String idMetaData, final String lastSynch) {
		this.idMetaData = idMetaData;
		this.lastSynch = lastSynch;
	}

	@Override
	public final String getId() {
		return getIdMetaData();
	}

	// Getters
	public final String getIdMetaData() {
		return idMetaData;
	}

	public final String getLastSynch() {
		return lastSynch;
	}

	// Setters
	public final void setIdMetaData(final String idMetaData) {
		this.idMetaData = idMetaData;
	}

	public final void setLastSynch(final String lastSynch) {
		this.lastSynch = lastSynch;
	}
}
