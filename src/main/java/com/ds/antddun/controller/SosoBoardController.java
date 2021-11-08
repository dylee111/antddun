package com.ds.antddun.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class SosoBoardController {
    @GetMapping("/sosojob/main")
    public String mainRead() {
        return "sosojob/sosojobMain";
    }
}
