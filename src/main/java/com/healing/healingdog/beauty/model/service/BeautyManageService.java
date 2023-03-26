package com.healing.healingdog.beauty.model.service;

import com.healing.healingdog.beauty.model.dao.BeautyManageMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BeautyManageService {
    private final BeautyManageMapper beautyManageMapper;
}
