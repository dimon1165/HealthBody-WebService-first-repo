package edu.softserveinc.healthbody.entity;

	/**
	 * Class  edu.softserveinc.healthbody.entity.User is POJO.
	 * Called by: 
	 * edu.softserveinc.healthbody.dao.UserDao - create instance,
	 * edu.softserveinc.healthbody.services.impl.UserProfileServiceImpl
	 * Return info about users 
	 * 
	 * @version 9 August 2016 	
	 * 
	 * */
public class User implements IEntity {
	
	private String idUser;
	private String login;
	private String passwd;
	private String firsName;
	private String lastName;
	private String mail;
	private Integer age;
	private Double weight;
	private String gender;
	private String health;
	private String avatar;
	private String googleApi;
	private String idRole;
	private String status;
	private boolean isDisabled;
	
	
	 /**
     *  Constructor of edu.softserveinc.healthbody.entity.User  
     */
	public User(final String idUser, final String login, final String passwd, final String firsName, final String lastName, 
			final String mail, final Integer age, final Double weight, final String gender, final String health,
			final String avatar, final String googleApi, final String idRole, final String status, final boolean isDisabled) {
		this.idUser = idUser;
		this.login = login;
		this.passwd = passwd;
		this.firsName = firsName;
		this.lastName = lastName;
		this.mail = mail;
		this.age = age;
		this.weight = weight;
		this.gender = gender;
		this.health = health;
		this.avatar = avatar;
		this.googleApi = googleApi;
		this.idRole = idRole;
		this.status = status;
		this.isDisabled = isDisabled;		
	}

	@Override
	public final String getId() {
		return idUser;
	}

	 /**
     * Setters  
     */
	public final void setIdUser(final String idUser) {
		this.idUser = idUser;
	}

	public final void setLogin(final String login) {
		this.login = login;
	}

	public final void setPasswd(final String passwd) {
		this.passwd = passwd;
	}

	public final void setFirsName(final String firsName) {
		this.firsName = firsName;
	}

	public final void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	public final void setMail(final String mail) {
		this.mail = mail;
	}

	public final void setGender(final String gender) {
		this.gender = gender;
	}

	public final void setWeight(final Double weight) {
		this.weight = weight;
	}

	public final void setAge(final Integer age) {
		this.age = age;
	}

	public final void setGoogleApi(final String googleApi) {
		this.googleApi = googleApi;
	}

	public final void setHealth(final String health) {
		this.health = health;
	}

	public final void setAvatar(final String avatar) {
		this.avatar = avatar;
	}

	public final void setStatus(final String status) {
		this.status = status;
	}

	public final void setIdRole(final String idRole) {
		this.idRole = idRole;
	}
	
	public final void setIsDisabled(final boolean isDisabled) {
		this.isDisabled = isDisabled;
	}
	
	 /**
     * Getters  
     */
	public final String getIdUser() {
		return idUser;
	}

	public final String getLogin() {
		return login;
	}

	public final String getPasswd() {
		return passwd;
	}

	public final String getFirsName() {
		return firsName;
	}

	public final String getLastName() {
		return lastName;
	}

	public final String getMail() {
		return mail;
	}

	public final String getGender() {
		return gender;
	}

	public final Double getWeight() {
		return weight;
	}

	public final Integer getAge() {
		return age;
	}

	public final String getGoogleApi() {
		return googleApi;
	}

	public final String getHealth() {
		return health;
	}

	public final String getAvatar() {
		return avatar;
	}

	public final String getStatus() {
		return status;
	}

	public final String getIdRole() {
		return idRole;
	}

	public final boolean getIsDisabled() {
		return isDisabled;
	}
}
