package edu.softserveinc.healthbody.entity;


public class Award implements IEntity {

	private Integer idAward;
	private String name;
	
	public Award(Integer idAward, String name) {
		super();
		this.idAward = idAward;
		this.name = name;
	}

	@Override
	public Integer getId() {
		return getIdAward();
	}
	
	//getters
	public Integer getIdAward() {
		return idAward;
	}
	public String getName() {
		return name;
	}
	
	//setters
	public void setIdAward(Integer idAward) {
		this.idAward = idAward;
	}
	public void setName(String name) {
		this.name = name;
	}
}