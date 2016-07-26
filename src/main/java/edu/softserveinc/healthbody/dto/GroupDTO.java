package edu.softserveinc.healthbody.dto;

public class GroupDTO {

	private String idGroup;
	private String name;
	private String count;
	private String descriptions;
	private String scoreGroup;
	// private String status;

	public GroupDTO() {}
	
	public GroupDTO(String idGroup, String name, String count, String descriptions, String scoreGroup) {
		this.idGroup = idGroup;
		this.name = name;
		this.count = count;
		this.descriptions = descriptions;
		this.scoreGroup = scoreGroup;
		
	}

	// Getters
	public String getIdGroup() {
		return idGroup;
	}
	public String getName() {
		return name;
	}
	public String getCount() {
		return count;
	}
	public String getDescriptions() {
		return descriptions;
	}
	public String getScoreGroup() {
		return scoreGroup;
	}

	// Setters
	public void setIdGroup(String idGroup) {
		this.idGroup = idGroup;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public void setDescriptions(String descriptions) {
		this.descriptions = descriptions;
	}

	public void setScoreGroup(String scoreGroup) {
		this.scoreGroup = scoreGroup;
	}
	

}