package edu.softserveinc.healthbody.servlets;

public class GooglePojo {
	String id;
	String email;
	String name;
	String given_name;
	String family_name;
	String picture;
	String gender;

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGiven_name() {
		return given_name;
	}

	public void setGiven_name(String given_name) {
		this.given_name = given_name;
	}

	public String getFamily_name() {
		return family_name;
	}

	public void setFamily_name(String family_name) {
		this.family_name = family_name;
	}

	@Override
	public String toString() {
		return "GooglePojo [id=" + id + ", email=" + email + ", name=" + name + ", given_name=" + given_name
				+ ", family_name=" + family_name + ", picture=" + picture + ", gender=" + gender + "]";
	}
}