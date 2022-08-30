package com.example.demo.service.impl;

import com.example.demo.config.jwt.UserPrincipal;
import com.example.demo.model.member.Member;
import com.example.demo.repository.MemberRepository;
import com.example.demo.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @Author Eric
 * @Description
 * @Since 22. 8. 30.
 **/
@Service
@RequiredArgsConstructor
public class CustomUserDetailsServiceImpl implements UserDetailsService, CustomUserDetailsService {
    private final MemberRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String usernameOrEmail) {
        Member member = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User not found with this username or email: %s", usernameOrEmail)));
        return UserPrincipal.create(member);
    }

    @Override
    @Transactional
    public UserDetails loadUserById(Long id) {
        Member member = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException(String.format("User not found with id: %s", id)));

        return UserPrincipal.create(member);
    }
}
