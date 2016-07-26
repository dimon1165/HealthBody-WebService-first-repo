package edu.softserveinc.healthbody.exceptions;

public class EmptyResultSetException extends Exception {

    private static final long serialVersionUID = 1L;

    public EmptyResultSetException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public EmptyResultSetException(final String message) {
        super(message);
    }
}
