package com.healing.healingdog.beauty.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CommonDTO {
    /**
     * 일정코드
     * */
    private int schedulesCode;
    /**
     * 서비스 카테고리 코드
     * */
    private int serviceCategoryCode;

    /**
     * 일정 제목
     * */
    private String title;
    /**
     * 일정 본문
     * */
    private String text;
    /**
     * 시작시간
     * */
    private String startTime;
    /**
     * 종료시간
     * */
    private String endTime;

    /**
     * 운영시간 코드
     * */
    private int timeCode;

    /**
     * 운영시간 요일
     * */
    private String day;

}
