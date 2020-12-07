package edu.epam.daotask.exception;

import java.util.concurrent.Executor;

public class LibraryReaderException extends Exception {

    public LibraryReaderException(){}

    public LibraryReaderException(String message) {
        super(message);
    }

    public LibraryReaderException(String message, Throwable cause) {
        super(message, cause);
    }
     public LibraryReaderException(Throwable cause) {
        super(cause);
    }
}
