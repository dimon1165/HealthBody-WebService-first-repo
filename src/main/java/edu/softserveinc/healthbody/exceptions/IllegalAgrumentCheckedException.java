package edu.softserveinc.healthbody.exceptions;

public class IllegalAgrumentCheckedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IllegalAgrumentCheckedException (String message, Throwable cause) {
		super(message, cause);
		
	}

	public IllegalAgrumentCheckedException (String message) {
		super(message);
		
	}
	
	
	
	

}
