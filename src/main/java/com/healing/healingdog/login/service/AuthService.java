package com.healing.healingdog.login.service;

import com.healing.healingdog.exception.DuplicatedUsernameException;
import com.healing.healingdog.exception.LoginFailedException;
import com.healing.healingdog.jwt.TokenProvider;
import com.healing.healingdog.login.dao.UserMapper;
import com.healing.healingdog.login.dto.ProviderDTO;
import com.healing.healingdog.login.dto.UserDTO;
import com.healing.healingdog.login.dto.TokenDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@Slf4j
public class AuthService {

    private final UserMapper userMapper;



    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    public AuthService(UserMapper userMapper, PasswordEncoder passwordEncoder, TokenProvider tokenProvider) {
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
    }

    @Transactional
    public UserDTO signup(UserDTO userDto) {
        log.info("[AuthService] Signup Start ===================================");
        log.info("[AuthService] UserRequestDto {}", userDto);

        if(userMapper.selectByEmail(userDto.getEmail()) != null) {
            log.info("[AuthService] 이메일이 중복됩니다.");
            throw new DuplicatedUsernameException("이메일이 중복됩니다.");
        }

        log.info("[AuthService] User Signup Start ==============================");
        userDto.setUserPassword(passwordEncoder.encode(userDto.getUserPassword()));
        log.info("[AuthService] User {}", userDto);
        int result = userMapper.insertUser(userDto);
        log.info("[AuthService] User Insert Result {}", result > 0 ? "회원 가입 성공" : "회원 가입 실패");

        log.info("[AuthService] Signup End ==============================");

        return userDto;
    }

    @Transactional
    public TokenDTO login(UserDTO userDto, ProviderDTO providerDto) {
        log.info("[AuthService] Login Start ===================================");
        log.info("[AuthService] {}", userDto);
        log.info("[AuthService] {}", providerDto);


        // 1. 이메일 조회
        UserDTO user = userMapper.findByUserEmail(userDto.getEmail())
                .orElseThrow(() -> new LoginFailedException("잘못된 이메일 또는 비밀번호입니다"));
        log.info("[AuthService] 이메일조회 완료 {} ", user);

        ProviderDTO provider = userMapper.findByProviderEmail(providerDto.getEmail())
                .orElseThrow(() -> new LoginFailedException("잘못된 이메일 또는 비밀번호입니다22222222"));
        log.info("[AuthService] 이메일조회 완료 {} ", provider);




        // 2. 비밀번호 매칭
        if (!passwordEncoder.matches(userDto.getUserPassword(), user.getUserPassword())) {
            log.info("[AuthService] Password Match Fail!!!!!!!!!!!!");
            throw new LoginFailedException("잘못된 이메일 또는 비밀번호입니다");
        }

        if (!passwordEncoder.matches(providerDto.getProviderPassword(), provider.getProviderPassword())) {
            log.info("[AuthService] Password Match Fail!!!!!!!!!!!!");
            throw new LoginFailedException("잘못된 이메일 또는 비밀번호입니다");
        }


        // 3. 토큰 발급
        TokenDTO tokenDto = tokenProvider.generateUserTokenDto(user);
        log.info("[AuthService] tokenDto {}", tokenDto);
        tokenDto = tokenProvider.generateUserTokenDto(provider);
        log.info("[AuthService] tokenDto {}", tokenDto);

        log.info("[AuthService] Login End ===================================");

        return tokenDto;
    }

}
