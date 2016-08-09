package edu.softserveinc.healthbody.dto;

import java.util.List;
	
	/**
	 * Class  edu.softserveinc.healthbody.dto.CompetitionDTO is POJO.
	 * Used by edu.softserveinc.healthbody.services.impl.CompetitionsViewServiceImpl
	 * to transfer data about competitions.
	 * 
	 * @version 9 August 2016 	
	 * 
	 * */
public class CompetitionDTO {

	private String idCompetition;
	private String name;
	private String count;
	private String startDate;
	private String finishDate;
	private String description;
	private String nameCriteria;
	private List<String> groups;
	private List<String> logins;

	 /**
     *  Default constructor of edu.softserveinc.healthbody.dto.CompetitionDTO   
     */
	public CompetitionDTO() { }
	
	 /**
     *  Constructor of edu.softserveinc.healthbody.dto.CompetitionDTO   
     */
	public CompetitionDTO(final String idCompetition, final String name, final String count, final String startDate,
			final String finishDate, final String description, final String nameCriteria, final List<String> groups, 
			final List<String> logins) {
		this.idCompetition = idCompetition;
		this.name = name;
		this.count = count;
		this.startDate = startDate;
		this.finishDate = finishDate;
		this.description = description;
		this.nameCriteria = nameCriteria;
		this.groups = groups;
		this.logins = logins;
	}
	
	 /**
     * Getters   
     */
	public String getIdCompetition(){
		return idCompetition;
	}

	public final String getName() {
		return name;
	}

	public final String getCount() {
		return count;
	}

	public final String getStartDate() {
		return startDate;
	}

	public final String getFinishDate() {
		return finishDate;
	}

	public final List<String> getGroups() {
		return groups;
	}

	public final List<String> getLogins() {
		return logins;
	}

	public final String getDescription() {
		return description;
	}

	public final String getNameCriteria() {
		return nameCriteria;
	}

	 /**
     * Setters   
     */
	public final void setIdCompetition(final String idCompetition) {
		this.idCompetition = idCompetition;
	}	
	
	public final void setName(final String name) {
		this.name = name;
	}

	public final void setCount(final String count) {
		this.count = count;
	}

	public final void setStartDate(final String startDate) {
		this.startDate = startDate;
	}

	public final void setFinishDate(String finishDate) {
		this.finishDate = finishDate;
	}

	public final void setDescription(String description) {
		this.description = description;
	}

	public final void setNameCriteria(final String nameCriteria) {
		this.nameCriteria = nameCriteria;
	}

	public final void setGroups(final List<String> groups) {
		this.groups = groups;
	}

	public final void setLogins(final List<String> logins) {
		this.logins = logins;
	}

	@Override
	public final String toString() {
		return "CompetitionDTO [name=" + name + ", count=" + count + ", startDate="
				+ startDate + ", finishDate=" + finishDate + "]" + System.lineSeparator();
	}

}