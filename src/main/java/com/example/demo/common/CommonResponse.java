package com.example.demo.common;

/**
 * @Author Eric
 * @Description enum으로 api 요청 결과에 대한 code, message를 정의
 * @Since 22. 8. 30.
 **/
public enum CommonResponse {
    SUCCESS(0, "Success");

    int code;
    String msg;

    CommonResponse(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
