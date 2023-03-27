package com.healing.healingdog.community.model.dao;

import com.healing.healingdog.community.model.dto.BoardDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 커뮤니티의 매퍼 인터페이스입니다.
 *
 * @since 1.0
 * @version 1.0
 * @author 이진녕
 */
@Mapper
public interface CommunityMapper {
    List<BoardDTO> selectBoardListWithPage(String category);

    int selectBoardCountAll(int categoryCode);
}
