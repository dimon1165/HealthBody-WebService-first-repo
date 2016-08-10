package edu.softserveinc.healthbody.dto;

import java.util.List;

	/**
	 * Class  edu.softserveinc.healthbody.dto.RoleDTO is POJO.
	 *
	 *  
	 * @version 9 August 2016 	
	 * 
	 * */
public class RoleDTO {

	private String idRole;
	private String name;
	private String description;
	private List<UserDTO> users;

	/**
    *  Default constructor of edu.softserveinc.healthbody.dto.RoleDTO   
    */
	public RoleDTO() { }
	
	/**
    *  Constructor of edu.softserveinc.healthbody.dto.RoleDTO   
    */
	public RoleDTO(final String idRole, final String name, final String description, final List<UserDTO> users) {
		this.idRole = idRole;
		this.name = name;
		this.description = description;
		this.users = users;
	}
	
	/**
    * Getters   
    */
	public String getIdRole() {
		return idRole;
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