package com.healing.healingdog.community.model.dao;

import com.healing.healingdog.community.model.dto.BoardDTO;

import java.util.List;

public interface CommunityMapper {
    List<BoardDTO> selectBoardListWithPage(String category);
}
