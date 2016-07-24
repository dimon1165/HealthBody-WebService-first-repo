package edu.softserveinc.healthbody.entity;

public class GroupCompetitions implements IEntity {
	private Integer idGroupCompetitions;
	private Integer idGroup;
	private Integer idCompetition;
	
	@Override
	public final Integer getId() {
		return idGroupCompetitions;
	}

	public GroupCompetitions(final Integer idGroupCompetitions, final Integer idGroup, final Integer idCompetition) {
		this.idGroupCompetitions = idGroupCompetitions;
		this.idGroup = idGroup;
		this.idCompetition = idCompetition;
	}

	//	getters
	public final Integer getIdGroupCompetitions() {
		return idGroupCompetitions;
	}

	public final Integer getIdGroup() {
		return idGroup;
	}

	public final Integer getIdCompetition() {
		return idCompetition;
	}

	//	setters
	public final void setIdGroupCompetitions(final Integer idGroupCompetitions) {
		this.idGroupCompetitions = idGroupCompetitions;
	}

	public final void setIdGroup(final Integer idGroup) {
		this.idGroup = idGroup;
	}

	public final void setIdCompetition(final Integer idCompetition) {
		this.idCompetition = idCompetition;
	}
}
