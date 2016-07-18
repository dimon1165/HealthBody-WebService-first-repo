package edu.softserveinc.healthbody.entity;


public class User implements IEntity {
	
	private Integer idUser;
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
	private Integer idRole;
	private String status;
	private boolean isDisabled;
	
	public User(Integer idUser, String login, String passwd, String firsName, String lastName, String mail, Integer age,
			Double weight, String gender, String health, String avatar, String googleApi, Integer idRole, String status,
			boolean isDisabled) {

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
	public Integer getId() {

		return idUser;
	}

	// setters
	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public void setFirsName(String firsName) {
		this.firsName = firsName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public void setGoogleApi(String googleApi) {
		this.googleApi = googleApi;
	}

	public void setHealth(String health) {
		this.health = health;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setIdRole(Integer idRole) {
		this.idRole = idRole;
	}
	
	public void setIsDisabled(boolean isDisabled) {
		this.isDisabled = isDisabled;
	}
	
	// getters
	public Integer getIdUser() {
		return getId();
	}

	public String getLogin() {
		return login;
	}

	public String getPasswd() {
		return passwd;
	}

	public String getFirsName() {
		return firsName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getMail() {
		return mail;
	}

	public String getGender() {
		return gender;
	}

	public Double getWeight() {
		return weight;
	}

	public Integer getAge() {
		return age;
	}

	public String getGoogleApi() {
		return googleApi;
	}

	public String getHealth() {
		return health;
	}

	public String getAvatar() {
		return avatar;
	}

	public String getStatus() {
		return status;
	}

	public Integer getIdRole() {
		return idRole;
	}

	public boolean getIsDisabled() {
		return isDisabled;
	}
}
