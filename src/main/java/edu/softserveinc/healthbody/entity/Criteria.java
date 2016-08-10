package edu.softserveinc.healthbody.entity;

	/**
	 * Class  edu.softserveinc.healthbody.entity.Criteria is POJO. 
	 * Called by:edu.softserveinc.healthbody.dao.CriteriaDAO
	 * The purpose is get info about criteria of groups competitions
	 * Return info about Criteria
	 *  
	 * @version 9 August 2016 	
	 * 
	 * */
public class Criteria implements IEntity {
	
	private String idCriteria;
	
	/** Name of criteria */
	private String name;
	
	/** Metrics on which we are going to judge winner*/
	private Double metrics;
	
	/** Metrics on which we are going to judge winner
	 * Not in use for the moment 
	 * Intended to be data from Google*/
	private String getGoogle;
	
	 /**
     *  Constructor of edu.softserveinc.healthbody.entity.Criteria
     */
	public Criteria (final String idCriteria, final String name, final Double metrics,
			final String getGoogle) {
		this.idCriteria = idCriteria;
		this.name = name;
		this.metrics = metrics;
		this.getGoogle = getGoogle;
	}

	 /**
     * Setters  
     */
	public final void setIdCriteria(final String idCriteria) {
		this.idCriteria = idCriteria;
	}

	public final void setName(final String name) {
		this.name = name;
	}

	public final void setMetrics(final Double metrics) {
		this.metrics = metrics;
	}

	public final void setGetGoogle(final String getGoogle) {
		this.getGoogle = getGoogle;
	}
	
	 /**
     * Getters  
     */
	public final String getId() {
		return getIdCriteria();
	}
	  
	public final String getIdCriteria() {
		return idCriteria;
	}

	public final String getName() {
		return name;
	}

	public final Double getMetrics() {
		return metrics;
	}

	public final String getGetGoogle() {
		return getGoogle;
	}
}

