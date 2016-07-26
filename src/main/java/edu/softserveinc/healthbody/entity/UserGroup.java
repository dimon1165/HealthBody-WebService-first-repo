package edu.softserveinc.healthbody.entity;

public class UserGroup implements IEntity{

	private String idUserGroup;
    private String idUser;
	private String idGroup;
	
	public UserGroup(String idUserGroup, String idUser, String idGroup) {
		this.idUserGroup = idUserGroup;
		this.idUser = idUser;
		this.idGroup = idGroup;
	}

	// Setters
	public void setIdUserGroup(String idUserGroup) {
		this.idUserGroup = idUserGroup;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

	public void setIdGroup(String idGroup) {
		this.idGroup = idGroup;
	}
	

	// Getters
	public String getIdUserGroup() {
		return idUserGroup;
	}

	public String getIdUser() {
		return idUser;
	}

	public String getIdGroup() {
		return idGroup;
	}
	
	@Override
	public String getId() {
		return getIdUser();
	}
}
