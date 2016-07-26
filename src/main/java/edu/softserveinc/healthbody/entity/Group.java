package edu.softserveinc.healthbody.entity;

public class Group implements IEntity {

	private String idGroup;
	private String name;
	private Integer count;
	private String description;
	private String scoreGroup;
	private String status;
	

	public Group(String idGroup, String name, Integer count, String description, String scoreGroup, String status) {
		this.idGroup = idGroup;
		this.name = name;
		this.count = count;
		this.description = description;
		this.scoreGroup = scoreGroup;
		this.status = status;
	}

	@Override
	public String getId() {
		return getIdGroup();
	}
	
	// Getters
	public String getIdGroup() {
		return idGroup;
	}
	public String getName() {
		return name;
	}
	public Integer getCount() {
		return count;
	}
	public String getDescription() {
		return description;
	}	
	public String getScoreGroup() {
		return scoreGroup;
	}
	public String getStatus() {
		return status;
	}	
	
	// Setters
	public void setIdGroup(String idGroup) {
		this.idGroup = idGroup;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setScoreGroup(String scoreGroup) {
		this.scoreGroup = scoreGroup;
	}
	public void setStatus(String status) {
		this.status = status;
	}	

}