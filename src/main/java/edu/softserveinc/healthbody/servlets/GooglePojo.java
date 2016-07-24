package edu.softserveinc.healthbody.servlets;

public class GooglePojo {
	String id;
	String email;
	String name;
	String given_name;
	String family_name;
	String picture;
	String gender;
	
	public final String getPicture() {
		return picture;
	}

	public final void setPicture(final String picture) {
		this.picture = picture;
	}

	public final String getGender() {
		return gender;
	}

	public final void setGender(final String gender) {
		this.gender = gender;
	}

	public final String getId() {
		return id;
	}

	public final void setId(final String id) {
		this.id = id;
	}

	public final String getEmail() {
		return email;
	}

	public final void setEmail(final String email) {
		this.email = email;
	}

	public final String getName() {
		return name;
	}

	public final void setName(final String name) {
		this.name = name;
	}

	public final String getGiven_name() {
		return given_name;
	}

	public final void setGiven_name(final String given_name) {
		this.given_name = given_name;
	}

	public final String getFamily_name() {
		return family_name;
	}

	public final void setFamily_name(final String family_name) {
		this.family_name = family_name;
	}

	@Override
	public final String toString() {
		return "GooglePojo [id=" + id + ", email=" + email + ", name=" + name + ", given_name=" + given_name
				+ ", family_name=" + family_name + ", picture=" + picture + ", gender=" + gender + "]";
	}
}