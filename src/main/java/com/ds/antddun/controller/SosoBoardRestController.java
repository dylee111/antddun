package com.ds.antddun.controller;

import com.ds.antddun.dto.SosoBoardDTO;
import com.ds.antddun.dto.SosoPageRequestDTO;
import com.ds.antddun.entity.SosoCategory;
import com.ds.antddun.entity.SosoJobBoard;
import com.ds.antddun.service.SosoCateService;
import com.ds.antddun.service.SosoJobService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/member")
@Log4j2
@RequiredArgsConstructor
public class SosoBoardRestController {

    private final SosoJobService sosoJobService;
    private final SosoCateService sosoCateService;

    @GetMapping(value = "/sosojob/list/{category}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SosoJobBoard>> cateList(@PathVariable("category")int categoryNo, Model model) {
        log.info("CATEGORYLIST======================");

        List<SosoCategory> list = sosoCateService.getList(); // 카테고리 리스트
        List<Integer> cateNoList = new ArrayList<>(); // 카테고리 No. 담기 위한 LIST


        for (int i = 0; i < list.size(); i++) {
            categoryNo = list.get(i).getCateNo();
            cateNoList.add(categoryNo);
//            if (categoryNo == list.get(i).getCateNo()) {
//                model.addAttribute("cate", sosoJobService.getListByCategoryNo(categoryNo));
//            }
        }

        // 마지막 번호만 찍힘....
//        model.addAttribute("cate", sosoJobService.getListByCategoryNo(cateNoList.get(0)));
//        model.addAttribute("cate", sosoJobService.getList(cateNoList.get(0)));

        log.info("CATENOLIST>>>"+cateNoList);
        log.info("CATENONONO>>>"+categoryNo);
        return new ResponseEntity<>(sosoJobService.getListByCategoryNo(categoryNo), HttpStatus.OK);
    }
}
