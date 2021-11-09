package com.ds.antddun.controller;

import com.ds.antddun.service.JobListService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
//@RequestMapping("/member")
public class QnaBoardController {

    @Autowired
    private JobListService jobListService;

    @GetMapping("/qna/list")
    public String list(Model model) {

        model.addAttribute("jobList", jobListService.getList());

        return "/qna/list";
    }
}
