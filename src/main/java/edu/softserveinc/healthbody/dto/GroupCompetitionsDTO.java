package edu.softserveinc.healthbody.dto;

/**
 * Class edu.softserveinc.healthbody.dto.GroupCompetitionsDTO is POJO. Used by:
 * edu.softserveinc.healthbody.services.impl.CompetitionsViewServiceImpl
 * to transfer data about groups, competitions, groups in competitions.
 * 
 * @version 5 September 2016
 * 
 */
public class GroupCompetitionsDTO {

	private String idGroupCompetition;
	private String idCompetition;
	private String idGroup;

	/**
	 * Default constructor of
	 * edu.softserveinc.healthbody.dto.GroupCompetitionsDTO
	 */
	public GroupCompetitionsDTO() {
	}
	
	/**
	 * Constructor of edu.softserveinc.healthbody.dto.GroupCompetitionsDTO
	 */
	public GroupCompetitionsDTO(final String idGroupCompetition, final String idCompetition, final String idGroup) {
		this.idGroupCompetition = idGroupCompetition;
		this.idCompetition = idCompetition;
		this.idGroup = idGroup;
	}

	/**
	 * Getters
	 */
	public String getIdGroupCompetition() {
		return idGroupCompetition;
	}

	public String getIdCompetition() {
		return idCompetition;
	}

	public String getIdGroup() {
		return idGroup;
	}

	/**
	 * Setters
	 */
	public void setIdGroupCompetition(String idGroupCompetition) {
		this.idGroupCompetition = idGroupCompetition;
	}

	public void setIdCompetition(String idCompetition) {
		this.idCompetition = idCompetition;
	}

	public void setIdGroup(String idGroup) {
		this.idGroup = idGroup;
	}
	
}
