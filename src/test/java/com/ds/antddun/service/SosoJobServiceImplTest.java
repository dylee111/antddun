package com.ds.antddun.service;

import com.ds.antddun.dto.*;
import com.ds.antddun.entity.SosoJobBoard;
import com.ds.antddun.repository.SosoBoardRepository;
import com.ds.antddun.repository.SosoCategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@SpringBootTest
class SosoJobServiceImplTest {

    @Autowired
    private SosoJobService sosoJobService;

    @Autowired
    private SosoBoardRepository sosoBoardRepository;

    @Autowired
    private SosoCategoryRepository sosoCategoryRepository;

    @Test
    public void readCate() {
        System.out.println(sosoCategoryRepository.findById(1).get().getCateNo());

    }

    @Test
    public void testRead() {
        Pageable pageable = PageRequest.of(0, 12);

        Page<SosoJobBoard> result = sosoBoardRepository.getPageByCategoryNo(1,pageable);

        System.out.println(result);
        System.out.println(result.getTotalElements());
        System.out.println(result.getTotalPages());

    }

    @Test
    void getListByCategoryTest() {


        System.out.println(sosoJobService.getListByCategory("IT"));
    }

    @Test
    void getList() {
        SosoPageRequestDTO sosoPageRequestDTO = SosoPageRequestDTO.builder().page(1).size(12).build();
        System.out.println("DTODTO" + sosoPageRequestDTO);

        SosoPageResultDTO<SosoBoardDTO, SosoJobBoard> resultDTO = sosoJobService.getList(1,sosoPageRequestDTO);
        System.out.println("RESULT >>> " + resultDTO);

        for (SosoBoardDTO sosoBoardDTO : resultDTO.getDtoList()) {
            System.out.println("PAGINATION>>>"+ sosoBoardDTO);
        }
    }


}