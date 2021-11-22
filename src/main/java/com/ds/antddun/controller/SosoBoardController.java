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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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

        model.addAttribute("category", getCateList);
        model.addAttribute("cateNoList", cateNoList);

        return "sosojob/sosojobMain";
    }

    /*
     * 글 등록
     * */
    @GetMapping("/sosojob/register")
    public String register(Model model) {

        model.addAttribute("category", cateService.getCateList());

        return "/sosojob/register";
    }

    @PostMapping("/sosojob/register")
    public String register(SosoBoardDTO sosoBoardDTO,
                           SosoCategoryDTO sosoCategoryDTO,
                           @AuthenticationPrincipal PrincipalDetails principal) {
        log.info("reg.DTO >>> " + sosoBoardDTO.getCategoryNo());
        sosoJobService.register(sosoBoardDTO, sosoCategoryDTO, principal.getMember());

//        return "/sosojob/sosojobMain";
        return "redirect:/member/sosojob/list/" + (sosoBoardDTO.getCategoryNo()+1);
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

    @GetMapping("/sosojob/sosoList/{category}")
    public ModelAndView cateList(@PathVariable("category") int categoryNo, Model model) {
        ModelAndView mv = new ModelAndView();

        mv.addObject("mvData", sosoJobService.getList(categoryNo));
        mv.setViewName("sosojob/sosoList");

        log.info("NEXT"+sosoJobService.getList(categoryNo).isNext());
        log.info("getDtoList"+sosoJobService.getList(categoryNo).getDtoList());
        log.info("getEnd"+sosoJobService.getList(categoryNo).getEnd());

        List<SosoCategory> list = cateService.getCateList(); // 카테고리 리스트(cateNo / cateName)
        List<Integer> cateNoList = new ArrayList<>(); // 카테고리 No. 담기 위한 LIST

        Map<String, List<SosoJobBoard>> getCateList = new HashMap<>(); // key(카테고리 이름):value(카테고리 별 리스트)

        getCateList.put(list.get(categoryNo - 1).getSosoCateName(),
                sosoJobService.getListByCategory(list.get(categoryNo - 1).getSosoCateName()));
        cateNoList.add(categoryNo);

//        model.addAttribute("cate", sosoJobService.getList(categoryNo));
        model.addAttribute("cateName", list);
        model.addAttribute("cateNum", cateNoList);

        return mv;
    }

    @GetMapping("/sosojob/list/read")
    public String sosoRead(Long sosoNo, Model model) {

        SosoBoardDTO sosoBoardDTO = sosoJobService.read(sosoNo);
        log.info("SOSODTO>>>" + sosoBoardDTO);
        model.addAttribute("read", sosoBoardDTO);
        return "/sosojob/sosoDetailView";
    }
}
