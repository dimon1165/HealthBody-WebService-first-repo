package edu.softserveinc.healthbody.dto;

	/**
	 * Class  edu.softserveinc.healthbody.dto.GroupDTO is POJO.
	 * Used by: 
	 * edu.softserveinc.healthbody.services.impl.UserProfileServiceImpl
	 * edu.softserveinc.healthbody.services.impl.GroupServiceImpl
	 * to transfer data about groups, users, users in groups.
	 *  
	 * @version 9 August 2016 	
	 * 
	 * */
public class GroupDTO {

	private String idGroup;
	private String name;
	private String count;
	private String descriptions;
	private String scoreGroup;
	private String status;
	private String[] users;
	private String[] firstname;
	private String[] lastname;
	// private String status;

	 /**
     *  Default constructor of edu.softserveinc.healthbody.dto.GroupDTO   
     */
	public GroupDTO() {}
	
	 /**
     *  Constructor of edu.softserveinc.healthbody.dto.GroupDTO   
     */
	public GroupDTO(String idGroup, String name, String count, String descriptions, String scoreGroup, String status,
			String[] users, String[] firstname, String[] lastname) {
		super();
		this.idGroup = idGroup;
		this.name = name;
		this.count = count;
		this.descriptions = descriptions;
		this.scoreGroup = scoreGroup;
		this.status = status;
		this.users = users;
		this.firstname = firstname;
		this.lastname = lastname;
	}
	
	 /**
     * Getters   
     */
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
	public String getStatus() {
		return status;
	}
	public String[] getUsers() {
		return users;
	}
	public String[] getFirstname() {
		return firstname;
	}
	public String[] getLastname() {
		return lastname;
	}
	
	 /**
     * Setters   
     */
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
	public void setStatus(String status) {
		this.status = status;
	}
	public void setUsers(String[] users) {
		this.users = users;
	}
	public void setFirstname(String[] firstname) {
		this.firstname = firstname;
	}
	public void setLastname(String[] lastname) {
		this.lastname = lastname;
	}

}