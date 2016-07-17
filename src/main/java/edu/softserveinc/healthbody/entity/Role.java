package edu.softserveinc.healthbody.entity;

public class Role implements IEntity{

	private Integer idRole;
	private String name;
	private String description;
	
	public Role(Integer idRole, String name, String description) {
		super();
		this.idRole = idRole;
		this.name = name;
		this.description = description;
	}
	
	// getters
	public Integer getIdRole() {
		return idRole;
	}
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}


	// setters
	public void setIdRole(Integer idRole) {
		this.idRole = idRole;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	@Override
	public Integer getId() {
		return null;
	}

}
