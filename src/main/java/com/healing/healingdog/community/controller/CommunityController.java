package com.healing.healingdog.community.controller;

import com.healing.healingdog.community.model.dto.BoardDTO;
import com.healing.healingdog.community.model.service.CommunityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/community")
public class CommunityController {
    @Value("${page.show-list-amount.community}")
    private int showItemAmount;
    private final CommunityService communityService;

    public CommunityController(CommunityService communityService) {
        this.communityService = communityService;
    }

    @GetMapping("/lists/boards")
    public List<BoardDTO> selectBoardList(@RequestParam(name="category", defaultValue = "5")int categoryParam,
                                          @RequestParam(name="page", defaultValue = "1")int currPage) {
        log.info("[CommunityComtroller] selectBoardList 호출됨");
        log.debug("categoryParam : " + categoryParam);
        log.debug("currPage : " + currPage);

        //int boardCount = communityService.selectProductCount(categoryParam);
        return null;
    }
}
