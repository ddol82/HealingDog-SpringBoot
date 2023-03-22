package com.healing.healingdog.boarding.dao;

import com.healing.healingdog.boarding.dto.BoardingServiceDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public class BoardingManageMapper {


    BoardingServiceDTO selectBoardingInfo(int providerCode);
}
