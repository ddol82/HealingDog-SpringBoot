package com.healing.healingdog.beauty.model.dao;

import com.healing.healingdog.beauty.model.dto.BeautyDTO;
import com.healing.healingdog.beauty.model.dto.CommonDTO;
import com.healing.healingdog.common.file.model.dto.CertificatesDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface BeautyManageMapper {

    BeautyDTO selectBeautyInfo(int providerCode);

    CommonDTO selectBeautyTimes(int providerCode);

    List<BeautyDTO> selectBeautyReservation(int providerCode);

    int registerBeautyInfo(BeautyDTO beautyDTO);

    int registerBeautyCertificates(CertificatesDTO certificatesDTO);

    int registerBeautyTimes(CommonDTO commonDTO);

    int updateBeautyInfo(BeautyDTO beautyDTO);

    int updateBeautyCategories(BeautyDTO beautyDTO);

    int updateCertificates(CertificatesDTO certificatesDTO);

    int updateBeautyTimes(CommonDTO commonDTO);

    int updateBeautyReservation(BeautyDTO beautyDTO);

    int deleteBeautyInfo(int providerCode);

    int deleteBeautyTimes(int providerCode);

}

