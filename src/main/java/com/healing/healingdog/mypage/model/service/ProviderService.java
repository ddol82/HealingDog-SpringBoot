package com.healing.healingdog.mypage.model.service;

import com.healing.healingdog.login.model.dto.ProviderDTO;
import com.healing.healingdog.mypage.model.dao.ProviderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProviderService {
    private final ProviderMapper providerMapper;
    private final PasswordEncoder passwordEncoder;

    public ProviderService(ProviderMapper providerMapper, PasswordEncoder passwordEncoder) {
        this.providerMapper = providerMapper;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     *Service
     *Provider 제공자의 정보를 조회 합니다
     * @param providerCode Provider의 번호
     * @return provider 제공자정보를 반환한다
     */
    public ProviderDTO selectMyProviderInfo(int providerCode) {
        log.info("[ProviderService] selectMyProviderInfo Start ==============================");

        ProviderDTO provider = providerMapper.selectMyProviderInfo(providerCode);
        log.info("[ProviderService] {}", provider);
        log.info("[ProviderService] selectMyProviderInfo End ==============================");

        return provider;
    }

    /**
     *Service
     *Provider 제공자의 정보를 수정(update) 합니다
     * @param providerDTO,providerCode 제공자 DTO와 제공자코드
     * @return result 를 반환한다
     */
    public Object updateMyProviderInfo(ProviderDTO providerDTO, int providerCode) {
        log.info("[ProviderService] updateMyProviderInfo Start ==============================");
        log.info("[ProviderService] {}", providerDTO);

        int result = 0 ;
        providerDTO.setProviderCode(providerCode);

        providerDTO.setProviderPassword(passwordEncoder.encode(providerDTO.getProviderPassword()));

        log.info("[ProviderService] {}", providerDTO);

        result = providerMapper.updateMyProviderInfo(providerDTO);

        log.info("[ProviderService] updateMyProviderInfo End ==============================");

        return result;

    }

    /**
     *Service
     *Provider 제공자의 정보를 삭제(탈퇴 ) 합니다
     * @param providerCode 삭제(탈퇴) 할 제공자의 코드
     * @return providerCode 를 반환한다
     */
    public int deleteMyProviderInfo(int providerCode) {

        return providerMapper.deleteMyProviderInfo(providerCode);
    }
}
