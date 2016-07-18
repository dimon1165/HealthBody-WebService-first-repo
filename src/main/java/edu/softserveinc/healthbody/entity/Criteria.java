package edu.softserveinc.healthbody.entity;

public class Criteria implements IEntity {
	
	private Integer idCriteria;
	private String name;
	private Double metrics;
	private String getGoogle;
	
	public Criteria (Integer idCriteria, String name, Double metrics,
			String getGoogle) {
		this.idCriteria = idCriteria;
		this.name = name;
		this.metrics = metrics;
		this.getGoogle = getGoogle;
	}

	// setters
	
	public void setIdCriteria(Integer idCriteria) {
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
	
	// getters
	
	public Integer getId() {
		return getIdCriteria();
	}
	  
	public Integer getIdCriteria() {
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
