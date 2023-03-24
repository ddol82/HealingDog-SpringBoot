package com.healing.healingdog.login.service;


import com.healing.healingdog.login.dao.UserMapper;
import com.healing.healingdog.exception.UserNotFoundException;
import com.healing.healingdog.login.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserMapper mapper;

    public CustomUserDetailsService(UserMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info("[CustomUserDetailsService] ===================================");
        log.info("[CustomUserDetailsService] loadUserByUsername {}", email);

        return mapper.findByUserEmail(email)
                .map(user -> addAuthorities(user))
                .orElseThrow(() -> new UserNotFoundException(email + "> 찾을 수 없습니다."));
    }

    private UserDTO addAuthorities(UserDTO userDto) {
        userDto.setAuthorities(Arrays.asList(new SimpleGrantedAuthority(userDto.getRole())));

        return userDto;
    }

}