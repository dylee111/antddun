package com.ds.antddun.controller;

import com.ds.antddun.dto.SosoBoardDTO;
import com.ds.antddun.entity.SosoJobBoard;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
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
public class SosoBoardController {
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

}
