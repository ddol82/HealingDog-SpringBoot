package com.healing.healingdog.mypage.model.dto;

public class ChecklistDTO {
    private int checklistCode;
    private int mypetCode;
    private String strangerReaction;
    private String skinship;
    private String walkLineReaction;
    private String walkAttention;
    private String walkFoot;
    private String healthPrecautions;
    private String rabies;
    private String synthesisVaccine;
    private String corona;
    private String kennelcorp;
    private String unvaccinated;
    private String heartworm;
    private String ectoparasite;

    public ChecklistDTO() {
    }

    public ChecklistDTO(int checklistCode, int mypetCode, String strangerReaction, String skinship, String walkLineReaction, String walkAttention, String walkFoot, String healthPrecautions, String rabies, String synthesisVaccine, String corona, String kennelcorp, String unvaccinated, String heartworm, String ectoparasite) {
        this.checklistCode = checklistCode;
        this.mypetCode = mypetCode;
        this.strangerReaction = strangerReaction;
        this.skinship = skinship;
        this.walkLineReaction = walkLineReaction;
        this.walkAttention = walkAttention;
        this.walkFoot = walkFoot;
        this.healthPrecautions = healthPrecautions;
        this.rabies = rabies;
        this.synthesisVaccine = synthesisVaccine;
        this.corona = corona;
        this.kennelcorp = kennelcorp;
        this.unvaccinated = unvaccinated;
        this.heartworm = heartworm;
        this.ectoparasite = ectoparasite;
    }

    public int getChecklistCode() {
        return checklistCode;
    }

    public void setChecklistCode(int checklistCode) {
        this.checklistCode = checklistCode;
    }

    public int getMypetCode() {
        return mypetCode;
    }

    public void setMypetCode(int mypetCode) {
        this.mypetCode = mypetCode;
    }

    public String getStrangerReaction() {
        return strangerReaction;
    }

    public void setStrangerReaction(String strangerReaction) {
        this.strangerReaction = strangerReaction;
    }

    public String getSkinship() {
        return skinship;
    }

    public void setSkinship(String skinship) {
        this.skinship = skinship;
    }

    public String getWalkLineReaction() {
        return walkLineReaction;
    }

    public void setWalkLineReaction(String walkLineReaction) {
        this.walkLineReaction = walkLineReaction;
    }

    public String getWalkAttention() {
        return walkAttention;
    }

    public void setWalkAttention(String walkAttention) {
        this.walkAttention = walkAttention;
    }

    public String getWalkFoot() {
        return walkFoot;
    }

    public void setWalkFoot(String walkFoot) {
        this.walkFoot = walkFoot;
    }

    public String getHealthPrecautions() {
        return healthPrecautions;
    }

    public void setHealthPrecautions(String healthPrecautions) {
        this.healthPrecautions = healthPrecautions;
    }

    public String getRabies() {
        return rabies;
    }

    public void setRabies(String rabies) {
        this.rabies = rabies;
    }

    public String getSynthesisVaccine() {
        return synthesisVaccine;
    }

    public void setSynthesisVaccine(String synthesisVaccine) {
        this.synthesisVaccine = synthesisVaccine;
    }

    public String getCorona() {
        return corona;
    }

    public void setCorona(String corona) {
        this.corona = corona;
    }

    public String getKennelcorp() {
        return kennelcorp;
    }

    public void setKennelcorp(String kennelcorp) {
        this.kennelcorp = kennelcorp;
    }

    public String getUnvaccinated() {
        return unvaccinated;
    }

    public void setUnvaccinated(String unvaccinated) {
        this.unvaccinated = unvaccinated;
    }

    public String getHeartworm() {
        return heartworm;
    }

    public void setHeartworm(String heartworm) {
        this.heartworm = heartworm;
    }

    public String getEctoparasite() {
        return ectoparasite;
    }

    public void setEctoparasite(String ectoparasite) {
        this.ectoparasite = ectoparasite;
    }

    @Override
    public String toString() {
        return "ChecklistDTO{" +
                "checklistCode=" + checklistCode +
                ", mypetCode=" + mypetCode +
                ", strangerReaction='" + strangerReaction + '\'' +
                ", skinship='" + skinship + '\'' +
                ", walkLineReaction='" + walkLineReaction + '\'' +
                ", walkAttention='" + walkAttention + '\'' +
                ", walkFoot='" + walkFoot + '\'' +
                ", healthPrecautions='" + healthPrecautions + '\'' +
                ", rabies='" + rabies + '\'' +
                ", synthesisVaccine='" + synthesisVaccine + '\'' +
                ", corona='" + corona + '\'' +
                ", kennelcorp='" + kennelcorp + '\'' +
                ", unvaccinated='" + unvaccinated + '\'' +
                ", heartworm='" + heartworm + '\'' +
                ", ectoparasite='" + ectoparasite + '\'' +
                '}';
    }
}
