package com.healing.healingdog.common.paging;

import org.springframework.beans.factory.annotation.Value;

/**
 * {@link PageData}의 정보를 자동으로 채워주는 기능의 클래스입니다.
 *
 * @author 이진녕
 * @since 1.0
 * @version 1.0
 */
public class PageDataAutoFill {
    @Value("page.show-list-amount")
    private static int SHOW_ITEMS_PER_PAGE;
    @Value("page.show-page-amount")
    private static int SHOW_PAGES_AT_ONCE;

    /**
     * {@link PageData}의 정보 중 계산이 필요한 부분을 완성시켜줍니다.
     *
     * @param currPage 조회하는 현재 페이지입니다.
     * @param totalItemAmount 조회된 전체 결과의 수입니다.
     * @return 계산이 완료된 다른 정보를 넣은 {@link PageData}를 반환합니다.
     */
    public static PageData get(int currPage, int totalItemAmount) {
        PageData pageData = new PageData();
        pageData.setItemAllCount(totalItemAmount);
        pageData.setCurrPage(currPage);

        pageData.setPageAmount((int) Math.ceil((double) totalItemAmount / SHOW_ITEMS_PER_PAGE));
        pageData.setStartPage((int) (Math.ceil((double) currPage / SHOW_PAGES_AT_ONCE) - 1) * SHOW_PAGES_AT_ONCE + 1);
        pageData.setEndPage(Math.min(pageData.getStartPage() + SHOW_PAGES_AT_ONCE - 1, pageData.getPageAmount()));
        pageData.setStartCursor((currPage-1) * SHOW_ITEMS_PER_PAGE + 1);
        pageData.setEndCursor(Math.min(currPage * SHOW_ITEMS_PER_PAGE, totalItemAmount));

        return pageData;
    }
}
