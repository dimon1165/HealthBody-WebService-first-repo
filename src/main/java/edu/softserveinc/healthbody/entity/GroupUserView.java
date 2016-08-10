package edu.softserveinc.healthbody.entity;

	/**
	 * Class  edu.softserveinc.healthbody.entity.GroupUserView is POJO.
	 * Called by: 
	 * edu.softserveinc.healthbody.dao.GroupUserViewDao - create instance
	 * Return info about all users in particular group
	 * 
	 * @version 9 August 2016 	
	 * 
	 * */
public class GroupUserView implements IEntity {
	
	private String  idGroup;
	private String  name;
	private Integer count;
	private String  description;
	private String  scoreGroup;
	private String  status;
	private String  users;
	private String  firstname;
	private String  lastname;
	
	 /**
     *  Constructor of edu.softserveinc.healthbody.entity.GroupUsersView  
     */
	public GroupUserView(final String idGroup, final String name, final Integer count, final String description, final String scoreGroup,
			final String status,  final String users, final String firstname, final String lastname) {
		super();
		this.idGroup = idGroup;
		this.name = name;
		this.count = count;
		this.description = description;
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

	public Integer getCount() {
		return count;
	}

	public String getDescription() {
		return description;
	}

	public String getScoreGroup() {
		return scoreGroup;
	}

	public String getStatus() {
		return status;
	}

	public String getUsers() {
		return users;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
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

	public void setCount(Integer count) {
		this.count = count;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setScoreGroup(String scoreGroup) {
		this.scoreGroup = scoreGroup;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setUsers(String users) {
		this.users = users;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	@Override
	public String getId() {
		return null;
	}

}
