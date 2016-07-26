package edu.softserveinc.healthbody.exceptions;

public class IllegalAgrumentCheckedException extends Exception {

    private static final long serialVersionUID = 1L;

    public IllegalAgrumentCheckedException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public IllegalAgrumentCheckedException(final String message) {
        super(message);
    }
}
