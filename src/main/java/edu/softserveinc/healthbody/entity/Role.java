package edu.softserveinc.healthbody.entity;

public class Role implements IEntity {

	private String idRole;
	private String name;
	private String description;
	
	public Role(final String idRole, String name, final String description) {
		this.idRole = idRole;
		this.name = name;
		this.description = description;
	}
	
	// Getters
	public final String getIdRole() {
		return idRole;
	}
	public final String getName() {
		return name;
	}
	public final String getDescription() {
		return description;
	}

	// Setters
	public final void setIdRole(final String idRole) {
		this.idRole = idRole;
	}
	public final void setName(final String name) {
		this.name = name;
	}
	public final void setDescription(final String description) {
		this.description = description;
	}
	
	@Override
	public final String getId() {
		return getIdRole();
	}

}
