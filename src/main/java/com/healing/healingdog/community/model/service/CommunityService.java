package com.healing.healingdog.community.model.service;

import com.healing.healingdog.community.model.dao.CommunityMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 커뮤니티의 서비스. {@link Slf4j @Slf4j}를 사용하여 {@code log}를 통한 logging 사용이 가능합니다.
 *
 * @since 1.0
 * @version 1.0
 * @author 이진녕
 */
@Slf4j
@Service
public class CommunityService {
    private final CommunityMapper communityMapper;

    public CommunityService(CommunityMapper communityMapper) {
        this.communityMapper = communityMapper;
    }

    public int selectBoardCountAll(int categoryCode) {
        log.info("[CommunityService] selectBoardCount 호출");
        int count = communityMapper.selectBoardCountAll(categoryCode);
        log.debug("(selectBoardCount) 조회 결과 : " + count);
        return count;
    }
}
