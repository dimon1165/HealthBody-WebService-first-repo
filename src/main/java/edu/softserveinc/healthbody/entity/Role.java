package edu.softserveinc.healthbody.entity;

public class Role implements IEntity{

	private String idRole;
	private String name;
	private String description;
	
	public Role(String idRole, String name, String description) {
		this.idRole = idRole;
		this.name = name;
		this.description = description;
	}
	
	// getters
	public String getIdRole() {
		return idRole;
	}
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}


	// setters
	public void setIdRole(String idRole) {
		this.idRole = idRole;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	@Override
	public String getId() {
		return null;
	}

}
