package com.ds.antddun.service;

import com.ds.antddun.entity.JayuCategory;
import com.ds.antddun.repository.JayuCateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor

public class JayuCateServiceImpl implements JayuCateService {

    private final JayuCateRepository jayuCateRepository;

    @Override
    public List<JayuCategory> getCateList() {
        List<JayuCategory> result = jayuCateRepository.findAll();

        return result;
    }
}