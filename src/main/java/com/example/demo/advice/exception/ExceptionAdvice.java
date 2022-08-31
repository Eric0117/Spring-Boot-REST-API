package com.example.demo.advice.exception;

import com.example.demo.advice.exception.exceptions.EmailDuplicatedException;
import com.example.demo.advice.exception.exceptions.NameDuplicatedException;
import com.example.demo.advice.exception.exceptions.RoleNotSetException;
import com.example.demo.common.CommonResult;
import com.example.demo.service.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author Eric
 * @Description
 * @Since 22. 8. 30.
 **/
@RequiredArgsConstructor
@RestControllerAdvice
public class ExceptionAdvice {
    private final ResponseService responseService;

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult defaultException(HttpServletRequest request, Exception e) {
        e.printStackTrace();
        return responseService.getFailResult(Integer.parseInt(ExceptionEnum.UNKNOWN_ERROR.getCode()), ExceptionEnum.UNKNOWN_ERROR.getMessage());
    }

    @ExceptionHandler(EmailDuplicatedException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public CommonResult communicationException(HttpServletRequest request, EmailDuplicatedException e) {
        return responseService.getFailResult(Integer.parseInt(ExceptionEnum.DUPLICATED_MEMBER_EMAIL.getCode()), ExceptionEnum.DUPLICATED_MEMBER_EMAIL.getMessage());
    }

    @ExceptionHandler(NameDuplicatedException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public CommonResult communicationException(HttpServletRequest request, NameDuplicatedException e) {
        return responseService.getFailResult(Integer.parseInt(ExceptionEnum.DUPLICATED_MEMBER_NAME.getCode()), ExceptionEnum.DUPLICATED_MEMBER_NAME.getMessage());
    }

    @ExceptionHandler(RoleNotSetException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public CommonResult communicationException(HttpServletRequest request, RoleNotSetException e) {
        return responseService.getFailResult(Integer.parseInt(ExceptionEnum.ROLE_NOT_SET.getCode()), ExceptionEnum.ROLE_NOT_SET.getMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public CommonResult accessDeniedException(HttpServletRequest request, AccessDeniedException e) {
        return responseService.getFailResult(Integer.parseInt(ExceptionEnum.ACCESS_DENIED.getCode()), ExceptionEnum.ACCESS_DENIED.getMessage());
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public CommonResult badCredentialsException(HttpServletRequest request, BadCredentialsException e) {
        return responseService.getFailResult(Integer.parseInt(ExceptionEnum.BAD_CREDENTIALS.getCode()), ExceptionEnum.BAD_CREDENTIALS.getMessage());
    }

}
