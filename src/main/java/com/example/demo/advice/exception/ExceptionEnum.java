package com.example.demo.advice.exception;

import com.example.demo.advice.exception.exceptions.InvalidSearchTypeException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * @Author Eric
 * @Description
 * @Since 22. 8. 30.
 **/
public enum ExceptionEnum {

    UNKNOWN_ERROR("-9999", "알 수 없는 오류가 발생했습니다."),
    ROLE_NOT_SET("-9998","멤버 권한이 설정되지 않았습니다."),
    ACCESS_DENIED("-9997", "해당 리소스에 접근하기 위한 권한이 없습니다."),
    INVALID_SIGNATURE_JWT("-9996", "인증 정보가 잘못되었습니다."),
    INVALID_JWT("-9995", "인증 정보가 잘못되었습니다."),
    EXPIRED_JWT("-9994", "로그인 정보가 만료되었습니다."),
    EMPTY_CLAIM_JWT("-9993", "인증 정보가 잘못되었습니다."),
    BAD_CREDENTIALS("-1000","아이디 또는 비밀번호가 잘못되었습니다."),
    DUPLICATED_MEMBER_EMAIL("-1001", "이미 가입된 이메일입니다."),
    DUPLICATED_MEMBER_NAME("-1002", "이미 가입된 닉네임입니다."),
    MEMBER_NOT_FOUND("-1003", "존재하지 않는 회원입니다."),
    INVALID_SEARCH_TYPE("-1004", "검색 설정이 올바르지 않습니다.");

    @Getter
    private String code;
    @Getter
    private String message;

    ExceptionEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
