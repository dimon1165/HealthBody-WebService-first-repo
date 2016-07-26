package edu.softserveinc.healthbody.entity;

public class Award implements IEntity {

	private String idAward;
	private String name;
	
	public Award(String idAward, String name) {
		this.idAward = idAward;
		this.name = name;
	}

	@Override
	public String getId() {
		return getIdAward();
	}
	
	// Getters
	public String getIdAward() {
		return idAward;
	}
	public String getName() {
		return name;
	}
	
	// Setters
	public void setIdAward(String idAward) {
		this.idAward = idAward;
	}
	public void setName(String name) {
		this.name = name;
	}
}