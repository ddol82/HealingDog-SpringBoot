package com.healing.healingdog.boarding.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardingServiceDTO {
    // boarding-service table
    private int boardingServiceCode;
    private int providerCode;
    private String title;
    private String address;
    private String hashtag;
    private String introduce;
    private int daysCostS;
    private int daysCostM;
    private int daysCostL;
    private int timeCostS;
    private int timeCostM;
    private int timeCostL;
    private String state;

}
