package com.healing.healingdog.boarding.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardingBookingDTO {

    private int boardingBookingCode;
    private int boardingServiceCode;
    private int userCode;
    private int mypetCode;
    private Timestamp bookingDate;
    private String boardingCategory;
    private Timestamp checkIn ;
    private Timestamp checkOut;
    private int payment;
}
