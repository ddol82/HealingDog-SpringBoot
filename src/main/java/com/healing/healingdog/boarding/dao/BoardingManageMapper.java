package com.healing.healingdog.boarding.dao;

import com.healing.healingdog.boarding.dto.BoardingServiceDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardingManageMapper {


    BoardingServiceDTO selectBoardingInfo(int providerCode);

    int registerBoardingInfo(BoardingServiceDTO boardingServiceDTO);

    int updateBoardingInfo(BoardingServiceDTO boardingServiceDTO);

    int deleteBoardingInfo(int providerCode);
}
