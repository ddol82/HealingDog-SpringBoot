package com.healing.healingdog.login.service;


import com.healing.healingdog.exception.UserNotFoundException;
import com.healing.healingdog.login.dao.AuthMapper;
import com.healing.healingdog.login.dto.ProviderDTO;
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

    private final AuthMapper mapper;

    public CustomUserDetailsService(AuthMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info("[CustomUserDetailsService] ===================================");
        log.info("[CustomUserDetailsService] loadUserByUsername {}", email);

        UserDetails userDetails = null;

        userDetails = mapper.findByUserEmail(email)
                .map(this::addAuthorities)
                .orElse(null);
        if(userDetails != null) {
            return userDetails;
        }
        userDetails = mapper.findByProviderEmail(email)
                .map(this::addAuthorities)
                .orElse(null);
        if(userDetails == null) {
            throw new UsernameNotFoundException(email + "에 해당하는 user 또는 provider를 찾을 수 없습니다.");
        }
        return userDetails;
    }

    private UserDTO addAuthorities(UserDTO userDto) {
        userDto.setAuthorities(Arrays.asList(new SimpleGrantedAuthority(userDto.getRole())));

        return userDto;
    }

    private ProviderDTO addAuthorities(ProviderDTO providerDTO) {
        providerDTO.setAuthorities(Arrays.asList(new SimpleGrantedAuthority(providerDTO.getRole())));

        return providerDTO;
    }

}