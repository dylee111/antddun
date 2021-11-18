package com.ds.antddun.service;

import com.ds.antddun.entity.SosoCategory;
import com.ds.antddun.repository.SosoCategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class SosoCateServiceImpl implements SosoCateService{

    private final SosoCategoryRepository sosoCategoryRepository;

    @Override
    public List<SosoCategory> getCateList() {
        List<SosoCategory> result = sosoCategoryRepository.findAll();

        return result;
    }
}
