package edu.softserveinc.healthbody.entity;
	/**
	 * Class  edu.softserveinc.healthbody.entity.UserCompetitions is POJO.
	 * Called by:
	 * edu.softserveinc.healthbody.dao.UserGroupDao - create instance
	 * Return info about id user and his groups
	 * 
	 * @version 9 August 2016 	
	 * 
	 * */
public class UserGroup implements IEntity {

	private String idUserGroup;
    private String idUser;
	private String idGroup;
	
	 /**
     *  Constructor of edu.softserveinc.healthbody.entity.UserGroup  
     */
	public UserGroup(final String idUserGroup, final String idUser, final String idGroup) {
		this.idUserGroup = idUserGroup;
		this.idUser = idUser;
		this.idGroup = idGroup;
	}

	/**
    * Setters  
    */
	public final void setIdUserGroup(final String idUserGroup) {
		this.idUserGroup = idUserGroup;
	}

	public final void setIdUser(final String idUser) {
		this.idUser = idUser;
	}

	public final void setIdGroup(final String idGroup) {
		this.idGroup = idGroup;
	}

	/**
	* Getters  
	*/
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
