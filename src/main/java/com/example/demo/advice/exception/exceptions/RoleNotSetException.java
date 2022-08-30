package com.example.demo.advice.exception.exceptions;

/**
 * @Author Eric
 * @Description
 * @Since 22. 8. 30.
 **/
public class RoleNotSetException extends RuntimeException {
    public RoleNotSetException(String msg, Throwable t) {
        super(msg, t);
    }

    public RoleNotSetException(String msg) {
        super(msg);
    }

    public RoleNotSetException() {
        super();
    }
}