package com.healing.healingdog.login.model.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class ProviderDTO implements UserDetails {
    private int providerCode;
    private String name;
    private String email;
    private String providerPassword;
    private String phone;
    private String beautyApproval;
    private String petsitterApproval;
    private String trainersApproval;
    private String kindergardenApproval;
    private String boardingApproval;
    private String blacklist;
    private String role;

    public ProviderDTO() {
    }

    public ProviderDTO(int providerCode, String name, String email, String providerPassword, String phone, String beautyApproval, String petsitterApproval, String trainersApproval, String kindergardenApproval, String boardingApproval,String blacklist, String role, Collection<? extends GrantedAuthority> authorities) {
        this.providerCode = providerCode;
        this.name = name;
        this.email = email;
        this.providerPassword = providerPassword;
        this.phone = phone;
        this.beautyApproval = beautyApproval;
        this.petsitterApproval = petsitterApproval;
        this.trainersApproval = trainersApproval;
        this.kindergardenApproval = kindergardenApproval;
        this.boardingApproval = boardingApproval;
        this.blacklist = blacklist;
        this.role = role;
        this.authorities = authorities;
    }

    public int getProviderCode() {
        return providerCode;
    }

    public void setProviderCode(int providerCode) {
        this.providerCode = providerCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProviderPassword() {
        return providerPassword;
    }

    public void setProviderPassword(String providerPassword) {
        this.providerPassword = providerPassword;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBeautyApproval() {
        return beautyApproval;
    }

    public void setBeautyApproval(String beautyApproval) {
        this.beautyApproval = beautyApproval;
    }

    public String getPetsitterApproval() {
        return petsitterApproval;
    }

    public void setPetsitterApproval(String petsitterApproval) {
        this.petsitterApproval = petsitterApproval;
    }

    public String getTrainersApproval() {
        return trainersApproval;
    }

    public void setTrainersApproval(String trainersApproval) {
        this.trainersApproval = trainersApproval;
    }

    public String getKindergardenApproval() {
        return kindergardenApproval;
    }

    public void setKindergardenApproval(String kindergardenApproval) {
        this.kindergardenApproval = kindergardenApproval;
    }

    public String getBoardingApproval() {
        return boardingApproval;
    }

    public void setBoardingApproval(String boardingApproval) {
        this.boardingApproval = boardingApproval;
    }

    public String getBlacklist() {
        return blacklist;
    }

    public void setBlacklist(String blacklist) {
        this.blacklist = blacklist;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }


    // 이하 코드는 security 를 위한 용도
    private Collection<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.providerPassword;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "ProviderDTO{" +
                "providerCode=" + providerCode +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", providerPassword='" + providerPassword + '\'' +
                ", phone='" + phone + '\'' +
                ", beautyApproval='" + beautyApproval + '\'' +
                ", petsitterApproval='" + petsitterApproval + '\'' +
                ", trainersApproval='" + trainersApproval + '\'' +
                ", kindergardenApproval='" + kindergardenApproval + '\'' +
                ", boardingApproval='" + boardingApproval + '\'' +
                ", blacklist='" + blacklist + '\'' +
                ", role='" + role + '\'' +
                ", authorities=" + authorities +
                '}';
    }
}
