package edu.softserveinc.healthbody.dto;

import java.util.List;

public class RoleDTO {

	private String name;
	private String description;
	private List<UserDTO> users;

	public RoleDTO() { }
	
	public RoleDTO(final String name, final String description, final List<UserDTO> users) {
		this.name = name;
		this.description = description;
		this.users = users;
	}

	public final String getName() {
		return name;
	}

	public final String getDescription() {
		return description;
	}

	public final List<UserDTO> getUsers() {
		return users;
	}
}