package com.ds.antddun.controller;

import com.ds.antddun.config.auth.PrincipalDetails;
import com.ds.antddun.dto.JayuBoardDTO;
import com.ds.antddun.dto.PageRequestDTO;
import com.ds.antddun.dto.PageResultDTO;
import com.ds.antddun.entity.JayuBoard;
import com.ds.antddun.entity.JayuCategory;
import com.ds.antddun.entity.MemberWishList;
import com.ds.antddun.repository.JayuBoardRepository;
import com.ds.antddun.service.JayuBoardService;
import com.ds.antddun.service.JayuCateService;
import com.ds.antddun.service.JayuReplyService;
import com.ds.antddun.service.WishListService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    private JayuBoardRepository jayuBoardRepository;

    @Autowired
    private JayuReplyService jayuReplyService;


    //게시글 작성
    @GetMapping("/member/jayu/register")
    public String register(Model model, @AuthenticationPrincipal PrincipalDetails principal){

        if (principal == null) {return "redirect:/login";}

        //카테고리
        List<JayuCategory> cateList = jayuCateService.getCateList();
        model.addAttribute("cateList",cateList);

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


        return "redirect:/member/jayu/read?jayuNo="+jayuNo;
    }

    //게시글 조회
    @GetMapping("/member/jayu/read")
    public String read(Long jayuNo, Model model, @AuthenticationPrincipal PrincipalDetails principal,
                       @ModelAttribute("pageRequestDTO") PageRequestDTO pageRequestDTO) {
        if (principal == null) {return "redirect:/login";}

        //조회수 추가
        jayuBoardRepository.updateViewCnt(jayuNo);

        //게시판 정보
        JayuBoardDTO jayuBoardDTO = jayuBoardService.read(jayuNo);
        model.addAttribute("jayuBoardDTO", jayuBoardDTO);
        log.info("read......");

        //수정 삭제 버튼 보이기
        Long actualWriter = jayuBoardRepository.getById(jayuNo).getMember().getMno();
        if (principal.getMember().getMno() == actualWriter) {
            model.addAttribute("checkUser", true);
        }

        //댓글
        model.addAttribute("replyList",jayuReplyService.getReplyByJayuNo(jayuNo));

        //위시리스트
        List<MemberWishList> wishLists = wishListService.getListByMno(principal.getMember().getMno());
        model.addAttribute("wishList", wishListService.getListByMno(principal.getMember().getMno()));
        if (wishLists.size() != 0) {
            model.addAttribute("wishListIndex", wishLists.get(0));
        }

        return "/jayu/read";
    }

    //게시글 목록
    @GetMapping("/jayu/list/all")
    public String listAll(Model model, PageRequestDTO pageRequestDTO, HttpServletRequest http, @AuthenticationPrincipal PrincipalDetails principal) {
        //summernote 태그 제거, 게시판 정보
        PageResultDTO<JayuBoardDTO, JayuBoard> jayuList = jayuBoardService.getList(pageRequestDTO);
        log.info("pageRequestDOT"+pageRequestDTO);
        log.info("KEYYYYY"+http.getParameter("keyword"));
        List<JayuBoardDTO> list = jayuList.getDtoList();
        for (int i = 0; i < list.size(); i++) {
            JayuBoardDTO tmp = (JayuBoardDTO) list.get(i);
            tmp.setContent(tmp.getContent().replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", ""));
            list.set(i, tmp);
        }
        jayuList.setDtoList((List<JayuBoardDTO>) list);
        model.addAttribute("jayuList",jayuList);

        //카테고리
        List<JayuCategory> cateList = jayuCateService.getCateList();
        model.addAttribute("cateList",cateList);

        //위시리스트
        if (principal != null) {
            List<MemberWishList> wishLists = wishListService.getListByMno(principal.getMember().getMno());
            model.addAttribute("wishList", wishListService.getListByMno(principal.getMember().getMno()));
            if (wishLists.size() != 0) {
                model.addAttribute("wishListIndex", wishLists.get(0));
            }
        }
        return "/jayu/listAll";
    }

    //게시글 카테고리 목록
    @GetMapping("/jayu/list")
    public String list(Model model, PageRequestDTO pageRequestDTO, @AuthenticationPrincipal PrincipalDetails principal) {
        //summernote 태그 제거, 게시판 정보
        PageResultDTO<JayuBoardDTO, JayuBoard> jayuList = jayuBoardService.getListByCate(pageRequestDTO.getCate(), pageRequestDTO);
        log.info("pageDTO.CATE"+pageRequestDTO.getCate());

        List<JayuBoardDTO> list = jayuList.getDtoList();
        for (int i = 0; i < list.size(); i++) {
            JayuBoardDTO tmp = (JayuBoardDTO) list.get(i);
            tmp.setContent(tmp.getContent().replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", ""));
            list.set(i, tmp);
        }
        jayuList.setDtoList((List<JayuBoardDTO>) list);
        model.addAttribute("jayuList",jayuList);

        //카테고리
        List<JayuCategory> cateList = jayuCateService.getCateList();
        model.addAttribute("cateList",cateList);

        //위시리스트
        if (principal != null) {
            List<MemberWishList> wishLists = wishListService.getListByMno(principal.getMember().getMno());
            model.addAttribute("wishList", wishListService.getListByMno(principal.getMember().getMno()));
            if (wishLists.size() != 0) {
                model.addAttribute("wishListIndex", wishLists.get(0));
            }
        }

        return "/jayu/list";
    }
}