package com.healing.healingdog.common.file.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CertificatesDTO {
    private int certificateCode;
    private int providerCode;
    private int serviceCategoryCode;
    private String name;
    private String text;

}
