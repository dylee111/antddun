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

    @Test
    public void readCate() {
        System.out.println(sosoCategoryRepository.findById(1).get().getCateNo());

    }

    @Test
    public void testRead() {

        System.out.println("DTOLIST>>>"+sosoJobService.getList(1).getDtoList());
        System.out.println("SIZE>>>"+sosoJobService.getList(1).getSize());
        System.out.println("PAGELIST>>>"+sosoJobService.getList(1).getPageList());
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


}