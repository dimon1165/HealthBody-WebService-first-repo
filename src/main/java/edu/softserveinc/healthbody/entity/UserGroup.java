package edu.softserveinc.healthbody.entity;

public class UserGroup implements IEntity {

	private Integer idUserGroup;
    private Integer idUser;
	private Integer idGroup;
	
	public UserGroup(final Integer idUserGroup, final Integer idUser, final Integer idGroup) {
		this.idUserGroup = idUserGroup;
		this.idUser = idUser;
		this.idGroup = idGroup;
	}

	// setters
	public final void setIdUserGroup(final Integer idUserGroup) {
		this.idUserGroup = idUserGroup;
	}

	public final void setIdUser(final Integer idUser) {
		this.idUser = idUser;
	}

	public final void setIdGroup(final Integer idGroup) {
		this.idGroup = idGroup;
	}

	// getters
	public final Integer getIdUserGroup() {
		return idUserGroup;
	}

	public final Integer getIdUser() {
		return idUser;
	}

	public final Integer getIdGroup() {
		return idGroup;
	}
	
	@Override
	public final Integer getId() {
		return getIdUser();
	}

}
