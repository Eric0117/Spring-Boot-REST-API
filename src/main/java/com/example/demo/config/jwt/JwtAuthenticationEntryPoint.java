package com.example.demo.config.jwt;

import com.example.demo.advice.exception.ExceptionEnum;
import com.example.demo.service.ResponseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @Author Eric
 * @Description
 * @Since 22. 8. 30.
 **/
@Component
@RequiredArgsConstructor
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationEntryPoint.class);
    private final ResponseService responseService;
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e)
            throws IOException, ServletException {

        String exceptionType = (String) request.getAttribute("exception");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.UNAUTHORIZED.value());

        int code = Integer.parseInt(ExceptionEnum.INVALID_JWT.getCode());
        String message = ExceptionEnum.INVALID_JWT.getMessage();


        if(exceptionType != null) {
            switch (exceptionType) {
                case "invalidSignature":
                    code = Integer.parseInt(ExceptionEnum.INVALID_SIGNATURE_JWT.getCode());
                    message = ExceptionEnum.INVALID_SIGNATURE_JWT.getMessage();
                    break;
                case "invalidJwt":
                    code = Integer.parseInt(ExceptionEnum.INVALID_JWT.getCode());
                    message = ExceptionEnum.INVALID_JWT.getMessage();
                    break;
                case "expiredJwt":
                    code = Integer.parseInt(ExceptionEnum.EXPIRED_JWT.getCode());
                    message = ExceptionEnum.EXPIRED_JWT.getMessage();
                    break;
                case "claimsEmpty":
                    code = Integer.parseInt(ExceptionEnum.EMPTY_CLAIM_JWT.getCode());
                    message = ExceptionEnum.EMPTY_CLAIM_JWT.getMessage();
                    break;
            }
        }


        try (OutputStream os = response.getOutputStream()) {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(os, responseService.getFailResult(code, message));
            os.flush();
        }
    }
}
