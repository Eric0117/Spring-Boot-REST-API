package com.example.demo.service.impl;

import com.example.demo.advice.exception.exceptions.EmailDuplicatedException;
import com.example.demo.advice.exception.exceptions.NameDuplicatedException;
import com.example.demo.advice.exception.exceptions.RoleNotSetException;
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
import com.example.demo.service.ResponseService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author Eric
 * @Description
 * @Since 22. 8. 30.
 **/
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthServiceImpl.class);

    private final ResponseService responseService;
    private final AuthenticationManager authenticationManager;
    private final MemberRepository memberRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public SingleResult<String> signIn(SignInRequestDTO signInRequestDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequestDTO.getUsernameOrEmail(), signInRequestDTO.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenProvider.generateToken(authentication);
        return responseService.getSingleResult(jwt);
    }

    @Override
    public CommonResult signUp(SignUpRequestDTO signUpRequestDTO) {
        if (Boolean.TRUE.equals(memberRepository.existsByUsername(signUpRequestDTO.getUsername()))) {
            throw new NameDuplicatedException();
        }

        if (Boolean.TRUE.equals(memberRepository.existsByEmail(signUpRequestDTO.getEmail()))) {
            throw new EmailDuplicatedException();
        }
        String username = signUpRequestDTO.getUsername().toLowerCase();

        String email = signUpRequestDTO.getEmail().toLowerCase();

        String password = passwordEncoder.encode(signUpRequestDTO.getPassword());
        Member member = new Member(username, email, password);

        Set<Role> roles = new HashSet<>();

        if (memberRepository.count() == 0) {
            roles.add(roleRepository.findByName(RoleName.ROLE_USER)
                    .orElseThrow(RoleNotSetException::new));
            roles.add(roleRepository.findByName(RoleName.ROLE_ADMIN)
                    .orElseThrow(RoleNotSetException::new));
        } else {
            roles.add(roleRepository.findByName(RoleName.ROLE_USER)
                    .orElseThrow(RoleNotSetException::new));
        }

        member.setRoles(roles);

        memberRepository.save(member);

        return responseService.getSuccessResult();
    }

}
