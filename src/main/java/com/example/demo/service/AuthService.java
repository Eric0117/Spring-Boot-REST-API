package com.example.demo.service;

import com.example.demo.common.CommonResult;
import com.example.demo.common.SingleResult;
import com.example.demo.model.dto.SignInRequestDTO;
import com.example.demo.model.dto.SignUpRequestDTO;

/**
 * @Author Eric
 * @Description
 * @Since 22. 8. 30.
 **/
public interface AuthService {
    SingleResult<String> signIn(SignInRequestDTO signInRequestDTO);
    CommonResult signUp(SignUpRequestDTO signUpRequestDTO);
}
