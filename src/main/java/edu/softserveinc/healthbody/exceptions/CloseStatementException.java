package edu.softserveinc.healthbody.exceptions;

public class CloseStatementException extends Exception {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    public CloseStatementException(String message, Throwable cause) {
        super(message, cause);

    }

    public CloseStatementException(String message) {
        super(message);

    }

}
