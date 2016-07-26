package edu.softserveinc.healthbody.exceptions;

public class JDBCDriverException extends Exception {

    private static final long serialVersionUID = 1L;

    public JDBCDriverException(final String message) {
        super(message);
    }

    public JDBCDriverException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
