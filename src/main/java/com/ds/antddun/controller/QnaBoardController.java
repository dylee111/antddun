package com.ds.antddun.controller;

import com.ds.antddun.config.auth.PrincipalDetails;
import com.ds.antddun.dto.PageRequestDTO;
import com.ds.antddun.dto.QnaBoardDTO;
import com.ds.antddun.entity.JobList;
import com.ds.antddun.entity.MemberWishList;
import com.ds.antddun.repository.QnaBoardRepository;
import com.ds.antddun.repository.QnaLikesRepository;
import com.ds.antddun.service.JobListService;
import com.ds.antddun.service.QnaService;
import com.ds.antddun.service.WishListService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Log4j2
public class QnaBoardController {

    @Autowired
    private JobListService jobListService;

    @Autowired
    private QnaBoardRepository qnaBoardRepository;

    @Autowired
    private QnaService qnaService;

    @Autowired
    private QnaLikesRepository qnaLikesRepository;

    @Autowired
    private WishListService wishListService;


    //리스트 출력
    @GetMapping("/qna/list")
    public String allList(Model model, PageRequestDTO requestDTO, @AuthenticationPrincipal PrincipalDetails principal) {

        //위시리스트
        if (principal != null) {
            List<MemberWishList> wishLists = wishListService.getListByMno(principal.getMember().getMno());
            model.addAttribute("wishList", wishListService.getListByMno(principal.getMember().getMno()));
            if (wishLists.size() != 0) {
                model.addAttribute("wishListIndex", wishLists.get(0));
            }
        }

        List<JobList> list = jobListService.getList();
        model.addAttribute("jobList", list);
        model.addAttribute("boardList", qnaService.getListAll(requestDTO));
        log.info("whe"+qnaService.getListAll(requestDTO));

        return "/qna/list";
    }


    //카테고리 별 리스트 출력
    @GetMapping("/qna/list/{jno}")
    public String cateList(@PathVariable int jno, Model model, PageRequestDTO requestDTO, @AuthenticationPrincipal PrincipalDetails principal) {

        //위시리스트
        if (principal != null) {
            List<MemberWishList> wishLists = wishListService.getListByMno(principal.getMember().getMno());
            model.addAttribute("wishList", wishListService.getListByMno(principal.getMember().getMno()));
            if (wishLists.size() != 0) {
                model.addAttribute("wishListIndex", wishLists.get(0));
            }
        }

        List<JobList> list = jobListService.getList(); // 카테고리 리스트(cateNo / cateName)
        log.info("boardList>>>>"+qnaService.getListByCate(jno, requestDTO));
        model.addAttribute("jobList", list);
        model.addAttribute("boardList", qnaService.getListByCate(jno, requestDTO)); //jno로 구분된 리스트들

        return "/qna/list";
    }


    //게시물 작성 양식
    @GetMapping("/member/qna/registerForm")
    public String register(QnaBoardDTO qnaBoardDTO, @AuthenticationPrincipal PrincipalDetails principal,Model model) {

        if (principal == null) {return "redirect:/login";}

        //위시리스트
        List<MemberWishList> wishLists = wishListService.getListByMno(principal.getMember().getMno());

        model.addAttribute("wishList", wishListService.getListByMno(principal.getMember().getMno()));
        if (wishLists.size() != 0) {
            model.addAttribute("wishListIndex", wishLists.get(0));
        }

        model.addAttribute("jobList", jobListService.getList());
        return "/qna/registerForm";
    }


    //글 등록
    @PostMapping("/member/qna/register")
    public String register(QnaBoardDTO qnaBoardDTO, RedirectAttributes redirectAttributes,
                           @AuthenticationPrincipal PrincipalDetails principal) {
        Long qnaNo = qnaService.register(qnaBoardDTO, principal.getMember());
        redirectAttributes.addFlashAttribute("qnaNo", qnaNo);
        return "redirect:/member/qna/read/" + qnaNo;
    }


    //게시판 조회
    @GetMapping("/member/qna/read/{qnaNo}")
    public String read(@PathVariable Long qnaNo,
                       @AuthenticationPrincipal PrincipalDetails principal, Model model){

        if (principal == null) { return "redirect:/login"; }
        model.addAttribute("boardList", qnaService.getBoard(qnaNo));

        //위시리스트
        List<MemberWishList> wishLists = wishListService.getListByMno(principal.getMember().getMno());

        model.addAttribute("wishList", wishListService.getListByMno(principal.getMember().getMno()));
        if (wishLists.size() != 0) {
            model.addAttribute("wishListIndex", wishLists.get(0));
        }

        return "/qna/read";
    }


}