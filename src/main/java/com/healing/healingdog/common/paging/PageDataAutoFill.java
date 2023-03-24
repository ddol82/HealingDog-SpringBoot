package com.healing.healingdog.common.paging;

import org.springframework.beans.factory.annotation.Value;

public class PageDataAutoFill {
    @Value("page.show-list-amount")
    private static int SHOW_ITEMS_PER_PAGE;
    @Value("page.show-page-amount")
    private static int SHOW_PAGES_AT_ONCE;

    public static PageData get(int currPage, int totalItemAmount) {
        PageData pageData = new PageData();
        pageData.setItemAllCount(totalItemAmount);
        pageData.setCurrPage(currPage);

        pageData.setPageAmount((int) Math.ceil((double) totalItemAmount / SHOW_ITEMS_PER_PAGE));
        pageData.setStartPage((int) (Math.ceil((double) currPage / SHOW_PAGES_AT_ONCE) - 1) * SHOW_PAGES_AT_ONCE + 1);
        pageData.setEndPage(Math.min(pageData.getStartPage() + SHOW_PAGES_AT_ONCE - 1, 0));

        return pageData;
    }
}
