package com.healing.healingdog.common.file.model.dao;

import com.healing.healingdog.common.file.model.dto.ImageTableDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ImageMapper {
    List<ImageTableDTO> boardImageListLoadByRef(String refCode);
}
