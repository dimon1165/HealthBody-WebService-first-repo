package edu.softserveinc.healthbody.entity;

	/**
	 * Class  edu.softserveinc.healthbody.entity.GroupCompetitions is POJO.
	 * Called by: 
	 * edu.softserveinc.healthbody.dao.GroupCompetitionsDao - create instance
	 * Return info about Competitions among groups
	 * 
	 * @version 9 August 2016 	
	 * 
	 * */
public class GroupCompetitions implements IEntity{
	private String idGroupCompetitions;
	private String idGroup;
	private String idCompetition;
	
	@Override
	public String getId() {
		return idGroupCompetitions;
	}

	 /**
     *  Constructor of edu.softserveinc.healthbody.entity.GroupCompetitions  
     */
	public GroupCompetitions(final String idGroupCompetitions, final String idGroup, final String idCompetition) {
		this.idGroupCompetitions = idGroupCompetitions;
		this.idGroup = idGroup;
		this.idCompetition = idCompetition;
	}

	 /**
     * Getters  
     */
	public final String getIdGroupCompetitions() {
		return idGroupCompetitions;
	}

	public final String getIdGroup() {
		return idGroup;
	}

	public final String getIdCompetition() {
		return idCompetition;
	}

	 /**
     * Setters  
     */
	public final void setIdGroupCompetitions(final String idGroupCompetitions) {
		this.idGroupCompetitions = idGroupCompetitions;
	}

	public final void setIdGroup(final String idGroup) {
		this.idGroup = idGroup;
	}

	public final void setIdCompetition(final String idCompetition) {
		this.idCompetition = idCompetition;
	}	
		
}
