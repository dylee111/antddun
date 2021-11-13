package com.ds.antddun.service;

import com.ds.antddun.dto.PageRequestDTO;
import com.ds.antddun.dto.PageResultDTO;
import com.ds.antddun.dto.SosoBoardDTO;
import com.ds.antddun.entity.SosoJobBoard;
import com.ds.antddun.repository.SosoBoardRepository;
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

//    @Test
//    void register() {
//
//
//        IntStream.rangeClosed(1, 50).forEach(i -> {
//
//                SosoBoardDTO sosoBoardDTO = SosoBoardDTO.builder()
//                        .title("홈페이지 제작합니다" + i)
//                        .category(i < 25 ? "IT" : "마케팅")
//                        .content("홈페이지 제작" + i)
//                        .ddun(10000)
//                        .mno(1L)
//                        .build();
//
////            System.out.println(sosoJobService.register(sosoBoardDTO));
//        });
//    }


    @Test
    public void testRead() {
        Pageable pageable = PageRequest.of(0, 12);

        Page<SosoJobBoard> result = sosoBoardRepository.findAll(pageable);

        System.out.println(result);

    }

    @Test
    void getListByCategoryTest() {


        System.out.println(sosoJobService.getListByCategory("IT"));
    }

    @Test
    void getList() {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(1).size(10).build();

        PageResultDTO<SosoBoardDTO, SosoJobBoard> resultDTO = sosoJobService.getList(pageRequestDTO);
    }


}