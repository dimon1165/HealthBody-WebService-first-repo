package edu.softserveinc.healthbody.dto;

import java.util.List;

public class RoleDTO {

	private String name;
	private String description;
	private List<UserDTO> users;

	public RoleDTO() {}
	
	public RoleDTO(String name, String description, List<UserDTO> users) {
		this.name = name;
		this.description = description;
		this.users = users;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public List<UserDTO> getUsers() {
		return users;
	}
}