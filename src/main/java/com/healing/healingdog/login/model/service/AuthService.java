package com.healing.healingdog.login.model.service;

import com.healing.healingdog.exception.DuplicatedUsernameException;
import com.healing.healingdog.exception.LoginFailedException;
import com.healing.healingdog.jwt.TokenProvider;
import com.healing.healingdog.login.model.dao.AuthMapper;
import com.healing.healingdog.login.model.dto.ProviderDTO;
import com.healing.healingdog.login.model.dto.TokenDTO;
import com.healing.healingdog.login.model.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class AuthService {

    private final AuthMapper authMapper;


    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    public AuthService(AuthMapper authMapper, PasswordEncoder passwordEncoder, TokenProvider tokenProvider) {
        this.authMapper = authMapper;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
    }

    @Transactional
    public UserDTO userSignup(UserDTO userDto) {
        log.info("[AuthService] Signup Start ===================================");
        log.info("[AuthService] UserRequestDto {}", userDto);

        if(authMapper.selectByUserSignupEmail(userDto.getEmail()) != null) {
            log.info("[AuthService] 이메일이 중복됩니다.");
            throw new DuplicatedUsernameException("이메일이 중복됩니다.");
        }

        log.info("[AuthService] User Signup Start ==============================");
        userDto.setUserPassword(passwordEncoder.encode(userDto.getUserPassword()));
        log.info("[AuthService] User {}", userDto);
        int result = authMapper.insertUser(userDto);
        log.info("[AuthService] User Insert Result {}", result > 0 ? "회원 가입 성공" : "회원 가입 실패");

        log.info("[AuthService] Signup End ==============================");

        return userDto;
    }

    @Transactional
    public ProviderDTO providerSignup(ProviderDTO providerDTO) {
        log.info("[AuthService] Signup Start ===================================");
        log.info("[AuthService] UserRequestDto {}", providerDTO);

        if(authMapper.selectByProviderSignupEmail(providerDTO.getEmail()) != null) {
            log.info("[AuthService] 이메일이 중복됩니다.");
            throw new DuplicatedUsernameException("이메일이 중복됩니다.");
        }

        log.info("[AuthService] User Signup Start ==============================");
        providerDTO.setProviderPassword(passwordEncoder.encode(providerDTO.getProviderPassword()));
        log.info("[AuthService] User {}", providerDTO);
        int result = authMapper.insertProvider(providerDTO);
        log.info("[AuthService] User Insert Result {}", result > 0 ? "회원 가입 성공" : "회원 가입 실패");

        log.info("[AuthService] Signup End ==============================");

        return providerDTO;
    }



    @Transactional
    public TokenDTO userLogin(UserDTO userDto) {
        log.info("[AuthService] Login Start ===================================");
        log.info("[AuthService] {}", userDto);

        // 1. 이메일 조회
        UserDTO user = authMapper.findByUserEmail(userDto.getEmail())
                .orElseThrow(() -> new LoginFailedException("잘못된 이메일 또는 비밀번호입니다"));
        log.info("[AuthService] 이메일조회 완료 {} ", user);


        // 2. 비밀번호 매칭
        if (!passwordEncoder.matches(userDto.getUserPassword(), user.getUserPassword())) {
            log.info("[AuthService] Password Match Fail!!!!!!!!!!!!");
            throw new LoginFailedException("잘못된 이메일 또는 비밀번호입니다");
        }

        // 3. 토큰 발급
        TokenDTO tokenDto = tokenProvider.generateUserTokenDto(user);
        log.info("[AuthService] tokenDto {}", tokenDto);

        log.info("[AuthService] Login End ===================================");

        return tokenDto;
    }

    @Transactional
    public TokenDTO providerLogin(ProviderDTO providerDto) {
        log.info("[AuthService] Login Start ===================================");
        log.info("[AuthService] {}", providerDto);


        // 1. 이메일 조회
        ProviderDTO provider = authMapper.findByProviderEmail(providerDto.getEmail())
                .orElseThrow(() -> new LoginFailedException("잘못된 이메일 또는 비밀번호입니다22222222"));
        log.info("[AuthService] 이메일조회 완료 {} ", provider);


        // 2. 비밀번호 매칭
        if (!passwordEncoder.matches(providerDto.getProviderPassword(), provider.getProviderPassword())) {
            log.info("[AuthService] Password Match Fail!!!!!!!!!!!!");
            throw new LoginFailedException("잘못된 이메일 또는 비밀번호입니다");
        }


        // 3. 토큰 발급
        TokenDTO tokenDto = tokenProvider.generateProviderTokenDto(provider);
        log.info("[AuthService] tokenDto {}", tokenDto);

        log.info("[AuthService] Login End ===================================");

        return tokenDto;
    }

}
