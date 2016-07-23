package edu.softserveinc.healthbody.db;

import java.sql.Driver;

public final class DataSource {

	private Driver jdbcDriver;
	private String connectionUrl;
	private String user;
	private String passwrd;

	public DataSource(final Driver jdbcDriver, final String connectionUrl, final String user, final String passwrd) {
		this.jdbcDriver = jdbcDriver;
		this.connectionUrl = connectionUrl;
		this.user = user;
		this.passwrd = passwrd;
	}

	//setters
	public void setJdbcDriver(final Driver jdbcDriver) {
		this.jdbcDriver = jdbcDriver;
	}

	public void setConnectionUrl(final String connectionUrl) {
		this.connectionUrl = connectionUrl;
	}

	public void setUser(final String user) {
		this.user = user;
	}

	public void setPasswrd(final String passwrd) {
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
	public boolean equals(final Object dataSource) {
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
