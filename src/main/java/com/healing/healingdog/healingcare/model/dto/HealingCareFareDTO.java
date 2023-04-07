package com.healing.healingdog.healingcare.model.dto;

public class HealingCareFareDTO {

    private int healingCareFareCode;
    private String careTime;
    private String careFare;

    public HealingCareFareDTO() {}

    public HealingCareFareDTO(int healingCareFareCode, String careTime, String careFare) {
        this.healingCareFareCode = healingCareFareCode;
        this.careTime = careTime;
        this.careFare = careFare;
    }

    public int getHealingCareFareCode() {
        return healingCareFareCode;
    }

    public void setHealingCareFareCode(int healingCareFareCode) {
        this.healingCareFareCode = healingCareFareCode;
    }

    public String getCareTime() {
        return careTime;
    }

    public void setCareTime(String careTime) {
        this.careTime = careTime;
    }

    public String getCareFare() {
        return careFare;
    }

    public void setCareFare(String careFare) {
        this.careFare = careFare;
    }

    @Override
    public String toString() {
        return "HealingCareFareDTO{" +
                "healingCareFareCode=" + healingCareFareCode +
                ", careTime='" + careTime + '\'' +
                ", careFare='" + careFare + '\'' +
                '}';
    }
}
