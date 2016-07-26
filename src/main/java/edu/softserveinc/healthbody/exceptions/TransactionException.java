package edu.softserveinc.healthbody.exceptions;

public class TransactionException extends Exception {

    private static final long serialVersionUID = 1L;

    public TransactionException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public TransactionException(final String message) {
        super(message);
    }

}
