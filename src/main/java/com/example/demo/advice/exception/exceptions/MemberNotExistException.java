package com.example.demo.advice.exception.exceptions;

/**
 * @Author Eric
 * @Description
 * @Since 22. 9. 1.
 **/
public class MemberNotExistException extends RuntimeException {
    public MemberNotExistException(String msg, Throwable t) {
        super(msg, t);
    }

    public MemberNotExistException(String msg) {
        super(msg);
    }

    public MemberNotExistException() {
        super();
    }
}