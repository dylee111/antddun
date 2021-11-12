package com.ds.antddun.controller;

import com.ds.antddun.config.auth.PrincipalDetails;
import com.ds.antddun.dto.SosoBoardDTO;
import com.ds.antddun.entity.SosoCategory;
import com.ds.antddun.entity.SosoJobBoard;
import com.ds.antddun.service.SosoCateService;
import com.ds.antddun.service.SosoJobService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@Log4j2
@RequestMapping("/member")
@SessionAttributes("member")
public class SosoBoardController {

    @Autowired
    private SosoJobService sosoJobService;

    @Autowired
    private SosoCateService cateService;

    /*
     * 메인 페이지
     * */
    @GetMapping("/sosojob/main")
    public String mainRead(Model model,
                           @AuthenticationPrincipal PrincipalDetails principalDetails) {

        List<SosoCategory> list = cateService.getList();
        Map<String, List<SosoJobBoard>> getCateList = new HashMap<>();
        List<Integer> cateNoList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            getCateList.put(list.get(i).getSosoCateName(),
                    sosoJobService.getListByCategory(list.get(i).getSosoCateName()));
            cateNoList.add(list.get(i).getCateNo());
        }

        log.info("MAPMAP" + getCateList);
        log.info("CATENO" + cateNoList);

        model.addAttribute("category", getCateList);
        model.addAttribute("cateNoList", cateNoList);

        return "sosojob/sosojobMain";
    }

    /*
     * 글 등록
     * */
    @GetMapping("/sosojob/register")
    public String register(Model model) {

        log.info("CATELIST >>" + cateService.getList().toString());
        model.addAttribute("category", cateService.getList());

        return "/sosojob/register";
    }

    @PostMapping("/sosojob/register")
    public String register(SosoBoardDTO sosoBoardDTO, @AuthenticationPrincipal PrincipalDetails principal) {

        sosoJobService.register(sosoBoardDTO, principal.getMember());

        return "/sosojob/sosojobMain";
    }

    /*
     * 글 수정
     * */
    @GetMapping("/sosojob/modify")
    public String modify(Model model) {

        log.info("CATELIST >>" + cateService.getList().toString());
        model.addAttribute("category", cateService.getList());

        return "/sosojob/modify";
    }

    @PostMapping("/sosojob/modify")
    public String modify(SosoBoardDTO sosoBoardDTO, @AuthenticationPrincipal PrincipalDetails principal) {

        sosoJobService.register(sosoBoardDTO, principal.getMember());

        return "/sosojob/sosojobMain";
    }

    @GetMapping("/sosojob/list/{category}")
    public String cateList(@PathVariable("category")int categoryNo, Model model) {
        log.info("CATEGORYLIST======================");
        List<SosoCategory> list = cateService.getList();
        for (int i = 0; i < list.size(); i++) {
            categoryNo = list.get(i).getCateNo();
        }

        return "/sosojob/sosoList/" + categoryNo;
    }

}
