package edu.softserveinc.healthbody.entity;


public class Group implements IEntity {

	private Integer idGroup;
	private String name;
	private Integer count;
	private String description;
	private String scoreGroup;
	private String status;
	

	public Group(Integer idGroup, String name, Integer count, String description, String scoreGroup, String status) {
		super();
		this.idGroup = idGroup;
		this.name = name;
		this.count = count;
		this.description = description;
		this.scoreGroup = scoreGroup;
		this.status = status;
	}

	@Override
	public Integer getId() {
		return getIdGroup();
	}
	
	//getters
	public Integer getIdGroup() {
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
	
	
	//setters
	public void setIdGroup(Integer idGroup) {
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