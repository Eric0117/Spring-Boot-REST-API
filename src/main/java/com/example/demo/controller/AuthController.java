package com.example.demo.controller;

import com.example.demo.common.CommonResult;
import com.example.demo.common.SingleResult;
import com.example.demo.config.jwt.JwtTokenProvider;
import com.example.demo.model.dto.SignInRequestDTO;
import com.example.demo.model.dto.SignUpRequestDTO;
import com.example.demo.model.member.Member;
import com.example.demo.model.role.Role;
import com.example.demo.model.role.RoleName;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author Eric
 * @Description
 * @Since 22. 8. 30.
 **/
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signin")
    public SingleResult<String> signIn(@Valid @RequestBody SignInRequestDTO signInRequestDTO) {
        return authService.signIn(signInRequestDTO);
    }

    @PostMapping("/signup")
    public CommonResult registerUser(@Valid @RequestBody SignUpRequestDTO signUpRequestDTO) throws Exception {
       return authService.signUp(signUpRequestDTO);
    }
}
