package com.healing.healingdog.beauty.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BeautyDTO {
    /**
     * 미용실 코드
     * */
    private int beautyCode;
    /**
     * 제공자 코드
     * */
    private int providerCode;
    /**
     * 제공자 코드
     * */
    private int userCode;
    /**
     * 미용실 가격코드
     * */
    private int beautyPriceCode;
    /**
     * 카테고리 코드
     * */
    private int categoryCode;
    /**
     * 예약내역 코드
     * */
    private int beautyReservationListCode;
    /**
     * 마이펫 코드
     * */
    private int mypetCode;
    /**
     * 운영시간 코드
     * */
    private int timeCode;
    /**
     * 이름
     * */
    private String name;
    /**
     * 연락처
     * */
    private String phone;
    /**
     * 홈페이지
     * */
    private String web;
    /**
     * 우편번호
     * */
    private String zoneCode;
    /**
     * 주소
     * */
    private String address;
    /**
     * 상세주소
     * */
    private String addressDetail;
    /**
     * 소개
     * */
    private String intro;
    /**
     * 블랙리스트 여부
     * */
    private String blacklist;
    /**
     * 날짜
     * */
    private Timestamp date;
    /**
     * 시간
     * */
    private Timestamp time;
    /**
     * 예약상태
     * */
    private String reservation;
    /**
     * 분류
     * */
    private String size;
    /**
     * 컷트명
     * */
    private String cut;
    /**
     * 가격
     * */
    private int price;

    /**
     * 카테고리 라지
     * */
    private String large;
    /**
     * 카테고리 미디움
     * */
    private String medium;
    /**
     * 카테고리 스몰
     * */
    private String small;
    /**
     * 카테고리 오픈미용
     * */
    private String openBeauty;
    /**
     * 카테고리 스파
     * */
    private String spa;
    /**
     * 카테고리 마사지
     * */
    private String massage;
    /**
     * 카테고리 셀프미용
     * */
    private String selfBeauty;
    /**
     * 카테고리 호텔링
     * */
    private String hoteling;
    /**
     * 카테고리 놀이터
     * */
    private String playground;
    /**
     * 카테고리 무료주차
     * */
    private String freeParking;
    /**
     * 카테고리 와이파이
     * */
    private String wiFi;

    private String gender;
    private String variety;
    private Date birthday;
    private String weight;
    private String neutered;
    private String animalHospital;
    private String referenceInfo;

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
     * 운영시간 요일
     * */
    private String day;

    /**
     * 자격명
     * */
    private String certificateName;
}
