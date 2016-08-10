package edu.softserveinc.healthbody.entity;

import java.sql.Date;

	/**
	 * Class  edu.softserveinc.healthbody.entity.Competiotions is POJO. 
	 * Called by: 
	 * edu.softserveinc.healthbody.dao.CompetitionDao - create instance
	 * edu.softserveinc.healthbody.services.impl.CompetitionsServiceImpl - fill DTO's
	 * 
	 * Return info about Competitions.
	 *  
	 * @version 9 August 2016 	
	 * 
	 * */
public class Competition implements IEntity {

	private String idCompetition;
	private String name;
	private String description;
	private Date start;
	private Date finish;
	private String idCriteria;

	 /**
     *  Constructor of edu.softserveinc.healthbody.entity.Competitions   
     */
	public Competition(final String idCompetition, final String name, final String description, final Date start, 
			final Date finish, final String idCriteria) {
		this.idCompetition = idCompetition;
		this.name = name;
		this.description = description;
		this.start = start;
		this.finish = finish;
		this.idCriteria = idCriteria;
	}
	
	 /**
     *  Setters   
     */
	public final void setIdCompetitions(final String idCompetition) {
		this.idCompetition = idCompetition;
	}

	public final void setName(final String name) {
		this.name = name;
	}

	public final void setDescription(final String description) {
		this.description = description;
	}

	public final void setStart(final Date start) {
		this.start = start;
	}

	public final void setFinish(final Date finish) {
		this.finish = finish;
	}

	public final void setIdCriteria(final String idCriteria) {
		this.idCriteria = idCriteria;
	}

	 /**
     *  Getters
     */	
	public final String getId() {
		return getIdCompetition();
	}
	
	public final String getIdCompetition() {
		return idCompetition;
	}

	public final String getName() {
		return name;
	}

	public final String getDescription() {
		return description;
	}

	public final Date getStart() {
		return start;
	}

	public final Date getFinish() {
		return finish;
	}

	public final String getIdCriteria() {
		return idCriteria;
	}
	
}
