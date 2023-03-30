package com.healing.healingdog.mypage.model.dto;

import java.sql.Date;

public class MypetDTO {
    private int mypetCode;
    private int userCode;
    private String name;
    private String gender;
    private String variety;
    private Date birthday;
    private String weight;
    private String neutered;
    private String animalHospital;
    private String referenceInfo;

    public MypetDTO() {
    }

    public MypetDTO(int mypetCode, int userCode, String name, String gender, String variety, Date birthday, String weight, String neutered, String animalHospital, String referenceInfo) {
        this.mypetCode = mypetCode;
        this.userCode = userCode;
        this.name = name;
        this.gender = gender;
        this.variety = variety;
        this.birthday = birthday;
        this.weight = weight;
        this.neutered = neutered;
        this.animalHospital = animalHospital;
        this.referenceInfo = referenceInfo;
    }

    public int getMypetCode() {
        return mypetCode;
    }

    public void setMypetCode(int mypetCode) {
        this.mypetCode = mypetCode;
    }

    public int getUserCode() {
        return userCode;
    }

    public void setUserCode(int userCode) {
        this.userCode = userCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getVariety() {
        return variety;
    }

    public void setVariety(String variety) {
        this.variety = variety;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getNeutered() {
        return neutered;
    }

    public void setNeutered(String neutered) {
        this.neutered = neutered;
    }

    public String getAnimalHospital() {
        return animalHospital;
    }

    public void setAnimalHospital(String animalHospital) {
        this.animalHospital = animalHospital;
    }

    public String getReferenceInfo() {
        return referenceInfo;
    }

    public void setReferenceInfo(String referenceInfo) {
        this.referenceInfo = referenceInfo;
    }

    @Override
    public String toString() {
        return "MypetDTO{" +
                "mypetCode=" + mypetCode +
                ", userCode=" + userCode +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", variety='" + variety + '\'' +
                ", birthday=" + birthday +
                ", weight='" + weight + '\'' +
                ", neutered='" + neutered + '\'' +
                ", animalHospital='" + animalHospital + '\'' +
                ", referenceInfo='" + referenceInfo + '\'' +
                '}';
    }
}

