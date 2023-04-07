package com.healing.healingdog.healingcare.model.dto;

import java.sql.Date;

public class HealingCareReservationDTO {

    private int healingCareReservationCode;
    private Date date;
    private String day;
    private String startTime;
    private String endTime;
    private String visitTime;
    private String reservationState;

    public HealingCareReservationDTO() {}

    public HealingCareReservationDTO(int healingCareReservationCode, Date date, String day, String startTime, String endTime, String visitTime, String reservationState) {
        this.healingCareReservationCode = healingCareReservationCode;
        this.date = date;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.visitTime = visitTime;
        this.reservationState = reservationState;
    }

    public int getHealingCareReservationCode() {
        return healingCareReservationCode;
    }

    public void setHealingCareReservationCode(int healingCareReservationCode) {
        this.healingCareReservationCode = healingCareReservationCode;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(String visitTime) {
        this.visitTime = visitTime;
    }

    public String getReservationState() {
        return reservationState;
    }

    public void setReservationState(String reservationState) {
        this.reservationState = reservationState;
    }

    @Override
    public String toString() {
        return "HealingCareReservationDTO{" +
                "healingCareReservationCode=" + healingCareReservationCode +
                ", date=" + date +
                ", day='" + day + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", visitTime='" + visitTime + '\'' +
                ", reservationState='" + reservationState + '\'' +
                '}';
    }
}
