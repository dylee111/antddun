package com.ds.antddun.controller;

import com.ds.antddun.config.auth.PrincipalDetails;
import com.ds.antddun.dto.PageRequestDTO;
import com.ds.antddun.dto.SosoBoardDTO;
import com.ds.antddun.dto.SosoCategoryDTO;
import com.ds.antddun.dto.SosoPageRequestDTO;
import com.ds.antddun.entity.SosoCategory;
import com.ds.antddun.entity.SosoJobBoard;
import com.ds.antddun.service.DdunService;
import com.ds.antddun.service.SosoCateService;
import com.ds.antddun.service.SosoJobService;
import com.ds.antddun.service.SosoReplyService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@Log4j2
public class SosoBoardController {

    @Autowired
    private SosoJobService sosoJobService;

    @Autowired
    private SosoCateService cateService;

    @Autowired
    private SosoReplyService sosoReplyService;

    @Autowired
    private DdunService ddunService;

    /*메인 페이지*/
    @GetMapping("/sosojob/main")
    public String mainRead(Model model,
                           @AuthenticationPrincipal PrincipalDetails principalDetails) {

        List<SosoCategory> list = cateService.getCateList(); // 카테고리 리스트 호출
        List<Integer> cateNoList = new ArrayList<>(); // 카테고리 No. 담을 리스트

        Map<String, List<SosoJobBoard>> getCateList = new HashMap<>(); // key(카테고리 이름):value(카테고리 별 리스트)

        for (int i = 0; i < list.size(); i++) {
            getCateList.put(list.get(i).getSosoCateName(),
                    sosoJobService.getListLimit(list.get(i).getCateNo()));
//                    sosoJobService.getListByCategory(list.get(i).getSosoCateName()));

            cateNoList.add(list.get(i).getCateNo());
        }

        model.addAttribute("category", getCateList);
        model.addAttribute("cateNoList", cateNoList);
        model.addAttribute("cateName", list);

        return "sosojob/sosojobMain";
    }

    /*글 등록*/
    @GetMapping("/member/sosojob/register")
    public String register(Model model) {

        model.addAttribute("category", cateService.getCateList());

        return "/sosojob/register";
    }

    @PostMapping("/member/sosojob/register")
    public String register(SosoBoardDTO sosoBoardDTO,
                           SosoCategoryDTO sosoCategoryDTO,
                           @AuthenticationPrincipal PrincipalDetails principal) {
        log.info("reg.DTO >>> " + sosoBoardDTO.getCategoryNo());
        sosoJobService.register(sosoBoardDTO, sosoCategoryDTO, principal.getMember());

//        return "/sosojob/sosojobMain";
        return "redirect:/sosojob/sosoList/sosoCategory=" + (sosoBoardDTO.getCategoryNo()+1);
    }

    /* 글 수정 화면 */
    @GetMapping("/member/sosojob/modify/sosoNo={sosoNo}")
    public ModelAndView modify(@PathVariable("sosoNo")Long sosoNo, SosoBoardDTO sosoBoardDTO,
                         PageRequestDTO pageRequestDTO, Model model) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("sosojob/modify");

        sosoBoardDTO = sosoJobService.read(sosoNo);
        mv.addObject("category", cateService.getCateList());
        mv.addObject("modify", sosoBoardDTO);

        return mv;
    }
    /* 글 수정 */
    @PostMapping("/member/sosojob/modify/sosoNo={sosoNo}")
    public String modify(@PathVariable("sosoNo")Long sosoNo, SosoBoardDTO sosoBoardDTO) {

        sosoBoardDTO.setSosoNo(sosoNo);
        sosoBoardDTO.setTitle(sosoBoardDTO.getTitle());
        sosoBoardDTO.setContent(sosoBoardDTO.getContent());
        sosoBoardDTO.setCategoryName(sosoBoardDTO.getCategoryName());
        sosoBoardDTO.setDdun(sosoBoardDTO.getDdun());
        sosoJobService.modify(sosoBoardDTO);

        return "redirect:/member/sosojob/list/read?sosoNo="+sosoNo;
    }

    /* 글 삭제 */
    @GetMapping("/member/sosojob/delete/{sosoNo}")
    public String delete(@PathVariable("sosoNo") Long sosoNo, SosoBoardDTO sosoBoardDTO) {
        int categoryNo = sosoBoardDTO.getCategoryNo();

        sosoJobService.delete(sosoBoardDTO);

        return "redirect:/sosojob/sosoList/sosoCategory="+categoryNo;
    }

    /* 카테고리 별 페이징 */
    @GetMapping("/sosojob/sosoList/sosoCategory={category}")
    public ModelAndView cateList(@PathVariable("category") int categoryNo, Model model, SosoPageRequestDTO sosoPageRequestDTO) {
        ModelAndView mv = new ModelAndView();

        mv.addObject("mvData", sosoJobService.getList(categoryNo, sosoPageRequestDTO));
        mv.setViewName("sosojob/sosoList");

        List<SosoCategory> list = cateService.getCateList(); // 카테고리 리스트(cateNo / cateName)
        List<Integer> cateNoList = new ArrayList<>(); // 카테고리 No. 담기 위한 LIST

        cateNoList.add(categoryNo);

        model.addAttribute("cateList", list);
        model.addAttribute("cateNum", cateNoList);

        return mv;
    }

    @GetMapping("/sosojob/sosoList/sosoCategory={category}&keyword={keyword}")
    public ModelAndView cateListByKeyword(@PathVariable("category") int categoryNo,
                                          @PathVariable("keyword") String keyword,
                                          Model model,
                                          SosoPageRequestDTO sosoPageRequestDTO) {
        ModelAndView mv = new ModelAndView();

        mv.addObject("mvData", sosoJobService.getListByKeyword(categoryNo, keyword, sosoPageRequestDTO));
        mv.setViewName("sosojob/sosoList");

        List<SosoCategory> list = cateService.getCateList(); // 카테고리 리스트(cateNo / cateName)
        List<Integer> cateNoList = new ArrayList<>(); // 카테고리 No. 담기 위한 LIST

        cateNoList.add(categoryNo);

        model.addAttribute("requestDTO", sosoPageRequestDTO);
        model.addAttribute("cateList", list);
        model.addAttribute("cateNum", cateNoList);

        return mv;
    }

    /* 글 상세보기 */
    @GetMapping("/member/sosojob/list/read")
    public String sosoRead(Long sosoNo, Model model, @AuthenticationPrincipal PrincipalDetails principalDetails) {

        SosoBoardDTO sosoBoardDTO = sosoJobService.read(sosoNo);
        log.info("SOSODTO>>>" + sosoReplyService.getListBySosoNo(sosoNo));
        log.info("member>>>" + principalDetails.getMember().getMno());
        log.info("read>>>" + sosoBoardDTO.getMno());

        Long totalDdun = ddunService.totalAmountByMno(principalDetails.getMember().getMno());
        model.addAttribute("totalDdun", totalDdun);

        sosoJobService.updateCnt(sosoNo);
        model.addAttribute("read", sosoBoardDTO);
        model.addAttribute("writer", principalDetails.getMember().getMno());
        model.addAttribute("replyList" , sosoReplyService.getListBySosoNo(sosoNo));
        return "/sosojob/sosoDetailView";
    }
}
