package com.healing.healingdog.beauty.model.dao;

import com.healing.healingdog.beauty.model.dto.BeautyDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BeautyManageMapper {

    BeautyDTO selectBeautyInfo(int providerCode);

    int registerBeautyInfo(BeautyDTO beautyDTO);

    int updateBeautyInfo(BeautyDTO beautyDTO);

    int deleteBeautyInfo(int providerCode);
}
