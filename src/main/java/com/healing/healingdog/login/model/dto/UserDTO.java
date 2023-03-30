package com.healing.healingdog.login.model.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class UserDTO implements UserDetails {
    private int userCode;
    private String name;
    private String email;
    private String userPassword;
    private String phone;
    private String nickname;
    private String address;
    private String simpleIntro;
    private String selfIntro;
    private char blacklist;
    private String role;

    public UserDTO() {
    }

    public UserDTO(int userCode, String name, String email, String userPassword, String phone, String nickname, String address, String simpleIntro, String selfIntro, char blacklist, String role, Collection<? extends GrantedAuthority> authorities) {
        this.userCode = userCode;
        this.name = name;
        this.email = email;
        this.userPassword = userPassword;
        this.phone = phone;
        this.nickname = nickname;
        this.address = address;
        this.simpleIntro = simpleIntro;
        this.selfIntro = selfIntro;
        this.blacklist = blacklist;
        this.role = role;
        this.authorities = authorities;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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

    public char getBlacklist() {
        return blacklist;
    }

    public void setBlacklist(char blacklist) {
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
        return this.userPassword;
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
        return "UserDto{" +
                "userCode=" + userCode +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", phone='" + phone + '\'' +
                ", nickname='" + nickname + '\'' +
                ", address='" + address + '\'' +
                ", simpleIntro='" + simpleIntro + '\'' +
                ", selfIntro='" + selfIntro + '\'' +
                ", blacklist=" + blacklist +
                ", role='" + role + '\'' +
                ", authorities=" + authorities +
                '}';
    }
}

