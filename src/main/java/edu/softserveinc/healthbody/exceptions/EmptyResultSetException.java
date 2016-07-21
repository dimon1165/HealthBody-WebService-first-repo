package edu.softserveinc.healthbody.exceptions;

public class EmptyResultSetException extends Exception {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    public EmptyResultSetException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmptyResultSetException(String message) {
        super(message);
    }

}
