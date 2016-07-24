package edu.softserveinc.healthbody.exceptions;

public class QueryNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    public QueryNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public QueryNotFoundException(final String message) {
        super(message);
    }
}
