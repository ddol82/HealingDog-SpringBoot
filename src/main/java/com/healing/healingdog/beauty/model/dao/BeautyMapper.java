package com.healing.healingdog.beauty.model.dao;

import com.healing.healingdog.beauty.model.dto.BeautyDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BeautyMapper {
    int registerBeautyReservation(BeautyDTO beautyDTO);
}
