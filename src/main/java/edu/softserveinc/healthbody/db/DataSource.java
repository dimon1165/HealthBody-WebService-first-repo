package edu.softserveinc.healthbody.db;

import java.sql.Driver;

public final class DataSource {

	private Driver jdbcDriver;
	private String connectionUrl;
	private String user;
	private String passwrd;

	public DataSource(Driver jdbcDriver, String connectionUrl, String user, String passwrd) {
		this.jdbcDriver = jdbcDriver;
		this.connectionUrl = connectionUrl;
		this.user = user;
		this.passwrd = passwrd;
	}

	//setters
	public void setJdbcDriver(Driver jdbcDriver) {
		this.jdbcDriver = jdbcDriver;
	}

	public void setConnectionUrl(String connectionUrl) {
		this.connectionUrl = connectionUrl;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public void setPasswrd(String passwrd) {
		this.passwrd = passwrd;
	}
	
	//getters
	public Driver getJdbcDriver() {
		return jdbcDriver;
	}

	public String getConnectionUrl() {
		return connectionUrl;
	}

	public String getUser() {
		return user;
	}

	public String getPasswrd() {
		return passwrd;
	}

	@Override
	public boolean equals(Object dataSource) {
		boolean result = false;
		if (dataSource instanceof DataSource) {
			result  = getJdbcDriver().getClass().getName().equals(((DataSource) dataSource).getJdbcDriver().getClass().getName())
				&& getConnectionUrl().equals(((DataSource) dataSource).getConnectionUrl())
				&& getUser().equals(((DataSource) dataSource).getUser())
				&& getPasswrd().equals(((DataSource) dataSource).getPasswrd());
		}
		return result;
	}
	
	
}
