package edu.softserveinc.healthbody.exceptions;

public class CloseStatementException extends Exception {

    private static final long serialVersionUID = 1L;

    public CloseStatementException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public CloseStatementException(final String message) {
        super(message);

    }

}
