package com.ds.antddun.controller;

import com.ds.antddun.config.auth.PrincipalDetails;
import com.ds.antddun.dto.JobListDTO;
import com.ds.antddun.dto.MemberDTO;
import com.ds.antddun.dto.MemberWishListDTO;
import com.ds.antddun.entity.MemberWishList;
import com.ds.antddun.service.JobListService;
import com.ds.antddun.service.MemberService;
import com.ds.antddun.service.WishListService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/")
@Log4j2
public class MemberController {
    @Autowired
    private MemberService memberService;

    @Autowired
    private JobListService jobListService;

    @Autowired
    private WishListService wishListService;

    @GetMapping("")
    public String main (Model model, MemberDTO memberDTO, @AuthenticationPrincipal PrincipalDetails principal) {
        if(principal != null) {

            List<MemberWishList> wishLists = wishListService.getListByMno(principal.getMember().getMno());

            model.addAttribute("wishList", wishListService.getListByMno(principal.getMember().getMno()));
            model.addAttribute("member", principal.getMember());

            if(wishLists.size() != 0) {
                model.addAttribute("wishListIndex", wishLists.get(0));
            }

        }
        return "index";
    }

    @GetMapping("/member/mypage")
    public void userinfo (Model model, @AuthenticationPrincipal PrincipalDetails principal, MemberWishListDTO memberWishListDTO) {
        List<MemberWishList> wishLists = wishListService.getListByMno(principal.getMember().getMno());

        if(principal != null) {
            model.addAttribute("member", principal.getMember());
            model.addAttribute("jobList", jobListService.getList());
            if (wishLists.size() != 0) {
                model.addAttribute("wishList", wishLists);
                model.addAttribute("wishListIndex", wishLists.get(0));
            }
        }
    }

    @ResponseBody
    @PostMapping("/member/mypage/wishlist/save")
    public int saveWishList(MemberWishListDTO wishListDTO, @AuthenticationPrincipal PrincipalDetails principalDetails) {

        int result = wishListService.wishListCnt(principalDetails.getMember().getMno());
        log.info("MNOCNT>>>" + result);
        // 위시리스트 3개까지 작성 가능.
        if (result < 3) {
            wishListService.register(wishListDTO, principalDetails.getMember());
        }
        return result;
    }

    @DeleteMapping("/member/mypage/wishlist/delete/{wno}")
    public ResponseEntity<String> removeWishList(@PathVariable("wno") Long wno) {
        wishListService.remove(wno);
        return new ResponseEntity<>("delete", HttpStatus.OK);
    }

    @ResponseBody
    @PutMapping("/member/mypage/wishlist/modify/{wno}")
    public ResponseEntity<String> modifyWishList(@RequestBody MemberWishListDTO memberWishListDTO,
                                         @AuthenticationPrincipal PrincipalDetails principalDetails) {
        wishListService.modify(memberWishListDTO,principalDetails.getMember());
        return new ResponseEntity<>("modify", HttpStatus.OK);
    }

    @ResponseBody
    @PutMapping("/member/mypage/info/modify/{mno}")
    public ResponseEntity<String> modifyMember(@RequestBody MemberDTO memberDTO, JobListDTO jobListDTO) {
        memberService.modifyMember(memberDTO, jobListDTO);
        return new ResponseEntity<>("MemberModify", HttpStatus.OK);
    }

    @PostMapping("/member/mypage")
    public void wishList(MemberWishListDTO wishListDTO,
                               @AuthenticationPrincipal PrincipalDetails principalDetail) {

    }

}