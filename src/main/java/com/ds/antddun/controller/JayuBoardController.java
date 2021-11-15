package com.ds.antddun.controller;

import com.ds.antddun.dto.JayuBoardDTO;
import com.ds.antddun.entity.JayuBoard;
import com.ds.antddun.repository.JayuBoardRepository;
import com.ds.antddun.service.JayuBoardService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/member")
@Log4j2
public class JayuBoardController {

    @Autowired
    private JayuBoardService jayuBoardService;

    //작성
    @GetMapping("/jayu/register")
    public String register(){
        return "jayu/register";
    }

    @PostMapping("/jayu/register")
    public String setRegister(JayuBoardDTO jayuBoardDTO, Model model){
        jayuBoardService.register(jayuBoardDTO);
        return "redirect:/jayu/read/" + jayuBoardDTO.getJayuNo();
    }

    //보기
    @GetMapping("/jayu/read/{jayuNo}")
    public String read(Model model, @PathVariable Long jayuNo) {
        JayuBoard jayuBoard = jayuBoardService.findById(jayuNo);
        model.addAttribute("jayuNo", jayuNo);
        return "/jayu/read";
    }

    //목록
    @GetMapping("/jayu")
    public String main(Model model) {
        List<JayuBoard> jayuList = jayuBoardService.findAll();
        model.addAttribute("jayuList", jayuList);
        jayuList.forEach(System.out::println);
        return "jayu/main";
    }


}