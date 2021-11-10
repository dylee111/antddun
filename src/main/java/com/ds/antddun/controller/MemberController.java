package com.ds.antddun.controller;

import com.ds.antddun.config.auth.PrincipalDetails;
import com.ds.antddun.dto.MemberDTO;
import com.ds.antddun.entity.Member;
import com.ds.antddun.service.MemberService;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
@Log4j2
public class MemberController {
    //추가
    private MemberService memberService;

    @GetMapping("/loginPage")
    public String loginPage() {
        log.info("LOGIN >>>>>>>>>>>>>>>>>>>>>>>>>>>");
        return "/member/login";
    }

    @GetMapping("/member/mypage")
    public String wallet() {
        return "/member/mypage";
    }
//    @PutMapping("member/updateMember")
//    public

    @RequestMapping({""})
    public String main (Model model, MemberDTO memberDTO, @AuthenticationPrincipal PrincipalDetails principal) {
        if(principal != null) {
            Member member = principal.getMember();
            log.info(">>>>>>>>>>>>" + member);
        }
        return "index";
    }
}