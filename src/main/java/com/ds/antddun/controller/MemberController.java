package com.ds.antddun.controller;

import com.ds.antddun.config.auth.PrincipalDetails;
import com.ds.antddun.dto.MemberDTO;
import com.ds.antddun.service.JobListService;
import com.ds.antddun.service.MemberService;
import com.ds.antddun.service.WishListService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

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


//    @GetMapping("/member/mypage")
//    public String wallet() {
//        return "/member/mypage";
//    }
//    //    @PutMapping("member/updateMember")
//    //    public

    @GetMapping("")
    public String main (Model model, MemberDTO memberDTO, @AuthenticationPrincipal PrincipalDetails principal) {
        if(principal != null) {
            model.addAttribute("member", principal.getMember());
        }
        return "index";
    }

    @GetMapping("/member/mypage")
    public String userinfo (Model model, MemberDTO memberDTO, @AuthenticationPrincipal PrincipalDetails principal) {
        if(principal != null) {
            model.addAttribute("member", principal.getMember());
            model.addAttribute("jobList", jobListService.getList());
        }
        return "member/mypage";
    }

    @PostMapping("/member/mypage")
    public String wishListSave(HttpServletRequest request) {
        // 동일한 name 속성을 가진 데이터는 배열 형태로 Controller로 넘어온다. split(",")을 이용해 잘라서 사용.'
        String[] nameArr = request.getParameterValues("wishList");
        String[] priceArr = request.getParameterValues("price");
        String[] rateArr = request.getParameterValues("rate");
        for (int i = 0; i < nameArr.length; i++) {

            log.info("NAME ARR[" + i + "] >>> " + nameArr[i]);
            log.info("PRICE ARR[" + i + "] >>> " + priceArr[i]);
            log.info("RATE ARR[" + i + "] >>> " + rateArr[i]);
        }
        return "member/mypage";
    }

    @GetMapping("/member/messenger")
    public String messenger (Model model, MemberDTO memberDTO, @AuthenticationPrincipal PrincipalDetails principal) {
        if(principal != null) {
            model.addAttribute("member", principal.getMember());
            model.addAttribute("jobList", jobListService.getList());
        }
        return "member/messenger";
    }
}