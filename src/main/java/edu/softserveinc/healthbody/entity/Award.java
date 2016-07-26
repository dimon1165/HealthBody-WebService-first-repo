package edu.softserveinc.healthbody.entity;

public class Award implements IEntity {

	private String idAward;
	private String name;
	
	public Award(final String idAward, final String name) {
		this.idAward = idAward;
		this.name = name;
	}

	@Override
	public final String getId() {
		return getIdAward();
	}
	
	// Getters
	public final String getIdAward() {
		return idAward;
	}
	public final String getName() {
		return name;
	}
	
	// Setters
	public final void setIdAward(final String idAward) {
		this.idAward = idAward;
	}
	public final void setName(final String name) {
		this.name = name;
	}
}