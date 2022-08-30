package com.example.demo.advice.exception.exceptions;

/**
 * @Author Eric
 * @Description
 * @Since 22. 8. 30.
 **/
public class NameDuplicatedException extends RuntimeException {
    public NameDuplicatedException(String msg, Throwable t) {
        super(msg, t);
    }

    public NameDuplicatedException(String msg) {
        super(msg);
    }

    public NameDuplicatedException() {
        super();
    }
}