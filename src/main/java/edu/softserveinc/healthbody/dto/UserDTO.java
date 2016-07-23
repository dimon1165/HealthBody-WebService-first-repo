package edu.softserveinc.healthbody.dto;

import java.util.List;

public class UserDTO {

	private String firstname;
	private String lastname;
	private String login;
	private String password;
	private String email;
	private String age;
	private String weight;
	private String gender;
	private String photoURL;
	private String roleName;
	private String health;
	private String googleApi;
	private String status;
	private String score;
	private List<GroupDTO> groups;
	private String isDisabled;

	public UserDTO() { }
	
	public UserDTO(final String login, final String password, final String firstname, final String lastname,
			final String email, final String age, final String weight, final String gender,
			final String photoURL, final String roleName, final String status, final String score,
			final List<GroupDTO> groups, final String isDisabled) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.login = login;
		this.password = password;
		this.email = email;
		this.age = age;
		this.weight = weight;
		this.gender = gender;
		this.photoURL = photoURL;
		this.roleName = roleName;
		this.status = status;
		this.score = score;
		this.groups = groups;
	}
	
	//getters
	public final String getFirstname() {
		return firstname;
	}

	public final String getLastname() {
		return lastname;
	}

	public final String getLogin() {
		return login;
	}

	public final String getPassword() {
		return password;
	}

	public final String getEmail() {
		return email;
	}

	public final String getAge() {
		return age;
	}

	public final String getWeight() {
		return weight;
	}

	public final String getGender() {
		return gender;
	}

	public final String getPhotoURL() {
		return photoURL;
	}

	public final String getRoleName() {
		return roleName;
	}
	public final String getStatus() {
		return status;
	}

	public final String getScore() {
		return score;
	}

	public final List<GroupDTO> getGroups() {
		return groups;
	}

	public final String getHealth() {
		return health;
	}

	public final String getIsDisabled() {
		return isDisabled;
	}

	//setters
	public final void setFirstname(final String firstname) {
		this.firstname = firstname;
	}

	public final void setLastname(final String lastname) {
		this.lastname = lastname;
	}

	public final void setLogin(final String login) {
		this.login = login;
	}

	public final void setPassword(final String password) {
		this.password = password;
	}

	public final void setEmail(final String email) {
		this.email = email;
	}

	public final void setAge(final String age) {
		this.age = age;
	}

	public final void setWeight(final String weight) {
		this.weight = weight;
	}

	public final void setGender(final String gender) {
		this.gender = gender;
	}

	public final void setPhotoURL(final String photoURL) {
		this.photoURL = photoURL;
	}

	public final void setRoleName(final String roleName) {
		this.roleName = roleName;
	}

	public final void setStatus(final String status) {
		this.status = status;
	}

	public final void setScore(final String score) {
		this.score = score;
	}

	public final void setGroups(final List<GroupDTO> groups) {
		this.groups = groups;
	}
	
	public final void setHealth(final String health) {
		this.health = health;
	}

	public final String getGoogleApi() {
		return googleApi;
	}

	public final void setGoogleApi(final String googleApi) {
		this.googleApi = googleApi;
	}
	
	public final void setIsDisabled(final String isDisabled) {
	this.isDisabled = isDisabled;
	}

	@Override
	public final String toString() {
		return "UserDTO [firstname=" + firstname + ", lastname=" + lastname + ", login=" + login + ", password="
				+ password + ", email=" + email + ", age=" + age + ", weight=" + weight + ", gender=" + gender
				+ ", photoURL=" + photoURL + ", roleName=" + roleName + ", health=" + health + ", googleApi="
				+ googleApi + ", status=" + status + ", score=" + score + ", groups=" + groups + "]" + System.lineSeparator();
	}
}