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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@Log4j2
@RequestMapping("/member")
@SessionAttributes("member")
public class SosoBoardController {

    @Autowired
    private SosoJobService sosoJobService;

    @GetMapping("/sosojob/main")
    public String mainRead(SosoJobBoard sosoJobBoard, Model model, @AuthenticationPrincipal PrincipalDetails principalDetails) {
//        log.info(">>>>"+sosoJobService.getListByCategory("IT"));
        log.info("FIRSTNAME >>>>>" + principalDetails.getMember().getFirstName());

        model.addAttribute("soso", principalDetails.getMember());

        return "sosojob/sosojobMain";
    }

    @GetMapping("/sosojob/register")
    public String register(SosoBoardDTO sosoBoardDTO) {

        return "/sosojob/register";
    }

    @PostMapping("/sosojob/confirm")
    public String register(SosoBoardDTO sosoBoardDTO, @AuthenticationPrincipal PrincipalDetails principal) {

        sosoJobService.register(sosoBoardDTO, principal.getMember());

        return "/sosojob/sosojobMain";
    }

}
