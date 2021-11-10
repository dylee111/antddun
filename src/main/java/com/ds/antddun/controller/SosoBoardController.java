package com.ds.antddun.controller;

import com.ds.antddun.config.auth.PrincipalDetails;
import com.ds.antddun.dto.SosoBoardDTO;
import com.ds.antddun.entity.SosoJobBoard;
import com.ds.antddun.service.SosoJobService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@Log4j2
@RequestMapping("/member")
@SessionAttributes("member")
public class SosoBoardController {

    @Autowired
    private SosoJobService sosoJobService;

    @GetMapping("/sosojob/main")
    public String mainRead(SosoJobBoard sosoJobBoard, Model model) {

//        model.addAttribute("soso", )

        return "sosojob/sosojobMain";
    }

    @GetMapping("/sosoJob/register")
    public String register(SosoBoardDTO sosoBoardDTO, HttpSession httpSession, HttpServletRequest request) {
        httpSession = request.getSession();
        log.info("httpSession >>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + httpSession.getId());
        return "/sosojob/register";
    }

    @PostMapping("/sosojob/confirm")
    public String register(SosoBoardDTO sosoBoardDTO, @AuthenticationPrincipal PrincipalDetails principal) {
        log.info("REGISTER11111>>>>>>>>>>>"+sosoBoardDTO+"////"+principal.getMember());
        sosoJobService.register(sosoBoardDTO, principal.getMember());
        log.info("REGISTER22222>>>>>>>>>>>"+sosoBoardDTO+"////"+principal.getMember());

        return "sosojob/sosojobMain";
    }
}
