package com.example.demo.advice.exception.exceptions;

/**
 * @Author Eric
 * @Description
 * @Since 22. 9. 4.
 **/
public class PostNotExistException extends RuntimeException {
    public PostNotExistException(String msg, Throwable t) {
        super(msg, t);
    }

    public PostNotExistException(String msg) {
        super(msg);
    }

    public PostNotExistException() {
        super();
    }
} 