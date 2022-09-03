package com.example.demo.advice.exception.exceptions;

/**
 * @Author Eric
 * @Description
 * @Since 22. 9. 4.
 **/
public class InvalidSearchTypeException extends RuntimeException {
    public InvalidSearchTypeException(String msg, Throwable t) {
        super(msg, t);
    }

    public InvalidSearchTypeException(String msg) {
        super(msg);
    }

    public InvalidSearchTypeException() {
        super();
    }
}