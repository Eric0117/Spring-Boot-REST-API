package com.example.demo.advice.exception;

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
    DUPLICATED_MEMBER_EMAIL("-1005", "이미 가입된 이메일입니다."),
    DUPLICATED_MEMBER_NAME("-1006", "이미 가입된 닉네임입니다.");


    @Getter
    private String code;
    @Getter
    private String message;

    ExceptionEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
