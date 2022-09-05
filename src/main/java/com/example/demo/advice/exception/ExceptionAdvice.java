package com.example.demo.advice.exception;

import com.example.demo.advice.exception.exceptions.*;
import com.example.demo.common.CommonResult;
import com.example.demo.service.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
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
    public CommonResult emailDuplicatedException(HttpServletRequest request, EmailDuplicatedException e) {
        return responseService.getFailResult(Integer.parseInt(ExceptionEnum.DUPLICATED_MEMBER_EMAIL.getCode()), ExceptionEnum.DUPLICATED_MEMBER_EMAIL.getMessage());
    }

    @ExceptionHandler(NameDuplicatedException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public CommonResult nameDuplicatedException(HttpServletRequest request, NameDuplicatedException e) {
        return responseService.getFailResult(Integer.parseInt(ExceptionEnum.DUPLICATED_MEMBER_NAME.getCode()), ExceptionEnum.DUPLICATED_MEMBER_NAME.getMessage());
    }

    @ExceptionHandler(RoleNotSetException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public CommonResult roleNotSetException(HttpServletRequest request, RoleNotSetException e) {
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

    @ExceptionHandler(MemberNotExistException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public CommonResult memberNotExistException(HttpServletRequest request, MemberNotExistException e) {
        return responseService.getFailResult(Integer.parseInt(ExceptionEnum.MEMBER_NOT_FOUND.getCode()), ExceptionEnum.MEMBER_NOT_FOUND.getMessage());
    }

    @ExceptionHandler(PostNotExistException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public CommonResult postNotExistException(HttpServletRequest request, PostNotExistException e) {
        return responseService.getFailResult(Integer.parseInt(ExceptionEnum.POST_NOT_FOUND.getCode()), ExceptionEnum.POST_NOT_FOUND.getMessage());
    }

    @ExceptionHandler(CommentNotExistException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public CommonResult commentNotExistException(HttpServletRequest request, CommentNotExistException e) {
        return responseService.getFailResult(Integer.parseInt(ExceptionEnum.COMMENT_NOT_FOUND.getCode()), ExceptionEnum.COMMENT_NOT_FOUND.getMessage());
    }

    @ExceptionHandler(InvalidSearchTypeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CommonResult invalidSearchTypeException(HttpServletRequest request, InvalidSearchTypeException e) {
        return responseService.getFailResult(Integer.parseInt(ExceptionEnum.INVALID_SEARCH_TYPE.getCode()), ExceptionEnum.INVALID_SEARCH_TYPE.getMessage());
    }


}
