package edu.softserveinc.healthbody.entity;

public class UserGroup implements IEntity{

	private Integer idUserGroup;
    private Integer idUser;
	private Integer idGroup;
	
	public UserGroup(Integer idUserGroup, Integer idUser, Integer idGroup) {
		this.idUserGroup = idUserGroup;
		this.idUser = idUser;
		this.idGroup = idGroup;
	}

	// setters

	public void setIdUserGroup(Integer idUserGroup) {
		this.idUserGroup = idUserGroup;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	public void setIdGroup(Integer idGroup) {
		this.idGroup = idGroup;
	}
	

	// getters

	public Integer getIdUserGroup() {
		return idUserGroup;
	}

	public Integer getIdUser() {
		return idUser;
	}

	public Integer getIdGroup() {
		return idGroup;
	}
	
	@Override
	public Integer getId() {
		return getIdUser();
	}

}
