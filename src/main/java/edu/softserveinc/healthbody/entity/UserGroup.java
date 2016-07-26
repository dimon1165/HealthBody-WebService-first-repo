package edu.softserveinc.healthbody.entity;

public class UserGroup implements IEntity {

	private String idUserGroup;
    private String idUser;
	private String idGroup;
	
	public UserGroup(final String idUserGroup, final String idUser, final String idGroup) {
		this.idUserGroup = idUserGroup;
		this.idUser = idUser;
		this.idGroup = idGroup;
	}

	// Setters
	public final void setIdUserGroup(final String idUserGroup) {
		this.idUserGroup = idUserGroup;
	}

	public final void setIdUser(final String idUser) {
		this.idUser = idUser;
	}

	public final void setIdGroup(final String idGroup) {
		this.idGroup = idGroup;
	}

	// Getters
	public final String getIdUserGroup() {
		return idUserGroup;
	}

	public final String getIdUser() {
		return idUser;
	}

	public final String getIdGroup() {
		return idGroup;
	}
	
	@Override
	public final String getId() {
		return getIdUser();
	}
}
