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
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class SosoJobServiceImplTest {

    @Autowired
    private SosoJobService sosoJobService;

    @Autowired
    private SosoBoardRepository sosoBoardRepository;

    @Autowired
    private SosoCategoryRepository sosoCategoryRepository;

    @Autowired
    private SosoCateService sosoCateService;

    @Test
    public void readCate() {
        System.out.println(sosoCategoryRepository.findById(1).get().getCateNo());

    }

    @Test
    void getListByCategoryTest() {
        System.out.println(sosoJobService.getListByCategory("IT"));
    }

    @Test
    void getList() {
        // test ok
        SosoBoardDTO result = sosoJobService.read(1L);

        System.out.println("CONTENT>>>>"+result.getContent());
        System.out.println("MNO>>>>"+result.getMno());
        System.out.println("TITLE>>>>"+result.getJob());
        System.out.println("TITLE>>>>"+result.getCategoryName());
        System.out.println("TITLE>>>>"+result.getTitle());
        System.out.println("TITLE>>>>"+result.getFirstName());


    }

    @Test
    void 글하나읽기() {
        System.out.println(sosoBoardRepository.readBySosoNo(10L));
    }

//    @Test
//    void page() {
//
//        PageResultDTO<SosoBoardDTO, SosoJobBoard> resultDTO = sosoJobService.getList(1);
//        System.out.println(resultDTO.isNext());
//        System.out.println(resultDTO.getPage());
//        for (SosoBoardDTO sosoBoardDTO : resultDTO.getDtoList()) {
//            System.out.println(sosoBoardDTO);
//        }
//    }

    @Test
    void 카테고리로드() {
        System.out.println(sosoCateService.getCateList().get(0));
    }

//    @Test
//    void paging() {
//        System.out.println(sosoJobService.getList(1).getPage());
//        System.out.println(sosoJobService.getList(1).getTotalPage());
//    }

}