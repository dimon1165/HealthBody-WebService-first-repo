package edu.softserveinc.healthbody.entity;

public class GroupCompetitions implements IEntity{
	private String idGroupCompetitions;
	private String idGroup;
	private String idCompetition;
	
	@Override
	public String getId() {
		return idGroupCompetitions;
	}

	public GroupCompetitions(String idGroupCompetitions, String idGroup, String idCompetition) {
		this.idGroupCompetitions = idGroupCompetitions;
		this.idGroup = idGroup;
		this.idCompetition = idCompetition;
	}

	//	Getters
	public String getIdGroupCompetitions() {
		return idGroupCompetitions;
	}

	public String getIdGroup() {
		return idGroup;
	}

	public String getIdCompetition() {
		return idCompetition;
	}

	//	Setters
	public void setIdGroupCompetitions(String idGroupCompetitions) {
		this.idGroupCompetitions = idGroupCompetitions;
	}

	public void setIdGroup(String idGroup) {
		this.idGroup = idGroup;
	}

	public void setIdCompetition(String idCompetition) {
		this.idCompetition = idCompetition;
	}	
		
}
