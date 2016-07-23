package edu.softserveinc.healthbody.entity;

public class Role implements IEntity {

	private Integer idRole;
	private String name;
	private String description;
	
	public Role(final Integer idRole, String name, final String description) {
		this.idRole = idRole;
		this.name = name;
		this.description = description;
	}
	
	// getters
	public final Integer getIdRole() {
		return idRole;
	}
	public final String getName() {
		return name;
	}
	public final String getDescription() {
		return description;
	}

	// setters
	public final void setIdRole(final Integer idRole) {
		this.idRole = idRole;
	}
	public final void setName(final String name) {
		this.name = name;
	}
	public final void setDescription(final String description) {
		this.description = description;
	}
	
	@Override
	public final Integer getId() {
		return getIdRole();
	}

}
