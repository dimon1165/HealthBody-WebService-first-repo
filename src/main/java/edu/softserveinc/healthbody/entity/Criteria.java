package edu.softserveinc.healthbody.entity;

public class Criteria implements IEntity {
	
	private String idCriteria;
	private String name;
	private Double metrics;
	private String getGoogle;
	
	public Criteria (String idCriteria, String name, Double metrics,
			String getGoogle) {
		this.idCriteria = idCriteria;
		this.name = name;
		this.metrics = metrics;
		this.getGoogle = getGoogle;
	}

	// Setters	
	public void setIdCriteria(String idCriteria) {
		this.idCriteria = idCriteria;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setMetrics(Double metrics) {
		this.metrics = metrics;
	}

	public void setGetGoogle(String getGoogle) {
		this.getGoogle = getGoogle;
	}
	
	// Getters	
	public String getId() {
		return getIdCriteria();
	}
	  
	public String getIdCriteria() {
		return idCriteria;
	}

	public String getName() {
		return name;
	}

	public Double getMetrics() {
		return metrics;
	}

	public String getGetGoogle() {
		return getGoogle;
	}
}
