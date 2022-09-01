package com.example.demo.advice.exception.exceptions;

/**
 * @Author Eric
 * @Description
 * @Since 22. 9. 1.
 **/
public class AccessDeniedException extends RuntimeException {
    public AccessDeniedException(String msg, Throwable t) {
        super(msg, t);
    }

    public AccessDeniedException(String msg) {
        super(msg);
    }

    public AccessDeniedException() {
        super();
    }
}