package edu.softserveinc.healthbody.dto;

public class GroupDTO {

	private String idGroup;
	private String name;
	private String count;
	private String descriptions;
	private String scoreGroup;
	// private String status;

	public GroupDTO() {}
	
	public GroupDTO(final String idGroup, final String name, final String count, final String descriptions, 
			final String scoreGroup) {
		this.idGroup = idGroup;
		this.name = name;
		this.count = count;
		this.descriptions = descriptions;
		this.scoreGroup = scoreGroup;
		
	}

	// Getters
	public final String getIdGroup() {
		return idGroup;
	}
	public final String getName() {
		return name;
	}
	public final String getCount() {
		return count;
	}
	public final String getDescriptions() {
		return descriptions;
	}
	public final String getScoreGroup() {
		return scoreGroup;
	}

	// Setters
	public final void setIdGroup(final String idGroup) {
		this.idGroup = idGroup;
	}
	
	public final void setName(final String name) {
		this.name = name;
	}

	public final void setCount(final String count) {
		this.count = count;
	}

	public final void setDescriptions(final String descriptions) {
		this.descriptions = descriptions;
	}

	public final void setScoreGroup(final String scoreGroup) {
		this.scoreGroup = scoreGroup;
	}
	
}