package edu.softserveinc.healthbody.exceptions;

public class JDBCDriverException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JDBCDriverException(String message) {
		super(message);
	}

	public JDBCDriverException(String message, Throwable cause) {
		super(message, cause);
	}

}
