package edu.softserveinc.healthbody.entity;

public class GroupCompetitions implements IEntity{
	private Integer idGroupCompetitions;
	private Integer idGroup;
	private Integer idCompetition;
	
	@Override
	public Integer getId() {
		return idGroupCompetitions;
	}


	public GroupCompetitions(Integer idGroupCompetitions, Integer idGroup, Integer idCompetition) {
		this.idGroupCompetitions = idGroupCompetitions;
		this.idGroup = idGroup;
		this.idCompetition = idCompetition;
	}

	//	getters
	public Integer getIdGroupCompetitions() {
		return idGroupCompetitions;
	}

	public Integer getIdGroup() {
		return idGroup;
	}

	public Integer getIdCompetition() {
		return idCompetition;
	}

//	setters
	public void setIdGroupCompetitions(Integer idGroupCompetitions) {
		this.idGroupCompetitions = idGroupCompetitions;
	}

	public void setIdGroup(Integer idGroup) {
		this.idGroup = idGroup;
	}

	public void setIdCompetition(Integer idCompetition) {
		this.idCompetition = idCompetition;
	}
	
		
}
