package com.ds.antddun.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
@Log4j2
public class JayuBoardController {
    @GetMapping("/jayu/register")
    public String register(){
        return "jayu/register";
    }
}
