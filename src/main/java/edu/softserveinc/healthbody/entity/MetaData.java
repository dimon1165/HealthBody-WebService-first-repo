package edu.softserveinc.healthbody.entity;

public class MetaData implements IEntity {

	private Integer idMetaData;
	private String lastSynch;

	public MetaData(final Integer idMetaData, final String lastSynch) {
		this.idMetaData = idMetaData;
		this.lastSynch = lastSynch;
	}

	@Override
	public final Integer getId() {
		return getIdMetaData();
	}

	// getters
	public final Integer getIdMetaData() {
		return idMetaData;
	}

	public final String getLastSynch() {
		return lastSynch;
	}

	// setters
	public final void setIdMetaData(final Integer idMetaData) {
		this.idMetaData = idMetaData;
	}

	public final void setLastSynch(final String lastSynch) {
		this.lastSynch = lastSynch;
	}
}
