package com.ds.antddun.controller;

import com.ds.antddun.config.auth.PrincipalDetails;
import com.ds.antddun.dto.JayuBoardDTO;
import com.ds.antddun.service.JayuBoardService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@Log4j2
@RequestMapping("/member")
@SessionAttributes("member")
public class JayuBoardController {

    @Autowired
    private JayuBoardService jayuBoardService;

    @GetMapping("/jayu/register")
    public String register(JayuBoardDTO jayuBoardDTO, HttpSession httpSession, HttpServletRequest request){
        httpSession = request.getSession();
        return "jayu/register";
    }


    @PostMapping("/jayu/read")
    public String register(JayuBoardDTO jayuBoardDTO, @AuthenticationPrincipal PrincipalDetails principal) {

        jayuBoardService.register(jayuBoardDTO, principal.getMember());
        return "jayu/read";
    }

}

