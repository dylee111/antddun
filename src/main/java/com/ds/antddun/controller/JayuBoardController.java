package com.ds.antddun.controller;

import com.ds.antddun.config.auth.PrincipalDetails;
import com.ds.antddun.dto.JayuBoardDTO;
import com.ds.antddun.entity.JayuBoard;
import com.ds.antddun.service.JayuBoardService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@SessionAttributes("member")
@Log4j2
public class JayuBoardController {

    @Autowired
    private JayuBoardService jayuBoardService;

    //게시글 작성
    @GetMapping("/member/jayu/register")
    public String register(){
        return "jayu/register";
    }

    //게시글 등록
    @PostMapping("/member/jayu/register")
    public String setRegister(JayuBoardDTO jayuBoardDTO, Model model, @AuthenticationPrincipal PrincipalDetails principal){
        log.info(">>>>>"+jayuBoardDTO.getJayuNo());
        jayuBoardService.register(jayuBoardDTO, principal.getMember());
        return "redirect:/member/jayu/read/";
    }

    //게시글 조회
    @GetMapping("/member/jayu/read")
    public String read(Model model, @PathVariable Long jayuNo) {
        JayuBoard jayuBoard = jayuBoardService.findById(jayuNo);
        model.addAttribute("jayuNo", jayuNo);
        return "member/jayu/read";
    }

    //게시글 목록
    @GetMapping("/member/jayu/list")
    public String main(Model model) {
        List<JayuBoard> jayuList = jayuBoardService.findAll();
        model.addAttribute("jayuList", jayuList);
        jayuList.forEach(System.out::println);
        return "jayu/list";
    }
}