package com.example.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @Author Eric
 * @Description
 * @Since 22. 8. 30.
 **/
public interface CustomUserDetailsService {
    UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException;

    UserDetails loadUserById(Long id);
}
