package com.ds.antddun.controller;

import com.ds.antddun.config.auth.PrincipalDetails;
import com.ds.antddun.dto.SosoBoardDTO;
import com.ds.antddun.dto.SosoCategoryDTO;
import com.ds.antddun.dto.SosoPageRequestDTO;
import com.ds.antddun.entity.SosoCategory;
import com.ds.antddun.entity.SosoJobBoard;
import com.ds.antddun.service.SosoCateService;
import com.ds.antddun.service.SosoJobService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

        List<SosoCategory> list = cateService.getCateList(); // 카테고리 리스트 호출
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

        log.info("CATELIST >>" + cateService.getCateList().toString());
        model.addAttribute("category", cateService.getCateList());

        return "/sosojob/register";
    }

    @PostMapping("/sosojob/register")
    public String register(SosoBoardDTO sosoBoardDTO,
                           SosoCategoryDTO sosoCategoryDTO,
                           @AuthenticationPrincipal PrincipalDetails principal) {

        log.info("BEFORE" + sosoCategoryDTO);
        sosoJobService.register(sosoBoardDTO, sosoCategoryDTO, principal.getMember());
        log.info("AFTER" + sosoCategoryDTO);

        return "/sosojob/sosojobMain";
    }

    /*
     * 글 수정
     * */
    @GetMapping("/sosojob/modify")
    public String modify(Model model) {

        log.info("CATELIST >>" + cateService.getCateList().toString());
        model.addAttribute("category", cateService.getCateList());

        return "/sosojob/modify";
    }

    @PostMapping("/sosojob/modify")
    public String modify(SosoBoardDTO sosoBoardDTO, @AuthenticationPrincipal PrincipalDetails principal) {

//        sosoJobService.register(sosoBoardDTO, principal.getMember());

        return "/sosojob/sosojobMain";
    }

    @GetMapping("/sosojob/list/{category}")
    public String cateList(@PathVariable("category") int categoryNo, Model model, SosoPageRequestDTO sosoPageRequestDTO) {

        List<SosoCategory> list = cateService.getCateList(); // 카테고리 리스트(cateNo / cateName)
        List<Integer> cateNoList = new ArrayList<>(); // 카테고리 No. 담기 위한 LIST

        Map<String, List<SosoJobBoard>> getCateList = new HashMap<>(); // key(카테고리 이름):value(카테고리 별 리스트)

        getCateList.put(list.get(categoryNo - 1).getSosoCateName(),
                sosoJobService.getListByCategory(list.get(categoryNo - 1).getSosoCateName()));
        cateNoList.add(categoryNo);

//        log.info("PAGING RESULT >>>" + sosoPageRequestDTO + "//" + categoryNo);
        model.addAttribute("cate", sosoJobService.getList(categoryNo, sosoPageRequestDTO));
//        model.addAttribute("cate", getCateList);
        model.addAttribute("cateName", list);

        log.info("LIST>>>"+list);
        log.info("getCateList>>>"+getCateList);

        return "/sosojob/sosoList";
    }

    @GetMapping("/sosojob/list/read")
    public void sosoRead(Long sosoNo, Model model) {
        SosoBoardDTO sosoBoardDTO = sosoJobService.read(sosoNo);
        model.addAttribute("read", sosoBoardDTO);
    }
}
