package edu.epam.daotask.exception;

public class BaseDaoException extends Exception {
    public BaseDaoException(){}

    public BaseDaoException(String message) {
        super(message);
    }

    public BaseDaoException(String message, Throwable cause) {
        super(message, cause);
    }
    public BaseDaoException(Throwable cause) {
        super(cause);
    }
}
