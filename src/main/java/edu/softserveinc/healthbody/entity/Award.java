package edu.softserveinc.healthbody.entity;


public class Award implements IEntity {

	private Integer idAward;
	private String name;
	
	public Award(final Integer idAward, final String name) {
		this.idAward = idAward;
		this.name = name;
	}

	@Override
	public final Integer getId() {
		return getIdAward();
	}
	
	//getters
	public final Integer getIdAward() {
		return idAward;
	}
	public final String getName() {
		return name;
	}
	
	//setters
	public final void setIdAward(final Integer idAward) {
		this.idAward = idAward;
	}
	public final void setName(final String name) {
		this.name = name;
	}
}