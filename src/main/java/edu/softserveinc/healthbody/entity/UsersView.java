package edu.softserveinc.healthbody.entity;

	/**
	 * Class  edu.softserveinc.healthbody.entity.UserView is POJO.
	 * Called by:
	 * edu.softserveinc.healthbody.dao.UsersViewDao - create instance
	 * Return info about users 
	 * 
	 * @version 9 August 2016 	
	 * 
	 * */
public class UsersView implements IEntity {
	
	private String idUser;
	private String firsName;
	private String lastName;
	private String login;
	private String passwd;
	private String mail;
	private Integer age;
	private Double weight;
	private String gender;
	private String avatar;
	private String roleName;
	private String health;
	private String googleApi;
	private String status;
	private Integer score;
	
	 /**
     *  Constructor of edu.softserveinc.healthbody.entity.UserView  
     */
	public UsersView(final String idUser, final String firsName, final String lastName, final String login, 
			final String passwd, final String mail, final Integer age, final Double weight, final String gender, 
			final String avatar, final String roleName, final String health, final String googleApi, final String status, 
			final Integer score) {
		this.idUser = idUser;
		this.firsName = firsName;
		this.lastName = lastName;
		this.login = login;
		this.passwd = passwd;
		this.mail = mail;
		this.age = age;
		this.weight = weight;
		this.gender = gender;
		this.avatar = avatar;
		this.roleName = roleName;
		this.health = health;
		this.googleApi = googleApi;
		this.status = status;
		this.score = score;
	}
	
	 /**
     * Setters  
     */
	public final void setIdUser(final String idUser) {
		this.idUser = idUser;
	}

	public final void setFirsName(final String firsName) {
		this.firsName = firsName;
	}

	public final void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	public final void setLogin(final String login) {
		this.login = login;
	}

	public final void setPasswd(final String passwd) {
		this.passwd = passwd;
	}

	public final void setMail(final String mail) {
		this.mail = mail;
	}

	public final void setAge(final Integer age) {
		this.age = age;
	}

	public final void setWeight(final Double weight) {
		this.weight = weight;
	}

	public final void setGender(final String gender) {
		this.gender = gender;
	}

	public final void setAvatar(final String avatar) {
		this.avatar = avatar;
	}

	public final void setRoleName(final String roleName) {
		this.roleName = roleName;
	}

	public final void setHealth(final String health) {
		this.health = health;
	}

	public final void setGoogleApi(final String googleApi) {
		this.googleApi = googleApi;
	}

	public final void setStatus(final String status) {
		this.status = status;
	}

	public final void setScore(final Integer score) {
		this.score = score;
	}
	
	 /**
     * Getters  
     */
	public final String getIdUser() {
		return idUser;
	}

	public final String getFirsName() {
		return firsName;
	}

	public final String getLastName() {
		return lastName;
	}

	public final String getLogin() {
		return login;
	}

	public final String getPasswd() {
		return passwd;
	}

	public final String getMail() {
		return mail;
	}

	public final Integer getAge() {
		return age;
	}

	public final Double getWeight() {
		return weight;
	}

	public final String getGender() {
		return gender;
	}

	public final String getAvatar() {
		return avatar;
	}

	public final String getRoleName() {
		return roleName;
	}

	public final String getHealth() {
		return health;
	}

	public final String getGoogleApi() {
		return googleApi;
	}

	public final String getStatus() {
		return status;
	}

	public final Integer getScore() {
		return score;
	}
	
	@Override
	public final String getId() {
		return getIdUser();
	}

}