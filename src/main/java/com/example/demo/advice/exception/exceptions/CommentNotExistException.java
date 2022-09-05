package com.example.demo.advice.exception.exceptions;

/**
 * @Author Eric
 * @Description
 * @Since 22. 9. 5.
 **/
public class CommentNotExistException extends RuntimeException {
    public CommentNotExistException(String msg, Throwable t) {
        super(msg, t);
    }

    public CommentNotExistException(String msg) {
        super(msg);
    }

    public CommentNotExistException() {
        super();
    }
}