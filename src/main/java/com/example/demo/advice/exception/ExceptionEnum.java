package com.example.demo.advice.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * @Author Eric
 * @Description
 * @Since 22. 8. 30.
 **/
public enum ExceptionEnum {

    UNKNOWN_ERROR("-9999", "알 수 없는 오류가 발생했습니다.");

    @Getter
    private String code;
    @Getter
    private String message;

    ExceptionEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
