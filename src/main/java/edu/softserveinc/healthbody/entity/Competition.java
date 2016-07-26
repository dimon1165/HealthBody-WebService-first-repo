package edu.softserveinc.healthbody.entity;

import java.sql.Date;

public class Competition implements IEntity {

	private String idCompetition;
	private String name;
	private String description;
	private Date start;
	private Date finish;
	private String idCriteria;

	public Competition(String idCompetition, String name, String description, Date start, Date finish, 
			String idCriteria) {
		this.idCompetition = idCompetition;
		this.name = name;
		this.description = description;
		this.start = start;
		this.finish = finish;
		this.idCriteria = idCriteria;
	}
	
	// Setters
	public void setIdCompetitions(String idCompetition) {
		this.idCompetition = idCompetition;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public void setFinish(Date finish) {
		this.finish = finish;
	}

	public void setIdCriteria(String idCriteria) {
		this.idCriteria = idCriteria;
	}

	// Getters	
	public String getId() {
		return getIdCompetition();
	}
	
	public String getIdCompetition() {
		return idCompetition;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Date getStart() {
		return start;
	}

	public Date getFinish() {
		return finish;
	}

	public String getIdCriteria() {
		return idCriteria;
	}
	
}
