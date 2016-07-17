package edu.softserveinc.healthbody.exceptions;

public class QueryNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	

	public QueryNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}



	public QueryNotFoundException(String message) {
		super(message);
	}

}
