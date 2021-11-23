package com.ds.antddun.controller;

import com.ds.antddun.config.auth.PrincipalDetails;
import com.ds.antddun.dto.JayuBoardDTO;
import com.ds.antddun.dto.PageRequestDTO;
import com.ds.antddun.dto.PageResultDTO;
import com.ds.antddun.entity.JayuBoard;
import com.ds.antddun.entity.JayuCategory;
import com.ds.antddun.entity.MemberWishList;
import com.ds.antddun.service.JayuBoardService;
import com.ds.antddun.service.JayuCateService;
import com.ds.antddun.service.WishListService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Log4j2
public class JayuBoardController {

    @Autowired
    private JayuBoardService jayuBoardService;

    @Autowired
    private WishListService wishListService;

    @Autowired
    private JayuCateService jayuCateService;


    //게시글 작성
    @GetMapping("/member/jayu/register")
    public String register(Model model, @AuthenticationPrincipal PrincipalDetails principal){

        if (principal == null) {
            return "redirect:/login";
        } else {
            log.info("principal.getMember())" + principal.getMember());
        }

        //위시리스트
        List<MemberWishList> wishLists = wishListService.getListByMno(principal.getMember().getMno());
        model.addAttribute("wishList", wishListService.getListByMno(principal.getMember().getMno()));
        if (wishLists.size() != 0) {
            model.addAttribute("wishListIndex", wishLists.get(0));
        }

        return "jayu/register";
    }

    //게시글 등록
    @PostMapping("/member/jayu/register")
    public String register(JayuBoardDTO jayuBoardDTO, RedirectAttributes redirectAttributes, @AuthenticationPrincipal PrincipalDetails principal){
        log.info("register.....");
        Long jayuNo = jayuBoardService.register(jayuBoardDTO, principal.getMember());
        redirectAttributes.addFlashAttribute("jayuNo", jayuNo);
        log.info(">>>>>"+jayuBoardDTO);
        return "redirect:/member/jayu/read/jayuNo=" + jayuNo+ "";
    }

    //게시글 조회
    @GetMapping("/member/jayu/read/jayuNo={jayuNo}")
    public String read(Model model, @PathVariable Long jayuNo, @AuthenticationPrincipal PrincipalDetails principal,
                       @ModelAttribute("pageRequestDTO") PageRequestDTO pageRequestDTO) {

        if (principal == null) {
            return "redirect:/login";
        } else {
            log.info("principal.getMember())" + principal.getMember());
        }

        JayuBoardDTO jayuBoardDTO = jayuBoardService.read(jayuNo);
        model.addAttribute("jayuBoardDTO", jayuBoardDTO);
        log.info("read......");

        //위시리스트
        List<MemberWishList> wishLists = wishListService.getListByMno(principal.getMember().getMno());
        model.addAttribute("wishList", wishListService.getListByMno(principal.getMember().getMno()));
        if (wishLists.size() != 0) {
            model.addAttribute("wishListIndex", wishLists.get(0));
        }

        return "/jayu/read";
    }

    //게시글 목록
    @GetMapping("/jayu/list")
    public String list(Model model, PageRequestDTO pageRequestDTO,  @AuthenticationPrincipal PrincipalDetails principal) {
        PageResultDTO<JayuBoardDTO, JayuBoard> jayuList = jayuBoardService.getList(pageRequestDTO);

        //summernote 태그 제거
        List<JayuBoardDTO> list = jayuList.getDtoList();
        for (int i = 0; i < list.size(); i++) {
            JayuBoardDTO tmp = (JayuBoardDTO) list.get(i);
            tmp.setContent(tmp.getContent().replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", ""));
            list.set(i, tmp);
        }
        jayuList.setDtoList((List<JayuBoardDTO>) list);
        model.addAttribute("jayuList",jayuList);

        //위시리스트
        List<MemberWishList> wishLists = wishListService.getListByMno(principal.getMember().getMno());
        model.addAttribute("wishList", wishListService.getListByMno(principal.getMember().getMno()));
        if (wishLists.size() != 0) {
            model.addAttribute("wishListIndex", wishLists.get(0));
        }

        return "/jayu/list";
    }

    //카테고리 목록
    @GetMapping("jayu/list/jayuCateNo={jayuCateNo}")
    public String cateList(Model model, PageRequestDTO pageRequestDTO, @PathVariable int jayuCateNo, @AuthenticationPrincipal PrincipalDetails principal) {
        log.info("jayuCateNo"+jayuCateNo);
        PageResultDTO<JayuBoardDTO, JayuBoard> jayuList = jayuBoardService.getListByCate(jayuCateNo, pageRequestDTO);
        log.info("List>>>"+ jayuList);


        return "/qna/list";
    }
}