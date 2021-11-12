package com.ds.antddun.controller;

import com.ds.antddun.config.auth.PrincipalDetails;
import com.ds.antddun.dto.SosoBoardDTO;
import com.ds.antddun.entity.SosoCategory;
import com.ds.antddun.service.SosoCateService;
import com.ds.antddun.service.SosoJobService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;


@Controller
@Log4j2
@RequestMapping("/member")
@SessionAttributes("member")
public class SosoBoardController {

    @Autowired
    private SosoJobService sosoJobService;

    @Autowired
    private SosoCateService cateService;

    @GetMapping("/sosojob/main")
    public String mainRead(Model model,
                           @AuthenticationPrincipal PrincipalDetails principalDetails) {

        String cate = "";
        List<SosoCategory> list = cateService.getList();
        for (int i = 0; i < list.size(); i++) {
            cate = list.get(i).getSosoCateName();
            log.info("LIST_NO>>>"+list.get(i).getCateNo());
            log.info("LISTLIST>>"+sosoJobService.getListByCategory(list.get(i).getSosoCateName()));
            // 마지막 인덱스에 데이터가 없으니깐 뿌리는 데이터가 없네..........
        }

        model.addAttribute("category", sosoJobService.getListByCategory(list.get(0).getSosoCateName()));
        model.addAttribute("soso", principalDetails.getMember());
        return "sosojob/sosojobMain";
    }

    @GetMapping("/sosojob/register")
    public String register(Model model) {

        log.info("CATELIST >>" + cateService.getList().toString());
        model.addAttribute("category", cateService.getList());

        return "/sosojob/register";
    }

    @PostMapping("/sosojob/confirm")
    public String register(SosoBoardDTO sosoBoardDTO, @AuthenticationPrincipal PrincipalDetails principal) {

        sosoJobService.register(sosoBoardDTO, principal.getMember());

        return "/sosojob/sosojobMain";
    }
}
