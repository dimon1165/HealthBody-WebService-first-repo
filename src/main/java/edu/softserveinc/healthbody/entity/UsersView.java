package edu.softserveinc.healthbody.entity;

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
	
	public UsersView(String idUser, String firsName, String lastName, String login, String passwd, String mail,
			Integer age, Double weight, String gender, String avatar, String roleName, String health, String googleApi,
			String status, Integer score) {
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
	
	// Setters
	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

	public void setFirsName(String firsName) {
		this.firsName = firsName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public void setHealth(String health) {
		this.health = health;
	}

	public void setGoogleApi(String googleApi) {
		this.googleApi = googleApi;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setScore(Integer score) {
		this.score = score;
	}
	
	// Getters	
	public String getIdUser() {
		return idUser;
	}

	public String getFirsName() {
		return firsName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getLogin() {
		return login;
	}

	public String getPasswd() {
		return passwd;
	}

	public String getMail() {
		return mail;
	}

	public Integer getAge() {
		return age;
	}

	public Double getWeight() {
		return weight;
	}

	public String getGender() {
		return gender;
	}

	public String getAvatar() {
		return avatar;
	}

	public String getRoleName() {
		return roleName;
	}

	public String getHealth() {
		return health;
	}

	public String getGoogleApi() {
		return googleApi;
	}

	public String getStatus() {
		return status;
	}

	public Integer getScore() {
		return score;
	}
	
	@Override
	public String getId() {
		return getIdUser();
	}
}