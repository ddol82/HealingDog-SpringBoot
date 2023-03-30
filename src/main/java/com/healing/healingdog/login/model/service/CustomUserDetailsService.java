package com.healing.healingdog.login.model.service;


import com.healing.healingdog.login.model.dao.AuthMapper;
import com.healing.healingdog.login.model.dto.ProviderDTO;
import com.healing.healingdog.login.model.dto.UserDTO;
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

        UserDetails userDetails = null;//UserDetails 을 null 로 선언

        userDetails = mapper.findByUserEmail(email)
                .map(this::addAuthorities)
                .orElse(null);
        if(userDetails != null) {
            return userDetails; // findByUserEmail이 있으면 리턴한다
        }
        //아니면 findByProviderEmail 을 찾는다
        userDetails = mapper.findByProviderEmail(email)
                .map(this::addAuthorities)
                .orElse(null);
        if(userDetails == null) { // findByUserEmail, findByProviderEmail 두개다 없으면 UsernameNotFoundException을 던진다
            throw new UsernameNotFoundException(email + "에 해당하는 user 또는 provider를 찾을 수 없습니다."); //UserDetails이 비어있으면  UsernameNotFoundException 을 날린다
        }
        return userDetails;

    }


    private ProviderDTO addAuthorities(ProviderDTO providerDTO) {
        providerDTO.setAuthorities(Arrays.asList(new SimpleGrantedAuthority(providerDTO.getRole())));

        return providerDTO;
    }

    private UserDTO addAuthorities(UserDTO userDto) {
        userDto.setAuthorities(Arrays.asList(new SimpleGrantedAuthority(userDto.getRole())));

        return userDto;
    }




}