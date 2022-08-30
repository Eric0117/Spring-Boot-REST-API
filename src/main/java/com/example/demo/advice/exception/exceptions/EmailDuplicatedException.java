package com.example.demo.advice.exception.exceptions;

/**
 * @Author Eric
 * @Description
 * @Since 22. 8. 30.
 **/
public class EmailDuplicatedException extends RuntimeException {
    public EmailDuplicatedException(String msg, Throwable t) {
        super(msg, t);
    }

    public EmailDuplicatedException(String msg) {
        super(msg);
    }

    public EmailDuplicatedException() {
        super();
    }
}
