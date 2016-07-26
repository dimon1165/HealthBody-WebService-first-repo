package edu.softserveinc.healthbody.exceptions;

public class DataBaseReadingException extends Exception {

    private static final long serialVersionUID = 1L;

    public DataBaseReadingException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public DataBaseReadingException(final String message) {
        super(message);
    }
}
