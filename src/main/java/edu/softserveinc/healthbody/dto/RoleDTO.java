package edu.softserveinc.healthbody.dto;

import java.util.List;

public class RoleDTO {

	private String idRole;
	private String name;
	private String description;
	private List<UserDTO> users;

	public RoleDTO() {}
	
	public RoleDTO(String idRole, String name, String description, List<UserDTO> users) {
		this.idRole = idRole;
		this.name = name;
		this.description = description;
		this.users = users;
	}
	
	public String getIdRole() {
		return idRole;
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