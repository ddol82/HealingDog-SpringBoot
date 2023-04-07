package com.healing.healingdog.healingcare.model.dto;

public class PetSitterDTO {

    private int petSitterCode;
    private int providerCode;
    private String address;
    private String simpleIntro;
    private String selfIntro;
    private String blackList;

    public PetSitterDTO() {}

    public PetSitterDTO(int petSitterCode, int providerCode, String address, String simpleIntro, String selfIntro, String blackList) {
        this.petSitterCode = petSitterCode;
        this.providerCode = providerCode;
        this.address = address;
        this.simpleIntro = simpleIntro;
        this.selfIntro = selfIntro;
        this.blackList = blackList;
    }

    public int getPetSitterCode() {
        return petSitterCode;
    }

    public void setPetSitterCode(int petSitterCode) {
        this.petSitterCode = petSitterCode;
    }

    public int getProviderCode() {
        return providerCode;
    }

    public void setProviderCode(int providerCode) {
        this.providerCode = providerCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSimpleIntro() {
        return simpleIntro;
    }

    public void setSimpleIntro(String simpleIntro) {
        this.simpleIntro = simpleIntro;
    }

    public String getSelfIntro() {
        return selfIntro;
    }

    public void setSelfIntro(String selfIntro) {
        this.selfIntro = selfIntro;
    }

    public String getBlackList() {
        return blackList;
    }

    public void setBlackList(String blackList) {
        this.blackList = blackList;
    }

    @Override
    public String toString() {
        return "PetSitterDTO{" +
                "petSitterCode=" + petSitterCode +
                ", providerCode=" + providerCode +
                ", address='" + address + '\'' +
                ", simpleIntro='" + simpleIntro + '\'' +
                ", selfIntro='" + selfIntro + '\'' +
                ", blackList='" + blackList + '\'' +
                '}';
    }
}
