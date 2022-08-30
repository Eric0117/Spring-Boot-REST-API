package com.example.demo.common;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author Eric
 * @Description
 * @Since 22. 8. 30.
 **/
@Getter
@Setter
public class CommonResult {
    // 응답 성공여부 : true/false
    private boolean success;

    // 응답 코드 번호 : >= 0 정상 , < 0 비정상
    private int code;

    // 응답 메시지
    private String msg;
}
