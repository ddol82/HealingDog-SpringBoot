package com.healing.healingdog.healingcare.model.dto;

public class HealingCareDTO {

    private int healingCareCode;
    private int userCode;
    private int petSitterCode;
    private int healingCareFareCode;
    private int healingCareReservationCode;
    private String visitWay;
    private String requestTerm;

    public HealingCareDTO() {}

    public HealingCareDTO(int healingCareCode, int userCode, int petSitterCode, int healingCareFareCode, int healingCareReservationCode, String visitWay, String requestTerm) {
        this.healingCareCode = healingCareCode;
        this.userCode = userCode;
        this.petSitterCode = petSitterCode;
        this.healingCareFareCode = healingCareFareCode;
        this.healingCareReservationCode = healingCareReservationCode;
        this.visitWay = visitWay;
        this.requestTerm = requestTerm;
    }

    public int getHealingCareCode() {
        return healingCareCode;
    }

    public void setHealingCareCode(int healingCareCode) {
        this.healingCareCode = healingCareCode;
    }

    public int getUserCode() {
        return userCode;
    }

    public void setUserCode(int userCode) {
        this.userCode = userCode;
    }

    public int getPetSitterCode() {
        return petSitterCode;
    }

    public void setPetSitterCode(int petSitterCode) {
        this.petSitterCode = petSitterCode;
    }

    public int getHealingCareFareCode() {
        return healingCareFareCode;
    }

    public void setHealingCareFareCode(int healingCareFareCode) {
        this.healingCareFareCode = healingCareFareCode;
    }

    public int getHealingCareReservationCode() {
        return healingCareReservationCode;
    }

    public void setHealingCareReservationCode(int healingCareReservationCode) {
        this.healingCareReservationCode = healingCareReservationCode;
    }

    public String getVisitWay() {
        return visitWay;
    }

    public void setVisitWay(String visitWay) {
        this.visitWay = visitWay;
    }

    public String getRequestTerm() {
        return requestTerm;
    }

    public void setRequestTerm(String requestTerm) {
        this.requestTerm = requestTerm;
    }

    @Override
    public String toString() {
        return "HealingCareDTO{" +
                "healingCareCode=" + healingCareCode +
                ", userCode=" + userCode +
                ", petSitterCode=" + petSitterCode +
                ", healingCareFareCode=" + healingCareFareCode +
                ", healingCareReservationCode=" + healingCareReservationCode +
                ", visitWay='" + visitWay + '\'' +
                ", requestTerm='" + requestTerm + '\'' +
                '}';
    }
}
