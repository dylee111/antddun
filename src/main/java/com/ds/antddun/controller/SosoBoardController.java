package com.ds.antddun.controller;

import com.ds.antddun.config.auth.PrincipalDetails;
import com.ds.antddun.dto.SosoBoardDTO;
import com.ds.antddun.dto.SosoCategoryDTO;
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

        List<SosoCategory> list = cateService.getList(); // 카테고리 리스트 호출
        List<Integer> cateNoList = new ArrayList<>(); // 카테고리 No. 담을 리스트

        Map<String, List<SosoJobBoard>> getCateList = new HashMap<>(); // key(카테고리 이름):value(카테고리 별 리스트)

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
    public String register(SosoBoardDTO sosoBoardDTO,
                           SosoCategoryDTO sosoCategoryDTO,
                           @AuthenticationPrincipal PrincipalDetails principal) {

        log.info("BEFORE"+ sosoCategoryDTO);
        sosoJobService.register(sosoBoardDTO, sosoCategoryDTO ,principal.getMember());
        log.info("AFTER"+ sosoCategoryDTO);

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

//        sosoJobService.register(sosoBoardDTO, principal.getMember());

        return "/sosojob/sosojobMain";
    }

    @GetMapping("/sosojob/list/{category}")
    public String cateList(@PathVariable("category")int categoryNo, Model model) {
        log.info("CATEGORYLIST======================");

        List<SosoCategory> list = cateService.getList(); // 카테고리 리스트
        List<Integer> cateNoList = new ArrayList<>(); // 카테고리 No. 담기 위한 LIST

        for (int i = 0; i < list.size(); i++) {
            categoryNo = list.get(i).getCateNo();
            cateNoList.add(categoryNo);
            if (categoryNo == list.get(i).getCateNo()) {
                model.addAttribute("cate", sosoJobService.getListByCategoryNo(categoryNo));
            }
        }

        model.addAttribute("cate", sosoJobService.getListByCategoryNo(cateNoList.get(0)));
//        model.addAttribute("cate", sosoJobService.getListByCategoryNo(cateNoList.get(1)));
//        model.addAttribute("cate", sosoJobService.getListByCategoryNo(cateNoList.get(2)));
//        model.addAttribute("cate", sosoJobService.getListByCategoryNo(cateNoList.get(3)));
//        model.addAttribute("cate", sosoJobService.getListByCategoryNo(cateNoList.get(4)));
//        model.addAttribute("cate", sosoJobService.getListByCategoryNo(cateNoList.get(5)));
//        model.addAttribute("cate", sosoJobService.getListByCategoryNo(cateNoList.get(6)));
//        model.addAttribute("cate", sosoJobService.getListByCategoryNo(cateNoList.get(7)));

        log.info("CATENOLIST>>>"+cateNoList);
        return "/sosojob/sosoList";
    }

}
