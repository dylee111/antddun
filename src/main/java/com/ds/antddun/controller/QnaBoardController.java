package com.ds.antddun.controller;

import com.ds.antddun.config.auth.PrincipalDetails;
import com.ds.antddun.dto.MemberDTO;
import com.ds.antddun.dto.QnaBoardDTO;
import com.ds.antddun.entity.QnaBoard;
import com.ds.antddun.service.JobListService;
import com.ds.antddun.service.QnaService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@Log4j2
@RequestMapping("/member")
@SessionAttributes("member")
public class QnaBoardController {

    @Autowired
    private JobListService jobListService;

    @Autowired
    private QnaService qnaService;

/*    @GetMapping("/qna/list")
    public String list(Model model) {
        model.addAttribute("jobList", jobListService.getList());
        return "/qna/list";
    }*/

    @GetMapping("/qna/list")
    public String list(QnaBoard qnaBoard, Model model, @AuthenticationPrincipal PrincipalDetails principal) {
        List<QnaBoardDTO> boardDTOList = qnaService.getBoardList();
        if (principal != null) {
            model.addAttribute("postList", boardDTOList);
            model.addAttribute("info", principal.getMember());
            log.info("LASTNAME >>>>>" + principal.getMember().getLastName());
            log.info(principal.getMember().getJob());
            log.info(principal.getMember().getExperience());
        }
        return "/qna/list";
    }




    @GetMapping("/qna/register")
    public String register(QnaBoardDTO qnaBoardDTO, HttpSession httpSession, HttpServletRequest request) {
        httpSession = request.getSession();
        log.info("httpSession >>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + httpSession.getId());
        return "/qna/register";
    }

    @PostMapping("/qna/confirm")
    public String register(QnaBoardDTO qnaBoardDTO, @AuthenticationPrincipal PrincipalDetails principal) {
        log.info("REGISTER11111>>>>>>>>>>>"+qnaBoardDTO+"////"+principal.getMember());
        qnaService.register(qnaBoardDTO, principal.getMember());
        log.info("REGISTER22222>>>>>>>>>>>"+qnaBoardDTO+"////"+principal.getMember());
        return "/qna/list";
    }





}
